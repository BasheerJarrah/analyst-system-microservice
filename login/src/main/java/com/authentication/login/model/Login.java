package com.authentication.login.model;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@Setter
@Getter
public class Login {
    private String username;
    private String password;
}
