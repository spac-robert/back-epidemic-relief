package ro.robert.epidemicrelief.converter;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.dto.LotDTO;
import ro.robert.epidemicrelief.model.Lot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LotConverter {

    @NonNull
    public LotDTO from(@NonNull Lot source) {

        LotDTO target = new LotDTO();
        target.setId(source.getId());
        target.setExpirationDate(String.valueOf(source.getExpirationDate()));
        target.setQuantity(source.getQuantity());
        return target;
    }

    @NonNull
    public Lot to(@NonNull LotDTO source) {
        Lot target = new Lot();
        target.setId(source.getId());
        target.setQuantity(source.getQuantity());
        try {
            target.setExpirationDate(dateFormatter(source.getExpirationDate()));
        } catch (ParseException e) {
            System.out.println("Invalid date format!");
        }
        return target;
    }

    private Date dateFormatter(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(date);
    }

}
