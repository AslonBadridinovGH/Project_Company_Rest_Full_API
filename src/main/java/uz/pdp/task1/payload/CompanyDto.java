package uz.pdp.task1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CompanyDto {


 @NotNull(message = "corpName bösh bölmasin")
 private String corpName;

 private String directorName;

 private String street;

 private String homeNumber;

}
