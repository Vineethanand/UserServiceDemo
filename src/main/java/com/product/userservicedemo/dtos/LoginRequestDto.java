package com.product.userservicedemo.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;
}
