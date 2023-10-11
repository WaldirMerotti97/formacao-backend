package models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;

@Builder
public record AuthenticationResponse(
    String token,
    String type
) {
}

