package com.sharp.ing.domain;

import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN", "관리자권한"),
	USER("ROLE_USER", "일반 사용자"),
	UNKNOWN("UNKNOWN", "알수없는 권한");;


	private String code;
	private String description;

	Role(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public static Role of(String code) {
		return Arrays.stream(Role.values())
				.filter(r -> r.getCode().equals(code))
				.findAny()
				.orElse(UNKNOWN);
	}
}