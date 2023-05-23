package com.scm.myblog.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class K_V_DictionaryDto {
    private Integer id;
    private Map<String,String> k_v_string;
}
