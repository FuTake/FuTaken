package hbasetest;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import hbasetest.entity.Dept;
import hbasetest.entity.User;
import hbasetest.service.HbaseService;
import hbasetest.utils.HbaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE) //不启动tomcat容器
public class test {
    @Autowired
    HbaseService hbaseService;
    @Autowired
    HbaseUtils hbaseUtils;

    @Test
    public void readTest() throws Exception {
        User user = hbaseUtils.get("users", "zhangsan", User.class);
        log.info("test map:{}", JSONObject.toJSONString(user));
    }
    @Test
    public void createTableTest() throws IOException {
        hbaseService.createTable("dept", Lists.newArrayList("info1", "info2"));
    }

    @Test
    public void preBuildTest() throws IOException {
        // 会创建4个region，然后执行bigDataWriteTest() length=40， 最后执行startRowTest可以得到4个region的startKey
        hbaseService.preBuildPartition("dept", Lists.newArrayList("info1", "info2"), Lists.newArrayList("10", "20", "30"));
    }

    @Test
    public void deleteTabletest() throws IOException {
        hbaseService.deleteTable("dept");
    }

    @Test
    public void putData() throws IOException {
        String tableName = "dept";
        String family1 = "info1";
        String family2 = "info2";
        hbaseService.putData(tableName, family1, "classroom", Collections.singletonMap("name", "教室"));
        hbaseService.putData(tableName, family2, "dormitory", Collections.singletonMap("id", "5"));
    }
    @Test
    public void readTest2() throws Exception {
        Dept dept = hbaseUtils.get("dept", "classroom", Dept.class);
        log.info("test map:{}", JSONObject.toJSONString(dept));
        Dept dept2 = hbaseUtils.get("dept", "dormitory", Dept.class);
        log.info("test map:{}", JSONObject.toJSONString(dept2));
    }

    @Test
    public void bigDataWriteTest(){
        int length = 40;
        List<String> data = new ArrayList<>(length);
        List<String> keys = new ArrayList<>(length);
        for(int i=0; i<length; i++){
            data.add(i + "");
            keys.add(i + "");
        }
//        hbaseService.putBigData("dept", "info2", "id", keys, data);
        long startTime = System.currentTimeMillis();
        hbaseService.putBigDataWithThreadPool("dept", "info2", "id", keys, data);
        log.info("spendTime:{}", System.currentTimeMillis()-startTime);
    }

    @Test
    public void startRowTest() throws IOException {
        //https://blog.csdn.net/u014730165/article/details/126622805
        hbaseService.getStartRow("dept");
    }

}
