package ro.robert.epidemicrelief.converter;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ro.robert.epidemicrelief.dto.MediaDTO;
import ro.robert.epidemicrelief.model.Media;

import java.io.IOException;

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
    public Media to(@NonNull MultipartFile source) throws IOException {
        return new Media(source.getOriginalFilename(), source.getBytes(), source.getContentType());
    }
}
