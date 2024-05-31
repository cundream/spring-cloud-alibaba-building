package com.lison.springcloudservice.mapper;

import com.lison.springcloudservice.entity.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @className: com.lison.springcloudservice.mapper-> TestMapper
 * @description:
 * @author: Lison
 * @createDate: 2024-05-30 10:37
 */
@Mapper
@Component
public interface TestMapper {
    @Insert("insert into t_test(one,two,createTime) values ( 0,18,now())")
    int insert();
}
