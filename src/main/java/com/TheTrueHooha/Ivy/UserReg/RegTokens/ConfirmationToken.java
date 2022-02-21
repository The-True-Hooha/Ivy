package com.TheTrueHooha.Ivy.UserReg.RegTokens;

//class stores the token created in the database

import com.TheTrueHooha.Ivy.Users.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor


public class ConfirmationToken {

    @Id
    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )

    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdLocalDateTime; //java keyword that stores information about the data and time

    @Column(nullable = false)
    private LocalDateTime expiredLocalDateTime;

    private LocalDateTime confirmedAtLocalDateTime;

    @ManyToOne //declares that many user can have confirmation tokens
    @JoinColumn (
            nullable = false,
            name = "user_id"
    )
    private AppUser appUser;

    public ConfirmationToken (String token,
                             LocalDateTime createdLocalDateTime,
                             LocalDateTime expiredLocalDateTime,
                              AppUser appUser)
    {
        this.token = token;
        this.createdLocalDateTime = createdLocalDateTime;
        this.expiredLocalDateTime = expiredLocalDateTime;
        this.appUser = appUser;
    }
}
