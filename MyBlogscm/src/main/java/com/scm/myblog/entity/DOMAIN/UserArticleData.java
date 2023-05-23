package com.scm.myblog.entity.DOMAIN;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserArticleData {

  @TableId(type = IdType.AUTO)
  private Integer id;
  private Integer uid;
  private Integer isShouCang;
  private Integer isDianZan;
  private Integer isPingLun;
  private Integer articleId;
  @TableField(fill = FieldFill.UPDATE)
  private java.sql.Timestamp updateTime;
  @TableField(fill = FieldFill.INSERT)
  private java.sql.Timestamp createTime;
}
