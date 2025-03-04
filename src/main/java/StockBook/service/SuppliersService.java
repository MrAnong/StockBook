package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.SuppliersResponse;
import StockBook.model.Suppliers;
import StockBook.model.Users;
import StockBook.repository.SuppliersRepository;
import StockBook.repository.UsersRepository;
import jakarta.transaction.Transactional;

@Service
public class SuppliersService {

	@Autowired
    private SuppliersRepository suppliersRepository;
	
	@Autowired
	private UsersRepository usersRepository;

    //1. to add a supplier
    @Transactional
    public SuppliersResponse addSupplier(Suppliers supplier){
        SuppliersResponse response = new SuppliersResponse();
        
        Optional<Users> foundUser = usersRepository.findById(supplier.getFkStoreManager());
        if(foundUser.isEmpty()) {
        	response.setSupplier(null);
            response.setMessage("failed");
            return response;
        }
        supplier.setStoreManager(foundUser.get());
        response.setSupplier(suppliersRepository.save(supplier));
        response.setMessage("added successfully");
        return response;
    }

    //2. to get a supplier
    @Transactional
    public SuppliersResponse getSupplier(Long id){
        SuppliersResponse response = new SuppliersResponse();
        Optional<Suppliers> foundSuppliers = suppliersRepository.findById(id);
        if(foundSuppliers.isEmpty()){
            response.setMessage("Store not found");
            response.setSupplier(null);
            return response;
        }
        response.setMessage("success");
        response.setSupplier(foundSuppliers.get());
        return response;
    }

    //3. to get all suppliers
    @Transactional
    public List<Suppliers> getAll(){
        return suppliersRepository.findAll();
    }

    //4. to delete a supplier
    @Transactional
    public SuppliersResponse deleteOne(Long id){
        SuppliersResponse response = new SuppliersResponse();
        Optional<Suppliers> foundSuppliers = suppliersRepository.findById(id);
        if(foundSuppliers.isEmpty()){
            response.setMessage("failed! category not found.");
            response.setSupplier((null));
            return response;
        }
        suppliersRepository.delete(foundSuppliers.get());
        response.setMessage("delete sucess");
        response.setSupplier(foundSuppliers.get());
        return response;
    }

    //5. to delete a list of suppliers
    @Transactional
    public List<SuppliersResponse> deleteList(List<Long> idList){
        List<SuppliersResponse> list = new ArrayList<>();
        for(Long id: idList){
            list.add(deleteOne(id));
        }
        return list;
    }

    //6. to modify a supplier.
    @Transactional
    public SuppliersResponse modifyOne(Suppliers supplier){
        SuppliersResponse response = new SuppliersResponse();
        Optional<Suppliers> foundSuppliers = suppliersRepository.findById(supplier.getId());
        if(foundSuppliers.isEmpty()){
            response.setMessage("falied! category not found");
            response.setSupplier(null);
            return response;
        }
        response.setMessage("success");
        response.setSupplier(suppliersRepository.save(supplier));
        return response;
    }
}
