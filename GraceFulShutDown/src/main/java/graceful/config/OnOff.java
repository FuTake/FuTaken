package graceful.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * nothing
 *
 * @ClassName OnOff
 * @Description
 * @Author zsks
 * @Date 2021/12/23 7:33
 * @Version 1.0
 **/

@Component
@Data
public class OnOff {

    private boolean onoff;

}
