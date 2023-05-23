package com.scm.myblog.config.securityconfig;

public class ApiConfig {

    //无需权限即可访问的Api接口地址
    public static String [] NoAuthApi=new String[] {
            // 图片上传
            "/Upload/**",
            // 博客前台
            "/Blog/**",
            // 第三方登陆
            "/Auth/**",
            //统一测试接口
            "/Test/**",
            // vue打包后的静态资源放行, 注意一定不要加/static/，不然无法排除成功！！！
            "/*.*",
            "/**/*.*.*",
            "/**/*.*.*.*",
            "/v2/api-docs/**",
            // 测试文档2 (失效)
            "/swagger-ui.html",
            "/swagger-ui.html/**/**/**",
            "/swagger-ui.html/**/**",
            "/swagger-ui.html/**",
            "/webjars/**/*.*",
            "/webjars/springfox-swagger-ui/favicon-32x32.png",
            "/webjars/**/*.*.*",

            // 测试文档3 （失效）
//            "/swagger-ui/**",
//            "/swagger-ui/**/**",
//            "/swagger-ui/**/**/**",
//            "/swagger-ui/*.*/**/**/**",
//            "/swagger-ui/**/**/**/**",
//            "/swagger-ui/index.html/swagger-resources/configuration/ui",
//            "/swagger-ui/index.html/swagger-resources/configuration/security",
//            "/swagger-ui/index.html/swagger-resources",
//            "/swagger-ui/*.*",
    };
}
