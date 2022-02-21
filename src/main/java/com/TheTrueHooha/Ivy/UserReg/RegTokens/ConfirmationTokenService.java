package com.TheTrueHooha.Ivy.UserReg.RegTokens;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken (ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }
}
