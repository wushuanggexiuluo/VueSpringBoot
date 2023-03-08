//引入需要路有的组件

import MyLogins from "@/components/login/MyLogins";

import statusNoConnection from "@/components/Err/pc/StatusNoConnection";


// 如果是移动端，给 .vue 的命名特殊处理，这里多了一级目录 mobile/
const path = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i) ? 'mobile/' : 'pc/'
console.log(path)
export const  data={
    routes: [
        {
            name: "index",
            path: "/",
            component: MyIndex=>{require([`@/components/IndexPage/${path}MyIndex`],MyIndex)},
            meta: {title: "幻梦博客"}
        },
        {
            name: "jh",
            path: "/Boutique",
            component: BoutiqueUser=>{require([`@/components/BoutiquePage/${path}BoutiqueUser`],BoutiqueUser)},
            meta: {title: "本站精华"},
        },
        {
            name: "sort",
            path: "/Sort",
            component: SortUser=>{require([`@/components/SortPage/${path}SortIndex/SortUser`],SortUser)},
            meta: {title: "分类索引"},
        },
        {
            name: "sortDetails",
            path: "/SortPage/:tag",
            component: SortDetailsPage=>{require([`@/components/SortPage/${path}SortDetails/SortDetailsPage`],SortDetailsPage)},
            meta: {title: "分类页"},
        },
        {
            name: "file",
            path: "/TimelineFile",
            component: TimelineFileUser=>{require([`@/components/TimelineFilePage/${path}TimelineFileUser`],TimelineFileUser)},
            meta: {title: "文章归档"},
        },
        {
            name: "link",
            path: "/MyLink",
            component: FriendDetialsPage=>{require([`@/components/FriendLink/${path}FriendDetialsPage`],FriendDetialsPage)},
            meta: {title: "友情链接"}
        },
        {
            name: "about",
            path: "/About",
            component: AboutPage=>{require([`@/components/AboutPage/${path}AboutPage`],AboutPage)},
            meta: {title: "关于本站"},
        },
        {
            name: "login",
            path: "/Logins",
            component: MyLogins,
            //配置是否需要进行权限校验
            meta: {isAuth: true, title: "登陆注册一体机"},
        },

        {
            name: "details",
            path: "/details/:title",
            component: MyDetails=>{require([`@/components/DetailsPage/${path}MyDetails`],MyDetails)},
            meta: {title: "文章详情"}
        },

        {
            name: "search",
            path: "/Search/:search",
            component: SearchUser=>{require([`@/components/SearchPage/${path}SearchUser`],SearchUser)},
            meta: {title: "全站搜索"}
        },
        {
            name: "404",
            path: "*",
            component: Status404=>{require([`@/components/Err/${path}Status404`],Status404)},
            meta: {title: "404"}
        },
        {
            name: "500",
            path: "/500",
            component: Status500=>{require([`@/components/Err/${path}Status500`],Status500)},
            meta: {title: "500"}
        },
        {
            name: "noconn",
            path: "/NOTCONNECTION",
            component: statusNoConnection,
            meta: {title: "无网络连接"}
        },
        {
            //只有一个孩子时，必须要加的
            alwaysShow: true,
            name: "admin",
            path: "/Admin",
            component: MyAdmin=>{require([`@/components/SystemAdmin/${path}MyAdmin`],MyAdmin)},
            //配置是否需要进行权限校验
            meta: {isAuth: true, title: "博客后台"},
            children: [
                {
                    name: "wzM",
                    path: "/Admin/Article",
                    component: ArticleFact=>{require([`@/components/SystemAdmin/${path}ManageFact/ArticleFact`],ArticleFact)},
                    meta: {isAuth: true, title: "文章管理"},
                },
                {
                    name: "plM",
                    path: "/Admin/Comment",
                    component: CommentFact=>{require([`@/components/SystemAdmin/${path}ManageFact/CommentFact`],CommentFact)},
                    meta: {isAuth: true, title: "评论管理"},
                },
                {
                    name: "lyM",
                    path: "/Admin/Message",
                    component: MessageFact=>{require([`@/components/SystemAdmin/${path}ManageFact/MessageFact`],MessageFact)},
                    meta: {isAuth: true, title: "留言管理"},
                },
                {
                    name: "ylM",
                    path: "/Admin/FriendLink",
                    component: LinkFact=>{require([`@/components/SystemAdmin/${path}ManageFact/LinkFact`],LinkFact)},
                    meta: {isAuth: true, title: "友链管理"},
                },
                {
                    name: "bqM",
                    path: "/Admin/Tag",
                    component: TagFact=>{require([`@/components/SystemAdmin/${path}ManageFact/TagFact`],TagFact)},
                    meta: {isAuth: true, title: "标签管理"},
                }, {
                    name: "jhM",
                    path: "/Admin/Boutique",
                    component: BoutiqueFact=>{require([`@/components/SystemAdmin/${path}ManageFact/BoutiqueFact`],BoutiqueFact)},
                    meta: {isAuth: true, title: "精华管理"},
                },
                {
                    name: "gdM",
                    path: "/Admin/TimelineFile",
                    component: TimelineFileFact=>{require([`@/components/SystemAdmin/${path}ManageFact/TimelineFileFact`],TimelineFileFact)},
                    meta: {isAuth: true, title: "归档管理"},
                }
            ]
        }
    ]
}
