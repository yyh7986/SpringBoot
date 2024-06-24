package com.himedia.g14.controller;

import com.google.gson.Gson;
import com.himedia.g14.dto.KakaoProfile;
import com.himedia.g14.dto.MemberVO;
import com.himedia.g14.dto.OAuthToken;
import com.himedia.g14.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


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
        if (result.getFieldError("userid") != null) {
            model.addAttribute("message", result.getFieldError("userid").getDefaultMessage());
        } else if (result.getFieldError("pwd") != null) {
            model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
        } else {
            MemberVO mvo = ms.getMember(membervo.getUserid());
            if (mvo == null) {
                model.addAttribute("message", "아이디/비번을 확인하세요");
            } else if (!mvo.getPwd().equals(membervo.getPwd())) {
                model.addAttribute("message", "아이디/비번을 확인하세요");
            } else if (mvo.getPwd().equals(membervo.getPwd())) {
                HttpSession session = request.getSession();
                session.setAttribute("loginUser", mvo);
                url = "redirect:/";
            }
        }
        return url;
    }

    @GetMapping("/kakaostart")
    public @ResponseBody String kakaostart() {
        String a = "<script type='text/javascript'>"
                + "location.href='https://kauth.kakao.com/oauth/authorize?"
                + "client_id=094b768919904a40b88d9ccc5feb0c54"
                + "&redirect_uri=http://localhost:8070/kakaoLogin"
                + "&response_type=code'"
                + "</script>";
        return a;
    }

    @GetMapping("/kakaoLogin")
    public String login(HttpServletRequest request) throws IOException {
        String code = request.getParameter("code");
        System.out.println(code);   // 토큰 출력

        // 실제 User info를 요청할 url과 전달인수 설정
        String endpoint = "https://kauth.kakao.com/oauth/token";
        URL url = new URL(endpoint);    // import java.net.URL;
        String bodyData = "grant_type=authorization_code";
        bodyData += "&client_id=094b768919904a40b88d9ccc5feb0c54";
        bodyData += "&redirect_uri=http://localhost:8070/kakaoLogin";
        bodyData += "&code=" + code;

        // Stream 연결
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  // import java.net;
        // http header 값 넣기
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        conn.setDoOutput(true);

        // 인증절차를 완료하고 User info 요청을 위한 정보를 요청 및 수신한다
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
        bw.write(bodyData);
        bw.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        String input = "";
        StringBuilder sb = new StringBuilder(); // 조각난 String을 조립하기위한 객체
        while ((input = br.readLine()) != null) {
            sb.append(input);
            System.out.println(input);
        }

        // 사용자가 실제로 동의한 항목들만 요청하고 받을 수 있도록 권한을 갖고 있는 새로운 토큰으로 구성
        // {"access_token":"-iNiRc3FlbmFr4MhgKeV70meqnbKpPI8AAAAAQo9c-sAAAGQLmh1eSrd4XW-Oo9G","token_type":"bearer","refresh_token":"RJ88t8wgLQJ_ilDrgWdFoidanAZh5m_DAAAAAgo9c-sAAAGQLmh1dird4XW-Oo9G","expires_in":21599,"scope":"profile_nickname","refresh_token_expires_in":5183999}

        // 수신내용을 GSon 으로 변경(파싱)
        Gson gson = new Gson();
        OAuthToken oAuthToken = gson.fromJson(sb.toString(), OAuthToken.class);
        // oAuthToken <- sb{"access_token":"-iNiRc3FlbmFr4MhgKeV70meqnbKpPI8AAAAAQo9c ....
        // sb 내용을 항목에 맞추서 OAuthToken 객체에 옮겨 담는다


        // 실제 정보 요청 및 수신
        String endpoint2 = "https://kapi.kakao.com/v2/user/me";
        URL url2 = new URL(endpoint2);
        // import java.net.HttpURLConnection
        HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
        // header 값 넣기
        conn2.setRequestProperty("Authorization", "Bearer " + oAuthToken.getAccess_token());
        conn2.setDoOutput(true);
        // UserInfo 수신
        BufferedReader br2 = new BufferedReader(
                new InputStreamReader(conn2.getInputStream(), "UTF-8"));
        String input2 = "";
        StringBuilder sb2 = new StringBuilder();
        while ((input2 = br2.readLine()) != null) {
            sb2.append(input2);
            System.out.println(input2);
        }

        // 수신내용
        // {"id":3586440497,"connected_at":"2024-06-19T02:51:56Z","properties":{"nickname":"양용호"},"kakao_account":{"profile_nickname_needs_agreement":false,"profile":{"nickname":"양용호","is_default_nickname":false}}}

        Gson gson2 = new Gson();
        KakaoProfile kakaoProfile = gson2.fromJson(sb2.toString(), KakaoProfile.class);

        System.out.println(kakaoProfile.getId());
        KakaoProfile.KakaoAccount ac = kakaoProfile.getAccount();
        System.out.println(ac.getEmail());
        KakaoProfile.KakaoAccount.Profile pf = ac.getProfile();
        System.out.println(pf.getNickname());

        MemberVO mvo = ms.getMember(kakaoProfile.getId());
        if (mvo == null) {
            mvo = new MemberVO();
            mvo.setUserid(kakaoProfile.getId());
            // mvo.setEmail(ac.getEmail());
            mvo.setEmail("kakao");
            mvo.setName(pf.getNickname());
            mvo.setProvider("kakao");
            mvo.setPwd("kakao");
            mvo.setPhone("");
            ms.insertMember(mvo);
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", mvo);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        return "redirect:/";
    }

    @GetMapping("/contract")
    public String contract() {
        return "member/contract";
    }

    @PostMapping("/joinForm")
    public String joinForm() {
        return "member/joinForm";
    }

    @GetMapping("/idcheckForm")
    public ModelAndView idcheckForm(@RequestParam("userid") String userid, Model model) {
        ModelAndView mav = new ModelAndView();
        MemberVO mvo = ms.getMember(userid);

        mav.addObject("result", mvo == null ? -1 : 1);
        mav.addObject("userid", userid);
        mav.setViewName("member/idcheck");

        return mav;
    }

    @PostMapping("/join")
    public String joinForm(@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult result,
                           @RequestParam(value = "reid", required = false) String reid,
                           @RequestParam(value = "pwdCheck", required = false) String pwdCheck, Model model) {
        String url = "member/joinForm";

        if (result.getFieldError("userid") != null) {
            model.addAttribute("message", result.getFieldError("userid").getDefaultMessage());
        } else if (result.getFieldError("pwd") != null) {
            model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
        } else if (result.getFieldError("name") != null) {
            model.addAttribute("message", result.getFieldError("name").getDefaultMessage());
        } else if (result.getFieldError("phone") != null) {
            model.addAttribute("message", result.getFieldError("phone").getDefaultMessage());
        } else if (result.getFieldError("email") != null) {
            model.addAttribute("message", result.getFieldError("email").getDefaultMessage());
        } else if (reid == null || (!reid.equals(membervo.getUserid()))) {
            model.addAttribute("message", "아이디 중복체크를 완료하세요");
        } else if (pwdCheck == null || (!pwdCheck.equals(membervo.getPwd()))) {
            model.addAttribute("message", " 비밀번호 확인이 일치하지 않습니다");
        } else {
            url = "member/login";
            model.addAttribute("message", "회원가입 완료");
            ms.insertMember(membervo);
        }
        return url;
    }

    @GetMapping("/updateMemberForm")
    public ModelAndView updateMemberForm() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("mypage/updateMemberForm");
        return mav;
    }

    @PostMapping("/updateMember")
    public String updateMember(@ModelAttribute("loginUser") @Valid MemberVO membervo, BindingResult result,
                           @RequestParam(value = "reid", required = false) String reid,
                           @RequestParam(value = "pwdCheck", required = false) String pwdCheck, Model model) {
        String url = "/updateMemberForm";

        if (result.getFieldError("userid") != null) {
            model.addAttribute("message", result.getFieldError("userid").getDefaultMessage());
        } else if (result.getFieldError("pwd") != null) {
            model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
        } else if (result.getFieldError("name") != null) {
            model.addAttribute("message", result.getFieldError("name").getDefaultMessage());
        } else if (result.getFieldError("phone") != null) {
            model.addAttribute("message", result.getFieldError("phone").getDefaultMessage());
        } else if (result.getFieldError("email") != null) {
            model.addAttribute("message", result.getFieldError("email").getDefaultMessage());
        } else if (reid == null || (!reid.equals(membervo.getUserid()))) {
            model.addAttribute("message", "아이디 중복체크를 완료하세요");
        } else if (pwdCheck == null || (!pwdCheck.equals(membervo.getPwd()))) {
            model.addAttribute("message", " 비밀번호 확인이 일치하지 않습니다");
        } else {
            url = "/";
            ms.updateMember(membervo);
        }
        return url;
    }
}
