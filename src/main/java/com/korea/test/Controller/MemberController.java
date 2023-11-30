package com.korea.test.Controller;

import com.korea.test.Service.MemberService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@Builder
public class MemberController {
  private MemberService memberService;
  
  @GetMapping("/signup")
  public String signup() {
    
    return "signup_form";
    
  }
  
  @PostMapping("/signup")
  
  public String signup(String userId, String password, String email, String name) {
    
    memberService.save(userId,password,email,name);
    
    return "redirect:/";
    
  }
  
  @GetMapping("/login")
  public String login() {
    return "login_form";
  }
  
  @PostMapping("/login")
  public String login(String userId) {
    return "login_form";
  }
  
  
}
