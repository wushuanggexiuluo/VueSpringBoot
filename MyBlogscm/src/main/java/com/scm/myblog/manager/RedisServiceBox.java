package com.scm.myblog.manager;

import com.scm.myblog.entity.BO.DPLDataBo;
import com.scm.myblog.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RedisServiceBox {
    public static ArticleMapper articleMapper;
    private static RedisTemplate<String, String> rt;
    public static RedisTemplate<Object, Object> rt2;

    @Autowired
    private ArticleMapper articleMapperA;

    @Autowired
    public RedisTemplate<String, String> rtA;
    @Autowired
    public RedisTemplate<Object, Object> rtB;


    @PostConstruct
    private void init() {
        articleMapper = articleMapperA;
        rt = rtA;
        rt2=rtB;
    }

    //初始化redis 点赞收藏评论人数
    public static void init_Redis() {
        String s = rt.opsForValue().get("isInit");
        log.info("Redis准备完毕!");
        if (!Objects.equals(s, "1")) {
            //从数据库拿到所有的点赞数据
            List<DPLDataBo> aLlDataBo = UserBlogUtils.getALlDataBoFromDb();
            //放入redis，其中id为key,其他数据为值
            for (DPLDataBo d : aLlDataBo) {
                rt.opsForList().leftPushAll(
                        CreateArticleIdOnRedis(d.getArticleId()),
                        d.getArticleViewCount().toString(),
                        d.getArticleDianzanCount().toString(),
                        d.getArticleCommentCount().toString());
            }
            //标记是否已经初始化
            rt.opsForValue().set("isInit", "1");
        }
    }

    /**
     * 点赞量增加
     *
     * @param title 标题
     */
    public static void addDianZanCount(String title, Boolean isRever) {
        if (title != null) {
            Long id = UserBlogUtils.getIdByTitle(title);
            assert CreateArticleIdOnRedis(id) != null;
            String s = rt.opsForList().index(CreateArticleIdOnRedis(id), 1);
            assert s != null;
            long oldDianZanCount = (Long.parseLong(s));
            if (isRever) {
                rt.opsForList().set(CreateArticleIdOnRedis(id), 1, String.valueOf((oldDianZanCount - 1L)));
            }
            else {
                rt.opsForList().set(CreateArticleIdOnRedis(id), 1, String.valueOf((oldDianZanCount + 1L)));
            }
        }
    }

    /**
     * 评论量增加
     *
     * @param title 标题
     */
    public static void addCommentCount(String title) {
        if (title != null) {
            Long id = UserBlogUtils.getIdByTitle(title);
            assert id != null;
            String s = rt.opsForList().index(CreateArticleIdOnRedis(id), 0);
            assert s != null;
            long oldCommentCount = (Long.parseLong(s));
            rt.opsForList().set(CreateArticleIdOnRedis(id), 0, String.valueOf((oldCommentCount + 1L)));
        }
    }

    /**
     * 评论量减少（删除）
     *
     * @param ids 文章idlist
     */
    public static void removeCommentCount(List<Long> ids) {
        if (ids != null) {
            for (Long id : ids) {
                String s = rt.opsForList().index(CreateArticleIdOnRedis(id), 0);
                assert s != null;
                long oldCommentCount = (Long.parseLong(s));
                if (oldCommentCount > 0) {
                    rt.opsForList().set(CreateArticleIdOnRedis(id), 0, String.valueOf((oldCommentCount - 1L)));
                }

            }

        }
    }

    /**
     * 设置点赞数
     *
     * @param title    标题
     */
    public static void setDianZanCount(String title, Integer newCount) {
        if (title != null) {
            Long id = UserBlogUtils.getIdByTitle(title);
            assert id != null;
            String s = rt.opsForList().index(CreateArticleIdOnRedis(id), 1);
            assert s != null;
            rt.opsForList().set(CreateArticleIdOnRedis(id), 1, String.valueOf(newCount));
        }
    }

    /**
     * 新增数据到redis,
     *
     * @param title 标题
     */
    public static void addDataToRedis(String title) {
        DPLDataBo d = UserBlogUtils.getSingDataBoFormDb(UserBlogUtils.getIdByTitle(title));
        rt.opsForList().leftPushAll(
                RedisServiceBox.CreateArticleIdOnRedis(d.getArticleId()),
                d.getArticleViewCount().toString(),
                d.getArticleDianzanCount().toString(),
                d.getArticleCommentCount().toString());
    }

    /**
     * 删除数据在redis,
     *
     * @param id ID
     */
    public static void deleteDataToRedis(Long id) {
        rt.delete(CreateArticleIdOnRedis(id));
    }

    public static void deleteBatchDataToRedis(Long[] id) {
        List<String> idlist=new ArrayList<>();
        for (Long x :id){
            idlist.add(CreateArticleIdOnRedis(x));
        }
        rt.delete(idlist);
    }

    /**
     * 添加浏览量
     *
     * @param title 标题
     */
    public static void addViewCount(String title) {
        if (title != null) {
            Long id = UserBlogUtils.getIdByTitle(title);
            assert id != null;
            String s = rt.opsForList().rightPop(CreateArticleIdOnRedis(id));
            assert s != null;
            long oldViewCount = (Long.parseLong(s));
            rt.opsForList().rightPush(CreateArticleIdOnRedis(id), String.valueOf((oldViewCount + 1L)));
        }
    }

    /**
     * 从redis获取，浏览量从大到小排序后的id值
     *
     * @return {@link Long}
     */
    public static Map<Long, Long> getMaxViewCountSortFromRedis() {
        List<DPLDataBo> data = getAllDPLDataFromRedis();
        List<DPLDataBo> boList = data.stream().sorted(Comparator.comparing(DPLDataBo::getArticleViewCount).reversed()).collect(Collectors.toList());
        Map<Long, Long> x = new LinkedHashMap<>();
        for (DPLDataBo dp : boList) {
            x.put(dp.getArticleId(), dp.getArticleViewCount());
        }
        return x;
    }

    /**
     * 从redis获取，点赞量从大到小排序后的id值
     *
     * @return {@link Long}
     */
    public static Map<Long, Long> getMaxDianZanSortFromRedis(int i) {
        List<DPLDataBo> boList;
        if (i==1){
            boList = getAllDPLDataFromRedis().stream().sorted(Comparator.comparing(DPLDataBo::getArticleDianzanCount)).collect(Collectors.toList());
        }
        else {
            boList= getAllDPLDataFromRedis().stream().sorted(Comparator.comparing(DPLDataBo::getArticleDianzanCount).reversed()).collect(Collectors.toList());
        }
        Map<Long, Long> x = new LinkedHashMap<>();
        // 不许转换为foreach
        for (DPLDataBo dp : boList) {
            log.info(dp.toString());
            x.put(dp.getArticleId(), dp.getArticleDianzanCount());
        }
        return x;
    }
    /**
     * 从redis 得到点赞浏览数据
     *
     * @param title 标题
     * @return {@link DPLDataBo}
     */
    public static DPLDataBo getSingleDPLDataFromRedisByTitle(String title) {
        Long id = UserBlogUtils.getIdByTitle(title);
        assert id != null;
        return getSingleDPLDataFromRedisById(id);
    }

    //生成可以存储在Redis中的文章的key
    public static String CreateArticleIdOnRedis(Long id) {
        return id==null?null:("Article:" + id.toString());
    }

    //解码Redis中的文章Id
    public static Long GetArticleTrueIdOnRedis(String ids) {
        String s = ids.replace("Article:", "");
        return Long.parseLong(s);
    }

    public static DPLDataBo getSingleDPLDataFromRedisById(Long id) {
        List<String> stringList = rt.opsForList().range(CreateArticleIdOnRedis(id), 0, -1);
        DPLDataBo da = new DPLDataBo();
        assert stringList != null;
        da.setArticleViewCount(Long.parseLong(stringList.get(stringList.size()-1)));
        da.setArticleDianzanCount(Long.parseLong(stringList.get(1)));
        da.setArticleCommentCount(Long.parseLong(stringList.get(0)));
        return da;
    }

    /**
     * 从redis中获取所有的点赞数据
     *
     * @return {@link DPLDataBo}
     */
    public static List<DPLDataBo> getAllDPLDataFromRedis() {
        //匹配4位的文章id
        Set<String> keys = rt.keys("Article:????");
        List<DPLDataBo> list = new ArrayList<>();
        assert keys != null;
        for (String key : keys) {
            List<String> range = rt.opsForList().range(key, 0, -1);
            DPLDataBo d = new DPLDataBo();
            d.setArticleId(RedisServiceBox.GetArticleTrueIdOnRedis(key));
            assert range != null;
            d.setArticleViewCount(Long.parseLong(range.get(range.size() - 1)));
            d.setArticleDianzanCount(Long.parseLong(range.get(1)));
            d.setArticleCommentCount(Long.parseLong(range.get(0)));
            list.add(d);
        }
        return list;
    }


}
