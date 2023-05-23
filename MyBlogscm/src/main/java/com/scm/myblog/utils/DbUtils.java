package com.scm.myblog.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.scm.myblog.entity.BO.KeyValueBo;
import com.scm.myblog.entity.DOMAIN.Dictionary;
import com.scm.myblog.entity.DOMAIN.KindTreeMenu;
import com.scm.myblog.mapper.DictionaryMapper;
import com.scm.myblog.mapper.KindTreeMenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
@Component
@Slf4j
public class DbUtils {

    @Autowired
    private KindTreeMenuMapper km;

    public static Integer getCurePage(Integer x,Integer y){
        return (x-1)*y;
    }

    public static String EncodePassword(String password){
        BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
        return  bcrypt.encode(password);
    }
    //    字符串分割
    public static Integer[] getIdsByDiv(String s){
        if (s==null){
            return null;
        }
        String[] split = s.split("-");
        Integer[] res=new Integer[split.length];
        int i=0;
        for (String ss: split) {
            res[i] = Integer.parseInt(ss);
            i++;
        }
        return res;
    }

    //    字符串加密
    public static String setIdsByDiv(Integer[] s){
        StringBuilder sb=new StringBuilder();
        int i=0;
        for (Integer ss: s) {
            i++;
            if (i>s.length-1) {
                sb.append(ss.toString());
            }
            else {
                sb.append(ss.toString()).append("-");
            }
        }
        return sb.toString();
    }

    /**
     * 列表和树
     *
     * @param kindTreeMenus 树菜单的原始数据,注意一定要保持顺序按照parentId进行排列
     * @return {@link List}<{@link KindTreeMenu}>
     */// 树状菜单递归遍历（扁平转立体）
    public List<KindTreeMenu> listWithTree(List<KindTreeMenu> kindTreeMenus,boolean isShowRootNode) {
        // 获取根节点id
        Integer LevelId = kindTreeMenus.get(0).getId();
        //是叶子节点时候返回当前源数据
        if (kindTreeMenus.size() == 1){
            return kindTreeMenus;
        }

        //1、组装成父子的树形结构
        //1.1 找到所有的一级分类
        List<KindTreeMenu> resTree = kindTreeMenus.stream()
                .filter(menu -> menu.getParentId().equals(LevelId))
                .filter(menu -> menu.getIsDelete() == 0)
                .filter(menu -> menu.getStatus() == 1)
                .peek(menu -> menu.setChildData(getChildrens(menu, kindTreeMenus)))
                .sorted(Comparator.comparingInt(menu -> (menu.getOrderBy() == null ? 0 : menu.getOrderBy())))
                .collect(Collectors.toList());
        if (!isShowRootNode){
            return resTree;
        }
        else {
            //查询情况下携带除根节点外的当前节点
            if (LevelId>0){
                List<KindTreeMenu> resTree2=new ArrayList<>();
                KindTreeMenu reTr=new KindTreeMenu();
                BeanUtils.copyProperties(kindTreeMenus.get(0),reTr);
                reTr.setChildData(resTree);
                resTree2.add(reTr);
                return  resTree2;
            }
        }

        return resTree;
    }
    //递归找到当前菜单的子菜单
    private List<KindTreeMenu> getChildrens(KindTreeMenu root,List<KindTreeMenu> all){
        return all.stream()
                .filter(menu -> menu.getParentId().equals(root.getId()))
                .filter(menu->menu.getIsDelete()==0)
                .filter(menu->menu.getStatus()== 1)
                .peek(menu -> menu.setChildData(getChildrens(menu,all)))
                .sorted(Comparator.comparingInt(menu -> (menu.getOrderBy() == null ? 0 : menu.getOrderBy())))
                .collect(Collectors.toList());
    }

    // 获取所有的数据字典键值对
    public static Map<String,String> getKeyValueDictionary(DictionaryMapper dm){
        LambdaQueryWrapper<Dictionary> l1=new LambdaQueryWrapper<>();
        l1.select(Dictionary::getKeyValueMap,Dictionary::getId).eq(Dictionary::getStatus,1);
        return commonK_V(dm,l1);
    }
    // 获取指定ID的数据字典
    public static Map<String,String> getKeyValueDictionaryById(DictionaryMapper dm,Integer id){
        LambdaQueryWrapper<Dictionary> l1=new LambdaQueryWrapper<>();
        l1.select(Dictionary::getKeyValueMap,Dictionary::getId)
                .eq(Dictionary::getStatus,1)
                .eq(Dictionary::getId,id);
        return commonK_V(dm,l1);
    }
    // 获取指定code的数据字典
    public static Map<String,String> getKeyValueDictionaryByCode(DictionaryMapper dm,Integer code){
        LambdaQueryWrapper<Dictionary> l1=new LambdaQueryWrapper<>();
        l1.select(Dictionary::getKeyValueMap,Dictionary::getId)
                .eq(Dictionary::getStatus,1)
                .eq(Dictionary::getKindCode,code);
        return commonK_V(dm,l1);
    }
    // 获取数据字典通用方案
    public static Map<String,String> commonK_V(DictionaryMapper dm,LambdaQueryWrapper<Dictionary> l1){
        List<Dictionary> dictionaries = dm.selectList(l1);
        List<String> collect = dictionaries.stream().map(Dictionary::getKeyValueMap).collect(Collectors.toList());
        log.info(collect.toString());
        List<KeyValueBo> keyValueBos=new ArrayList<>();
        for (String s:collect){
            List<KeyValueBo> k=JSON.parseArray(s,KeyValueBo.class);
            keyValueBos.addAll(k);
        }
        return keyValueBos.stream().collect(Collectors.toMap(KeyValueBo::getKey,KeyValueBo::getVal));
    }

    // 数据字典key_val字段增加
    public static boolean addDictionaryK_V(DictionaryMapper dm,Integer id,Map<String,String> addMap){
        Dictionary dictionary = dm.selectById(id);
        List<KeyValueBo> valueBos = JSON.parseArray(dictionary.getKeyValueMap(), KeyValueBo.class);
        for (Map.Entry<String,String> s:addMap.entrySet()){
            valueBos.add(new KeyValueBo(s.getKey(),s.getValue()));
        }
        Map<String,String> map=new HashMap<>();
        for (KeyValueBo bo:valueBos){
            map.put("key",bo.getKey());
            map.put("val",bo.getVal());
        }
        String s = JSON.toJSONString(map);
        return dm.updateById(new Dictionary(id,s))>0;
    }
}
