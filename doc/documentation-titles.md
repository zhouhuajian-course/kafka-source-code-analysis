# Kafka 文档标题

https://kafka.apache.org/documentation/

```
1. GETTING STARTED       入门
    1.1 Introduction     介绍
    1.2 Use Cases        使用案例
    1.3 Quick Start      快速开始
    1.4 Ecosystem        生态系统
    1.5 Upgrading        升级
2. APIS                  接口
    2.1 Producer API     生产者接口
    2.2 Consumer API     消费者接口
    2.3 Streams API      Streams 接口
    2.4 Connect API      连接接口
    2.5 Admin API        管理接口
3. CONFIGURATION                     配置
    3.1 Broker Configs               Broker 配置
    3.2 Topic Configs                主题配置
    3.3 Producer Configs             生产者配置
    3.4 Consumer Configs             消费者配置
    3.5 Kafka Connect Configs        卡夫卡连接配置
        Source Connector Configs     源连接器配置 
        Sink Connector Configs       Sink 连接器配置
    3.6 Kafka Streams Configs        卡夫卡 Streams 配置
    3.7 AdminClient Configs          AdminClient 配置
    3.8 MirrorMaker Configs          MirrorMaker 配置
    3.9 System Properties            系统属性
    3.10 Tiered Storage Configs      分层存储配置
4. DESIGN                              设计
    4.1 Motivation                     动机
    4.2 Persistence                    持久化
    4.3 Efficiency                     效率
    4.4 The Producer                   生产者
    4.5 The Consumer                   消费者
    4.6 Message Delivery Semantics     消息分发 Semantics
    4.7 Replication                    复制/副本/备份
    4.8 Log Compaction                 日志压缩
    4.9 Quotas                         限额/配额
5. IMPLEMENTATION          实现
    5.1 Network Layer      网络层
    5.2 Messages           消息
    5.3 Message format     消息格式
    5.4 Log                日志
    5.5 Distribution       分布式
6. OPERATIONS                              操作
    6.1 Basic Kafka Operations             卡夫卡基本操作
        Adding and removing topics         添加和删除主题
        Modifying topics                   修改主题
        Graceful shutdown                  优雅地关闭
        Balancing leadership               
        Balancing Replicas Across Racks
        Mirroring data between clusters                    集群间数据镜像
        Checking consumer position                         检查消费者位置(offset)
        Managing Consumer Groups                           管理消费者组
        Expanding your cluster                             扩展你的集群
        Decommissioning brokers                            下线 brokers
        Increasing replication factor                      增加复制 factor
        Limiting Bandwidth Usage during Data Migration     限制数据迁移期间的带宽使用
        Setting quotas                                     设置限额/配额
    6.2 Datacenters                                        数据中心
    6.3 Geo-Replication (Cross-Cluster Data Mirroring)     异地复制（跨集群数据镜像）
        Geo-Replication Overview                           异地复制概述
        What Are Replication Flows                         什么是复制流
        Configuring Geo-Replication                        配置异地复制
        Starting Geo-Replication                           开始异地复制
        Stopping Geo-Replication                           停止异地复制
        Applying Configuration Changes                     应用配置更改
        Monitoring Geo-Replication                         监控异地复制
    6.4 Multi-Tenancy                        多租户
        Multi-Tenancy Overview               多租户概述
        Creating User Spaces (Namespaces)    创建用户空间（命名空间）
        Configuring Topics                   配置主题
        Securing Clusters and Topics         集群和主题安全
        Isolating Tenants                    隔离租户
        Monitoring and Metering              监控和计量
        Multi-Tenancy and Geo-Replication    多租户和异地复制
        Further considerations               更多注意事项
    6.5 Important Configs              重要的配置
        Important Client Configs       重要的客户端配置
        A Production Server Configs    一个生产服务器的配置
    6.6 Java Version                   Java 版本
    6.7 Hardware and OS                        硬件和操作系统
        OS                                     操作系统
        Disks and Filesystems                  磁盘和文件系统
        Application vs OS Flush Management     应用 vs 操作系统刷盘管理
        Linux Flush Behavior                   Linux刷盘行为
        Filesystem Selection                   文件系统选择
        Replace KRaft Controller Disk          更换 KRaft 控制器磁盘
    6.8 Monitoring                                                  监控
        Security Considerations for Remote Monitoring using JMX     使用 JMX 远程监控的安全考虑
        Tiered Storage Monitoring                                   分层存储监控
        KRaft Monitoring KRaft                                      监控
        Selector Monitoring                                         选择器监控
        Common Node Monitoring                                      公共节点监控
        Producer Monitoring                                         生产者监控
        Consumer Monitoring                                         消费者监控
        Connect Monitoring                                          连接监控
        Streams Monitoring                                          Streams 监控
        Others                                                      其他监控
    6.9 ZooKeeper                 ZooKeeper
        Stable Version            稳定的版本
        ZooKeeper Deprecation     ZooKeeper 弃用
        Operationalization        操作化
    6.10 KRaft                           KRaft
        Configuration                    配置
        Storage Tool                     存储工具
        Debugging                        调试
        Deploying Considerations         部署注意事项
        Missing Features                 缺少的功能
        ZooKeeper to KRaft Migration     ZooKeeper 迁移到 KRaft
    6.11 Tiered Storage             分层存储
        Tiered Storage Overview     分层存储概述
        Configuration               配置
        Quick Start Example         快速入门示例
        Limitations                 局限性
7. SECURITY 安全
    7.1 Security Overview                                        安全概述
    7.2 Listener Configuration 监听器配置
    7.3 Encryption and Authentication using SSL                  使用 SSL 进行加密和身份认证
    7.4 Authentication using SASL                                使用 SASL 进行身份认证
    7.5 Authorization and ACLs                                   身份认证和 ACLs
    7.6 Incorporating Security Features in a Running Cluster     将安全功能集成到正在运行的集群中
    7.7 ZooKeeper Authentication                                 ZooKeeper 身份认证
        New Clusters                                             新集群
        ZooKeeper SASL Authentication                            ZooKeeper SASL 身份认证
        ZooKeeper Mutual TLS Authentication                      ZooKeeper 相互 TLS 身份认证
        Migrating Clusters                                       迁移集群
        Migrating the ZooKeeper Ensemble                         迁移 ZooKeeper 整体/全体
        ZooKeeper Quorum Mutual TLS Authentication               ZooKeeper仲裁相互TLS 身份认证
    7.8 ZooKeeper Encryption                                     ZooKeeper 加密
8. KAFKA CONNECT                       卡夫卡 Connect
    8.1 Overview                       概述
    8.2 User Guide                     用户指南
        Running Kafka Connect          运行卡夫卡 Connect
        Configuring Connectors         配置连接器
        Transformations                转换
        REST API                       REST接口
        Error Reporting in Connect     Connect 中的错误报告
        Exactly-once support           刚好一次的支持
        Plugin Discovery               插件发现
    8.3 Connector Development Guide       连接器开发指南
        Core Concepts and APIs            核心概念和API
        Developing a Simple Connector     开发一个简单的连接器
        Dynamic Input/Output Streams      动态的输入/输出流
        Configuration Validation          配置验证
        Working with Schemas              Working with Schemas
    8.4 Administration                    管理
9. KAFKA STREAMS                                卡夫卡 Streams
    9.1 Play with a Streams Application         使用一个 Streams 应用程序
    9.2 Write your own Streams Applications     编写你自己的 Streams 应用程序
    9.3 Developer Manual                        开发者手册
    9.4 Core Concepts                           核心概念
    9.5 Architecture                            架构
    9.6 Upgrade Guide                           升级指南
```