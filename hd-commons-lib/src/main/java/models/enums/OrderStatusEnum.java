package models.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum OrderStatusEnum {

    OPEN("Open"),
    IN_PROGRESS("In progress"),
    CLOSED("Closed"),
    CANCELLED("Cancelled");

    @Getter
    private final String description;

    public static OrderStatusEnum toEnum(String description) {
        return Arrays.stream(OrderStatusEnum.values())
                .filter(orderStatusEnum -> orderStatusEnum.getDescription().equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid description" + description));
    }


}
