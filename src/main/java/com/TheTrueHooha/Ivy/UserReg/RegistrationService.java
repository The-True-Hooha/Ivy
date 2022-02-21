package com.TheTrueHooha.Ivy.UserReg;


import com.TheTrueHooha.Ivy.Email.EmailSender;
import com.TheTrueHooha.Ivy.UserReg.RegTokens.ConfirmationToken;
import com.TheTrueHooha.Ivy.UserReg.RegTokens.ConfirmationTokenService;
import com.TheTrueHooha.Ivy.Users.AppUser;
import com.TheTrueHooha.Ivy.Users.AppUserRole;
import com.TheTrueHooha.Ivy.Users.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;


    //checks if the email is valid
    public String register(RegistrationRequest request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException ("please use a valid email");
        }
        String token = appUserService.signUpUser(new AppUser
                (
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber(), //TODO: haven't written a method to query and validate phone number
                AppUserRole.USER));

        String emailLink = "http://localhost:8080" ;
        emailSender.send(request.getEmail(), request.getFirstName());
        return token;
    }

    //TODO: tried writing the method to confirm the tokens, will look into again
    /*
    @Transactional
    public  String confirmToken (String token) {
        ConfirmationToken confirmationToken = new ConfirmationTokenService.getToken(token).orElseThrow(() ->
                new IllegalStateException("this token is invalid"));

        if (confirmationToken.getConfirmedAtLocalDateTime() != null) {
            throw new IllegalStateException("this email has already been confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredLocalDateTime();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("sorry, your token has expired, try again!");
        }

        confirmationTokenService.setConfirmedAtLocalDateTime(token);
        appUserService.enableAppUser (
        confirmationToken.getAppUser().getEmail());
        return "email confirmed";
    }

     */
}
