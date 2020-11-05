# 使用方式

## Server 端
#### 版本选择
    -5.3.x 采用JDK8+SpringBoot1.x的最后的一个版本，6.x开始用的是JDK11+SpringBoot2.x
    目前主流还是JDK8所以选择5.3.X
    -tomcat 采用8.5.x
#### 构建方式
    -官方推荐的war overlays 话外音：尽量不要在源码上做修改，否则升级维护很痛苦
    -如需定制UI和业务代码，在自有项目下创建src目录进行，重新打包会覆盖源码包对应的配置或class(路径和名称必须一样)
#### HTTPS支持
    -生产上从商业公司购买证书
    -测试采用JDK工作生成证书
        1. 修改host：127.0.0.1    cas.example.org
        2. 生成证书(服务端)：keytool -genkey -alias cas -keyalg RSA -keysize 2048 -keypass changeit -storepass changeit -keystore casexample.keystore -dname "CN=cas.example.org,OU=casexample,O=casexample,L=sz,ST=gd,C=CN" -deststoretype pkcs12
                2.1 war+外置tomcat部署:
                    <Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
                               maxThreads="150" SSLEnabled="true">
                        <SSLHostConfig>
                            <Certificate certificateKeystoreFile="E:\etc\cas\casexample.keystore"
                              certificateKeystorePassword="changeit" type="RSA" />
                        </SSLHostConfig>
                    </Connector>
                2.2 bootjar+内嵌tomcat部署:
                    server.ssl.key-store=classpath:casexample.keystore
                    server.ssl.key-store-password=changeit
                    server.ssl.key-password=changeit
                2.3 访问：https://cas.example.org:8443/cas/login
#### JDBC 验证
    - 数据库脚本
        CREATE TABLE `sys_user`
        (
            `id`       bigint(20) NOT NULL AUTO_INCREMENT,
            `username` varchar(100) DEFAULT NULL COMMENT '登录名',
            `password` varchar(100) DEFAULT NULL COMMENT '密码',
            `emp_name` varchar(100) DEFAULT NULL COMMENT '员工姓名',
            `mobile`   varchar(11)  DEFAULT NULL COMMENT '手机号码',
            `email`    varchar(30)  DEFAULT NULL COMMENT '邮箱',
            `disabled` tinyint(1)   DEFAULT '0' COMMENT '是否禁用',
            `expired`  tinyint(1)   DEFAULT '0' COMMENT '是否过期',
            PRIMARY KEY (`id`) USING BTREE,
            UNIQUE KEY `username_uq` (`username`) USING BTREE
        ) ENGINE = InnoDB
          AUTO_INCREMENT = 1
          DEFAULT CHARSET = utf8;
    - CAS 配置
        #查询账号密码sql
        cas.authn.jdbc.query[0].sql=select * from sys_user where username=?
        #指定上面的sql查询字段名（必须）
        cas.authn.jdbc.query[0].fieldPassword=password
        #指定过期字段，1为过期，若过期不可用
        cas.authn.jdbc.query[0].fieldExpired=expired
        #为不可用字段段，1为不可用，需要修改密码
        cas.authn.jdbc.query[0].fieldDisabled=disabled
        #指定数据库方言
        cas.authn.jdbc.query[0].dialect=org.hibernate.dialect.MySQL57Dialect
        #数据库驱动
        cas.authn.jdbc.query[0].driverClass=com.mysql.jdbc.Driver
        #数据库连接
        cas.authn.jdbc.query[0].url=jdbc:mysql://127.0.0.1:3306/db?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false
        #数据库用户名
        cas.authn.jdbc.query[0].user=root
        #数据库密码
        cas.authn.jdbc.query[0].password=123456
        #默认加密策略，通过encodingAlgorithm来指定算法，默认NONE不加密
        cas.authn.jdbc.query[0].passwordEncoder.type=DEFAULT
        cas.authn.jdbc.query[0].passwordEncoder.characterEncoding=UTF-8
        cas.authn.jdbc.query[0].passwordEncoder.encodingAlgorithm=MD5
    
#### JAR问题
    xmlsectool 2.0.0 maven仓库没有
    解决：
    从https://build.shibboleth.net/nexus/content/repositories/releases/net/shibboleth/tool/xmlsectool/下截到本地
    执行命令安装到本地：mvn install:install-file -Dfile="D:\jar\xmlsectool-2.0.0.jar" "-DgroupId=net.shibboleth.tool" "-DartifactId=xmlsectool" "-Dversion=2.0.0" "-Dpackaging=jar"
 
## Client 端
### 版本选择
    -选择springboot集成版本
        <dependency>
            <groupId>org.jasig.cas.client</groupId>
            <artifactId>cas-client-support-springboot</artifactId>
            <version>${java.cas.client.version}</version>
        </dependency>
### 配置    
    - 参数：
    cas.server-url-prefix=https://cas.example.org:8443
    cas.server-login-url=${cas.server-url-prefix}/cas/login
    cas.client-host-url=http://127.0.0.1:${server.port}
    
    -main主入口类上增加
    @EnableCasClient 
    
#### http问题
    -修改server端HTTPSandIMAPS-10000001.json文件，增加Http支持
        "serviceId" 由原来的"^(https|imaps)://.*"改成 "^(https|imaps|http)://.*"
    -在application.properties文件中增加
        #允许http
        cas.tgc.secure=false
        cas.serviceRegistry.initFromJson=true
#### 证书问题
    -出现java.security.cert.CertificateException: No name matching cas.example.org found
    -导入服务端证导入到本地JDK中
        先导出：keytool -export -file casexample.crt -alias cas -keystore casexample.keystore
        再导入：keytool -import -alias cas -file casexample.crt -keystore D:\Java\jdk1.8.0_141/jre/lib/security/cacerts
    
    