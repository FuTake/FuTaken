package groovytest.bind;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;
import groovytest.groovydsl.GroovyBaseScript;
import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class GroovyCompile {

    @Test
    public void testLink() throws Exception{
        String fileName = "GroovyScript_link.dsl";
        String scriptContent = getFileToString(fileName);
        compileAndRun(scriptContent, fileName);
    }
    @Test
    public void testClosure() throws Exception{
        String scriptContent = getFileToString("GroovyScript_template.dsl");
        compileAndRun(scriptContent, "template.dsl");
    }

    public void compileAndRun(String scriptContent, String fileName) throws IOException, InstantiationException, IllegalAccessException {
        ImportCustomizer importCustomizer = new ImportCustomizer();
        importCustomizer.addImports("groovy.transform.BaseScript", "groovy.json.JsonOutput", "groovy.json.JsonSlurper");
        importCustomizer.addImports(GroovyBaseScript.class.getName());

        CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
        compilerConfiguration.setScriptBaseClass(GroovyBaseScript.class.getName());
        compilerConfiguration.addCompilationCustomizers(importCustomizer);

        ClassLoader parent = ClassLoader.getSystemClassLoader();
        GroovyClassLoader cLassLoader = new GroovyClassLoader(parent, compilerConfiguration);


        Class scriptClass = cLassLoader.parseClass(scriptContent, fileName);
        Script script = (Script) scriptClass.newInstance();
        String result = (String)script.run();
        System.out.println(result);
        System.out.println("script work over");
//        Script script =

    }

    public String getFileToString(String fileName) throws IOException {
        File file = new File(Objects.requireNonNull(this.getClass().getResource("/dsl/"+fileName)).getFile());
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}
