package designmodel.strategy;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * nothing
 *
 * @ClassName demo
 * @Description
 * @Author zsks
 * @Date 2021/12/12 10:15
 * @Version 1.0
 **/
public class demo {
    private static final Map<String, Strategy> strategies = new HashMap<>();
    static{
        strategies.put("NormalUser", new NormalUser());
        strategies.put("PremiumUser", new PremiumUser());
        strategies.put("VIPUser", new VIPUser());
    }
    public static Strategy getStrategy(String name){
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("name is empty");
        }
        return strategies.get(name);
    }

    public static void main(String[] args) {
        Strategy user = getStrategy("NormalUser");
        user.buy();
        user = getStrategy("PremiumUser");
        user.buy();
        user = getStrategy("VIPUser");
        user.buy();
        
    }
}
