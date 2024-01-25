# Kafka 源码分析

## 源码编译

问题一 Gradle 下载 Connect time out 

问题二 可能 Scala 环境没弄好

    gradle.properties 有 Scala 的版本
    scalaVersion=2.13.11
    安装 Scala 插件和SDK，
        插件到设置里面搜索Scala下载，
        SDK访问kafka-3.6.1-src/core/src/main/scala/kafka/Kafka.scala，
        上方出现 No Scala Module in module，点击Setup Scala SDK，
            这个插件不仅仅有语法提示而且可以帮你下载 Scala SDK，切换 SDK 非常方便
        也可以手动下载Scala SDK，然后指定一下SDK位置
        https://scala-lang.org/download/ 点击 PICK SPECIAL RELEASE -> 找到 2.13.11 -> 下载 Windows 压缩包

问题三 运行 Kafka

![image_01.png](image/image_01.png)

问题四 Failed to load class org.slf4j.impl.StaticLoggerBinder

    build.gradle 

    // 原来的 testImplementation libs.slf4jlog4j
    implementation libs.slf4jlog4j

    刷新项目 sync 项目

问题五 log4j:WARN No appenders could be found for logger (kafka.utils.Log4jControllerRegistration$).

    把config/log4j.properties复制一份到
    core/src/main/resources
    
问题四 > Process 'command 'D:\java\jdk-17.0.4.1\bin\java.exe'' finished with non-zero exit value 1

    操作问题四、问题五
    很重要，不然运行时看不到运行日志和错误日志

    [2024-01-24 12:16:54,099] INFO Opening socket connection to server localhost/127.0.0.1:2181. (org.apache.zookeeper.ClientCnxn)
    ...
    [2024-01-24 12:16:55,340] ERROR Exiting Kafka due to fatal exception during startup. (kafka.Kafka$)
    kafka.zookeeper.ZooKeeperClientTimeoutException: Timed out waiting for connection while in state: CONNECTING

    原因 没启动 ZooKeeper

    解决办法

    下载ZooKeeper源码，调试，启动，然后运行Kafka，成功

## 工作原理 

1. 一个主题，每个分区里面能保证队列有序，不同分区不能保证顺序
2. 如果分区数量大于，一个消费者组里面的消费者数量，那么会有一个消费者负责消费多个分区，涉及分区消费策略
3. kafka-console-consumer不指定消费者组时，每次执行都是随机的不同的消费者组
4. 每个分区里面，涉及生产偏移量，消费偏移量，对于消费偏移量，每个消费者组都在这个分区维护有各自的消费偏移量
5. 比较经典的部署，就是3台机器，每个消费者组，3个消费者 
6. 命令行客户端、图形界面客户端、代码客户端
7. 分区、副本，其实对应的就是一个相应的文件夹
8. 删除主题时，修改相应的数据文件夹的名字，标记为删除，等时机到了，就会删除，这样性能高，机器资源利用率好

## 其他

大神们的命名习惯各不相同，不需要太在意 例如配置目录 conf config ... 

影响速度的四个因素 CPU > 内存 > 磁盘 > 网络

## 主题、分区、副本

一个主题有一个或多个分区，为了负载均衡，提高性能，多个分区需要分布在不同机器上；一个分区有一个或多个副本，为了避免单点故障，副本必须在其他机器上。

每个分区只能被一个消费者组里面的一个消费者消费，不能被一个消费组里面的多个消费者同时消费，避免同一个消费者组重复消费相同消息。

## Kafka Streams 简介

*From Internet*

```
1、Kafka Stream背景
    1.1 Kafka Stream是什么
    1.2 什么是流式计算
    1.3 为什么要有Kafka Stream
2、Kafka Stream如何解决流式系统中关键问题
    2.1 KTable和KSteam
    2.2 时间
    2.3 窗口
3、Kafka Stream应用示例
    3.1 案例一：将topicA的数据写入到topicB中(纯复制)
    3.2 案例二：将TopicA中的数据实现wordcount写入到TopicB
    3.3 示例三：在TopicA中每输入一个值求和并写入到TopicB
    3.4 案例四：窗口
        3.4.1 每隔2秒钟输出一次过去5秒内topicA里的wordcount，结果写入到TopicB
        3.4.2 每隔5秒钟输出一次过去5秒内topicA里的wordcount，结果写入到TopicB
        3.4.3 TopicA 15秒内的wordcount，结果写入TopicB
    3.5 案例五：将TopicA的某一列扁平化处理写入TopicB
    3.6 案例六：将TopicA的多列扁平化处理写入TopicB

1.1 Kafka Stream是什么
Kafka Streams是一套客户端类库，它可以对存储在Kafka内的数据进行流式处理和分析。
1.2 什么是流式计算
流式计算：输入是持续的，一般先定义目标计算，然后数据到来之后将计算逻辑应用于数据，往往用增量计算代替全量计算。
批量计算：一般先有全量数据集，然后定义计算逻辑，并将计算应用于全量数据。特点是全量计算，并且计算结果一次性全量输出。
1.3 为什么要有Kafka Stream
开源流式处理系统有：Spark Streaming和Apache Storm，它们能与SQL处理集成等优点，功能强大，那为何还需要Kafka Stream呢？
1、使用方便。Spark和Storm都是流式处理框架，而Kafka Stream是基于Kafka的流式处理类库。开发者很难了解框架的具体运行方式，调试成本高，使用受限。而类库直接提供具体的类给开发者使用，整个应用的运行方式主要由开发者控制，方便使用和调试。
2、使用成本低。就流式处理系统而言，基本都支持Kafka作为数据源。Kafka基本上是主流的流式处理系统的标准数据源。大部分流式系统中都部署了Kafka，包括Spark和Storm，此时使用Kafka Stream的成本非常低。
3、省资源。使用Storm或Spark Streaming时，需要为框架本身的进程预留资源，框架本身也占资源。
4、Kafka本身也有优点。由于Kafka Consumer Rebalance机制，Kafka Stream可以在线动态调整并发度。
```


## Kafka Connect 简介

Kafka Connect 是一种用于在 Apache Kafka 和其他系统之间可扩展且可靠地流式传输数据的工具。 它使快速定义将大量数据移入和移出 Kafka 的连接器变得简单。 Kafka Connect 可以摄取整个数据库或从所有应用程序服务器收集指标到 Kafka 主题中，使数据可用于低延迟的流处理。 导出作业可以将数据从 Kafka 主题传送到二级存储和查询系统或批处理系统进行离线分析。

Kafka Connect是一个用于数据导入和导出的工具。  
它能够把多种数据源（如MySQL，HDFS等）与Kafka之间进行连接，实现数据在不同系统之间的交互以及数据的流动。

## 文档链接

https://kafka.apache.org/documentation/

## 源码链接

https://github.com/apache/kafka

## 官网

https://kafka.apache.org/  
https://www.scala-lang.org/

## 简介

最初 LinkedIn /lɪŋktˈɪn/ 领英 开发 -> 后来 成为 Apache 软件基金会 项目 

开发语言主要是 Java 和 Scala /ˈskɑːlə, ˈskeɪlə/ 

Scala 运行于 Java 平台（Java虚拟机），并兼容现有的 Java 程序

Kafka /ˈkɑːfkə/ 名字来自 团队成员 Kreps 喜欢的一个作家的名字 - 弗朗茨·卡夫卡 Franz Kafka

LinkedIn 2016被 Microsoft 微软 收购

Kafka 中文名 卡夫卡

目前 超过 80% 的财富 100 强公司 信任并使用 Kafka。

10/10最大的保险公司  
10/10最大的制造公司  
10/10最大的信息技术和服务公司  
8/10最大的电信公司  
8/10最大的运输公司  
7/10最大的零售公司  
7/10最大的银行和金融公司  
6/10最大的能源和公用事业组织  