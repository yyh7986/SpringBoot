package com.himedia.g14.controller;

import com.himedia.g14.dto.MemberVO;
import com.himedia.g14.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MemberController {

    @Autowired
    MemberService ms;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("dto") MemberVO membervo, BindingResult result, Model model,
                        HttpServletRequest request) {
        String url = "member/login";
        if(result.getFieldError("userid") != null){
            model.addAttribute("message", result.getFieldError("userid").getDefaultMessage());
        }else if(result.getFieldError("pwd") != null){
            model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
        }else{
            MemberVO mvo = ms.getMember(membervo.getUserid());
            if(mvo == null){
                model.addAttribute("message", "아이디/비번을 확인하세요");
            }else if(!mvo.getPwd().equals(membervo.getPwd())){
                model.addAttribute("message", "아이디/비번을 확인하세요");
            }else if(mvo.getPwd().equals(membervo.getPwd())){
                HttpSession session = request.getSession();
                session.setAttribute("loginUser", mvo);
                url = "redirect:/";
            }
        }
        return url;
    }
}
