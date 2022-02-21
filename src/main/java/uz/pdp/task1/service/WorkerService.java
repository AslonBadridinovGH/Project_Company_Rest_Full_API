package uz.pdp.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task1.entity.Address;
import uz.pdp.task1.entity.Department;
import uz.pdp.task1.entity.Worker;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.payload.WorkerDto;
import uz.pdp.task1.repository.AddressRepository;
import uz.pdp.task1.repository.DepartmentRepository;
import uz.pdp.task1.repository.WorkerRepository;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    AddressRepository addressRepository;


    public ApiResponse addWorker(WorkerDto workerDto) {

        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber)
        return new ApiResponse("Such phoneNumber already exist",false);

        Worker worker=new Worker();
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setName(workerDto.getName());

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if(!optionalDepartment.isPresent())
        return new ApiResponse("Department not found",false);
        worker.setDepartment(optionalDepartment.get());

        Address address=new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);
        worker.setAddress(savedAddress);
        workerRepository.save(worker);
        return new ApiResponse("Worker saved",true);
    }

    public List<Worker> getWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElseGet(Worker::new);
    }

    public ApiResponse editWorker(WorkerDto workerDto, Integer id) {
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(),id);
        if (existsByPhoneNumber)
            return new ApiResponse("Such phoneNumber already exist",false);


        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (!optionalWorker.isPresent())
         return new ApiResponse("Worker not found",false);
        Worker worker = optionalWorker.get();
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setName(workerDto.getName());

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if(!optionalDepartment.isPresent())
            return new ApiResponse("Department not found",false);
        worker.setDepartment(optionalDepartment.get());

        Address address = worker.getAddress();

        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);
        worker.setAddress(savedAddress);
        workerRepository.save(worker);
        return new ApiResponse("Worker edited",true);
    }

    public ApiResponse deleteWorker(Integer id) {
        try {
            workerRepository.deleteById(id);
            return new ApiResponse("Worker öchirildi",true);
        }catch (Exception e){
            return new ApiResponse("Worker öchirilmadi",false);
        }
    }

}

