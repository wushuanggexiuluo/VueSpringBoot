// 引入插件
import VueRouter from "vue-router";
import UserStatus from "@/utils/userStatus";
import userStatus from "@/utils/userStatus";
import axios from "axios";
import {data} from "@/routers/details";
//创建路由器
const MyRouter = new VueRouter(data)
// 解决：Uncaught (in promise) NavigationDuplicated;
// 先保存一份 VueRouter.prototype.push方法
let originalPush = VueRouter.prototype.push;
let originalRepace = VueRouter.prototype.replace;

VueRouter.prototype.push = function push(location) {
    return originalPush
        // call(); 篡改上下文
        .call(this, location)
        // catch(); 捕获异常 ****
        .catch(e => e);
}
VueRouter.prototype.replace = function replace(location) {
    return originalRepace.call(this, location).catch(e => e);
}
// MyRouter.mode="history"
//定义需要携带请求头token的数组
//前置路由守卫,和后端的过滤器有点像
MyRouter.beforeEach((to, from, next) => {

    let isA = null
    try {
        isA=to.meta.isAuth
    }
    catch (e) {
        isA=null
    }
    if (isA != null) {
        if (isA) {
            // 统一设置Token请求头
            // 添加请求拦截器，在请求头中加token
            axios.interceptors.request.use(
                config => {
                    let token = userStatus.getData("token");
                    // 判断本地的cookie中是否有token
                    if (token) {
                        config.headers['token'] = token
                    }
                    return config
                },
                error => {
                    return Promise.reject(error)
                })
            next();
        }
    }
    //校验管理员是否合法
    if (to.name === "admin") {
        if (UserStatus.getUser("user") === "admin") {
            next();
        }
    } else {
        next();
    }

})

//后置路由守卫,实现网页标题联动效果
MyRouter.afterEach((to, from) => {
    document.title = to.meta.title || "幻梦博客"
})
export default MyRouter;
