package jsonrpc.service;

/**
 * 提供服务的接口
 *
 * @ClassName DemoService
 * @Description
 * @Author zsks
 * @Date 2021/12/6 21:10
 * @Version 1.0
 **/
public interface DemoService {

    public DemoBean getDemo(String code, String msg);

    public Integer getInt(Integer code);

    public String getString(String msg);

    public void doSomething();
}
