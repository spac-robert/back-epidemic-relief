package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "product", schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column
    private Long price;

    @Column
    private Integer stock;

    @Column
    private java.util.Date expirationDate;
    @Column
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column
    @NotBlank(message = "Manufacturer is mandatory")
    private String manufacturer;

    @OneToMany(mappedBy = "product")
    private List<Media> media;

    public Product(String name, Long price, Date expirationDate, String description, String manufacturer) {
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Product(String name, Long price, Date expirationDate, String description, String manufacturer, List<Media> media) {
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.description = description;
        this.manufacturer = manufacturer;
        this.media = media;
    }
}
