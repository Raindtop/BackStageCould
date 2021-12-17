package org.raindrop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.raindrop.entity.Person;

import java.util.List;

@Mapper
public interface PersonMapper extends BaseMapper<Person> {
    int insertBatch(@Param("list") List<Person> list);
}
