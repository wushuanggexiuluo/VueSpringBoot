import axios from "axios";
import MyRouter from "@/routers";
import AxiosStatus from "@/mixs/AxiosStatus";

import ShowAdminMessage from "@/mixs/ShowAdminMessage";

export default function AxiosHandle() {
    // //测试连接
    // axios.get(ApiOrigin+"/cs/get").then((e)=>{
    //     if(e.status!==200){
    //         MyRouter.push({name:"500"})
    //     }
    // }).catch(()=>{
    //     MyRouter.push({name:"500"})
    // })

    //响应结果拦截器
    axios.interceptors.response.use(response => {
        //登录凭证过期的操作
        // console.log(response)
        if (response!=null){
            if(response.data.code===ShowAdminMessage.data().LOGIN_LAPSE){
                alert("token失效，已退出登录")
                //清理浏览器缓存
                localStorage.removeItem("user")
                localStorage.removeItem("token")
                console.log(response.data.message);
                //赶到首页去
                MyRouter.push({name: 'index'}).then(r => console.log(r));
            }
        }
        return response;
    }, error => {
        let code=error.code
        console.log(error)
        //d多种异常处理
        switch (code) {
            case AxiosStatus.ErrNoConnection:
                MyRouter.push({name: 'login'}).then(r => console.log(r));
                break;
            case AxiosStatus.Err403:
                MyRouter.push({name: '500'}).then(r => console.log(r));
                break;
        }
        return Promise.reject(error);
    });
}
