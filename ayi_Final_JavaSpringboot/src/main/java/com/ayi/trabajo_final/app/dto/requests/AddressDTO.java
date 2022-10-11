package com.ayi.trabajo_final.app.dto.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
/*
@ApiModel(
        value = "CustomerDTO",
        description = "Represents the data needed to created Customers"
)
*/
public class AddressDTO implements Serializable {

    @ApiModelProperty(position = 1, notes = "Non negative value, The first name is required.")
    @NotNull
    //Filtramos antes que el valor no sea nulo para no llegar a la base y que la misma responda que no es posible.
    @NotEmpty
    @NotBlank
    private String streetName;

    @ApiModelProperty(position = 2, notes = "Non negative value, The last name is required.")
    private Integer streetNumber;

    @ApiModelProperty(position = 3, notes = "Non negative value, The last name is required.")
    private String floor;
    @ApiModelProperty(position = 4, notes = "Non negative value, The last name is required.")
    private Integer floorNumber;
    @ApiModelProperty(position = 5, notes = "Non negative value, The last name is required.")
    private String postalCode;
    @ApiModelProperty(position = 6, notes = "Non negative value, The last name is required.")
    private String state;
    @ApiModelProperty(position = 7, notes = "Non negative value, The last name is required.")
    private String city;
    @ApiModelProperty(position = 8, notes = "Non negative value, The last name is required.")
    private String country;
}
