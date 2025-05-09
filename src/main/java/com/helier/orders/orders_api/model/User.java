package com.helier.orders.orders_api.model;

import org.springframework.data.mongodb.core.mapping.Document;
import com.helier.orders.orders_api.model.enums.Role;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) 
public class User extends Base {
    @NotNull(message = "Password cannot be null")
    private String password;
    @NotNull(message = "Eemail cannot be null")
    private String email;
    private Role role;
}
