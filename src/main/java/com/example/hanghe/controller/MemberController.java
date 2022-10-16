package com.example.hanghe.controller;

import com.example.hanghe.base.controller.BaseController;
import com.example.hanghe.base.dto.BaseResponseDto;
import com.example.hanghe.dto.request.LoginRequestDto;
import com.example.hanghe.dto.request.MemberRequestDto;
import com.example.hanghe.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberController extends BaseController {

    private final MemberService memberService;

    @PostMapping(value = "/member/signup")
    public BaseResponseDto<?> signup(@RequestBody MemberRequestDto requestDto) {
        return memberService.createMember(requestDto);
    }

    @PostMapping(value = "/member/login")
    public BaseResponseDto<?> login(@RequestBody LoginRequestDto requestDto,
                                HttpServletResponse response
    ) {
        return memberService.login(requestDto, response);
    }

    @PostMapping(value = "/auth/member/logout")
    public BaseResponseDto<?> logout(HttpServletRequest request) {
        return memberService.logout(request);
    }
}
