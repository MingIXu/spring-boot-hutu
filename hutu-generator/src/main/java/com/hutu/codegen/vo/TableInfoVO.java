package com.hutu.codegen.vo;

import lombok.Data;

@Data
public class TableInfoVO {
    String tableName;
    String engine;
    String tableComment;
    String createTime;

}
