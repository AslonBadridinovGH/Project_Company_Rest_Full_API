package uz.pdp.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Address;
import uz.pdp.task1.entity.Company;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.payload.CompanyDto;
import uz.pdp.task1.repository.AddressRepository;
import uz.pdp.task1.repository.CompanyRepository;

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
        return new ApiResponse("Company added",true);
    }


    public List<Company> getCompanies(){
        List<Company> companyList = companyRepository.findAll();
        return companyList;
    }

    public Company getCompanyById( Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    public ApiResponse editCompany(CompanyDto companyDto, Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("Company not found",false);
        Company company = optionalCompany.get();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());

        Address address1 = company.getAddress();
        address1.setHomeNumber(companyDto.getHomeNumber());
        address1.setStreet(companyDto.getStreet());
        Address savedAddress1 = addressRepository.save(address1);
        company.setAddress(savedAddress1);
        companyRepository.save(company);
        return new ApiResponse("Company edited",true);
    }


    public ApiResponse deleteCompany(Integer id) {
        try {
            companyRepository.deleteById(id);
            return new ApiResponse("Company öchirildi",true);
        }catch (Exception e){
            return new ApiResponse("Company öchirilmadi",false);
        }
}
}
