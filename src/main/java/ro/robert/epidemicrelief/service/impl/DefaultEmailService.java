package ro.robert.epidemicrelief.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.tomcat.util.codec.binary.Base64;
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
            String base64ImageData = "";
            byte[] imageBytes = null;
            Product product = productService.getById(productOrderDTO.getIdProduct());
            if (!product.getMedia().isEmpty()) {
                base64ImageData = Base64.encodeBase64String(product.getMedia().get(0).getData());
                imageBytes = product.getMedia().get(0).getData();
            }
            int imageWidth = 100;

            contentBuilder.append("<tr>");
            contentBuilder.append("<td style='border: 1px solid black;'>");
            contentBuilder.append("<img src='data:image/png;base64,").append(base64ImageData).append("' ");
            contentBuilder.append("alt='Image Not Found'").append(base64ImageData).append("' ");
            contentBuilder.append("style='width:").append(imageWidth).append("px;'/>");
            contentBuilder.append("</td>");
            contentBuilder.append("<td style='border: 1px solid black; text-align: center;'>").append(product.getName()).append("</td>");
            contentBuilder.append("<td style='border: 1px solid black; text-align: center;'>").append(productOrderDTO.getQuantity()).append("</td>");
            contentBuilder.append("<td style='border: 1px solid black; text-align: center;'>").append(product.getPrice()).append("</td>");
            contentBuilder.append("</tr>");

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
