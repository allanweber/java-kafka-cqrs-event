package com.allanweber.checkcode.github.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class User {

    private String login;

    private String avatar;

    private String repos;

    @JsonCreator
    public User(@JsonProperty("login") String login, @JsonProperty("avatar_url") String avatar, @JsonProperty("repos_url") String repos) {
        this.login = login;
        this.avatar = avatar;
        this.repos = repos;
    }
}
