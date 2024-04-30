package uz.pdp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Address;
import uz.pdp.entity.Company;
import uz.pdp.payload.ApiResponse;
import uz.pdp.payload.CompanyDto;
import uz.pdp.repository.AddressRepository;
import uz.pdp.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public ApiResponse addCompany(CompanyDto companyDto) {

          Company company=new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());

          Address address=new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);
        company.setAddress(savedAddress);
        companyRepository.save(company);
        return new ApiResponse("Company was added",true);

    }

    public List<Company> getCompanies(){
        List<Company> companyList = companyRepository.findAll();
        return companyList;
    }

    public Company getCompanyById(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(new Company());
    }

    public ApiResponse editCompany(CompanyDto companyDto, Integer id) {
         Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company was not found",false);

         Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());

         Address address1 = company.getAddress();
        address1.setHomeNumber(companyDto.getHomeNumber());
        address1.setStreet(companyDto.getStreet());
         Address savedAddress1 = addressRepository.save(address1);
        company.setAddress(savedAddress1);
        companyRepository.save(company);
        return new ApiResponse("Company was edited",true);

    }


    public ApiResponse deleteCompany(Integer id) {
        try {
            companyRepository.deleteById(id);
            return new ApiResponse("Company was deleted",true);
        }catch (Exception e){
            return new ApiResponse("Company was not deleted",false);
        }
    }
}
