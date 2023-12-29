package br.com.waldir.orderserviceapi.mapper;

import br.com.waldir.orderserviceapi.entities.Order;
import models.enums.OrderStatusEnum;
import models.requests.CreateOrderRequest;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    @Mapping(target = "createdAt", expression = "java(mapCreatedAt())")
    Order fromEntity(final CreateOrderRequest request);

    @Named("mapStatus")
    default OrderStatusEnum mapStatus(final String status) {
        return OrderStatusEnum.toEnum(status);
    }

    @Named("mapCreatedAt")
    default LocalDateTime mapCreatedAt(){
        return LocalDateTime.now();
    }

}
