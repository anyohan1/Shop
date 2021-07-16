package com.sharp.ing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharp.ing.repository.UserRepository;

@Service("UserService")
public class UserService {


	@Autowired
	private UserRepository userRepository;

	//id 중복체크
	public boolean checkUserIdDuplicate(String userId) {
		return userRepository.existsByUserId(userId);
	}

}
