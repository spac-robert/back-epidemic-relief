package ro.robert.epidemicrelief.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

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

    public Subscription(Subscription sub) {
        this.date = sub.getDate();
        this.isSubscribed = sub.getIsSubscribed();
        this.sent = sub.getSent();
        this.account = sub.getAccount();
    }

    public void setDateForNextWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        date = calendar.getTime();
    }
}
