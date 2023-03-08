import {ApiOrigin} from "@/config/ApiOrigin";
import axios from "axios";

//About页获取数据Api
export const APIGetAboutPage_User = ApiOrigin + "/Blog/getAllMessage"


//登陆注销
export function cancelLogin_User(token) {
    return axios.get(ApiOrigin + "/Blog/CancelLogin", {headers: {"token": token}})
}

//增加友链
export function addLink_User(data) {
    return axios.post(ApiOrigin + "/Blog/addLink", data)
}

//新增吐槽
export function addMessage_User(data) {
    return axios.post(ApiOrigin + "/Blog/setMessage", data)
}

//新增文章评论
export function addArticleComment_User(data) {
    return axios.post(ApiOrigin + "/Blog/setComment", data)
}

//获取指定文章的所有评论
export function getArticleComment_User(title) {
    return axios.get(`${ApiOrigin}/Blog/getComment/${title}`)
}

//增加点赞量
export function addArticleDianZan_User(count) {
    return axios.get(`${ApiOrigin}/Blog/setDianZan/${count}`)
}

//获取指定文章的内容
export function getArticleContent_User(title) {
    return  axios.get(`${ApiOrigin}/Blog/getArticles/${title}`)
}

//文件上传
export function uploadFile_User(data) {
    return axios.post(ApiOrigin + "/Upload", data, {headers: {'Content-Type': 'multipart/form-data'}})
}

//模糊查询+分页，获取所有文章的数据
export function getAllArticle_User(data){
    return axios.post(ApiOrigin+"/Blog/getPageData",data)
}

//获取所有热门标签
export function getAllHotTag_User(){
    return axios.get(ApiOrigin+"/Blog/getAllHotTag")
}

//文章搜索
export function searchArticle_User(search){
    return axios.get(`${ApiOrigin}/Blog/getArticlesSearch/${search}`)
}

//根据标签获取文章
export function getArticleByTag_User(tag){
    return axios.get(`${ApiOrigin}/Blog/getTagArticle/${tag}`)
}
