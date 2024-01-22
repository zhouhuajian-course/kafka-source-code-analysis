# Kafka 文档标题

https://kafka.apache.org/documentation/

```
1. GETTING STARTED       入门
    1.1 Introduction     介绍
    1.2 Use Cases        使用案例
    1.3 Quick Start      快速开始
    1.4 Ecosystem        生态系统/生态圈
    1.5 Upgrading        升级
2. APIS 
    2.1 Producer API     生产者 API
    2.2 Consumer API     消费者 API
    2.3 Streams API
    2.4 Connect API      连接器 API
    2.5 Admin API        管理 API
3. CONFIGURATION 配置
    3.1 Broker Configs               Broker 配置
    3.2 Topic Configs                主题配置
    3.3 Producer Configs             生产者配置
    3.4 Consumer Configs             消费者配置
    3.5 Kafka Connect Configs        Kafka 连接 配置
        Source Connector Configs
        Sink Connector Configs
    3.6 Kafka Streams Configs
    3.7 AdminClient Configs
    3.8 MirrorMaker Configs
    3.9 System Properties            
    3.10 Tiered Storage Configs
4. DESIGN                              设计思想
    4.1 Motivation                     动机
    4.2 Persistence                    持久化
    4.3 Efficiency                     性能
    4.4 The Producer                   生产者
    4.5 The Consumer                   消费者
    4.6 Message Delivery Semantics     消息分发策略
    4.7 Replication                    副本/备份
    4.8 Log Compaction                 日志压缩
    4.9 Quotas 
5. IMPLEMENTATION          实现
    5.1 Network Layer      网络层
    5.2 Messages           消息
    5.3 Message format     消息格式
    5.4 Log                日志
    5.5 Distribution       分布式
6. OPERATIONS 操作
    6.1 Basic Kafka Operations             基本的 Kafka 操作
        Adding and removing topics         添加和移除主题
        Modifying topics                   修改主题
        Graceful shutdown                  优雅地关闭 Kafka
        Balancing leadership 
        Balancing Replicas Across Racks
        Mirroring data between clusters
        Checking consumer position          检查消费者位置(offset)
        Managing Consumer Groups            管理消费者组
        Expanding your cluster              扩展你的 Kafka 集群
        Decommissioning brokers             下线 Brokers
        Increasing replication factor
        Limiting Bandwidth Usage during Data Migration
        Setting quotas
    6.2 Datacenters     数据中心
    6.3 Geo-Replication (Cross-Cluster Data Mirroring)
        Geo-Replication Overview
        What Are Replication Flows
        Configuring Geo-Replication
        Starting Geo-Replication
        Stopping Geo-Replication
        Applying Configuration Changes
        Monitoring Geo-Replication
    6.4 Multi-Tenancy
        Multi-Tenancy Overview
        Creating User Spaces (Namespaces)
        Configuring Topics
        Securing Clusters and Topics
        Isolating Tenants
        Monitoring and Metering
        Multi-Tenancy and Geo-Replication
        Further considerations
    6.5 Important Configs              主要的配置
        Important Client Configs       重要的客户端配置
        A Production Server Configs    一个生产环境服务器的配置
    6.6 Java Version     Java 版本
    6.7 Hardware and OS                        硬件和操作系统
        OS                                     操作系统
        Disks and Filesystems                  磁盘和文件系统
        Application vs OS Flush Management     应用 vs 操作系统 Flush 管理
        Linux Flush Behavior 
        Filesystem Selection
        Replace KRaft Controller Disk
    6.8 Monitoring     监控
        Security Considerations for Remote Monitoring using JMX
        Tiered Storage Monitoring
        KRaft Monitoring
        Selector Monitoring
        Common Node Monitoring
        Producer Monitoring
        Consumer Monitoring
        Connect Monitoring
        Streams Monitoring
        Others
    6.9 ZooKeeper
        Stable Version            稳定的版本
        ZooKeeper Deprecation
        Operationalization
    6.10 KRaft
        Configuration
        Storage Tool
        Debugging
        Deploying Considerations
        Missing Features
        ZooKeeper to KRaft Migration
    6.11 Tiered Storage
        Tiered Storage Overview
        Configuration
        Quick Start Example
        Limitations
7. SECURITY 安全
    7.1 Security Overview                                        安全总览
    7.2 Listener Configuration 
    7.3 Encryption and Authentication using SSL                  使用 SSL 加密和授权
    7.4 Authentication using SASL                                使用 SASL 授权
    7.5 Authorization and ACLs                                   授权和 ACLs
    7.6 Incorporating Security Features in a Running Cluster     将安全功能集成到正在运行的集群中
    7.7 ZooKeeper Authentication ZooKeeper                       授权
        New Clusters                            新集群
        ZooKeeper SASL Authentication
        ZooKeeper Mutual TLS Authentication
        Migrating Clusters                      迁移集群
        Migrating the ZooKeeper Ensemble
        ZooKeeper Quorum Mutual TLS Authentication
    7.8 ZooKeeper Encryption
8. KAFKA CONNECT Kafka             连接器
    8.1 Overview                   概括/总览
    8.2 User Guide                 用户指南
        Running Kafka Connect      运行 Kafka 连接器
        Configuring Connectors     配置连接器
        Transformations            转换器
        REST API
        Error Reporting in Connect
        Exactly-once support
        Plugin Discovery
    8.3 Connector Development Guide     连接器开发指南
        Core Concepts and APIs
        Developing a Simple Connector
        Dynamic Input/Output Streams
        Configuration Validation
        Working with Schemas
    8.4 Administration
9. KAFKA STREAMS
    9.1 Play with a Streams Application         运行一个 Streams 应用
    9.2 Write your own Streams Applications     编写你自己的流应用程序
    9.3 Developer Manual                        开发者手册
    9.4 Core Concepts                           核心概念
    9.5 Architecture                            架构
    9.6 Upgrade Guide                           升级指南
```