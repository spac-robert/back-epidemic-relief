package ro.robert.epidemicrelief.converter;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.dto.MediaDTO;
import ro.robert.epidemicrelief.model.Media;

@Component
public class MediaConverter {
    @NonNull
    public MediaDTO from(@NonNull Media source) {

        MediaDTO target = new MediaDTO();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setData(source.getData());
        return target;
    }

    @NonNull
    public Media to(@NonNull MediaDTO source) {
        Media target = new Media();
        target.setName(source.getName());
        target.setData(source.getData());

        return target;
    }
}
