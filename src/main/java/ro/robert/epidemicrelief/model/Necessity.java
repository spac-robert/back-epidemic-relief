package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.robert.epidemicrelief.utils.PersonCategory;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Necessity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private PersonCategory personCategory;

    @Column
    @NotNull(message = "Quantity can't be null")
    @Min(value = 0, message = "Quantity should not be less then 0")
    private Long quantity;

    @OneToOne(mappedBy = "necessity", cascade = CascadeType.ALL)
    @Valid
    private Product product;

    public Necessity(PersonCategory personCategory, Long quantity, Product product) {
        this.personCategory = personCategory;
        this.quantity = quantity;
        this.product = product;
    }

    @Override
    public String toString() {
        return "Necessity{" +
                "id=" + id +
                ", personCategory=" + personCategory +
                ", quantity=" + quantity +
                ", productId=" + product.getId() +
                '}';
    }

}