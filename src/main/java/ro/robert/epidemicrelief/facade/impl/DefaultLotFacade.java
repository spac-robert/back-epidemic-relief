package ro.robert.epidemicrelief.facade.impl;

import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.dto.ProductOrderDTO;
import ro.robert.epidemicrelief.facade.LotFacade;
import ro.robert.epidemicrelief.service.LotService;

import java.util.List;

@Component(value = "lotFacade")
public class DefaultLotFacade implements LotFacade {

    private final LotService lotService;

    public DefaultLotFacade(LotService lotService) {
        this.lotService = lotService;
    }

    @Override
    public void updateLot(List<ProductOrderDTO> products) {
        for(ProductOrderDTO productOrderDTO :products){
           lotService.updateLot(productOrderDTO);
        }
    }
}
