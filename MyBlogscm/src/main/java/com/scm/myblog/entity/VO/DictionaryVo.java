package com.scm.myblog.entity.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryVo {
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
}
