package hbasetest.utils;

import hbasetest.common.HBaseColumn;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * HBase 查询方法封装
 */
@Slf4j
@Component
public class HbaseUtils {


    @Resource
    private Connection conn;

    @Value("${hbase.inquireSize}")
    private Integer number;

    /**
     * 根据 rowkey 获取数据
     *
     * @param tableName 表名
     * @param rowKey    主键
     * @param z
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T get(String tableName, String rowKey, Class<T> z) throws Exception {
        if (StringUtils.isEmpty(rowKey)) {
            return null;
        }

        // 获取表
        log.info("table name: {}", tableName);
        try (Table table = conn.getTable(TableName.valueOf(tableName))) {
            Get get = new Get(Bytes.toBytes(rowKey));
            Result result = table.get(get);
            if (result == null) {
                return null;
            }
            List<Cell> cellList = result.listCells();
            if (cellList != null && cellList.size() > 0) {
                T t = z.newInstance();
                BeanWrapper beanWrapper = new BeanWrapperImpl(t);
                beanWrapper.setAutoGrowNestedPaths(true);
                for (Cell cell : cellList) {
                    String cellName = Bytes.toString(CellUtil.cloneQualifier(cell));
                    String strValue = Bytes.toString(CellUtil.cloneValue(cell));
                    if (beanWrapper.isWritableProperty(cellName)) {
                        beanWrapper.setPropertyValue(cellName, strValue);
                    }
                }
                return t;
            }
        }
        return null;
    }

    /**
     * 根据rowkey 查询单个 列族 信息
     *
     * @param tableName  表
     * @param rowKey     主键
     * @param familyName 列族
     * @param z
     * @param <T>
     * @return
     */
    public <T> T getFamily(String tableName, String rowKey, String familyName, Class<T> z) {
        try (Table table = this.conn.getTable(TableName.valueOf(tableName))) {
            Get get = new Get(Bytes.toBytes(rowKey))
                    .addFamily(Bytes.toBytes(familyName));
            Result result = table.get(get);
            List<Cell> cellList = result.listCells();
            if (cellList != null && cellList.size() > 0) {
                T t = z.newInstance();
                BeanWrapper beanWrapper = new BeanWrapperImpl(t);
                beanWrapper.setAutoGrowNestedPaths(true);
                for (Cell cell : cellList) {
                    String cellName = Bytes.toString(CellUtil.cloneQualifier(cell));
                    String strValue = Bytes.toString(CellUtil.cloneValue(cell));
                    if (beanWrapper.isWritableProperty(cellName)) {
                        beanWrapper.setPropertyValue(cellName, strValue);
                    }
                }
                return t;
            }
        } catch (IOException | IllegalAccessException | InstantiationException e) {
            log.error("getFamily exception", e);
        }
        return null;
    }

    /**
     * 批量查询
     *
     * @param tableName 表
     * @param rowKeys   集合
     * @param z         实体类
     * @param <T>
     * @return
     */
    public <T> Map<String, T> batchGet(String tableName, Collection<String> rowKeys, Class<T> z) throws Exception {
        if (ObjectUtils.isEmpty(rowKeys)) {
            return Collections.emptyMap();
        }

        // 获取表
        log.info("table name: {}", tableName);
        try (Table table = conn.getTable(TableName.valueOf(tableName))) {
            List<Get> gets = rowKeys.stream()
                    .map(item -> new Get(Bytes.toBytes(item)))
                    .collect(Collectors.toList());
            Result[] results = table.get(gets);
            if (results == null || results.length == 0) {
                return Collections.emptyMap();
            }

            Map<String, T> resultMap = new HashMap<>();
            for (Result result : results) {
                String row = Bytes.toString(result.getRow());
                List<Cell> cellList = result.listCells();
                if (cellList != null && cellList.size() > 0) {
                    T t = z.newInstance();
                    BeanWrapper beanWrapper = new BeanWrapperImpl(t);
                    beanWrapper.setAutoGrowNestedPaths(true);
                    for (Cell cell : cellList) {
                        String cellName = Bytes.toString(CellUtil.cloneQualifier(cell));
                        String strValue = Bytes.toString(CellUtil.cloneValue(cell));
                        if (beanWrapper.isWritableProperty(cellName)) {
                            beanWrapper.setPropertyValue(cellName, strValue);
                        }
                    }
                    resultMap.put(row, t);
                }
            }
            return resultMap;
        }
    }


    public <T> Map<String, T> batchGetFamily(String tableName, Collection<String> rowKeys, String familyName,
                                             Class<T> z) throws Exception {
        if (ObjectUtils.isEmpty(rowKeys)) {
            return null;
        }
        // 获取表
        log.info("table name: {}", tableName);
        try (Table table = conn.getTable(TableName.valueOf(tableName))) {
            List<Get> gets = rowKeys.stream()
                    .map(item -> new Get(Bytes.toBytes(item)))
                    .collect(Collectors.toList());
            for (Get get : gets) {
                if (familyName != null) {
                    byte[] family = familyName.getBytes();
                    get.addFamily(family);
                }
            }

            Result[] results = table.get(gets);
            if (results == null || results.length == 0) {
                return null;
            }
            Map<String, T> resultMap = new HashMap<>();
            for (Result result : results) {
                String row = Bytes.toString(result.getRow());
                List<Cell> cellList = result.listCells();
                if (cellList != null && cellList.size() > 0) {
                    T t = z.newInstance();
                    BeanWrapper beanWrapper = new BeanWrapperImpl(t);
                    beanWrapper.setAutoGrowNestedPaths(true);
                    for (Cell cell : cellList) {
                        String cellName = Bytes.toString(CellUtil.cloneQualifier(cell));
                        String strValue = Bytes.toString(CellUtil.cloneValue(cell));
                        if (beanWrapper.isWritableProperty(cellName)) {
                            beanWrapper.setPropertyValue(cellName, strValue);
                        }
                    }
                    resultMap.put(row, t);
                }
            }
            return resultMap;
        }
    }

    private <T> T cellsToPojo(List<Cell> cellList, Class<T> clazz) throws InstantiationException,
            IllegalAccessException {
        if (cellList != null && cellList.size() > 0) {
            T rowObj = clazz.newInstance();
            BeanWrapper beanWrapper = new BeanWrapperImpl(rowObj);
            beanWrapper.setAutoGrowNestedPaths(true);
            for (Cell cell : cellList) {
                String qualifier = new String(CellUtil.cloneQualifier(cell));
                String value = new String(CellUtil.cloneValue(cell));
                if (beanWrapper.isWritableProperty(qualifier)) {
                    beanWrapper.setPropertyValue(qualifier, value);
                }
            }
            return rowObj;
        }
        return null;
    }

    private <T> T cellToSimpleObj(List<Cell> cellList, Class<T> clazz) throws InstantiationException,
            IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (cellList != null && cellList.size() == 1) {
            Constructor<T> constructor = clazz.getConstructor(String.class);
            return constructor.newInstance(new String(CellUtil.cloneValue(cellList.get(0))));
        }
        return null;
    }

    private boolean isSimpleClass(Class<?> clz) {
        if (clz == String.class || Number.class.isAssignableFrom(clz)) {
            return true;
        }
        try {
            Field typeField = clz.getField("TYPE");
            Class<?> typeValue = (Class<?>)typeField.get(null);
            return typeValue.isPrimitive();
        } catch (NoSuchFieldException | IllegalAccessException e) {
//            log.debug("No TYPE field {}", e.getMessage());
            return false;
        }
    }

    private <E extends Enum<E>> Enum<E> cellToEnum(List<Cell> cellList, Class<E> enumClass) {
//        log.debug("cellToEnum: cellList = {}", cellList);
        if (cellList != null && cellList.size() == 1) {
            return Enum.valueOf(enumClass, new String(CellUtil.cloneValue(cellList.get(0))));
        }
        return null;
    }

    private <T> T cellToObject(List<Cell> cells, Class<T> clazz) throws InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException {
        if (isSimpleClass(clazz)) {
            return cellToSimpleObj(cells, clazz);
        } else if (clazz.isEnum()) {
            return (T)cellToEnum(cells, (Class<? extends Enum>)clazz);
        } else {
            return cellsToPojo(cells, clazz);
        }
    }

    private <T> Map<String, T> scanResultMap(Table table, Scan scan, Class<T> clazz)
            throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        ResultScanner resultScanner = table.getScanner(scan);
        Map<String, T> resultMap = new LinkedHashMap<>();
        for (Result result : resultScanner) {
            String row = Bytes.toString(result.getRow());
            List<Cell> cellList = result.listCells();
            resultMap.put(row, cellToObject(cellList, clazz));
        }
        return resultMap;
    }

//    public <T> Map<String, T> scanByPrefixFilter(String tableName, String prefix, Filter filter,
//                                                 List<HBaseColumn> columns, Class<T> clazz) {
//        log.debug("scanByPrefixFilter(): tableName = {}, prefix = {},clazz = {}", tableName, prefix, clazz);
//        if (org.apache.commons.lang.StringUtils.isBlank(tableName) || org.apache.commons.lang.StringUtils.isBlank(prefix) || clazz == null) {
//            log.error("scanByPrefixFilter(): illegal arguments.");
//            return Collections.emptyMap();
//        }
//
//        if (columns != null) {
//            columns.remove(null);
//        }
//
//        try (Table table = this.conn.getTable(TableName.valueOf(tableName))) {
//            String startRow;
//            String endRow;
//            String lastChar = prefix.substring(prefix.length() - 1);
//            char endChar = (char)(IdUtils.ID_SEPARATOR.charAt(0) + 1);
//            if (!lastChar.equals(IdUtils.ID_SEPARATOR)) {
//                startRow = prefix + IdUtils.ID_SEPARATOR;
//                endRow = prefix + endChar;
//            } else {
//                startRow = prefix;
//                endRow = prefix.substring(0, prefix.length() - 1) + endChar;
//            }
//            Scan scan = new Scan()
//                    .setStartRow(Bytes.toBytes(startRow))
//                    .setStopRow(Bytes.toBytes(endRow));
//            if (filter != null) {
//                scan.setFilter(filter);
//            }
//            if (columns != null) {
//                for (HBaseColumn column : columns) {
//                    scan.addColumn(Bytes.toBytes(column.getFamily()), Bytes.toBytes(column.getQualifier()));
//                }
//            }
//            return scanResultMap(table, scan, clazz);
//        } catch (IOException | InstantiationException | IllegalAccessException | InvocationTargetException |
//                NoSuchMethodException e) {
//            log.error("scanByPrefixFilter exception", e);
//        }
//        return Collections.emptyMap();
//    }
}
