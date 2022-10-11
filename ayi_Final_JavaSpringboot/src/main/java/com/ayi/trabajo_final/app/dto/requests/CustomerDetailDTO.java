package com.ayi.trabajo_final.app.dto.requests;

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
public class CustomerDetailDTO implements Serializable {
    //@ApiModelProperty(position = 1, notes = "Non negative value, The first name is required.")
    @NotNull
    //Filtramos antes que el valor no sea nulo para no llegar a la base y que la misma responda que no es posible.
    @NotEmpty
    @NotBlank
    private Boolean vip;

    //@ApiModelProperty(position = 2, notes = "Non negative value, The last name is required.")
    private Long totalPoints;

}
