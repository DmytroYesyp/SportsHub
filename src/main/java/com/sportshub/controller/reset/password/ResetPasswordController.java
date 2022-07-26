package com.sportshub.controller.reset.password;


import com.sportshub.entity.user.User;
import com.sportshub.service.user.impl.UsersServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;


@Controller
public class ResetPasswordController {
    @Autowired
    Environment environment;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsersServiceImpl userService;

    public String getUrl(){
        return environment.getProperty("CLIENT_URL");
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<?> processForgotPassword(@RequestParam String email) {
        String token = RandomString.make(30);
        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = getUrl() + "reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
        } catch (UnsupportedEncodingException | MessagingException e) {

            return new ResponseEntity<>("Failed to send an email", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.loadUserByUsername(email), HttpStatus.OK);
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("sportshub.help@gmail.com", "SportsHub Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";
        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @PostMapping("/send_newsletter")
    public void sendEmail2(@RequestParam String email, @RequestParam String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("sportshub.help@gmail.com", "SportsHub");
        helper.setTo(email);
        String subject = "New article about your favourite league!";
        String content = "<p>Hello,</p>"
                + "<p>Check our site for new article about your favourite league</p>"
                + "<p>Click the link: " + link + "</p>";
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @PostMapping("/reset_password")
    public  ResponseEntity<String> processResetPassword(@RequestParam String reset_token, String newPassword, String confirmPassword) {
        if (confirmPassword.equals(newPassword)) {
            User user = userService.getByResetPasswordToken(reset_token);
            if (user != null) {
                userService.updatePassword(user, newPassword);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}