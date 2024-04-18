package groovyFromMySql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import groovyFromMySql.entity.GroovyScriptModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhg
 * @since 2024-02-07
 */
public interface GroovyScriptMapper extends BaseMapper<GroovyScriptModel> {

    List<GroovyScriptModel> selectNewest();
}
