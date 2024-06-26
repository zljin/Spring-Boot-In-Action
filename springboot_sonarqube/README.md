# springboot_sonarqube

## Jacoco
mvn clean verify

see code Coverage
cd target/site/jacoco/index.html

## Sonar

mvn sonar:sonar

mvn sonar:sonar \
-Dsonar.projectKey=springboot_sonarqube \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.login=383e41fe14decb690e7af4cc0f241a7dacf2c51c