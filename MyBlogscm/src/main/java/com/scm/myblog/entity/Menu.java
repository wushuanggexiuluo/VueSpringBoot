package com.scm.myblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Menu {

  private String menuName;
  private String permKey;
  @TableField
  private Integer id;
  private Integer status;
  private Integer delFlag;



}
