import {ApiOrigin} from "@/config/ApiOrigin";

export function getALlComment(this_) {
    var a;
     this_.$http.get(ApiOrigin+"/Blog/getAllMessage").then((resp) => {
        a=resp;
        return a;
    })

}
// export default {getALlComment}
