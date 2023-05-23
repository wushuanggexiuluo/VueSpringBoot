package com.scm.myblog.entity.DTO;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description="从前台接受的任务调度对象")
public class ScheduleSettingUpdateDto {
    /**
     * bean名称
     */
    private String beanName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 方法参数
     */
    private String methodParams;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 状态（1正常 0暂停）
     */
    @Pattern(regexp = "[0,1]",message = "状态必须是0或者1的一种！！")
    private Integer jobStatus;
    /**
     * 备注
     */
    private String remark;
}
