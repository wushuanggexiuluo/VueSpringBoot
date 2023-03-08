package com.scm.myblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Role {
  @TableField
  private Integer id;
  private String name;
  private String roleKey;
  private Integer status;



}
