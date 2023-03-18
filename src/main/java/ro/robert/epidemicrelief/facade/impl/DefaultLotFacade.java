package ro.robert.epidemicrelief.facade.impl;

import org.springframework.stereotype.Component;
import ro.robert.epidemicrelief.dto.ProductOrder;
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
    public void updateLot(List<ProductOrder> products) {
        for(ProductOrder productOrder:products){
           lotService.updateLot(productOrder);
        }
    }
}
