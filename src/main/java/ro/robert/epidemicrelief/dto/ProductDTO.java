package ro.robert.epidemicrelief.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ProductDTO {

    private Integer id;
    private String name;
    private Long price;
    private String expirationDate;
    private String description;
    private String manufacturer;
    private MediaDTO mediaUrl;

    private MultipartFile media;
}