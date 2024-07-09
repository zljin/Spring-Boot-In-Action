# springboot_sonarqube

## sonarQube

1. docker 创建sonarqube 容器
```
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:7.9.4-community
```

2. 浏览器访问：http://localhost:9000  用户名admin 密码admin

3. Create new project and sonarToken

> https://v2-1.docs.kubesphere.io/docs/zh-CN/devops/sonarqube/

> http://localhost:9000/dashboard?id=springboot_sonarqube


4. scan

```
mvn sonar:sonar \
  -Dsonar.projectKey=springboot_sonarqube \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=383e41fe14decb690e7af4cc0f241a7dacf2c51c
```

## Jacoco
mvn clean verify

see code Coverage
cd target/site/jacoco/index.html
