package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "my_package")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "household_id")
    private Household household;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "package_product",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @Column
    private Date deliveredDate;

    @Column
    private LocalDateTime createdDate;

}