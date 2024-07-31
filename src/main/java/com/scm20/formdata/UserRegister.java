package com.scm20.formdata;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserRegister {

   // @NotBlank(message = "user name is required")
    @Size( min = 3 , message = "minimum 3 characters required")
    private String name;

    // @NotBlank(message = "user email is required")
   // @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "invalid user email")
    private String email;

  //  @NotBlank(message = "phone number is required")
    //@Size(min = 10 , message = "use 10 digits only")  
    private String phoneNumber;
     
   // @Size(min = 6 , message = "minimum 6 characters are required")
    private String password;

    //@NotBlank(message = "about cannot be empty")
    private String about;
}
