package ro.robert.epidemicrelief.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.dto.ProductOrderDTO;
import ro.robert.epidemicrelief.model.Product;
import ro.robert.epidemicrelief.service.EmailService;
import ro.robert.epidemicrelief.service.ProductService;

import java.util.List;

@Service
public class DefaultEmailService implements EmailService {

    private final JavaMailSender mailSender;
    private final ProductService productService;

    public DefaultEmailService(JavaMailSender mailSender, ProductService productService) {
        this.mailSender = mailSender;
        this.productService = productService;
    }

    @Override
    public void sendEmail(String to, String subject, List<ProductOrderDTO> products, String address) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("<html><body><p>").append(subject).append("</p><table style='border-collapse: collapse; border: 1px solid black;'>");
        contentBuilder.append("<tr><th style='border: 1px solid black;'>Product</th><th style='border: 1px solid black;'>Name</th><th style='border: 1px solid black;'>Quantity</th><th style='border: 1px solid black;'>Price</th></tr>");

        for (ProductOrderDTO productOrderDTO : products) {
            Product product = productService.getById(productOrderDTO.getIdProduct());
            contentBuilder.append("<tr>");
            contentBuilder.append("<td style='border: 1px solid black;'><img src='cid:image").append(product.getId()).append("'/></td>");
            contentBuilder.append("<td style='border: 1px solid black;'>").append(product.getName()).append("</td>");
            contentBuilder.append("<td style='border: 1px solid black;'>").append(productOrderDTO.getQuantity()).append("</td>");
            contentBuilder.append("<td style='border: 1px solid black;'>").append(product.getPrice()).append("</td>");
            contentBuilder.append("</tr>");

            byte[] imageBytes = product.getMedia().get(0).getData();
            if (imageBytes != null) {
                ByteArrayResource imageResource = new ByteArrayResource(imageBytes);
                helper.addInline("image", imageResource, "image/png");
            }
        }

        contentBuilder.append("</table><p>").append(address).append("</p></body></html>");

        helper.setText(contentBuilder.toString(), true);

        mailSender.send(message);
    }
}
