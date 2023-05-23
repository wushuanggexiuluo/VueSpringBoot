package com.scm.myblog.entity.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KindTreeVO {
    List<KindTreeVO> klm;
    private Integer id;
    private String menuName;
    private String menuPath;
    private Integer parentId;
}
