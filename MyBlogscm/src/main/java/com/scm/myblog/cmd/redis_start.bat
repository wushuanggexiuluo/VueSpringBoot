::启动redis的命令
@echo off
:: 规定输出的字符使用的编码，防止中文乱码
chcp 65001
::这是简单的输出，相当于java中的log.info()
echo redis-server 开始启动喽......
::设置启动窗口的标题
title redis-server
::定义一个变量BootPath的变量
set BootPath=B:\redis
:: 进入BootPath路径
cd %BootPath%
:: 同时执行两条命令，使用配置文件的方式启动redis
redis-server.exe %BootPath%\redis.windows.conf
