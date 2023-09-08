package temp.generate.tempGenerate.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import temp.generate.tempGenerate.service.DynamicWordExportService;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = tomcattest.WebEnvironment.NONE) //不启动tomcat容器
public class TestController {

    @Resource
    DynamicWordExportService dynamicWordExportService;

    @Test
    public void test1(){
        dynamicWordExportService.export("2", null);
    }

    public static void main(String[] args) {
//        LocalDate date = LocalDate.of(2023, 2, 1);
//        System.out.println(date.format(DateTimeFormatter.ofPattern("yyyyMM")));
        BigDecimal test = new BigDecimal("11.2");
        BigDecimal test2= new BigDecimal("1.2000009");
        test = test.subtract(test2);
        if(test.compareTo(new BigDecimal(10)) >= 0){
            System.out.println(test.compareTo(new BigDecimal(10)) );
        }
    }
}
