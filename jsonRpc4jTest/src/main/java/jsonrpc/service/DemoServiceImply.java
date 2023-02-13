package jsonrpc.service;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 实现类
 *
 * @ClassName DemoServiceImpl
 * @Description
 * @Author zsks
 * @Date 2021/12/6 21:11
 * @Version 1.0
 **/
public class DemoServiceImply extends ObjectMapper implements DemoService {

    @Override
    public DemoBean getDemo(String code, String msg) {
        DemoBean bean1 = new DemoBean();
        bean1.setCode(Integer.parseInt(code));
        bean1.setMsg(msg);
        return bean1;
    }

    @Override
    public Integer getInt(Integer code) {
        return code;
    }

    @Override
    public String getString(String msg) {
        return msg;
    }

    @Override
    public void doSomething() {
        System.out.println("do something");
    }

}
