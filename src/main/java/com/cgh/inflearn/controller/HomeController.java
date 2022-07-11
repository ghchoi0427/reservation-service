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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping()
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String signIn(MemberLoginDto dto, HttpServletResponse response) {
        Cookie idCookie;

        if (memberService.userNameAlreadyExists(dto.getUserName()) && memberService.findByName(dto.getUserName()).isPresent()) {
            Member member = memberService.findByName(dto.getUserName()).get();
            idCookie = new Cookie("memberId", member.getId().toString());
        } else {
            Member newInstance = new Member(dto.getUserName(), dto.getUserPassword());
            memberService.signUp(newInstance);
            idCookie = new Cookie("memberId", newInstance.getId().toString());
        }
        idCookie.setPath("/");
        response.addCookie(idCookie);

        return "redirect:/home";
    }
}
