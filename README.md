servlet을 이용한 게시판 구현 프로젝트입니다  

한 도메인 안에서 비슷한 패턴의 요청을 command pattern으로 구현해보았습니다.  
/board/view/3, /board/list, /board/write ..

board라는 리소스안에서 서비스행위라는 반복되는 요청을 아래와 같이  
http요청 메서드와 리소스명마다 HttpService를 구현한 서비스를 매치시켜  
Servlet단에서는 http 메세지를 분석하여  
해당하는 서비스의 요청을 주고 받는것에만 집중 할 수 있었습니다.

```java
public interface HttpService {
    ServiceResult doService(HttpServletRequest request, HttpServletResponse response);
}
```

```java
public class ArticleServlet extends HttpServlet {
Map<String, HttpService> commandMap;

    init() {
        commandMap = new HashMap<>();
        commandMap.put("GET:list", new ListService());
        commandMap.put("POST:view", new ViewService());
        commandMap.put("GET:write", new WriteService());
        commandMap.put("POST:write",new WriteService());
    }
}
```
