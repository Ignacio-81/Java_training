package com.ayi.trabajo_final.app.dto.responses;

import com.ayi.trabajo_final.app.dto.requests.AddressDTO;
import com.ayi.trabajo_final.app.dto.requests.CustomerDetailDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel(
        value = "CustomerResponseDTO",
        description = "Represents the data needed to created Customers"
)
public class CustomerResponseDTO implements Serializable {

    private Long idCustomer;

    @ApiModelProperty(position = 1, notes = "Non negative value, The first name is required.")
    @NotNull
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

    @ApiModelProperty(position = 4, notes = "Date Created Customer")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateCreated;

    @ApiModelProperty(position = 5, notes = "Date Modified Customer")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateModified;

    public CustomerDetailResponseDTO customerDetailResponseDTO;

    public List<AddressResponseDTO> addressResponseDTO = new ArrayList<>();
    public CustomerResponseDTO(String firstName, String lastName, Integer documentNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
    }
}