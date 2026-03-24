package com.gv.dpal.notification.service;

import com.gv.dpal.account.event.AccountCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @RetryableTopic(attempts = "4", backOff = @BackOff(delay = 1000, multiplier = 2, maxDelay = 8000))
    @KafkaListener(topics = "account-created")
    public void listenForAccountCreated(AccountCreatedEvent accountCreatedEvent){
        log.info("Got message from account-created topic {}", accountCreatedEvent);

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springshop@email.com");
            messageHelper.setTo(accountCreatedEvent.getEmail());
            messageHelper.setSubject(
                    String.format("Your Account with AccountNumber %s is created successfully",
                            accountCreatedEvent.getAccountId())
            );
            messageHelper.setText(String.format("""
                            Dear Sir/Madam,

                            Your account with account number %s is now created successfully.

                            Best Regards
                            Spring Bank
                            """,
                    accountCreatedEvent.getAccountId()
            ));
        };

        try {
            javaMailSender.send(messagePreparator);
            log.info("Notification email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new RuntimeException("Exception occurred when sending mail", e);
        }
    }

    @DltHandler
    public void listenForAccountCreatedDLT(AccountCreatedEvent accountCreatedEvent){

    }

}