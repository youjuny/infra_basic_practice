package com.korea.test.Repository;

import com.korea.test.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
  
  Optional<Member> findByUserId(String username);
}
