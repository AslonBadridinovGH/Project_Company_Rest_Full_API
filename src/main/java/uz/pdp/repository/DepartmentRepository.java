package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
