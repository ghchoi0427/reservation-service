package com.cgh.inflearn.controller;

import com.cgh.inflearn.domain.Member;
import com.cgh.inflearn.dto.MemberLoginDto;
import com.cgh.inflearn.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping()
    public String home(@CookieValue(name = "memberId", required = false) String userId) {
        if (userId == null) {
            return "redirect:/home/login";
        }
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String signIn(MemberLoginDto dto, HttpServletResponse response) {
        if (!memberService.userNameAlreadyExists(dto.getUsername())) {
            return "redirect:/home/signup";
        }
        Member member = memberService.findByName(dto.getUsername());
        Cookie idCookie = new Cookie("memberId", member.getId().toString());
        response.addCookie(idCookie);
        if (!memberService.isMember(member.getId())) {
            return "redirect:/home/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String register(MemberLoginDto dto, HttpServletResponse response) {
        System.out.println(dto.getUsername() + dto.getUserPassword());
        Member member = new Member(dto.getUsername(), dto.getUserPassword());
        if (memberService.userNameAlreadyExists(dto.getUsername())) {
            return "redirect:/home/signup";
        }

        Cookie idCookie = new Cookie("memberId", member.getId().toString());
        idCookie.setPath("/");
        response.addCookie(idCookie);
        memberService.signUp(member);

        return "redirect:/home";
    }
}
