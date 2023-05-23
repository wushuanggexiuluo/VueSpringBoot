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
@ApiModel(description="从前台接受的任务分页查询对象")
public class ScheduleSettingSearchDto {
    private String name;
}
