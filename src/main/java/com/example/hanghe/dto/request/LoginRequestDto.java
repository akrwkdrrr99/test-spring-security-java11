package com.example.hanghe.dto.request;

import com.example.hanghe.base.dto.BaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto extends BaseRequestDto {
    private String nickname;
    private String password;
}
