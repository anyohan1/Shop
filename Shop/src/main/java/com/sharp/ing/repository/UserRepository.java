package com.sharp.ing.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharp.ing.domain.Member;

public interface UserRepository extends JpaRepository<Member, Long>{
	
	 Optional<Member> findByUserId(String userId);
	 
	 boolean existsByUserId(String userId);
}