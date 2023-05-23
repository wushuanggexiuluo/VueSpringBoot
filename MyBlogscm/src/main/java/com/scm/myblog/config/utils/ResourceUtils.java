package com.scm.myblog.config.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceUtils {
    public static List<String> getSpringSecurityResource(){
        return null;
    }

    // springmvc 拦截器的路径配置
    public static Map<String,String> getSpringMVCResource(){
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("/v2/api-docs/**","/");
        hashMap.put("swagger-ui.html","/");
        hashMap.put("/webjars/springfox-swagger-ui/springfox.css","/");
        hashMap.put("/webjars/springfox-swagger-ui/springfox.css.map","/");
        hashMap.put("/webjars/springfox-swagger-ui/swagger-ui-bundle.js","/");
        hashMap.put("/webjars/springfox-swagger-ui/swagger-ui.css","/");
        hashMap.put("/webjars/springfox-swagger-ui/swagger-ui.css.map","/");
        hashMap.put("/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js","/");
        hashMap.put("/webjars/springfox-swagger-ui/springfox.js","/");
        hashMap.put("/webjars/springfox-swagger-ui/favicon-16x16.png","/");
        hashMap.put("/webjars/springfox-swagger-ui/favicon-32x32.png","/");
        hashMap.put("swagger-ui.html/swagger-resources/configuration/ui","/");
        hashMap.put("swagger-ui.html/swagger-resources/configuration/security","/");
        hashMap.put("swagger-ui.html/swagger-resources","/");
        return hashMap;
    }
}
