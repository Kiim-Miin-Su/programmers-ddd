package com.growmighty.lectures.firstday.order;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "com.growmighty.lectures.firstday.order",
    "com.growmighty.lectures.firstday.command"
})
public class OrderServiceApplication {
    public static void main(String[] args) {

    }
}
