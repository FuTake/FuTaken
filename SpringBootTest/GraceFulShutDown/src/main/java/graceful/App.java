package graceful;

import graceful.config.GraceFulShutDown;
import graceful.config.OnOff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * nothing
 *
 * @ClassName App
 * @Description
 * @Author zsks
 * @Date 2021/12/23 7:29
 * @Version 1.0
 **/
@SpringBootApplication
public class App {

    @Autowired
    private GraceFulShutDown shutDown;
    @Autowired
    private OnOff onOff;

    @PostConstruct
    public void init(){
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            onOff.setOnoff(true);
            System.out.println("JVM即将关闭");
        }));
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


    /**
     *  SpringBoot2.x的配置方法
     **/
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(shutDown);
        return tomcat;
    }

    /**
     *  SpringBoot1.x的配置
     *
     @Bean
     public EmbeddedServletContainerCustomizer tomcatCustomizer() {
     return new EmbeddedServletContainerCustomizer() {
     @Override
     public void customize(ConfigurableEmbeddedServletContainer container) {
     if (container instanceof TomcatEmbeddedServletContainerFactory) {
     ((TomcatEmbeddedServletContainerFactory) container).addConnectorCustomizers(shutDown);
     }
     }
     };
     }
     **/

}
