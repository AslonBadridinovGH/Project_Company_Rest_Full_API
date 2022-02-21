package uz.pdp.task1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDto {

   @NotNull(message = "Department name bösh bölmasin")
   private String  name;

   private Integer companyId;

}
