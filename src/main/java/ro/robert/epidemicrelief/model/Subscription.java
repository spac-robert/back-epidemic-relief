package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscription", schema = "public")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private java.util.Date date;
    @Column
    private Boolean isSubscribed;
    @Column
    private Boolean sent;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account account;

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", date=" + date +
                ", isSubscribed=" + isSubscribed +
                ", sent=" + sent +
                ", accountId=" + account.getId() +
                '}';
    }
}
