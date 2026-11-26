package com.growmighty.lectures.firstday.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
    // /var/run/xxx env var 추가 (git@github.com에 연결하기 위함: JGit => $SSH_AUTH_SOCK 환경 변수에 지정된 소켓 -> ssh-agent -> github 인증 요청에 서명)
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
