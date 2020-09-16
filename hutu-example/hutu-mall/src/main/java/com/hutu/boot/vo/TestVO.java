package com.hutu.boot.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author hutu
 * @date 2020-01-15 17:11
 */
@Setter
@Getter
@Accessors(chain = true)
public class TestVO {
    @ApiModelProperty(value = "姓名")
    String name;
    @ApiModelProperty(value = "年龄")
    Integer age;
}
