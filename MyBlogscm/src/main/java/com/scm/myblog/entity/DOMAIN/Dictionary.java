package com.scm.myblog.entity.DOMAIN;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典
 * @TableName dictionary
 */
@TableName(value ="dictionary")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dictionary implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 大致的种类代码
     */
    private Integer kindCode;

    /**
     * 当前大致类别的名字
     */
    private String kindName;

    /**
     * 键值对
     */

    private String keyValueMap;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 1表示开启0表示停用
     */
    private Integer status;

    /**
     * 是否使用当前的key,value,1使用，0不使用
     */
    private Integer isUseCurrentKeyValue;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Dictionary(Integer id,String key_val){
        this.id=id;
        this.keyValueMap=key_val;
    }
}