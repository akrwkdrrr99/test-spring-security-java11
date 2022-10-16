package com.example.hanghe.service;

import com.example.hanghe.base.dto.BaseResponseDto;
import com.example.hanghe.base.service.BaseService;
import com.example.hanghe.domain.Member;
import com.example.hanghe.dto.request.LoginRequestDto;
import com.example.hanghe.dto.request.MemberRequestDto;
import com.example.hanghe.dto.response.MemberResponseDto;
import com.example.hanghe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService extends BaseService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
//    private final TokenProvider tokenProvider;

    @Transactional
    public BaseResponseDto<?> createMember(MemberRequestDto requestDto) {
        if (null != isPresentMember(requestDto.getNickname())) {
            return BaseResponseDto.fail("DUPLICATED_NICKNAME",
                    "중복된 닉네임 입니다.");
        }

        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())) {
            return BaseResponseDto.fail("PASSWORDS_NOT_MATCHED",
                    "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        Member member = Member.builder()
                .nickname(requestDto.getNickname())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
        memberRepository.save(member);
        return BaseResponseDto.success(
                MemberResponseDto.builder()
                        .id(member.getId())
                        .nickname(member.getNickname())
                        .createdAt(member.getCreatedAt())
                        .modifiedAt(member.getModifiedAt())
                        .build()
        );
    }

    @Transactional
    public BaseResponseDto<?> login(LoginRequestDto requestDto, HttpServletResponse response) {
        Member member = isPresentMember(requestDto.getNickname());
        if (null == member) {
            return BaseResponseDto.fail("MEMBER_NOT_FOUND",
                    "사용자를 찾을 수 없습니다.");
        }

        if (!member.validatePassword(passwordEncoder, requestDto.getPassword())) {
            return BaseResponseDto.fail("INVALID_MEMBER", "사용자를 찾을 수 없습니다.");
        }

//        TokenDto tokenDto = tokenProvider.generateTokenDto(member);
//        tokenToHeaders(tokenDto, response);

        return BaseResponseDto.success(
                MemberResponseDto.builder()
                        .id(member.getId())
                        .nickname(member.getNickname())
                        .createdAt(member.getCreatedAt())
                        .modifiedAt(member.getModifiedAt())
                        .build()
        );
    }


    public BaseResponseDto<?> logout(HttpServletRequest request) {
//        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
//            return BaseResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
//        }
//        Member member = tokenProvider.getMemberFromAuthentication();
//        if (null == member) {
            return BaseResponseDto.fail("MEMBER_NOT_FOUND",
                    "사용자를 찾을 수 없습니다.");
//        }
//
//        return tokenProvider.deleteRefreshToken(member);
    }

    @Transactional(readOnly = true)
    public Member isPresentMember(String nickname) {
        Optional<Member> optionalMember = memberRepository.findByNickname(nickname);
        return optionalMember.orElse(null);
    }

//    public void tokenToHeaders(TokenDto tokenDto, HttpServletResponse response) {
//        response.addHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
//        response.addHeader("Refresh-Token", tokenDto.getRefreshToken());
//        response.addHeader("Access-Token-Expire-Time", tokenDto.getAccessTokenExpiresIn().toString());
//    }

}
