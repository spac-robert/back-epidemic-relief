package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalIdCache;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product", schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column
    private Float price;
    @Column
    @NotBlank(message = "Description is mandatory")
    private String description;
    @Column
    @NotBlank(message = "Manufacturer is mandatory")
    private String manufacturer;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Media> media;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Lot> lots = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<PackageProduct> packageProducts;
    @OneToOne(cascade = CascadeType.ALL)
    private Necessity necessity;
    //TODO de pus pe front necessity(pt admin)
    //TODO sa adaug la produs pe front din ce categorie face parte, vegan, lactate, etc.) si pe be

    public Product(String name, Float price, String description, String manufacturer) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Product(String name, Float price, String description, String manufacturer, List<Media> media) {
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
