package com.TheTrueHooha.Ivy.UserReg;


import com.TheTrueHooha.Ivy.Users.AppUser;
import com.TheTrueHooha.Ivy.Users.AppUserRole;
import com.TheTrueHooha.Ivy.Users.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;


    //checks if the email is valid
    public String register(RegistrationRequest request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException ("please use a valid email");
        }
        return appUserService.signUpUser(new AppUser
                (
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber(), //TODO: haven't written a method to query and validate phone number
                AppUserRole.USER));
    }
}
