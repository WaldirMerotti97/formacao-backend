package br.com.waldir.orderserviceapi.services.impl;

import br.com.waldir.orderserviceapi.mapper.OrderMapper;
import br.com.waldir.orderserviceapi.repositories.OrderRepository;
import br.com.waldir.orderserviceapi.services.OrderService;
import lombok.RequiredArgsConstructor;
import models.requests.CreateOrderRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;

    @Override
    public void save(CreateOrderRequest request) {
        repository.save(mapper.fromEntity(request));

    }
}
