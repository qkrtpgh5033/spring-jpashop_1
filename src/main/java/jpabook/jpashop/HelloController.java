package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "오랜만에 오셨네요");
        //model에다가 데이터를 실어서 controller를 통해 view로 넘길 수 있다.

        return "hello"; // 화면 이름( 템플릿에 있는 파일 )
    }
}
