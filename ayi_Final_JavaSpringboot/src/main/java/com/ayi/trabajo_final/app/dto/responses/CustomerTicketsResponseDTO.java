package com.ayi.trabajo_final.app.dto.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel(
        value = "CustomerTicketsResponseDTO",
        description = "Represents the data Response for Customers With Tickets"
)
public class CustomerTicketsResponseDTO implements Serializable {

    private Long idTicket;

    @ApiModelProperty(position = 1, notes = "Non negative value, The first name is required.")
    @NotNull
    @NotEmpty
    @NotBlank
    private String description;

    @NotNull
    @NotEmpty
    @NotBlank
    @ApiModelProperty(position = 2, notes = "Non negative value, The last name is required.")
    private Double total;


}
