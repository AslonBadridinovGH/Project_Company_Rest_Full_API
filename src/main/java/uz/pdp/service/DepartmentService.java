package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Company;
import uz.pdp.payload.ApiResponse;
import uz.pdp.entity.Department;
import uz.pdp.payload.DepartmentDto;
import uz.pdp.repository.CompanyRepository;
import uz.pdp.repository.DepartmentRepository;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse addDepartment(DepartmentDto departmentDto) {

          Department department = new Department();
        department.setName(departmentDto.getName());

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent()){
            return new ApiResponse("Such Company was not found",false);
        }

        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse(" Department was saved",true);
    }

    public List<Department> getDepartments(){
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(new Department());
    }

    public ApiResponse editDepartment(DepartmentDto departmentDto, Integer id) {

        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent()){
            return new ApiResponse("Such Department not found",false);
        }

         Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Such Company was not found",false);
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return new ApiResponse(" Department was edited",true);
    }

    public ApiResponse deleteDepartment(Integer id) {
        try {
            departmentRepository.deleteById(id);
            return new ApiResponse("Department was deleted ",true);
        }catch (Exception e){
            return new ApiResponse("Department was not deleted",false);
        }
    }


}
