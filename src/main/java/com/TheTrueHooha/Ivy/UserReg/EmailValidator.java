package com.TheTrueHooha.Ivy.UserReg;


import org.springframework.stereotype.Service;

import java.util.function.Predicate;


//class that validates the email before allowing the user to sign up
@Service
public class EmailValidator implements Predicate <String> {
    @Override
    public boolean test(String s) {
        //TODO: Regex to validate email
        return true;
    }
}
