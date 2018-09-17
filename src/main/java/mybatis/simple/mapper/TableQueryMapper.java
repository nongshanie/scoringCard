package mybatis.simple.mapper;


import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface TableQueryMapper {

    /**
     * @param tableName  表名
     * @return           表类属性名集合
     */
    List<String> selectListStr(@Param("tableName") String tableName);
}
