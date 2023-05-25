package ro.robert.epidemicrelief.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@ToString
@JsonIgnoreProperties("items")
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
    private String address;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();
}
