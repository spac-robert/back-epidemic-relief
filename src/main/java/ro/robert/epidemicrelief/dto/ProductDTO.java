package ro.robert.epidemicrelief.dto;

import lombok.Getter;
import lombok.Setter;
import ro.robert.epidemicrelief.model.Media;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class ProductDTO {

    private Integer id;
    private String name;
    private Long price;
    private Date expirationDate;
    private String description;
    private String manufacturer;
    private List<Media> media;
}