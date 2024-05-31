package com.lison.springcloudservice.entity;

/**
 * @className: com.lison.springcloudservice.entity-> Test
 * @description:
 * @author: Lison
 * @createDate: 2024-05-30 10:22
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "test对象", description = "功能")
@Table(name = "t_test")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @Id
    private Integer id;

    @ApiModelProperty(value = "one")
    private String one;

    @ApiModelProperty(value = "two")
    private String two;

    @ApiModelProperty(value = "createTime")
    private LocalDateTime createTime;

}
