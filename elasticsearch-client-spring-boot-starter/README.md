# elasticsearch-spring-boot-starter


## h2u

1. mvn clean install
```xml
<dependency>
    <groupId>com.zljin</groupId>
    <artifactId>elasticsearch-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

2. setup in application.yml

```yml
elasticsearch:
    host: 127.0.0.1
    port: 9200
    enabled: true
    schema: http
```

3. use in component

```java
@RestController
public class HealthController {

    @Resource
    ElasticsearchService elasticsearchService;

    @GetMapping("/testes")
    public ResponseEntity<Object> testes() throws IOException {
        SearchResponse myIndex = elasticsearchService.matchAll("products");
        return ResponseEntity.status(200).body(myIndex);
    }
}
```