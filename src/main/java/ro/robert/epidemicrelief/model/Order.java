package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;
import lombok.*;
import ro.robert.epidemicrelief.enums.PaymentMethod;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "my_order", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NotBlank(message = "Name is mandatory")
    private String firstName;
    @Column
    @NotBlank(message = "Name is mandatory")
    private String lastName;
    @Column
    private Double totalPrice;
    @Column
    private String email;
    @Column
    private String cardNumber;
    @Column
    private String cardName;
    @Column
    private String address;
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();
}
