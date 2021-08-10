package com.study.projectimageserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequestDto {

    @NotBlank(message = "ID는 필수 입력 값입니다.")
    private String username;

    @NotBlank(message = "PW는 필수 입력 값입니다.")
    private String password;
    private String passwordCheck;
    private boolean admin = false;
    private String adminToken = "";
}