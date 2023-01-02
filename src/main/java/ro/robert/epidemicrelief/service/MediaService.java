package ro.robert.epidemicrelief.service;

import lombok.NonNull;
import ro.robert.epidemicrelief.model.Media;

public interface MediaService {
    void addMedia(@NonNull Media media);
}
