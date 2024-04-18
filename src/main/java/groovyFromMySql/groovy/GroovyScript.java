package groovyFromMySql.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.Script;
import groovyFromMySql.entity.GroovyScriptModel;
import groovyFromMySql.mapper.GroovyScriptMapper;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class GroovyScript {

    private static final Map<String, GroovyObject> SCRIPT_CACHE = new ConcurrentHashMap<>();
    private static Logger log = LoggerFactory.getLogger(GroovyScript.class);

    @Resource
    private GroovyScriptMapper groovyScriptMapper;
    @Resource
    private SpringContextUtil springContextUtil;

    public GroovyObject getScript(String scriptId){
        return SCRIPT_CACHE.get(scriptId);
    }

    public void initScriptCache() throws Exception {
        List<GroovyScriptModel> groovyScriptModels = groovyScriptMapper.selectNewest();
        if(batchCompileAndUpdate(groovyScriptModels)){
            log.info("GroovyScript initScriptCache success");
            return;
        }
        throw new Exception("initScriptCache error");
    }


    /**
     * 编译脚本放入缓存
     */
    public boolean compileAndUpdate(GroovyScriptModel model){
        try {
            Class scriptClass;
            Script script;
            GroovyObject groovyObject;
            try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
                scriptClass = classLoader.parseClass(model.getScriptContent());
                // 如果脚本内容是面向过程的写法 用Script对象
//                script = (Script) scriptClass.newInstance();
                // 如果脚本内容是面向对象的写法 用GroovyObject对象
                groovyObject = (GroovyObject) scriptClass.newInstance();
                classLoader.clearCache(); //remove groovy scriptCache for gc
            }
            ClassInfo.clearModifiedExpandos();
            InvokerHelper.removeClass(scriptClass);
            SCRIPT_CACHE.put(model.getScriptId(), groovyObject);
        }catch (Exception e){
            log.error("compileAndUpdate error scriptId:{} scriptCache.size:{}", model.getScriptId(), SCRIPT_CACHE.size(), e);
            return false;
        }
        log.info("compileAndUpdate scriptCache.size={}", SCRIPT_CACHE.size());
        return true;
    }

    /**
     * 批量编译脚本
     */
    public boolean batchCompileAndUpdate(List<GroovyScriptModel> list){
        for (GroovyScriptModel groovyScriptModel : list) {
            if(!compileAndUpdate(groovyScriptModel)){
                //编译失败
                return false;
            }
        }
        log.info("batchCompileAndUpdate complete list.size={}", list.size());
        return true;
    }


}
