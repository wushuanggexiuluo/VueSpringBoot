//邮箱正则校验
export function emailRegx(str) {
    console.log(1)
    if (str != null) {
        console.log(2)
        let r =  /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        return r.test(str)
    } else return false
}
//域名/网站匹配
export  function internetRegx(str){
    if (str!=null){
        let r=/[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?/
        return r.test(str)
    }
    else return false
}
//ip地址匹配
export  function ipRegx(str){
    if (str!=null){
        let r= /((2(5[0-5]|[0-4]\d))|[0-1]?\d{1,2})(\.((2(5[0-5]|[0-4]\d))|[0-1]?\d{1,2})){3}/
        return r.test(str)
    }
    else return false
}
