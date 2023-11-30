package com.korea.test.Service;

import com.korea.test.Entity.Member;
import com.korea.test.Repository.MemberRepository;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Builder
public class MemberService {
  private MemberRepository memberRepository;
  private PasswordEncoder passwordEncoder;
  
  
  public void save(String userId, String password, String email, String name) {
    Member member = new Member();
    
    member.setUserId(userId);
    member.setPassword(passwordEncoder.encode(password));
    member.setEmail(email);
    member.setName(name);
    member.setCreateDate(LocalDateTime.now());
    
    memberRepository.save(member);
  }
}
