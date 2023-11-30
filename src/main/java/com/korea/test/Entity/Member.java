package com.korea.test.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Member {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;
  
  public String userId;
  
  public String password;
  
  public String name;
  
  public String email;
  
  public LocalDateTime createDate;
  
  
  
}
