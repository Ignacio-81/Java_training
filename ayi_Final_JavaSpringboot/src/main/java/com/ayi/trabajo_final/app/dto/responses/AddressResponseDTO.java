package com.ayi.trabajo_final.app.dto.responses;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddressResponseDTO implements Serializable {

    private Long id;

    @ApiModelProperty(position = 1, notes = "Non negative value, The first name is required.")
    @NotNull
    @NotEmpty
    @NotBlank
    private String streetName;

    @ApiModelProperty(position = 2, notes = "Non negative value, The last name is required.")
    @NotNull
    @NotEmpty
    @NotBlank
    private Integer streetNumber;

    @ApiModelProperty(position = 3, notes = "Non negative value, The last name is required.")
    private Integer floor;
    @ApiModelProperty(position = 4, notes = "Non negative value, The last name is required.")
    private String apartmentLetter;
    @ApiModelProperty(position = 5, notes = "Non negative value, The last name is required.")
    @NotNull
    @NotEmpty
    @NotBlank
    private String postalCode;
    @ApiModelProperty(position = 6, notes = "Non negative value, The last name is required.")
    @NotNull
    @NotEmpty
    @NotBlank
    private String state;
    @ApiModelProperty(position = 7, notes = "Non negative value, The last name is required.")
    @NotNull
    @NotEmpty
    @NotBlank
    private String city;
    @ApiModelProperty(position = 8, notes = "Non negative value, The last name is required.")
    @NotNull
    @NotEmpty
    @NotBlank
    private String country;
}
