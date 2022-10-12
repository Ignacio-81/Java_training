package com.ayi.trabajo_final.app.dto.requests;

import com.ayi.trabajo_final.app.entities.CustomerDetailEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "CustomerDTO",
        description = "Represents the data needed to created Customers"
)

public class CustomerDTO implements Serializable {

    @ApiModelProperty(position = 1, notes = "Non negative value, The first name is required.")
    @NotNull //Filtramos antes que el valor no sea nulo para no llegar a la base y que la misma responda que no es posible.
    @NotEmpty
    @NotBlank
    private String firstName;

    @ApiModelProperty(position = 2, notes = "Non negative value, The last name is required.")
    @NotNull
    @NotEmpty
    @NotBlank
    private String lastName;

    @ApiModelProperty(position = 3, notes = "Non negative value, The number document is required.")
    @NotNull
    @NotEmpty
    @NotBlank
    private Integer documentNumber;

    @ApiModelProperty(position = 4, notes = "Non negative value, The number document is required.")
    public CustomerDetailDTO customerDetailDTO;

    @ApiModelProperty(position = 5, notes = "Non negative value, The number document is required.")
    public AddressDTO addressDTO;


    public CustomerDTO(String firstName, String lastName, Integer documentNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
    }

}
