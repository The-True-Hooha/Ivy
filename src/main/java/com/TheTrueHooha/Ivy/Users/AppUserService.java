package com.TheTrueHooha.Ivy.Users;

import com.TheTrueHooha.Ivy.UserReg.RegTokens.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String message = "user with email %s not found";
    private final static String PHONE_NOT_FOUND = "user with phone %s not found"; //Haven't written the method that throws an exception when the user is not found

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException(String.format(message, email)));
    }

    //method that confirms the link to signUp
    public String signUpUser (AppUser appUser) {


        //validates that the user exists
        boolean confirmUser = userRepository.findByEmail(appUser.getEmail()).isPresent();

        if (confirmUser) {
            throw new IllegalStateException("this email has already been taken by another user");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        userRepository.save(appUser); //saves the user to the database

        // send confirmation token to validate email
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(3),
                appUser
        );

        return "everything is in working order";
    }
}
