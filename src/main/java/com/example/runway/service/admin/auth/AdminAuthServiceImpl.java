package com.example.runway.service.admin.auth;

import static com.example.runway.constants.CommonResponseStatus.*;
import static com.example.runway.constants.Constants.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.runway.convertor.UserConvertor;
import com.example.runway.domain.User;
import com.example.runway.dto.user.UserReq;
import com.example.runway.dto.user.UserRes;
import com.example.runway.exception.BadRequestException;
import com.example.runway.exception.NotFoundException;
import com.example.runway.jwt.TokenProvider;
import com.example.runway.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminAuthServiceImpl implements AdminAuthService{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	@Override
	public UserRes.Token logIn(UserReq.LoginUserInfo loginUserInfo) {
		User user=userRepository.findByUsername(loginUserInfo.getPhone()).orElseThrow(()->new NotFoundException(NOT_EXIST_USER));

		Long userId = user.getId();

		if(!passwordEncoder.matches(loginUserInfo.getPassword(),user.getPassword())){
			throw new NotFoundException(NOT_CORRECT_PASSWORD);
		}

		List<String> roles = Stream.of(user.getRole().split(",")).collect(Collectors.toList());

		if (!roles.contains("ROLE_USER")) {
			throw new BadRequestException(NOT_ADMIN);
		}
		UserRes.GenerateToken generateToken = createToken(userId);

		return new UserRes.Token(userId,generateToken.getAccessToken(),generateToken.getRefreshToken());
	}

	@Override
	public UserRes.DashBoardDto getUserDto() {
		LocalDate localDate = LocalDate.now();
		Long totalUser = userRepository.countBy();
		Long oneDayUser = userRepository.countByCreatedAtGreaterThanAndCreatedAtLessThan(LocalDateTime.parse(localDate+FIRST_TIME), LocalDateTime.parse(localDate+LAST_TIME));
		Long weekUser = userRepository.countByCreatedAtGreaterThanAndCreatedAtLessThan(LocalDateTime.parse(localDate.minusWeeks(1)+FIRST_TIME) , LocalDateTime.parse(localDate+LAST_TIME));
		Long monthUser = userRepository.countByCreatedAtGreaterThanAndCreatedAtLessThan(LocalDateTime.parse(localDate.with(
			TemporalAdjusters.firstDayOfMonth())+FIRST_TIME), LocalDateTime.parse(localDate.with(TemporalAdjusters.lastDayOfMonth())+LAST_TIME));

		return UserConvertor.ConvertUserSignUpInfo(oneDayUser,weekUser,monthUser,totalUser);
	}

	public UserRes.GenerateToken createToken(Long userId) {
		String accessToken=tokenProvider.createToken(userId);
		String refreshToken=tokenProvider.createRefreshToken(userId);
		return new UserRes.GenerateToken(accessToken,refreshToken);
	}
}
