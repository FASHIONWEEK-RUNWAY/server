package com.example.runway.controller;

import javax.validation.constraints.Min;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.runway.common.CommonResponse;
import com.example.runway.dto.PageResponse;
import com.example.runway.dto.user.UserReq;
import com.example.runway.dto.user.UserRes;
import com.example.runway.service.admin.auth.AdminAuthService;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminUserController {
	private final AdminAuthService adminAuthService;

	@PostMapping("/logIn")
	public CommonResponse<UserRes.Token> logIn(@RequestBody UserReq.LoginUserInfo loginUserInfo) {
		return CommonResponse.onSuccess(adminAuthService.logIn(loginUserInfo));
	}

	@GetMapping("/info")
	public CommonResponse<UserRes.DashBoardDto> getUserDto(){
		return CommonResponse.onSuccess(adminAuthService.getUserDto());
	}

	@GetMapping("")
	public CommonResponse<PageResponse<UserRes.UserInfoDto>> getUserInfo(
		@Parameter(description = "페이지", example = "0") @RequestParam(required = true) @Min(value = 0) Integer page,
		@Parameter(description = "페이지 사이즈", example = "10") @RequestParam(required = true)  Integer size){

		return null;
	}
}
