package uz.pdp.payload;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class WorkerDto {

  @NotNull(message = "worker name doesn't must be empty")
  private String  name;

  @NotNull(message = "phone name doesn't must be empty")
  private String  phoneNumber;

  private Integer departmentId;

  private String  street;

  private String  homeNumber;

}
