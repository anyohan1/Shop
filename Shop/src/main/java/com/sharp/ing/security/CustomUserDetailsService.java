package com.sharp.ing.security;

import com.sharp.ing.domain.Member;
import com.sharp.ing.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Optional<Member> userOpt = userRepository.findByUserId(userId);
        Member member = userOpt.get();

        return new User(member.getUserId(), member.getPassword(), new ArrayList<>());
    }
}