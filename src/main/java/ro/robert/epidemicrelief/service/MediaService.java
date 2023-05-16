package ro.robert.epidemicrelief.service;

import lombok.NonNull;
import ro.robert.epidemicrelief.model.Media;
import ro.robert.epidemicrelief.model.Product;

public interface MediaService {
    void addMedia(@NonNull Media media);

    void updateMedia(Media media, Product id);
}
