package ro.robert.epidemicrelief.service;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}
