package mybatis.simple.mapper;


import mybatis.simple.model.XrSysDictionary;

import java.util.List;

public interface XrSysDictionaryMapper {

    List<XrSysDictionary> selectByPrimaryKey();

}