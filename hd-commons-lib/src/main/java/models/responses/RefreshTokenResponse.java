package models.responses;

import lombok.Builder;

public record RefreshTokenResponse(
        String refreshToken
) {
}
