package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;
import lombok.*;
import ro.robert.epidemicrelief.enums.Role;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "user", schema = "public")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    private Household household;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Account(String username, String email, String password, Household household, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.household = household;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", household=" + household +
                ", role=" + role +
                '}';
    }
}
