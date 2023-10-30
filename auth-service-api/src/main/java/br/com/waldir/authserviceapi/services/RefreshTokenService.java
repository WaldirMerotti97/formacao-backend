package br.com.waldir.authserviceapi.services;

import br.com.waldir.authserviceapi.models.RefreshToken;
import br.com.waldir.authserviceapi.repositories.RefreshTokenRepository;
import br.com.waldir.authserviceapi.security.dtos.UserDetailsDTO;
import br.com.waldir.authserviceapi.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import models.exceptions.RefreshTokenExpiredException;
import models.exceptions.ResourceNotFoundException;
import models.responses.RefreshTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.expiration-sec.refresh-token}")
    private Long refreshTokenExpirationSec;

    private final RefreshTokenRepository repository;

    private final UserDetailsService userDetailsService;
    private final JWTUtils jwtUtils;

    public RefreshToken save(final String username) {
        return this.repository.save(RefreshToken.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusSeconds(refreshTokenExpirationSec))
                .username(username)
                .build()
        );
    }

    public RefreshTokenResponse refreshToken(final String refreshTokenId) {
        final var refreshToken = repository.findById(refreshTokenId)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found. Id " + refreshTokenId));

        if (refreshToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RefreshTokenExpiredException("Refresh token expired. Id: " + refreshTokenId);
        }

        return new RefreshTokenResponse(
                jwtUtils.generateToken(
                        (UserDetailsDTO) userDetailsService.loadUserByUsername(refreshToken.getUsername())
                )
        );
    }

}
