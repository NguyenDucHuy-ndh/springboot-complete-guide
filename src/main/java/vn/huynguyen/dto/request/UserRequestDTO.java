package vn.huynguyen.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import vn.huynguyen.util.Gender;
import vn.huynguyen.util.UserType;
import vn.huynguyen.validator.EnumPattern;
import vn.huynguyen.validator.EnumValue;
import vn.huynguyen.validator.PhoneNumber;
import vn.huynguyen.util.UserStatus;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
public class UserRequestDTO implements Serializable {

    @NotBlank(message = "First name is required")
    private String firstName;

    private String lastName;
    private String email;

    //@Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "Phone number must be between 10 and 15 digits")
    @PhoneNumber
    private String phone;

    @EnumPattern(name = "status", regexp = "ACTIVE|INACTIVE|NONE")
    private UserStatus status;

    @EnumValue(name = "gender", enumClass = Gender.class)
    private Gender gender;

    @EnumValue(name = "userType", enumClass = UserType.class)
    private UserType userType;
}
