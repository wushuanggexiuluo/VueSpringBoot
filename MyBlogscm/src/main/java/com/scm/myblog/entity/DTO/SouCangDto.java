package com.scm.myblog.entity.DTO;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("收藏实体")
public class SouCangDto {
    private Integer uid;
    private Integer articleId;
}
