package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private List<PackageItem> packageItems = new ArrayList<>();
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
        String mediaId = (media != null && !media.isEmpty()) ? String.valueOf(media.get(0).getId()) : "N/A";
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", mediaId=" + mediaId +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
        result = prime * result + ((media == null) ? 0 : media.hashCode());
        result = prime * result + ((lots == null) ? 0 : lots.hashCode());
        result = prime * result + ((packageItems == null) ? 0 : packageItems.hashCode());
        return result;
    }
}
