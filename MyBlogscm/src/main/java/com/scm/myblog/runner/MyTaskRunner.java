package com.scm.myblog.runner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.scm.myblog.entity.DOMAIN.ScheduleSetting;
import com.scm.myblog.mapper.ScheduleSettingMapper;
import com.scm.myblog.scheduledtask.CronTaskRegistrar;
import com.scm.myblog.scheduledtask.SchedulingRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MyTaskRunner implements CommandLineRunner {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Autowired
    private ScheduleSettingMapper sm;

    @Override
    public void run(String... args) {
        // 初始加载数据库里状态为正常的定时任务
        LambdaQueryWrapper<ScheduleSetting> lq=new LambdaQueryWrapper<>();
        lq.eq(ScheduleSetting::getJobStatus,1);
        List<ScheduleSetting> jobList = sm.selectList(lq);
        if (CollectionUtils.isNotEmpty(jobList)) {
            for (ScheduleSetting job : jobList) {
                SchedulingRunnable task = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), job.getMethodParams());
                cronTaskRegistrar.addCronTask(task, job.getCronExpression());
            }
            log.info("定时任务初始化完毕！");
        }
    }
}
