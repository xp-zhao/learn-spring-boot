package com.boot.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author xp-zhao
 */
@Data
public class SingleTable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String key1;
    private Integer key2;
    private String key3;
    private String keyPart1;
    private String keyPart2;
    private String keyPart3;
    private String commonField;
}
