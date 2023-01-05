package mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 数据库配置文件
 *
 * @ClassName MybatisConfig
 * @Description
 * @Author zsks
 * @Date 2021/12/16 21:43
 * @Version 1.0
 **/
@Configuration
@MapperScan(basePackages = "mybatis.mapper.db2", sqlSessionFactoryRef = "secondSqlSessionFactory")
public class MybatisConfig2 {

    @Value("${mybatis.mapper-locations2}")
    String mapperLocation;
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //设置mapper.xml位置
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        // 加载mapper.xml用 getResources 不是 getResource
        bean.setMapperLocations(resourcePatternResolver.getResources(mapperLocation));

        //设置mybatis的配置文件
        bean.setConfigLocation(resourcePatternResolver.getResource("mybatis-config.xml"));
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory factory) throws Exception {
        return new SqlSessionTemplate(factory);
    }
}


