package ro.robert.epidemicrelief.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.dto.ProductOrderDTO;
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

    @Override
    public void updateLot(ProductOrderDTO productOrderDTO) {
        Lot lot = lotRepository.findByProductIdOrderByExpirationDateAsc(productOrderDTO.getIdProduct()).get(0);
        if (lot.getQuantity() - productOrderDTO.getQuantity() > 0) {
            lot.setQuantity(lot.getQuantity() - productOrderDTO.getQuantity());
            lotRepository.save(lot);
        } else if (lot.getQuantity() - productOrderDTO.getQuantity() == 0) {
            lotRepository.delete(lot);
        } else {
            throw new ArithmeticException("lot don't have enough products");
        }
    }
}
