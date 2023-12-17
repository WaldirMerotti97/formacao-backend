package br.com.waldir.orderserviceapi.repositories;

import br.com.waldir.orderserviceapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
