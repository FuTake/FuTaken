package groovytest.bind;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 并发时使用的binding
 *
 * @ClassName ConcurrentBinding
 * @Description
 * @Author zsks
 * @Date 2021/12/1 21:25
 * @Version 1.0
 **/
public class ConcurrentBinding {
    private static Logger logger = LoggerFactory.getLogger(ConcurrentBinding.class);

    private static ConcurrentBinding binding = new ConcurrentBinding();
    private static ThreadLocal<Binding> local = new ThreadLocal<>();
    public static ConcurrentBinding getStaticBinding(){
        local.remove();
        local.set(new Binding());
        return binding;
    }
    public void setVariable(String property, Object value){
        Binding binding = local.get();
        binding.setProperty(property, value);
    }
    public Object getVariable(String property){
        Binding binding = local.get();
        return binding.getVariable(property);
    }
    public Binding getBinding(){
        return local.get();
    }

    @Test
    public void test() throws InterruptedException {
        for(int i=0; i<2; i++){
            new Thread(()->{
                try {
                    ConcurrentBinding binding = ConcurrentBinding.getStaticBinding();
                    binding.setVariable("name", Thread.currentThread().getName());
                    GroovyClassLoader loader = new GroovyClassLoader();
                    BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("script/concurrentBinding.groovy")));
                    String content = null;
                    StringBuilder sb = new StringBuilder();
                    while ((content = br.readLine()) != null) {
                        sb.append(content + "\n");
                    }
                    Class clazz = loader.parseClass(sb.toString());
                    Script script = (Script) clazz.newInstance();
                    script.setBinding(binding.getBinding());
                    System.out.print(Thread.currentThread().getName() + " ");
                    script.run();
                }catch(Exception e){
                    logger.error("exception:{}", e.getMessage());
                }
            }).start();
        }
        Thread.sleep(1000000);
    }
}
