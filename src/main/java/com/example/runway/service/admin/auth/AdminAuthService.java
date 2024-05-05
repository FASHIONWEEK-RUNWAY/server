package com.example.runway.service.admin.auth;

import com.example.runway.dto.user.UserReq;
import com.example.runway.dto.user.UserRes;

public interface AdminAuthService {
	UserRes.Token logIn(UserReq.LoginUserInfo loginUserInfo);

	UserRes.DashBoardDto getUserDto();
}
