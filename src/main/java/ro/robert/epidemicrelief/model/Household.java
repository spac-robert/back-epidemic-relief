package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "household", schema = "public")
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Size(min = 2, max = 25, message
            = "Representative must be between 2 and 25 characters")
    @NotBlank(message = "Representative can't be null")
    @Pattern(regexp = "([a-zA-Z]+[ ]*)+", message = "Representative can't contain numbers")
    private String representative;

    @Column(name = "number_of_people")
    @Min(value = 1, message = "Number of people should not be less then 1")
    private Long numberOfPeople;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "household")
    @Valid
    private List<PackageEntity> packages;

    @Column
    @NotBlank(message = "Phone number can't be null")
    @Size(min = 10, max = 13, message
            = "Phone must be between 10 and 13 characters")
    private String phone;
    @Column
    @NotNull(message = "Number of children can't be null")
    @Min(value = 0, message = "Number of children should not be less then 0")
    private Long numberOfChildren;
    @Column
    @NotNull(message = "Number of vegans can't be null")
    @Min(value = 0, message = "Number of vegans should not be less then 0")
    private Long numberOfVegans;
    @Column
    @NotNull(message = "Number of non vegans can't be null")
    @Min(value = 0, message = "Number of non vegans should not be less then 0")
    private Long numberOfNonVegans;

    @Column
    @NotBlank(message = "Email address can't be blank")
    @Email(message = "Email should be valid")
    private String email;

    @Column
    @NotBlank(message = "Contact address can't be blank")
    @Size(min = 5, max = 150, message
            = "Contact address must be between 5 and 150 characters")
    private String contactAddress;

    //TODO country si city sa adaug
    @Column
    private String city;
    @Column
    private String county;

    public Household(String representative, Long numberOfPeople, String phone, Long numberOfChildren, Long numberOfVegans, Long numberOfNonVegans, String email, String contactAddress) {
        this.representative = representative;
        this.numberOfPeople = numberOfPeople;
        this.phone = phone;
        this.numberOfChildren = numberOfChildren;
        this.numberOfVegans = numberOfVegans;
        this.numberOfNonVegans = numberOfNonVegans;
        this.email = email;
        this.contactAddress = contactAddress;
    }

    @Override
    public String toString() {
        return "Household{" +
                "id=" + id +
                ", representative='" + representative + '\'' +
                ", numberOfPeople=" + numberOfPeople +
                ", packages=" + packages +
                ", phone='" + phone + '\'' +
                ", numberOfChildren=" + numberOfChildren +
                ", numberOfVegans=" + numberOfVegans +
                ", numberOfNonVegans=" + numberOfNonVegans +
                ", email='" + email + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                '}';
    }
}
