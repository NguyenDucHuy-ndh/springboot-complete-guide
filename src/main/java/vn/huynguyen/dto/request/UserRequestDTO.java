package vn.huynguyen.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import vn.huynguyen.util.Gender;
import vn.huynguyen.util.UserType;
import vn.huynguyen.validator.EnumPattern;
import vn.huynguyen.validator.EnumValue;
import vn.huynguyen.validator.PhoneNumber;
import vn.huynguyen.util.UserStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
public class UserRequestDTO implements Serializable {

    @NotBlank(message = "First name is required")
    private String firstName;

    private String lastName;

    @Email
    private String email;

    //@Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "Phone number must be between 10 and 15 digits")
    @PhoneNumber
    private String phone;

    @NotNull(message = "dateOfBirth must be not null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date dateOfBirth;

    @NotNull(message = "username must be not null")
    private String username;

    @NotNull(message = "password must be not null")
    private String password;

    @EnumPattern(name = "status", regexp = "ACTIVE|INACTIVE|NONE")
    private UserStatus status;

    @EnumValue(name = "gender", enumClass = Gender.class)
    private Gender gender;

    @NotNull(message = "userType is required")
    @EnumValue(name = "type", enumClass = UserType.class)
    private UserType type;

    @NotEmpty(message = "addresses can not empty")
    private Set<AddressDTO> addresses;
}
