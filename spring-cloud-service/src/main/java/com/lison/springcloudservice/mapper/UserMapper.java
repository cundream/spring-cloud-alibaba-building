package com.lison.springcloudservice.mapper;

import com.lison.springcloudservice.entity.Test;
import com.lison.springcloudservice.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Mapper;
/**
 * @className: com.lison.springcloudservice.mapper-> UserMapper
 * @description:
 * @author: Lison
 * @createDate: 2024-05-30 10:37
 */

@Mapper
@Component
public interface UserMapper{


    @Insert("insert into t_user (user_name,sex,age, create_time) values ('aaaaaaaaa', 0,18,now())")
    int insert();

}
