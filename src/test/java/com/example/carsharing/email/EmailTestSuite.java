package com.example.carsharing.email;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmailTestSuite {

    @Test
    void shouldGetMailToSubjectAndAddress(){
        //given & when
        Mail mail = new Mail("email@email.com","subject","message");
        //then
        assertEquals("email@email.com",mail.getMailTo());
        assertEquals("subject",mail.getSubject());
        assertEquals("message",mail.getMessage());
    }

}
