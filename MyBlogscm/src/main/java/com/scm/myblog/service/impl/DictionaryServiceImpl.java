package com.scm.myblog.service.impl;

import com.scm.myblog.entity.CORE.Result2;
import com.scm.myblog.entity.CORE.StatusMes;
import com.scm.myblog.entity.DOMAIN.Dictionary;
import com.scm.myblog.entity.DTO.K_V_DictionaryDto;
import com.scm.myblog.entity.VO.DictionaryVo;
import com.scm.myblog.entity.VO.PageData;
import com.scm.myblog.mapper.DictionaryMapper;
import com.scm.myblog.service.DictionaryService;
import com.scm.myblog.utils.DbUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author 孙超孟
* @description 针对表【dictionary(数据字典)】的数据库操作Service实现
* @createDate 2023-04-28 15:52:42
*/
@Service
public class DictionaryServiceImpl implements DictionaryService{

    DictionaryMapper dm;
    public DictionaryServiceImpl(DictionaryMapper dm)
    {
        this.dm=dm;
    }

    @Override
    public Result2<PageData<DictionaryVo>> getDictionary() {
        List<Dictionary> dictionaries = dm.selectList(null);
        List<DictionaryVo> vos = dictionaries.stream().map(po ->
        {
            DictionaryVo vo = new DictionaryVo();
            BeanUtils.copyProperties(po, vo);
            return vo;
        }).collect(Collectors.toList());
        return Result2.success(new PageData<>(vos,dictionaries.size()));
    }

    @Override
    public Result2<String> addKeyValById(K_V_DictionaryDto dto) {
        boolean b = DbUtils.addDictionaryK_V(dm, dto.getId(), dto.getK_v_string());
        return b?Result2.OK("新增成功！"):Result2.Fail(StatusMes.SAVE_ERR.getCode(),StatusMes.SAVE_ERR.getMessage());
    }

    @Override
    public Result2<Map<String, String>> getAllKeyValueOnDictionaryById(Integer id) {
        return Result2.success(DbUtils.getKeyValueDictionaryById(dm,id));
    }

    public Result2<Map<String,String>> getAllKeyValueOnDictionary(){
        Map<String, String> s = DbUtils.getKeyValueDictionary(dm);
        return Result2.success(s);
    }
}




