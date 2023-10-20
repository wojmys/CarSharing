package com.example.carsharing.email;

import com.example.carsharing.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private static final String SUBJECT = "Tasks: Once a day email";

    private final SimpleEmailService simpleEmailService;

//    @Scheduled(cron = "0 0 10 * * *")
//    @Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {

        simpleEmailService.send(
                new Mail(
                        "wojmys@gmail.com",
                        SUBJECT,
                        "Hello! We have an extra offer for you !"
                ));
    }
}
