package uz.pdp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CompanyDto {


 @NotNull(message = "corp name does not must be empty")
 private String corpName;

 private String directorName;

 private String street;

 private String homeNumber;

}
