package mybatis.mapper.db1;

import mybatis.entity.Student;

import java.util.List;

/**
 * mapper文件
 *
 * @ClassName TestMapper
 * @Description
 * @Author zsks
 * @Date 2021/12/16 21:46
 * @Version 1.0
 **/
public interface TestMapper {

    List<Student> getAllStudent();

    void insert();
}
