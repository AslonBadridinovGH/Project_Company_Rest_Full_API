package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);


}
