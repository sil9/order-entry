package com.netcracker.selyutin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @PostMapping("/login")
    public ModelAndView login(HttpSession session, HttpServletRequest request) {
        String mail = request.getParameter("mail");
        session.setMaxInactiveInterval(5999);
        session.setAttribute("mail", mail);
        return new ModelAndView("redirect:/operations");
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

}
