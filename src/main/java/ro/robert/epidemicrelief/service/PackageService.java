package ro.robert.epidemicrelief.service;

import lombok.NonNull;
import ro.robert.epidemicrelief.dto.OrderDTO;

public interface PackageService {

    @NonNull OrderDTO subscription(Long userId);
}
