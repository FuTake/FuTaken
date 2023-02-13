package hbasetest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class HbaseService {
    @Autowired
    private HBaseAdmin admin;

    @Autowired
    private Connection connection;

    public void createTable(String name, List<String> colFamily) throws IOException {
        TableName table = TableName.valueOf(name);
        if(admin.tableExists(table)) {
            log.info("table ["+name+"] exist.");
        }
        List<ColumnFamilyDescriptor> families = new ArrayList<>();

        for (String s : colFamily) {
            ColumnFamilyDescriptor cfd = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(s))
                    .setMaxVersions(1).build();
            families.add(cfd);
        }

        TableDescriptor tableDes = TableDescriptorBuilder.newBuilder(table).setColumnFamilies(families).build();
        admin.createTable(tableDes);

    }

    public void deleteTable(String name) throws IOException {
        // 删除表之前，需要先disable表
        TableName table = TableName.valueOf(name);
        if(admin.tableExists(table)){
            admin.disableTable(table);
            admin.deleteTable(table);
        }else{
            log.error("table {} is not exist", name);
        }
    }

    public void putData(String tableName,String colFamily,String rowKey,Map<String, String> data) throws IOException {
        TableName table = TableName.valueOf(tableName);
        if(admin.tableExists(table)) {
            Table t = connection.getTable(table);
            Put put = new Put(Bytes.toBytes(rowKey));
            for(Map.Entry<String, String> entry:data.entrySet()) {
                put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(entry.getKey()), Bytes.toBytes(entry.getValue()));
            }
            t.put(put);
        }else {
            log.info("table ["+tableName+"] does not exist.");
        }
    }

    // 单线程大批量写入
    //https://blog.csdn.net/u014730165/article/details/116447147
    public void putBigData(String tableName, String family, String column, List<String> keys, List<String> datas) {
        final BufferedMutator.ExceptionListener listener = new BufferedMutator.ExceptionListener() {
            // 添加数据处理异常的监听
            public void onException(RetriesExhaustedWithDetailsException e, BufferedMutator mutator) {
                for (int i = 0; i < e.getNumExceptions(); i++) {
                    log.info("Failed to sent put " + e.getRow(i) + ".");
                }
            }
        };
        TableName table = TableName.valueOf(tableName);
        BufferedMutatorParams params = new BufferedMutatorParams(table)
                .listener(listener);
        // 设置buffer缓冲区大小4M
        params.writeBufferSize(4*1023*1024);
        List<Put> puts = new ArrayList<>(datas.size() * 2);
        try (Connection connection = admin.getConnection();
             BufferedMutator bufferedMutator = connection.getBufferedMutator(table)) {
            for (int i = 0; i < datas.size(); i++) {
                Put put = new Put(Bytes.toBytes(keys.get(i)));
                put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(datas.get(i)));
                puts.add(put);
            }
            // 如果puts的量超过了mutator设置的缓存大小则这个puts会被分解成两次发送
            bufferedMutator.mutate(puts);
            bufferedMutator.flush();
        } catch (IOException e) {
            log.error("putBigDataError", e);
        }
    }

    //多线程大批量写入
    public void putBigDataWithThreadPool(String tableName, String family, String column, List<String> keys, List<String> datas) {
        final BufferedMutator.ExceptionListener listener = new BufferedMutator.ExceptionListener() {
            // 添加数据处理异常的监听
            public void onException(RetriesExhaustedWithDetailsException e, BufferedMutator mutator) {
                for (int i = 0; i < e.getNumExceptions(); i++) {
                    log.info("Failed to sent put " + e.getRow(i) + ".");
                }
            }
        };
        TableName table = TableName.valueOf(tableName);
        BufferedMutatorParams params = new BufferedMutatorParams(table)
                .listener(listener);
        // 设置buffer缓冲区大小4M
        params.writeBufferSize(4*1023*1024);
        try (Connection connection = admin.getConnection();
             BufferedMutator bufferedMutator = connection.getBufferedMutator(table);) {
            ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 2, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
            List<Future<Void>> futures = new ArrayList<>(datas.size());
            for (int i = 0; i < datas.size(); i++) {
                final int temp = i;
                futures.add(pool.submit(()->{
                    Put put = new Put(Bytes.toBytes(keys.get(temp)));
                    put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(datas.get(temp)));
                    try {
                        bufferedMutator.mutate(put);
                        //使用多线程写入数据，不需要flush，缓存区满自动刷新
//                      bufferedMutator.flush();
                    } catch (IOException e) {
                        log.error("bufferedMutatorError", e);
                    }finally {
                        return null;
                    }
                }));
            }
        } catch (IOException e) {
            log.error("putBigDataError", e);
        }
    }

    public void getData(String name) throws IOException{
        TableName table = TableName.valueOf(name);
        Table t = connection.getTable(table);
        ResultScanner rs = t.getScanner(new Scan());
        for(Result r:rs) {
            log.info("row:"+new String(r.getRow()));
            for(Cell cell:r.rawCells()) {
                log.info("colFamily:"+Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength())+""
                        +",qualifier:"+Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength())+
                        ",value:"+Bytes.toString(cell.getValueArray(),cell.getValueOffset(),cell.getValueLength()));
            }
        }
    }

    // 手动触发major compaction或 minor compaction
    public void compaction(String tableName, String colFamily) throws IOException {
        TableName table_name = TableName.valueOf(tableName);
        if (admin.tableExists(table_name)) {
            admin.majorCompact(table_name);
            log.info("[------]major compact " + table_name.getNameAsString());
//            minor compaction
//            admin.compact(table_name, Bytes.toBytes(colFamily));
        }
    }

    // 预建分区，根据给定的splitKeys划分不同region的startRowkey - endRowkey
    public void preBuildPartition(String name, List<String> colFamily, List<String> splitKeys) throws IOException {
        TableName table = TableName.valueOf(name);
        if(admin.tableExists(table)) {
            log.info("table ["+name+"] exist.");
        }
        List<ColumnFamilyDescriptor> families = new ArrayList<>();

        for (String s : colFamily) {
            ColumnFamilyDescriptor cfd = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(s))
                    .setMaxVersions(1).build();
            families.add(cfd);
        }
        byte[][] bytes = new byte[splitKeys.size()][];
        for (int i = 0; i < splitKeys.size(); i++) {
            bytes[i] = Bytes.toBytes(splitKeys.get(i));
        }
        TableDescriptor tableDes = TableDescriptorBuilder.newBuilder(table).setColumnFamilies(families).build();
        admin.createTable(tableDes, bytes);
    }

    public void getStartRow(String tableName) throws IOException {
        RegionLocator regionLocator = connection.getRegionLocator(TableName.valueOf(tableName));
        byte[][] startKeys = regionLocator.getStartKeys();
        for(int i = 0; i < startKeys.length;i++){
            log.info("getStartRow i:{} startKey:{}", i, Bytes.toString(startKeys[i]));
        }

    }
}