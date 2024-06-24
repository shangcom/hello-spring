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
        여기서는 value="name" 이므로, 요청 파라미터에서 '?name=XXXXX' 라고 되어있는 부분을
        메서드 파라미터인 String name에 바인딩함.

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
          컨트롤러 메서드가 반환하는 값을 뷰 이름이 아닌 HTTP 응답 본문으로 직접 처리.

          @ResponseBody는 Spring MVC의 한 부분으로, 컨트롤러 메서드의 반환값을 HTTP 응답 본문에 직접 쓰도록 함.
          @RestController는 @ResponseBody와 @Controller를 결합한 역할. 해당 클래스의 모든 메서드는 뷰가
          아닌 HTTP 응답 본문으로 직접 처리.
          Spring MVC에서 전형적인 "뷰" 처리 방식과 달리, @ResponseBody는 JSON, XML 또는 텍스트 데이터를 직접 반환할 때 사용.

          개발자 도구 - 네트워크
          페이로드 (Payload): HTTP 메시지는 Header와 Body(본문)으로 나뉘며, 페이로드는 본문을 의미한다.
          응답 (Response): 서버 → 클라이언트로 전송되는 데이터 (주로 응답 본문).

           페이지 소스 보기(View Page Source)
           내용: 브라우저가 서버로부터 받은 HTML 문서의 원시 소스를 보여줍니다.
           특징: 자바스크립트에 의해 동적으로 변경된 내용은 포함되지 않으며, 서버가 처음 보낸 그대로의 HTML을 보여줍니다.
           @ResponseBody가 순수 HTML을 반환하는 경우, 애초에 HTML로 받아왔으니까 페이지 소스 보기와 개발자 도구의 응답은 똑같다.

           개발자 도구 네트워크의 페이로드(Network Payload)
           내용: HTTP 요청 및 응답의 실제 데이터를 보여줍니다. 여기에는 요청 헤더, 응답 헤더, 요청 본문(페이로드),
           응답 본문(페이로드) 등이 포함됩니다.
           특징: 특히 응답 페이로드는 서버가 클라이언트로 보낸 실제 데이터를 포함하며, 이는 JSON, XML, HTML, 텍스트 등
           다양한 형식일 수 있습니다.

           비교
           HTML 페이지 소스 보기: 정적 HTML을 보여주며, 이는 초기 로드된 페이지의 HTML 코드입니다.
           네트워크 페이로드: 네트워크 요청과 응답의 실제 데이터를 포함하며, 이 데이터는 서버와의 상호작용을
           통해 전달되는 모든 정보입니다.
         */
    }

    @GetMapping("/hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
