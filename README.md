# Kafka 源码分析

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