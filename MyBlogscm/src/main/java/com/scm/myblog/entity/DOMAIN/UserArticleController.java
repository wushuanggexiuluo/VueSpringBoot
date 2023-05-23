package com.scm.myblog.entity.DOMAIN;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserArticleController {
  @TableId(type = IdType.AUTO)
  private Integer id;
  private Integer uid;
  private Integer articleSort;
  private Integer jinbingSort;
  private Integer tagSort;
  private String articleSortId;
  private Integer jingHuaSortId;
  private Integer guiDangSortId;
}
