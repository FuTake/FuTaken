package groovyFromMySql.groovy;

import groovy.lang.Binding;

import java.util.Map;

public class ConcurrentBinding extends Binding {
    private static final ConcurrentBinding BINDING = new ConcurrentBinding();
    private static final ThreadLocal<Binding> LOCAL = new ThreadLocal<>();
    private ConcurrentBinding(){

    }

    public static ConcurrentBinding getInstance(){
        LOCAL.remove();
        LOCAL.set(new Binding());
        return BINDING;
    }

    @Override
    public Object getVariable(String name) {
        Binding binding = LOCAL.get();
        return binding.getVariable(name);
    }

    @Override
    public void setVariable(String name, Object value) {
        Binding binding = LOCAL.get();
        binding.setVariable(name, value);
    }

    @Override
    public Map getVariables() {
        Binding binding = LOCAL.get();
        return binding == null?null:binding.getVariables();
    }

    @Override
    public boolean hasVariable(String name){
        Binding b = LOCAL.get();
        return b.hasVariable(name);
    }



    @Override
    public Object getProperty(String property) {
        Binding binding = LOCAL.get();
        return binding.getProperty(property);
    }

    @Override
    public void setProperty(String property, Object newValue) {
        Binding binding = LOCAL.get();
        binding.setProperty(property, newValue);
    }
}
