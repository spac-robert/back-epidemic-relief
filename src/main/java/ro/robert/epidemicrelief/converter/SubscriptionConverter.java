package ro.robert.epidemicrelief.converter;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.dto.SubscriptionDTO;
import ro.robert.epidemicrelief.model.Subscription;
import ro.robert.epidemicrelief.utils.DateFormatter;

import java.text.ParseException;

@Component
public class SubscriptionConverter {
    @NonNull
    public SubscriptionDTO from(@NonNull Subscription source) {
        SubscriptionDTO target = new SubscriptionDTO();
        target.setId(source.getId());
        target.setDate(String.valueOf(source.getDate()));
        target.setUserId(source.getAccount().getId());
        return target;
    }

    @NonNull
    public Subscription to(@NonNull SubscriptionDTO source) {
        Subscription target = new Subscription();
        target.setId(source.getId());
        try {
            target.setDate(DateFormatter.dateFormatter(source.getDate()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return target;
    }
}
