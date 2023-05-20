package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PackageItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private PackageEntity packageEntity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    public PackageItem(PackageEntity packageEntity, Product product, Integer quantity) {
        this.packageEntity = packageEntity;
        this.product = product;
        this.quantity = quantity;
    }

//    @Override
//    public String toString() {
//        return "PackageItem{" +
//                "id=" + id +
//                ", packageEntityId=" + packageEntity.getId() +
//                ", productId=" + product.getId() +
//                ", quantity=" + quantity +
//                '}';
//    }
}