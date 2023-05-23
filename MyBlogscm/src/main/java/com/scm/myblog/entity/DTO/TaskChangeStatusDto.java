package com.scm.myblog.entity.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("从前台接受任务状态改变对象")
public class TaskChangeStatusDto {
    private Integer jobId;
    private Integer jobStatus;
}
