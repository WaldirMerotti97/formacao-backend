package models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import models.enums.OrderStatusEnum;

public record CreateOrderRequest(
        @Schema(description = "Requester ID", example = "651775113eb1193aa91b1f08")
        @NotBlank(message = "The requesterId cannot be null or blank")
        @Size(min = 24, max = 36, message = "The requesterId must be 24 characters long")
        String requesterId,
        @Schema(description = "Costumer ID", example = "651775113eb1193aa91b1f08")
        @NotBlank(message = "The costumerId cannot be null or blank")
        @Size(min = 24, max = 36, message = "The costumerId must be 24 characters long")
        String costumerId,
        @Schema(description = "Title of order", example = "Fix my computer")
        @NotBlank(message = "The title cannot be null or blank")
        @Size(min = 3, max = 50, message = "The title must be between 3 and 50 characters")
        String title,
        @Schema(description = "Description of order", example = "My computer is broken")
        @NotBlank(message = "The description cannot be null or blank")
        @Size(min = 3, max = 50, message = "The description must be between 10 and 3000 characters")
        String description,
        @Schema(description = "Status of order", example = "OPEN")
        @NotBlank(message = "The status cannot be null or blank")
        @Size(min = 4, max = 15, message = "The status must be between 4 and 15 characters")
        OrderStatusEnum status
) {
}
