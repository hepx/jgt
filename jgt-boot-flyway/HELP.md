# 使用方式

#### 引入flyway 和数据库相磁的jar
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
        </dependency>
### 配置
        spring.datasource.url=jdbc:mysql://127.0.0.1:3306/test_pc?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&autoReconnect=true
        spring.datasource.username=root
        spring.datasource.password=123456

        #flyway配置 基本上采用默认值就OK
        #对于已经存在数据库要启用flyway 设置baseline-on-migrate为true自动创建基于当前已有数据库结构的基准线
        #基准线版本默认为"1"，后面的脚本不能以"1"为命名，新的数据见意设置为false
        spring.flyway.baseline-on-migrate=true
        spring.flyway.enabled=true
        spring.flyway.check-location=true
        spring.flyway.locations=classpath:db/migration
        spring.flyway.validate-on-migrate=true
