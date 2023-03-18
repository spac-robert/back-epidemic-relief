package ro.robert.epidemicrelief.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.dto.ProductOrder;
import ro.robert.epidemicrelief.model.Lot;
import ro.robert.epidemicrelief.repository.LotRepository;
import ro.robert.epidemicrelief.service.LotService;

import java.util.List;

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
    public void updateLot(ProductOrder productOrder) {
        Lot lot = lotRepository.findByProductIdOrderByExpirationDateAsc(productOrder.getIdProduct()).get(0);
        if (lot.getQuantity() - productOrder.getQuantity() > 0) {
            lot.setQuantity(lot.getQuantity() - productOrder.getQuantity());
            lotRepository.save(lot);
        } else if (lot.getQuantity() - productOrder.getQuantity() == 0) {
            lotRepository.delete(lot);
        } else {
            throw new ArithmeticException("lot don't have enough products");
        }
    }
}
