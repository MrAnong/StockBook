package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.StoresResponse;
import StockBook.model.Business;
import StockBook.model.Stores;
import StockBook.repository.BusinessRepository;
import StockBook.repository.StoresRepository;
import jakarta.transaction.Transactional;

@Service
public class StoresService {

	@Autowired
    private StoresRepository storesRepository;
	
	@Autowired
	private BusinessRepository businessRepository;

    //1. to add a Stores
    @Transactional
    public StoresResponse addStore(Stores store){
    	
        StoresResponse response = new StoresResponse();
        Optional<Business> foundBusiness = businessRepository.findById(store.getFkBusiness());
        if(foundBusiness.isEmpty()) {
        	response.setMessage("couldnt create store. Business doesnt exist");
        	response.setStore(null);
        	return response;
        }
        store.setStatus("Active");
        store.setBusiness(foundBusiness.get());
        
        response.setStore(storesRepository.save(store));
        response.setMessage("added successfully");
        return response;
    }

    //2. to get a store
    @Transactional
    public StoresResponse getStore(Long id){
        StoresResponse response = new StoresResponse();
        Optional<Stores> foundStores = storesRepository.findById(id);
        if(foundStores.isEmpty()){
            response.setMessage("Store not found");
            response.setStore(null);
            return response;
        }
        response.setMessage("success");
        response.setStore(foundStores.get());
        return response;
    }

    //3. to get all stores
    @Transactional
    public List<Stores> getAll(){
        return storesRepository.findAll();
    }

    //4. to delete a store
    @Transactional
    public StoresResponse deleteOne(Long id){
        StoresResponse response = new StoresResponse();
        Optional<Stores> foundStores = storesRepository.findById(id);
        if(foundStores.isEmpty()){
            response.setMessage("failed! category not found.");
            response.setStore((null));
            return response;
        }
        storesRepository.delete(foundStores.get());
        response.setMessage("delete sucess");
        response.setStore(foundStores.get());
        return response;
    }

    //5. to delete a list of stores
    @Transactional
    public List<StoresResponse> deleteList(List<Long> idList){
        List<StoresResponse> list = new ArrayList<>();
        for(Long id: idList){
            list.add(deleteOne(id));
        }
        return list;
    }

    //6. to modify a store.
    @Transactional
    public StoresResponse modifyOne(Stores store){
        StoresResponse response = new StoresResponse();
        Optional<Stores> foundStores = storesRepository.findById(store.getId());
        if(foundStores.isEmpty()){
            response.setMessage("falied! category not found");
            response.setStore(null);
            return response;
        }
        response.setMessage("success");
        response.setStore(storesRepository.save(store));
        return response;
    }
}
