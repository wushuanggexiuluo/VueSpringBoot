# Vue+SpringBoot前后端分离个人博客项目

#### 介绍
个人练手的Vue+SpringBoot前后端分离个人博客项目，比较简单适合小白(适配双端)

#### 版本说明：
Vue2+Redis5.8+Mysql5.8+Springboot2.7

#### 安装教程

要想正常的启动项目需要以下操作，请逐步完成 

##### 如何修改项目？ 
1 创建数据库: 执行SQL文件，请执行SQL文件，并记录下数据库名，密码名，用户名方便后续使用。 <br>
2 启动Redis: 请启动Redis，并将密码记录方便后续使用。 <br>
3 修改前端： -> 进入src(前端目录)中后，先更改目录中的config文件夹下的地址（默认是localhost）,请按照需要更改 <br>
4 修改后端： <br>
->1) 进入MyBlogscm/src/main/resources/application-dev.yaml文件，修改文件中的“你的数据库名”，“你的密码”，“你的用户名”信息为自己的<br> 
->2) 进入MyBlogscm/src/main/java/com/scm/myblog/utils/QiniuCloudUtil.java文件，修改文件中的七牛云配置 (可参考https://blog.csdn.net/qq_53679247/article/details/127748726，https://blog.csdn.net/qq_53679247/article/details/127747471) <br>
不想改也可以使用我默认的（也就是我自己的，别搞破坏就行了，谢谢）

###### 如何启动项目？ 在以上步骤全部完成后才能进行以下步骤 <br>
1 前端文件夹夹执行命令<br>
 npm install<br>
 vue run serve <br>
2 后端下载pom.xml中的依赖（使用maven）<br> 
3 启动项目即可，通过访问localhost:8080即可进入网页 <br>
##### 如何进入管理员后台？<br>
直接点击前台页面的登录，然后输入下面的信息即可进入：<br>
管理员：admin<br>
密码：abc123456<br>


#### 在线演示地址
www.forevergtt.love

#### 主要事项
1 这里使用的演示项目所以后台管理系统中的数据并不能被删除或修改，但是你下载下来的源代码是可以正常的修改删除的，这不是Bug奥<br>

#### 项目截图
![输入图片说明](https://foruda.gitee.com/images/1678268357952724242/d87cb624_10104977.png "屏幕截图")

![输入图片说明](https://foruda.gitee.com/images/1678268390514661950/0812aab8_10104977.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1678268402068061830/4f9f0acc_10104977.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1678268424788493012/0599a459_10104977.png "屏幕截图")
![输入图片说明](https://foruda.gitee.com/images/1678268489749462929/747f318f_10104977.png "屏幕截图")

![输入图片说明](https://foruda.gitee.com/images/1678268503537380910/6cf87551_10104977.png "屏幕截图")


#### ps:有问题联系我 
我的联系方式 <br>
qq：2872392768<br>
邮箱：2872392768@qq.com<br>