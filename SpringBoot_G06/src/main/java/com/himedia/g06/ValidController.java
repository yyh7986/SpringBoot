package com.himedia.g06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ValidController {

    @GetMapping("/")
    public String main() {
        return "startPage";
    }

    @GetMapping("/create")
    public String create(@ModelAttribute("dto") ContentDto contentdto, BindingResult result, Model model) {

        // 전송된 파라미터들을 검사해서 하나라도 비어있으면 startPage.jsp로 되돌아가고
        // 정상 전송되었으면 result.jsp 로 이동한다

        // validation 기능이 있는 클래스를 생성하고 그 객체를 이용하여 검사한다
        // 클래스의 이름은 ContentValidator, java의 Validator 인터페이스를 implements 한 클래스이다
        ContentValidator valid = new ContentValidator();
        valid.validate(contentdto, result);
        // BindingResult result : 에러 제목(키값)과 내용(밸류값)을 담을 수 있는 객체
        // validator 의 멤버메서드인 validate가 contentdto 내용을 검사한 후 result에 오류내용을 담아주고, 리턴되지 않아도 call by reference 이기 때문에 validate 메서드에서 넣어준 오류내용을 현재 위치에서도 result 라는 이름으로 확인이 가능하다

        if(result.hasErrors()) {
            // model.addAttribute("message", "writer와 content는 비어있으면 안됩니다");
            if(result.getFieldError("writer") != null) {
                model.addAttribute("message", "writer가 비어있으면 안됩니다");
            }else if(result.getFieldError("content") != null) {
                model.addAttribute("message", "content가 비어있으면 안됩니다");
            }
            return "startPage";
        }else {
            return "result";
        }
    }
}
