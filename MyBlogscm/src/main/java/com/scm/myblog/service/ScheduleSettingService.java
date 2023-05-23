package com.scm.myblog.service;

import com.scm.myblog.entity.CORE.Result2;
import com.scm.myblog.entity.DOMAIN.ScheduleSetting;
import com.scm.myblog.entity.DTO.PageDto;
import com.scm.myblog.entity.DTO.ScheduleSettingSearchDto;
import com.scm.myblog.entity.DTO.ScheduleSettingUpdateDto;
import com.scm.myblog.entity.VO.PageData;

public interface ScheduleSettingService {
    public Result2<String> insert(ScheduleSettingUpdateDto sd);
    public Result2<String> update(ScheduleSettingUpdateDto sd);
    public Result2<String> delete(Integer[] id);

    public Result2<PageData<ScheduleSetting>> findByNamesPage(PageDto<ScheduleSetting,ScheduleSettingSearchDto> dto);

    public Result2<String> cancel();
    public Result2<String> updateStatus(Integer id, Integer status);

}
