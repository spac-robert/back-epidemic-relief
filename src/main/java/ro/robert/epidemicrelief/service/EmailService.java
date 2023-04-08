package ro.robert.epidemicrelief.service;

import jakarta.mail.MessagingException;
import ro.robert.epidemicrelief.dto.ProductOrderDTO;

import java.util.List;

public interface EmailService {
    void sendEmail(String to, String subject, List<ProductOrderDTO> products,String address) throws MessagingException;
}
