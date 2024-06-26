package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Worker {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(nullable = false)
   private String  name;

   @Column(unique = true,nullable = false)
   private String  phoneNumber;

   @OneToOne
   private Address address;

   @ManyToOne
   private Department department;
}
