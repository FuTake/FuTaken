package groovyFromMySql.config;


import groovy.lang.GroovyObject;
import groovyFromMySql.groovy.ConcurrentBinding;
import groovyFromMySql.groovy.GroovyScript;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class InitProcess {

    private Logger log = LoggerFactory.getLogger(InitProcess.class);
    @Resource
    GroovyScript groovyScript;

    @PostConstruct
    public void init(){
        try {
            groovyScript.initScriptCache();
            execute("CalculateUtil",null, null);
        }catch (Exception e){
            log.error("InitProcess error", e);
        }
    }

    /**
     * 执行脚本流程
     */
    public <T> boolean execute(String scriptId, ConcurrentBinding binding, T result){
        try {
            if(StringUtils.isBlank(scriptId)){
                return false;
            }
            if(binding == null){
                binding = ConcurrentBinding.getInstance();
            }
            GroovyObject groovyObject = groovyScript.getScript(scriptId);
//            groovyObject.setProperty("a", 1);
//            groovyObject.setProperty("b", 2);
            long startTime = System.currentTimeMillis();
            log.info("GroovyScript execute start scriptId={}", scriptId);
            Object[] args = new Object[]{1, 2};
            Object scriptResult = groovyObject.invokeMethod("add", args);
            log.info("GroovyScript execute end costTime={} scriptId={} /n result={}", (System.currentTimeMillis() - startTime), scriptId, scriptResult);
            return true;
        }catch (Exception e){
            log.error("GroovyScript execute error", e);
        }
        return false;
    }

}
