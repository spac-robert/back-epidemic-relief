package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "lot", schema = "public")
public class Lot {

    @Id
    private String id;
    @Column
    private Integer quantity;
    @Column
    private java.util.Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public String toString() {
        return "Lot{" +
                "id='" + id + '\'' +
                ", quantity=" + quantity +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
