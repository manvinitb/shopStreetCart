package com.shopstreet.backend.cart.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailClient {
    static StringBuilder builder = new StringBuilder();
    static String emailMessage;

    static {
        builder.append("Hi Amigo!\n\n");
        builder.append("Your order with order id: %d has been successfully placed.\n");
        builder.append("Thanks for choosing shopStreet!!\n\n");
        builder.append("Cheers :)");
        emailMessage = builder.toString();
    }

    @Autowired
    private JavaMailSender sender;

    public void sendEmail(Long orderId, String emailId) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(emailId);
        helper.setText(String.format(emailMessage, orderId));
        helper.setSubject("Order Confirmation!!");
        sender.send(message);
    }
}
