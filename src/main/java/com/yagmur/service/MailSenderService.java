package com.yagmur.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender javaMailSender;

    @Value("${mail.host}") //springframeworkten anatosyon alınıyo
    private String mailHost = "${mail.host}";
    public void sendMail(String activationCode, String token, String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("kirazyagmur7@gmail.com");
        mailMessage.setTo(email); // burasi development tamamlandiginda kullanici mailiyle degistirilecek.
        mailMessage.setSubject("TATİL BURADA SEN NEREDESİN?...");
        mailMessage.setText("Merhaba aramıza hoşgeldin!\n"+"Holidaydeki hesabınızı doğrulamak için linke tıklayınız...: \n"
                +"http://"+mailHost+":8080/auth/activation?token="+token+"&activationCode="+activationCode);
        javaMailSender.send(mailMessage);
    }


}
