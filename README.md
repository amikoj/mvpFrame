# mvpFrame

#### 项目介绍
Mvp基本使用框架,实现mvp模式编码,分离View的UI与逻辑层,分隔数据操作和界面之间的操作,通过presenter实现数据、界面分离的优点。

#### 三方库

1. 版本控制
> 通过gradle脚本ext实现版本统一管理.便于版本升级管理

```

ext {
    minSdkVersion = 19
    targetSdkVersion = 27
    compileSdkVersion = 27
    buildToolsVersion = '27.0.3'


    // App dependencies
    supportLibraryVersion = '27.1.1'
    constraintLayoutVersion = '1.1.2'



    junitVersion = '4.12'
    testRunVersion = '1.0.2'
    espressoCoreVersion = '3.0.2'
    glideVersion = '4.7.1'
    glideOkhttp3IntegrationVersion = '4.6.1'
    eventBusVersion = '3.1.1'
    fastJsonVersion = '1.2.47'
    okHttp3Version = '3.11.0'

    guavaVersion = '18.0'
    mockitoVersion = '1.10.19'
    powerMockito = '1.6.2'
    hamcrestVersion = '1.3'
    rulesVersion = '1.0.0'
    rxjavaVersion = '2.1.3'
    rxandroidVersion = '2.0.1'
    sqlbriteVersion = '2.0.0'
}

```

2. 三方库
> 为了简化开发过程，对于开发过程中涉及的各个环节采用已经成熟的三方库进行实现并对其进行简单的封装管理。

- 网络请求框架
OkHttp3网路请求实现应用与网络的通信

- 本地数据管理
使用android自带的sqlite数据库实现本地数据的缓存处理.

- 图片处理
图片处理使用Glide加载在线图片或者图片相关要求的需求

- 线程操作
线程操作方面目前采用java自有的线程池进行管理操作

- 数据解析
Json数据解析采用fastJson实现数据的解析

- 通信传递
APP内通信采用EventBus实现








