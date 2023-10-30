package models.exceptions;

import models.responses.RefreshTokenResponse;

public class RefreshTokenExpiredException extends RuntimeException{
    public RefreshTokenExpiredException(String message){
        super(message);
    }
}
