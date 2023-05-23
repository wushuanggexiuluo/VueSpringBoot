package com.scm.myblog.service;

import com.scm.myblog.entity.CORE.Result2;
import com.scm.myblog.entity.DTO.K_V_DictionaryDto;
import com.scm.myblog.entity.VO.DictionaryVo;
import com.scm.myblog.entity.VO.PageData;

import java.util.Map;

/**
* @author 孙超孟
* @description 针对表【dictionary(数据字典)】的数据库操作Service
* @createDate 2023-04-28 15:52:42
*/
public interface DictionaryService {

    Result2<PageData<DictionaryVo>> getDictionary();

    Result2<String> addKeyValById(K_V_DictionaryDto dto);

    Result2<Map<String, String>> getAllKeyValueOnDictionaryById(Integer id);
}
