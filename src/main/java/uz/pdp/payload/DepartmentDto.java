package uz.pdp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDto {

   @NotNull(message = "department name doesn't must be empty")
   private String  name;

   private Integer companyId;

}
