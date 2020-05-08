package com.allanweber.checkcode.command.github.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRequest {
    private String login;
    private String avatar;
    private String repos;
}
