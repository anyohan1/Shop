package com.sharp.ing.controller;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sharp.ing.domain.AuthenticationRequestDto;
import com.sharp.ing.domain.AuthenticationResponseDto;
import com.sharp.ing.domain.Member;
import com.sharp.ing.repository.UserRepository;
import com.sharp.ing.security.JwtUtilService;
import com.sharp.ing.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	private final BCryptPasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtUtilService jwtUtilService;
	private final UserService userService;

	// 회원가입
	@RequestMapping(value = "/join", method = RequestMethod.POST, consumes = "application/json; charset=utf-8")
	public String join(@RequestBody Map<String, String> member) throws Exception{
		return userRepository.save(Member.builder()
				.userId(member.get("userId"))
				.password(passwordEncoder.encode(member.get("password")))
				.role(Collections.singletonList("ROLE_USER")) // 가입시 USER로 기본 설정
				.family_count(member.get("family_count"))
				.build()).getUserId();
	}

	// 로그인
	@PostMapping("/login" )
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws Exception {
		Authentication authentication;
		try {
			// 인증처리
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUserId(), authenticationRequestDto.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("아이디 또는 비밀번호가 적절하지 않습니다.", e);
		}

		final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		final String jwt = jwtUtilService.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
	}

	//user 인증 확인
	@PostMapping("/user")
	public ResponseEntity<?> authenticate(HttpServletRequest request, Authentication authentication) {

		// 인증처리 성공 시
		// 세션을 유지하지 않는 Stateless 전략을 선택했기 때문에 인증처리가 수행되어도 세션에 인증데이터가 저장되지 않음
		HttpSession mySession = request.getSession();

		return ResponseEntity.ok("인증 성공");
	}

	//user id 확인
	@GetMapping("/member")
	@ResponseBody 
	public String currentUserName(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return userDetails.getUsername(); 
	}
	
	
	@GetMapping("/test")
	@ResponseBody 
	public String test(Authentication authentication, Model model) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		
		System.out.println(userService.checkUserIdDuplicate(userId));
		
		return ""+userService.checkUserIdDuplicate(userId);
	}

	//중복 확인
	@GetMapping("/idcheck/{userId}")
	public ResponseEntity<Boolean> checkUserIdDuplicate(@PathVariable String userId) {
		return ResponseEntity.ok(userService.checkUserIdDuplicate(userId));
	}

	
}
