package models.responses;

import lombok.Builder;
import lombok.With;

@Builder
public record AuthenticationResponse(
        String token,
        @With
        String refreshToken,
        String type
) {
}

