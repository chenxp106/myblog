#### 系统功能
* 使用shiro实现登录功能，文章，分类，标签增删查改
* 使用腾讯云实现对象存储
* 实现了事物和日志管理
* 配置redis实现session和mybatis的二级缓存
* 配置Swagger2生成API文档

#### 数据库设计
1. 总共有10张表。article, tag, category, user, link, comment, login_log, articlt_category, article_tag, log
1. 其中，分类表category和标签表。分别于文章表关联。article_category和article_tag。article_category表的字段分别为id， category_id, article_id。
1. 评论表，字段有父评论p_id和子评论c_id。用于关联评论间的关系。
1. 使用InnoDB存储引擎。

#### 数据持久化框架mybatis-plus
1. mybatis 是一种半自动化的Java持久化框架，通过注解或xml的方式将对象数SQL关联起来
1. 我们不需要花费时间和精力去处理加载驱动、连接驱动，创建statement等繁琐操作。
1. 使用ORM思想，实现结果集的映射。
1. mybatis-plus是mybatis的的增强工具，在mubatis的基础上只做增强不做修改。简化开发，提高效率。

#### 登录功能
1. 基于Shiro实现。通过配置安全管理器，继承AuthorizingRealm 类实现身份验证。使用SimpleAuthenticationInfo来实现密码加密和匹配。
1. 同时使用shiro-redis开源插件来缓存session信息，减少对数据库的访问。

#### 分页插件
1. 封装分页信息，快速获取分页信息。

#### 链接和标签
1. 连接和标签的增删查改比较简单，直接操作。

#### 新增修改文章
1. 新增或修改文章会设计到三张表的修改。article，article_tags, article_category
1. 先根据文章id删除文章-分类表中对应的数据。然后将新的文章数据插入到表中。
1. 对于标签处理也是类似的，根据文章id将文章-标签表中旧数据删除，然后插入信息标签信息到文章-标签表中。

#### 评论的层级展示
1. 关于文章的评论功能，可以对文章进行评论和对评论回复。
1. 对于评论表的设计，添加了父评论的id，用于快速找到评论间对应关系。
1. 在展示这些评论时，用到了树这种数据结构来够间评论树。
1. 具体做法是，通过文章id查找该文章是所有评论数据，然后根据p_id字段的信息递归构建评论树。
1. 通过树型结构分层的显示评论信息。

#### 腾讯云对象存储
1. 配置cos_api。
1. 将图片先缓存到本地中，调用对象存储api将图片上传到服务器中。

#### 日志实现
1. 采用AOP思想实现日志
1. 在controller的方法中使用@Log，申明切入点。
1. 获取操作方法上的参数，保存到log表中。















