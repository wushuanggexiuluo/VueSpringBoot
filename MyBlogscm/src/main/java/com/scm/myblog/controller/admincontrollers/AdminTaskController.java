package com.scm.myblog.controller.admincontrollers;

import com.scm.myblog.entity.CORE.Result2;
import com.scm.myblog.entity.DOMAIN.ScheduleSetting;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.DTO.ScheduleSettingSearchDto;
import com.scm.myblog.entity.DTO.ScheduleSettingUpdateDto;
import com.scm.myblog.entity.DTO.TaskChangeStatusDto;
import com.scm.myblog.entity.VO.PageData;
import com.scm.myblog.service.impl.ScheduleSettingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/Admin/Task")
public class AdminTaskController {



    @Autowired
    ScheduleSettingServiceImpl ss;


    @PostMapping("/add")
    public Result2<String> addTask(@RequestBody @Validated ScheduleSettingUpdateDto dto) {
        return ss.insert(dto);
    }

    @GetMapping("/delete/{ids}")
    public Result2<String> delete(@PathVariable("ids") Integer[] ids) {
        return ss.delete(ids);
    }

    @GetMapping("/delete/{id}")
    public Result2<String> delete(@PathVariable("id") @Min(1) Integer id) {
        return ss.delete(new Integer[]{id});
    }

    @PostMapping("/update")
    public Result2<String> update(@RequestBody @Validated ScheduleSettingUpdateDto dto) {
        return ss.update(dto);
    }

    @PostMapping("/updateStatus")
    public Result2<String> updateStatus(@RequestBody @Validated TaskChangeStatusDto dto) {
        return ss.updateStatus(dto.getJobId(),dto.getJobStatus());
    }
    @PostMapping("/findByNamesPage")
    public Result2<PageData<ScheduleSetting>> findByNamesPage(@RequestBody @Validated PageDto<ScheduleSetting,ScheduleSettingSearchDto> dto) {
        return ss.findByNamesPage(dto);
    }
}