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

    @OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL)
    private List<PackageProduct> packageProducts = new ArrayList<>();

    @Column
    private Date deliveredDate;

    @Column
    private LocalDateTime createdDate;

}