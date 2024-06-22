package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String Hello(Model model) {
        model.addAttribute("data", "hello!");
        /*
        attributeName : 뷰에서 사용할 변수의 이름.
        attributeValue : 변수 초기화 값. */
        return "hello";
        /*Spring MVC는 이 문자열을 뷰 이름으로 해석.
        따라서 src/main/resources/templates/hello.html이라는 템플릿 파일을 찾아서 해당 뷰를 렌더링함.
        리턴타입 void로 하고 리턴값 없애도 똑같이 동작하지만, 명시적으로 써주는 것이 좋음.
        반환 타입이 void일 때는 요청 경로를 기반으로 뷰 이름이 결정*/

    }

    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
        // localhost:8080/hello-mvc?name=넘기고 싶은 말 쓰기.
        /*
        @RequestParam(value="name") : 요청 파라미터를 메서드 파라미터에 바인딩.
        value = "????" : 요청 파라미터 중 어떤 파라미터를 메서드의 파라미터와 바인딩할 것인지 지정.
        여기서는 value="name" 이므로, 요청 파라미터에서 '?name=XXXXX' 라고 되어있는 부분을 메서드 파라미터인 String name에 바인딩함.

        attributeName : 뷰에서 사용할 변수 명.
        attributeValue : String name 인자로 뷰의 "name" 변수를 초기화함.
        총 세 개의 name이 있는 것.
        1. 요청 파라미터의 ?name="xxxx"
        2. @RequestParam(value="xxxx") String name : 요청 파라미터를 메서드 파라미터와 바인딩.
           여기서는 String name에 요청 파라미터의 "name"을 바인딩. String name 값으로 "xxxx"가 들어감.
        3. Model 객체의 "name" : 메서드 파라미터 값으로 view에 넘겨줄 Model 객체의 "name" 속성을 초기화.
        */

        /*
        1.클라이언트가 ?name=xxxx와 같은 요청 파라미터를 전송.
        2.@RequestParam(value="name") 어노테이션을 통해 요청 파라미터의 값을 String name 변수에 바인딩.
        3. model.addAttribute("name", name) 메서드를 사용하여, 바인딩된 name 값을 모델에 추가.
           이 때, 첫 번째 name은 뷰에서 사용할 변수의 이름이고, 두 번째 name은 바인딩된 요청 파라미터의 값임.
        4. 뷰 템플릿에서는 ${name}을 통해 이 값을 참조.
        */
    }

    @GetMapping("/hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
        /*@ResponseBody를 사용하면 순수 문자열이나 JSON, XML 등의 데이터가 반환되어 HTML 소스가 보이지 않음.
          컨트롤러 메서드가 반환하는 값을 뷰 이름이 아닌 HTTP 응답 본문(HTML BODY으로 직접 처리.
         */
    }
}
