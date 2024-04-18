package groovyFromMySql.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>
 *  groovy脚本实体
 * </p>
 *
 * @author zhg
 * @since 2024-02-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
//@TableName("dm_groovy_script")
@TableName("dm_groovy_script_practice")
public class GroovyScriptModel extends Model {

    /**
     * 主键
     */
    private String id;

    /**
     * 脚本id
     */
    private String scriptId;

    /**
     * 脚本内容
     */
    private String scriptContent;

    /**
     * 脚本类型
     * Script、GroovyObject
     */
    private String scriptType;

    //yyyyMMddHHmmss
    private String version;
}
