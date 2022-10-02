package com.ayi.curso.rest.serv.app.dto.request.persons;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "PersonDTO",
        description = "Represents the data needed to created Persons"
)

public class PersonDTO implements Serializable {

    @ApiModelProperty(position = 4, notes = "Non negative value, The first name is required.")
    @NotNull //Filtramos antes que el valor no sea nulo para no llegar a la base y que la misma responda que no es posible.
    private String firstName;

    @ApiModelProperty(position = 5, notes = "Non negative value, The last name is required.")
    private String lastName;

    @ApiModelProperty(position = 7, notes = "Non negative value, The type document list is required.")
    private String typeDocument;

    @ApiModelProperty(position = 6, notes = "Non negative value, The number document is required.")
    private Integer numberDocument;

    @ApiModelProperty(position = 1, notes = "Non negative value, The Born date is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateBorn;

    @ApiModelProperty(position = 2, notes = "Date Created Person")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateCreated;

    @ApiModelProperty(position = 3, notes = "Date Modified Person")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateModified;




}
