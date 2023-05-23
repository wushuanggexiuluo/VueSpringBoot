package com.scm.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scm.myblog.entity.CORE.Result2;
import com.scm.myblog.entity.CORE.StatusMes;
import com.scm.myblog.entity.DOMAIN.ScheduleSetting;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.DTO.ScheduleSettingSearchDto;
import com.scm.myblog.entity.DTO.ScheduleSettingUpdateDto;
import com.scm.myblog.entity.VO.PageData;
import com.scm.myblog.mapper.ScheduleSettingMapper;
import com.scm.myblog.scheduledtask.CronTaskRegistrar;
import com.scm.myblog.scheduledtask.SchedulingRunnable;
import com.scm.myblog.service.ScheduleSettingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class ScheduleSettingServiceImpl implements ScheduleSettingService {
    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Autowired
    private ScheduleSettingMapper sm;

    public boolean isExist(ScheduleSettingUpdateDto sd){
        LambdaQueryWrapper<ScheduleSetting> l1=new LambdaQueryWrapper<>();
        l1.eq(ScheduleSetting::getBeanName,sd.getBeanName()).eq(ScheduleSetting::getMethodName,sd.getMethodName()).select(ScheduleSetting::getJobId);
        return sm.selectOne(l1)!=null;
    }
    public boolean isExist(Integer[] ids){
        return ids.length==sm.selectBatchIds(Arrays.asList(ids)).size();
    }
    @Override
    public Result2<String> insert(ScheduleSettingUpdateDto sd) {
        if(isExist(sd)){
            return Result2.error(StatusMes.Insert_Task_Err.getCode(),StatusMes.Insert_Task_Err.getMessage());
        }
        LambdaQueryWrapper<ScheduleSetting> l2=new LambdaQueryWrapper<>();
        ScheduleSetting setting = new ScheduleSetting();
        BeanUtils.copyProperties(sd,setting);
        int i = sm.insert(setting);
        // 看是否开启定时任务
        String t1="";
        if (i>0&&sd.getJobStatus().equals(1)){
            // 添加成功,并且状态是1，直接放入任务器
            SchedulingRunnable task = new SchedulingRunnable(sd.getBeanName(), sd.getMethodName(), sd.getMethodParams());
            cronTaskRegistrar.addCronTask(task, sd.getCronExpression());
            t1="并且此定时任务已启动！";
        }
        return Result2.OK("定时任务新增成功！"+t1);
    }

    @Override
    public Result2<String> update(ScheduleSettingUpdateDto sd) {
        if(isExist(sd)){
            ScheduleSetting setting = new ScheduleSetting();
            BeanUtils.copyProperties(sd,setting);
            if (sm.updateById(setting)>0){
                // 如果修改后的任务状态是1就加入任务器
                if (sd.getJobStatus().equals(1)) {
                    SchedulingRunnable task1 = new SchedulingRunnable(sd.getBeanName(), sd.getMethodName(), sd.getMethodParams());
                    cronTaskRegistrar.removeCronTask(task1);
                    SchedulingRunnable task = new SchedulingRunnable(sd.getBeanName(), sd.getMethodName(), sd.getMethodParams());
                    cronTaskRegistrar.addCronTask(task, sd.getCronExpression());
                }
                return Result2.ok("任务更新成功！");
            }
            return Result2.error(StatusMes.Update_Task_Err1.getCode(),StatusMes.Update_Task_Err1.getMessage());
        }
       return Result2.error(StatusMes.Update_Task_Err2.getCode(),StatusMes.Update_Task_Err2.getMessage());
    }

    @Override
    public Result2<String> delete(Integer[] id) {
        List<Integer> ids = Arrays.asList(id);
        List<ScheduleSetting> list = sm.selectBatchIds(ids);
        if (list.size()!=id.length){
            return Result2.error(StatusMes.Delete_Task_Err1.getCode(), StatusMes.Delete_Task_Err1.getMessage());
        }
        sm.deleteBatchIds(ids);
        // 批量删除任务
        for (ScheduleSetting s1:list){
            SchedulingRunnable task1 = new SchedulingRunnable(s1.getBeanName(), s1.getMethodName(), s1.getMethodParams());
            cronTaskRegistrar.removeCronTask(task1);
        }
        return Result2.OK(id.length>1?"当前任务取消成功！":"所以的任务全部取消成功！");
    }

    @Override
    public Result2<PageData<ScheduleSetting>> findByNamesPage(PageDto<ScheduleSetting,ScheduleSettingSearchDto> dto) {
        IPage<ScheduleSetting> pages = dto.pageBuild();
        LambdaQueryWrapper<ScheduleSetting> s=new LambdaQueryWrapper<>();
        boolean b = StringUtils.isEmpty(dto.getSearch().getName());
        s.like(b,ScheduleSetting::getBeanName,dto.getSearch().getName())
                .like(b,ScheduleSetting::getMethodName,dto.getSearch().getName());
        IPage<ScheduleSetting> iPage = sm.selectPage(pages, s);
        PageData<ScheduleSetting> list=new PageData<>(iPage.getRecords(), (int) iPage.getTotal());
        return Result2.OK(list);
    }

    @Override
    public Result2<String> cancel() {
        return null;
    }

    @Override
    public Result2<String> updateStatus(Integer id, Integer status) {
        ScheduleSetting setting = sm.selectById(id);
        SchedulingRunnable task1 = new SchedulingRunnable(setting.getBeanName()
                , setting.getMethodName()
                , setting.getMethodParams());
        ScheduleSetting s=new ScheduleSetting();
        s.setJobId(id);
        s.setJobStatus(status);
        sm.updateById(s);
        if (status==1){
            cronTaskRegistrar.removeCronTask(task1);
            cronTaskRegistrar.addCronTask(task1,setting.getCronExpression());
        }
        else {
            cronTaskRegistrar.removeCronTask(task1);
        }
        return Result2.OK("状态更新成功!");
    }
}
