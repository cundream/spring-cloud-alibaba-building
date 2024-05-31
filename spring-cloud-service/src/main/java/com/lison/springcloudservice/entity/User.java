package com.lison.springcloudservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @className: com.lison.springcloudservice.entity-> User
 * @description:
 * @author: Lison
 * @createDate: 2024-05-30 15:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "test对象", description = "功能")
@Table(name = "t_user")
public class User implements Serializable {

    @ApiModelProperty(value = "主键")
    @Id
    private Integer id;


    private String userName;

    private Integer sex;

    private Integer age;

    private String address;

    private String phone;

    private Date create_time;
}
