package com.nisum.api.users.infraestructure.rest.dto.request;

import com.nisum.api.users.infraestructure.springdata.entity.PhoneEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID id;

    @NotEmpty(message = "El nombre es requerido.")
    private String name;

    @NotEmpty(message = "El email es requerido.")
    @Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$",
            message = "El email no es valido.")
    private String email;

    @NotBlank(message = "La contraseña es requerida.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}$",
            message = "La contraseña no es valida. " +
                    "La contraseña debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula," +
                    " al menos una mayúscula y al menos un caracter no alfanumérico.")
    private String password;

    private List<PhoneDto> phones = new ArrayList<>();

}
