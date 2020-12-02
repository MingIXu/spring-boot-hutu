package com.hutu.boot.vo.index;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class IndexChildVO {
    Integer id;
    Integer relateId;
    String title;
    String description;
    Integer type;
    String picUrl;
}
