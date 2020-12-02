package com.hutu.boot.vo.index;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author hutu
 * @date 2020-01-15 17:11
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class IndexVO {
    Integer id;
    String title;
    Integer type;
    /**
     * 关联促销分类
     */
    Integer relateId;
    Integer sortOrder;
    List<IndexChildVO> list;
}
