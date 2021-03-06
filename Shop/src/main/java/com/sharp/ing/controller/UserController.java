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

	// ????????????
	@RequestMapping(value = "/join", method = RequestMethod.POST, consumes = "application/json; charset=utf-8")
	public String join(@RequestBody Map<String, String> member) throws Exception{
		return userRepository.save(Member.builder()
				.userId(member.get("userId"))
				.password(passwordEncoder.encode(member.get("password")))
				.role(Collections.singletonList("ROLE_USER")) // ????????? USER??? ?????? ??????
				.family_count(member.get("family_count"))
				.build()).getUserId();
	}

	// ?????????
	@PostMapping("/login" )
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws Exception {
		Authentication authentication;
		try {
			// ????????????
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUserId(), authenticationRequestDto.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("????????? ?????? ??????????????? ???????????? ????????????.", e);
		}

		final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		final String jwt = jwtUtilService.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
	}

	//user ?????? ??????
	@PostMapping("/user")
	public ResponseEntity<?> authenticate(HttpServletRequest request, Authentication authentication) {

		// ???????????? ?????? ???
		// ????????? ???????????? ?????? Stateless ????????? ???????????? ????????? ??????????????? ??????????????? ????????? ?????????????????? ???????????? ??????
		HttpSession mySession = request.getSession();

		return ResponseEntity.ok("?????? ??????");
	}

	//user id ??????
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

	//?????? ??????
	@GetMapping("/idcheck/{userId}")
	public ResponseEntity<Boolean> checkUserIdDuplicate(@PathVariable String userId) {
		return ResponseEntity.ok(userService.checkUserIdDuplicate(userId));
	}

	
}
