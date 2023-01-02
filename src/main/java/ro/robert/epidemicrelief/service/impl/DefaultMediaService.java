package ro.robert.epidemicrelief.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.model.Media;
import ro.robert.epidemicrelief.repository.MediaRepository;
import ro.robert.epidemicrelief.service.MediaService;

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
}
