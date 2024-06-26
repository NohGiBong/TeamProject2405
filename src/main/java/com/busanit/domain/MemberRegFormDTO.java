package com.busanit.domain;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class MemberRegFormDTO {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
//    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
    private String password;

//    @Min(6)
//    @Max(6)
    @NotEmpty(message = "필수 입력 값입니다.")
    private String age;

    private boolean social;

    private Integer grade_code;

    private Boolean checkedTermsE;

    private Boolean checkedTermsS;
}

/*
    NotEmpty - NULL 체크 및 문자열의 경우 길이 0인지 검사
    NotBlank - NULL 체크 및 문자열의 경우 0 및 빈 문자열(" ") 검사
    Length(min=, max=) - 최소, 최대 길이 검사
    Email - 이메일 형식인지 검사
    Max(숫자) - 지정한 값보다 작은지 검사
    Min(숫자) - 지정한 값보다 큰지 검사
    Null - 값이 NULL인지 검사
    NotNull - 값이 NULL이 아닌지 검사

 */
