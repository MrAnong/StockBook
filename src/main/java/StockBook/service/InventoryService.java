package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.InventoryResponse;
import StockBook.model.Inventory;
import StockBook.model.Product;
import StockBook.model.Users;
import StockBook.repository.InventoryRepository;
import StockBook.repository.ProductRepository;
import StockBook.repository.UsersRepository;
import jakarta.transaction.Transactional;

@Service
public class InventoryService {

	@Autowired
    private InventoryRepository inventoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UsersRepository usersRepository;


    //2. to get a product inventory
    @Transactional
    public InventoryResponse getInventory(Long id){
        InventoryResponse response = new InventoryResponse();
        Optional<Inventory> foundInventory = inventoryRepository.findByProductId(id);
        if(foundInventory.isEmpty()){
            response.setMessage("inventory not found");
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
    
    //7. to add the quantity of the product in stock
    @Transactional
    public InventoryResponse updateInventory(Inventory inventory) {
InventoryResponse response = new InventoryResponse();

		Optional<Users> foundUser = usersRepository.findById(inventory.getFkManager());
		if(foundUser.isEmpty()) {
			response.setMessage("failed! user not found");
			response.setInventory(null);
			return response;
		}
    	
    	Optional<Product> foundProduct = productRepository.findById(inventory.getFkProduct());
    	
    	if(foundProduct.isEmpty()) {
    		 response.setMessage("failed! product not found.");
             response.setInventory(null);
             return response;
    	}
    	
    	Optional<Inventory> foundInventory = inventoryRepository.findByProductId(inventory.getFkProduct());
    	
    	if(foundInventory.isEmpty()) {
    		
    		inventory.setProduct(foundProduct.get());
        	inventory.setStock_Manager(foundUser.get());
        	
        	Product product = foundProduct.get();
        	product.setInventoryQuantity(inventory.getQuantity());
        	productRepository.save(product);
        	
    		inventoryRepository.save(inventory);
    		
    		  response.setMessage("success! product inventory saved.");
              response.setInventory(inventory);
              return response;
    	}
    	
    	Inventory newInventory = foundInventory.get();
    	
    	newInventory.setQuantity(newInventory.getQuantity() + inventory.getQuantity());
    	newInventory.setProduct(foundProduct.get());
    	newInventory.setStock_Manager(foundUser.get());
    	
    	Product product = foundProduct.get();
    	product.setInventoryQuantity(newInventory.getQuantity());
    	productRepository.save(product);
    	
    	inventoryRepository.save(newInventory);
    	
    	response.setMessage("successfully added");
    	response.setInventory(newInventory);
    	return response;
    	
    }
    
    //8. to reduce the quantity of a product in stock
    @Transactional
    public InventoryResponse subtractInventory(Inventory inventory) {
    	InventoryResponse response = new InventoryResponse();
    	
    	Optional<Users> foundUser = usersRepository.findById(inventory.getFkManager());
    	if(foundUser.isEmpty()) {
    		response.setMessage("failed! user not found");
    		response.setInventory(null);
    		return response;
    	}
    	
    	Optional<Product> foundProduct = productRepository.findById(inventory.getFkProduct());
    	
    	if(foundProduct.isEmpty()) {
    		 response.setMessage("failed! product not found.");
             response.setInventory(null);
             return response;
    	}
    	
    	Optional<Inventory> foundInventory = inventoryRepository.findByProductId(inventory.getFkProduct());
    	if(foundInventory.isEmpty()) {
    		  response.setMessage("failed! product inventory not found.");
              response.setInventory(null);
              return response;
    	}
    	if(inventory.getQuantity() > foundInventory.get().getQuantity()){
    		 response.setMessage("failed! Only "+foundInventory.get().getQuantity()+" are left in stock");
             response.setInventory(null);
             return response;
    	}
    	
    	
    	Inventory newInventory = foundInventory.get();
    	
    	newInventory.setQuantity(foundInventory.get().getQuantity() - inventory.getQuantity());
    	newInventory.setProduct(foundProduct.get());
    	newInventory.setStock_Manager(foundUser.get());
    	
    	Product product = foundProduct.get();
    	product.setInventoryQuantity(newInventory.getQuantity());
    	productRepository.save(product);
    	
    	
    	inventoryRepository.save(newInventory);
    	
    	response.setMessage("successfully added");
    	response.setInventory(newInventory);
    	return response;
    }
    
}
