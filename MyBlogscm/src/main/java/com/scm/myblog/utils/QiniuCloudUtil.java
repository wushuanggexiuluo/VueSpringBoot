package com.scm.myblog.utils;

import com.alibaba.druid.util.StringUtils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * 七牛云文件上传工具
 *
 * @author Lancer
 * @date 2022/12/08
 */
public class QiniuCloudUtil {

    // 设置AK和SK
    private static final String ACCESS_KEY = "RxRUP-tmvA_XFz9JB61UTuEHh4JECdKptKlM-7qa";
    private static final String SECRET_KEY = "r1jcQAqIzAi3FgqbAuy08VlogNURrGMwQJt1VZ7C";
    // 要上传的空间名（存储空间）
    private static final String bucketname = "loveandlove";
    //外链域名
    private static final String domain = "cdn.forevergtt.love";
    //指定文件存储目录，没有的话可以写成空字符串
    private static final String route = "mysystem/medicine/";
    //用于拼接外链
    private static final String filelink = "http://" + domain + "/";

    // 构建密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);


    /**
     * 上传
     *
     * @param file 文件
     * @return {@link String}
     * @throws IOException ioexception
     */
    public static String upload(File file) throws IOException {
        // 创建上传对象，Region代表存储空间所在地区
        Configuration configuration = new Configuration(Region.huanan());
        UploadManager uploadManager = new UploadManager(configuration);
        try {
            // 构建token
            String token = auth.uploadToken(bucketname);
            if (StringUtils.isEmpty(token)) {
                System.out.println("获取到token失败！");
                return "";
            }
            //构建文件名
            // 其中UUID.randomUUID()用于生成唯一的文件名
            String imageName = route + UUID.randomUUID().toString();
            //上传操作
            Response res = uploadManager.put(file, imageName, token);
            //校验返回结果
            //拼接并返回外链
            return res.statusCode == 200 ? filelink + imageName : "";
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            e.printStackTrace();
            System.out.println("error " + r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                System.out.println("error " + e1.error());
            }
            return "";
        }
    }

    /**
     * 删除
     *
     * @param fileName 文件名称
     * @return {@link Boolean}
     * @throws IOException ioexception
     */
    public static Boolean delete(String fileName) throws IOException {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        String bucket = "loveandlove";
        String key = "mysystem/medicine/" + fileName;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
            return true;
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
            return false;
        }

    }


}