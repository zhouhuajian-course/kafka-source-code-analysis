# Kafka 源码分析

## 调试生产者

```
> .\kafka-console-producer.bat
Missing required option(s) [bootstrap-server]
Option                                   Description
------                                   -----------
--bootstrap-server <String: server to    REQUIRED unless --broker-list
  connect to>                              (deprecated) is specified. The server
                                           (s) to connect to. The broker list
                                           string in the form HOST1:PORT1,HOST2:
                                           PORT2.
--broker-list <String: broker-list>      DEPRECATED, use --bootstrap-server
                                           instead; ignored if --bootstrap-
                                           server is specified.  The broker
                                           list string in the form HOST1:PORT1,
                                           HOST2:PORT2.
--topic <String: topic>                  REQUIRED: The topic id to produce
                                           messages to.
--version                                Display Kafka version.
```

```
> .\kafka-console-producer.bat  --bootstrap-server 192.168.1.103:9092 --topic TopicTest
>test01
>test02
>test03
```

## 调试消费者

```

> kafka-console-consumer.bat
This tool helps to read data from Kafka topics and outputs it to standard output.
Option                                   Description
------                                   -----------
--bootstrap-server <String: server to    REQUIRED: The server(s) to connect to.
  connect to>
--from-beginning                         If the consumer does not already have
                                           an established offset to consume
                                           from, start with the earliest
                                           message present in the log rather
                                           than the latest message.
--group <String: consumer group id>      The consumer group id of the consumer.
--topic <String: topic>                  The topic to consume on.
--version                                Display Kafka version.
```

```
>kafka-console-consumer.bat  --bootstrap-server 192.168.1.103:9092 --topic TopicTest
test01
test02
test03
```

## 调试主题 增删改查

https://kafka.apache.org/documentation/#basic_ops

下载对应版本的 二进制 包

常用操作如下

```
[D:\kafka\kafka_2.13-3.6.1\bin\windows]$ kafka-topics.bat
Create, delete, describe, or change a topic.
Option                                   Description                            
------                                   -----------                            
--alter                                  Alter the number of partitions and     
                                           replica assignment. Update the       
                                           configuration of an existing topic   
                                           via --alter is no longer supported   
                                           here (the kafka-configs CLI supports 
                                           altering topic configs with a --     
                                           bootstrap-server option).            
--bootstrap-server <String: server to    REQUIRED: The Kafka server to connect  
  connect to>                              to.                                                         
--create                                 Create a new topic.                    
--delete                                 Delete a topic                         
--describe                               List details for the given topics.                           
--help                                   Print usage information.                  
--list                                   List all available topics.             
--partitions <Integer: # of partitions>  The number of partitions for the topic 
                                           being created or altered (WARNING:   
                                           If partitions are increased for a    
                                           topic that has a key, the partition  
                                           logic or ordering of the messages    
                                           will be affected). If not supplied   
                                           for create, defaults to the cluster  
                                           default.                                                                 
--replication-factor <Integer:           The replication factor for each        
  replication factor>                      partition in the topic being         
                                           created. If not supplied, defaults   
                                           to the cluster default.              
--topic <String: topic>                  The topic to create, alter, describe   
                                           or delete. It also accepts a regular 
                                           expression, except for --create      
                                           option. Put topic name in double     
                                           quotes and use the '\' prefix to     
                                           escape regular expression symbols; e.
                                           g. "test\.topic".                                                                
--version                                Display Kafka version.      
```

具体操作

```
> kafka-topics.bat --bootstrap-server 192.168.1.103:9092 --list


> kafka-topics.bat --bootstrap-server 192.168.1.103:9092 --create --topic TopicTest
Created topic TopicTest.

> kafka-topics.bat --bootstrap-server 192.168.1.103:9092 --create --topic TopicTest2
Created topic TopicTest2.

> kafka-topics.bat --bootstrap-server 192.168.1.103:9092 --list
TopicTest
TopicTest2

> kafka-topics.bat --bootstrap-server 192.168.1.103:9092 --describe --topic TopicTest
Topic: TopicTest	TopicId: TVn4roHhSGCu_wuxT_gD_w	PartitionCount: 1	ReplicationFactor: 1 Configs: 
	Topic: TopicTest	Partition: 0	Leader: 0	Replicas: 0	Isr: 0

> kafka-topics.bat --bootstrap-server 192.168.1.103:9092 --alter --topic TopicTest2 --partitions 2

> kafka-topics.bat --bootstrap-server 192.168.1.103:9092 --describe --topic TopicTest2
Topic: TopicTest2	TopicId: uy8MesrQSoWtCpNl-P8CWQ	PartitionCount: 2	ReplicationFactor: 1 Configs: 
	Topic: TopicTest2	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
	Topic: TopicTest2	Partition: 1	Leader: 0	Replicas: 0	Isr: 0

> kafka-topics.bat --bootstrap-server 192.168.1.103:9092 --delete --topic TopicTest2
```

## 源码编译

修改 数据目录，很重要，方便后期调试

```
config/server.properties
修改
# log.dirs=/tmp/kafka-logs
log.dirs=E:\\java-project\\kafka-source-code-analysis\\kafka-3.6.1-src\\kafka-logs
注意 \ 要改成 \\

或者
# 表示工作目录下的 kafka-logs，运行配置有配置当前工作目录，目录可以不存在
log.dirs=kafka-logs
```

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

问题六 ERROR Shutdown broker because all log dirs in ... have failed (kafka.log.LogManager)

```
据说 操作Topic时，Windows 才会出现这个问题

貌似做topic的删除操作就会出现，尽量不做删除topic操作

解决办法
1. 停掉 Kafka，删除数据目录 kafka-logs
2. 停掉 ZooKeeper，删除数据目录 zookeeper_data
3. 启动 ZooKeeper，启动 Kafka
问题解决
```



## 工作原理 

1. 一个主题，每个分区里面能保证队列有序，不同分区不能保证顺序
2. 如果分区数量大于，一个消费者组里面的消费者数量，那么会有一个消费者负责消费多个分区，涉及分区消费策略
3. kafka-console-consumer不指定消费者组时，每次执行都是随机的不同的消费者组
4. 每个分区里面，涉及生产偏移量，消费偏移量，对于消费偏移量，每个消费者组都在这个分区维护有各自的消费偏移量
5. 比较经典的部署，就是3台机器，每个消费者组，3个消费者 
6. 命令行客户端、图形界面客户端、代码客户端
7. 分区、副本，其实对应的就是一个相应的文件夹
8. 删除主题时，修改相应的数据文件夹的名字，标记为删除，等时机到了，就会删除，这样性能高，机器资源利用率好
9. 新的消费者组，默认从主题的末尾开始消费，也就是不消费之前的数据
10. 旧的消费者组，如果下线，kafka记录它之前消费到的偏移量，重新上线后，会在之前下线的位置开始消费消息
11. 消息数据使用分层存储，一个主题有多个分区，一个分区有多个log文件，log文件里面记录消息实际的数据，由于每个消息可能字节数不同，索引起来很慢，所以每个log文件都有一个index文件，专门用来做索引，记录消息id和该消息的偏移量，结构使用数组结构，每条消息的索引字节数一样，所以很容易找到具体数据，然后索引文件index里面的数据是提前生成好的，这样的好处是方便直接插入，而log文件的数据是慢慢追加的
12. 有index，有log文件，主要是为了考虑性能，需要那块数据，加载那块数据的index和log即可，也可高效利用内存
13. log和index叫做segment 分片
14. kafka-clients 默认的消费者，是异步发送消息，有main线程、sender线程，异步发送消息，可以配置batch.size、linger.time，满足batch.size会发送，或者满足linger.time也会发送，这个涉及批量发送的优化，据说linger.time默认为0ms，这些都给优化，如果需要
15. 源码结构貌似比RocketMQ整洁
16. 生产者，发送消息，一般经过 拦截器、序列化器（键、值序列化器）、分区器
17. 生产消息默认异步，还可以添加回调函数，使用Future可以实现同步发送
18. 分区策略；如果发送时可以指定分区，或者提供Key，那么会根据Key的Hash模分区总数后，得到分区，或者不填分区不填Key，那么会根据粘性分区缓存，会一批一批发送数据
19. 默认使用默认分区器，可以自己实现分区器接口，并自定义分区规则
20. Kafka有REST API，虽然性能要低，但各个语言都能用
21. 生产者 ack 策略 0 1 -1，不同的策略性能不同，可能会导致消息丢失、消息重复
22. 生产者生产消息相关的事务、消费者消费消息相关的事务
23. 保证消息不丢失、不重复；生产者默认开启幂等性校验
24. 磁盘随机读写 100K/s，顺序读写 600M/s，零拷贝，页缓存，JVM外内存

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