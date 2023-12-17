package models.responses;

import models.enums.OrderStatusEnum;

import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        String requesterId,
        String costumerId,
        String title,
        String description,
        OrderStatusEnum status,
        LocalDateTime createdAt,
        LocalDateTime closedAt
) {
}
