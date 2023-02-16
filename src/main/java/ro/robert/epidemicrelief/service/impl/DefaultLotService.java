package ro.robert.epidemicrelief.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.model.Lot;
import ro.robert.epidemicrelief.repository.LotRepository;
import ro.robert.epidemicrelief.service.LotService;

@Service
public class DefaultLotService implements LotService {
    private final LotRepository lotRepository;

    public DefaultLotService(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
    }

    @Override
    public void addLot(@NonNull Lot lot) {
        lotRepository.save(lot);
    }
}
