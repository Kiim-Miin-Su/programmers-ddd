package com.growmighty.lectures.firstday.user.application;

public interface UserPasswordEncoder {
    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
