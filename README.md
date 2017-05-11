###robot

---
项目配置：

1. 使用开发环境Nexus栈（而非默认的mavenCentral）、maven pom.xml里使用：
    ```
     <repositories>
         <repository>
             <id>nexus</id>
             <name>maven-public</name>
             <url>http://120.27.233.57:8081/repository/maven-public/</url>
         </repository>
     </repositories>
    ```

2. 更新项目版本、即pom.xml中`<version>`：
    ```
    mvn versions:set -DnewVersion={新的版本号}
    ```
    追加
    ```
    -DgenerateBackupPoms=false
    ```
    则不备份原pom文件。

3. 手工上传jar至Nexus：
    先在maven的setting.xml里增加nexus账号配置（仅上传时需要）：
    ```
    <servers>
    	<server>
    	    <id>nexus</id>
    		<username>账号</username>
    		<password>密码</password>
    	</server>
    </servers>
    ```
    上传jar包（支付宝sdk为例）：
    ```
     mvn deploy:deploy-file \
            -DgroupId=com.alipay \
            -DartifactId=alipay-sdk-java \
            -Dversion=20170307171631 \
            -Durl=http://120.27.233.57:8081/repository/maven-thirdparty/ \
            -DrepositoryId=nexus \
            -DupdateReleaseInfo=true \
            -DgeneratePom=true \
            -Dpackaging=jar \
            -Dfile={alipay-sdk-java-20170307171631.jar的本地路径} \
            -Dsources={alipay-sdk-java-20170307171631-sources.jar的本地路径}
    ```
    然后可以引入依赖：
    ```
    <dependency>
        <groupId>com.alipay</groupId>
        <artifactId>alipay-sdk-java</artifactId>
        <version>20170307171631</version>
    </dependency>
    ```

