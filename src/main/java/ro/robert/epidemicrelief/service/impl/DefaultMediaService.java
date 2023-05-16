package ro.robert.epidemicrelief.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.model.Media;
import ro.robert.epidemicrelief.model.Product;
import ro.robert.epidemicrelief.repository.MediaRepository;
import ro.robert.epidemicrelief.service.MediaService;

import java.util.Optional;

@Service
public class DefaultMediaService implements MediaService {

    private final MediaRepository mediaRepository;

    public DefaultMediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Override
    public void addMedia(@NonNull Media media) {
        mediaRepository.save(media);
    }

    @Override
    public void updateMedia(Media media, Product product) {
        Optional<Media> receivedMedia = mediaRepository.findByProduct(product);
        if (receivedMedia.isPresent()) {
            receivedMedia.get().setData(media.getData());
            mediaRepository.save(receivedMedia.get());
        }
    }
}
