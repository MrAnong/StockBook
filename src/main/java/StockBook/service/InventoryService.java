package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.InventoryResponse;
import StockBook.model.Inventory;
import StockBook.repository.InventoryRepository;
import jakarta.transaction.Transactional;

@Service
public class InventoryService {

	@Autowired
    private InventoryRepository inventoryRepository;

    //1. to add a product inventory
    @Transactional
    public InventoryResponse addInventory(Inventory inventory){
        InventoryResponse response = new InventoryResponse();
        response.setInventory(inventoryRepository.save(inventory));
        response.setMessage("added successfully");
        return response;
    }

    //2. to get a product inventory
    @Transactional
    public InventoryResponse getInventory(Long id){
        InventoryResponse response = new InventoryResponse();
        Optional<Inventory> foundInventory = inventoryRepository.findById(id);
        if(foundInventory.isEmpty()){
            response.setMessage("category not found");
            response.setInventory(null);
            return response;
        }
        response.setMessage("success");
        response.setInventory(foundInventory.get());
        return response;
    }

    //3. to get all product inventories
    @Transactional
    public List<Inventory> getAll(){
        return inventoryRepository.findAll();
    }

    //4. to delete a product's inventory
    @Transactional
    public InventoryResponse deleteOne(Long id){
        InventoryResponse response = new InventoryResponse();
        Optional<Inventory> foundInventory = inventoryRepository.findById(id);
        if(foundInventory.isEmpty()){
            response.setMessage("failed! category not found.");
            response.setInventory(null);
            return response;
        }
        inventoryRepository.delete(foundInventory.get());
        response.setMessage("delete sucess");
        response.setInventory(foundInventory.get());
        return response;
    }

    //5. to delete a list of inventories
    @Transactional
    public List<InventoryResponse> deleteList(List<Long> idList){
        List<InventoryResponse> list = new ArrayList<>();
        for(Long id: idList){
            list.add(deleteOne(id));
        }
        return list;
    }

    //6. to modify a product inventory.
    //TODO: create an addition and substraction method for products inventory which will be called during the process of making a sale at the teller before creating a receipt
    @Transactional
    public InventoryResponse modifyOne(Inventory inventory){
        InventoryResponse response = new InventoryResponse();
        Optional<Inventory> foundInventory = inventoryRepository.findById(inventory.getId());
        if(foundInventory.isEmpty()){
            response.setMessage("failed! category not found");
            response.setInventory(null);
            return response;
        }
        response.setMessage("success");
        response.setInventory(inventoryRepository.save(inventory));
        return response;
    }
    
    //7. to add the quantity of the product in stock
    @Transactional
    public InventoryResponse updateInventory(int quantity, long productId) {
    	InventoryResponse response = new InventoryResponse();
    	
    	Optional<Inventory> foundInventory = inventoryRepository.findByProductId(productId);
    	if(foundInventory.isEmpty()) {
    		  response.setMessage("failed! product inventory not found.");
              response.setInventory(null);
              return response;
    	}
    	foundInventory.get().setQuantity(foundInventory.get().getQuantity() + quantity);
    	response.setMessage("successfully added");
    	response.setInventory(foundInventory.get());
    	return response;
    	
    }
    
    //8. to reduce the quantity of a product in stock
    @Transactional
    public InventoryResponse downdateInventory(int quantity, long productId) {
InventoryResponse response = new InventoryResponse();
    	
    	Optional<Inventory> foundInventory = inventoryRepository.findByProductId(productId);
    	if(foundInventory.isEmpty()) {
    		  response.setMessage("failed! product inventory not found.");
              response.setInventory(null);
              return response;
    	}
    	if(quantity > foundInventory.get().getQuantity()){
    		 response.setMessage("failed! Only "+foundInventory.get().getQuantity()+" are left in stock");
             response.setInventory(null);
             return response;
    	}
    	foundInventory.get().setQuantity(foundInventory.get().getQuantity() - quantity);
    	response.setMessage("successfully added");
    	response.setInventory(foundInventory.get());
    	return response;
    }
    
}
