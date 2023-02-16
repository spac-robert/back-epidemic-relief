package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.ArrayList;
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
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Column
    @NotBlank(message = "Manufacturer is mandatory")
    private String manufacturer;

    @OneToMany(mappedBy = "product")
    private List<Media> media;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Lot> lots = new ArrayList<>();

    public Product(String name, Long price, Date expirationDate, String description, String manufacturer) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Product(String name, Long price, Date expirationDate, String description, String manufacturer, List<Media> media) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.manufacturer = manufacturer;
        this.media = media;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", media=" + media +
                '}';
    }
}
