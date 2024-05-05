package com.example.runway.repository;

import com.example.runway.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String phone);

    /*
    boolean existsByUsernameAndStatus(String userId, boolean b);

    boolean existsByNicknameAndStatus(String nickname, boolean b);

    Optional<User> findByUsernameAndStatus(String phone, boolean b);

    Optional<User> findByUsernameAndSocialAndStatus(String username, String social, boolean status);

    boolean existsByUsernameAndSocialAndStatus(String appleId, String apple, boolean b);

     */

    boolean existsByUsernameAndSocial(String toString, String kakao);

    boolean existsByNicknameAndIdNot(String nickname, Long id);

    Optional<User> findByUsername(String phone);

    boolean existsByUsername(String phone);

    Optional<User> findByUsernameAndSocial(String valueOf, String kakao);

    boolean existsByNickname(String nickname);

	Long countBy();

    Long countByCreatedAtGreaterThanAndCreatedAtLessThan(LocalDateTime parse, LocalDateTime parse1);
}
