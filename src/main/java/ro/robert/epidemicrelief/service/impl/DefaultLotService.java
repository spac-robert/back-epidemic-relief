package ro.robert.epidemicrelief.service.impl;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ro.robert.epidemicrelief.dto.ProductOrderDTO;
import ro.robert.epidemicrelief.model.Lot;
import ro.robert.epidemicrelief.repository.LotRepository;
import ro.robert.epidemicrelief.service.LotService;

import java.util.Date;
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
    public void updateLot(ProductOrderDTO productOrderDTO) {
        // Lot lot = lotRepository.findByProductIdOrderByExpirationDateAsc(productOrderDTO.getIdProduct()).get(0);
        List<Lot> lots = lotRepository.findByProductIdAndQuantityGreaterThanOrderByExpirationDateAsc(productOrderDTO.getIdProduct(), 0);
        if (!lots.isEmpty()) {
            Lot lot = lots.get(0);
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

    @Override
    public List<Lot> findLotsByExpirationDateLessThan(Date expirationDate) {
        return lotRepository.findLotsByExpirationDateLessThan(expirationDate);
    }

    @Override
    public void deleteAll(List<Lot> lots) {
        lotRepository.deleteAll(lots);
    }
}
