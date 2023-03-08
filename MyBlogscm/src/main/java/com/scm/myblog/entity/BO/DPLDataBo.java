package com.scm.myblog.entity.BO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DPLDataBo {
    private Long articleId;
    private Long articleViewCount;
    private Long articleCommentCount;
    private Long articleDianzanCount;
}
