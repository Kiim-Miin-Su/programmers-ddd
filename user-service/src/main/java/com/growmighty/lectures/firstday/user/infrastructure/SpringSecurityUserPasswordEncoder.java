package com.growmighty.lectures.firstday.user.infrastructure;

import com.growmighty.lectures.firstday.user.application.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurityPasswordEncoder implements PasswordEncoder {
    /*
    @Override
    public String encode(String rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashed = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashed);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 알고리즘을 사용할 수 없습니다.", e);
        }
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new SpringSecurityPasswordEncoder();
    }

}
