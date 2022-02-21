package uz.pdp.task1.payload;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class WorkerDto {

  @NotNull(message = "Worker nomi bösh bölmasin")
  private String  name;

  @NotNull(message = "Telefon nomer bösh bölmasin")
  private String  phoneNumber;

  private Integer departmentId;

  private String  street;

  private String  homeNumber;

}
