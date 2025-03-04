package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.Product_CategoryResponse;
import StockBook.model.Product_Category;
import StockBook.model.Users;
import StockBook.repository.Product_CategoryRepository;
import StockBook.repository.UsersRepository;
import jakarta.transaction.Transactional;


@Service
public class Product_CategoryService {
	
	@Autowired
    private Product_CategoryRepository product_categoryRepository;
	
	@Autowired
	public UsersRepository usersRepository;

    //1. to add a category
    @Transactional
    public Product_CategoryResponse addCategory(Product_Category category){
        Product_CategoryResponse response = new Product_CategoryResponse();
        
       Optional<Users> foundUser = usersRepository.findById(category.getFkStoreManager());
       if(foundUser.isEmpty()) {
    	   
    	   response.setMessage("failed! store manager doesnt exist");
    	   response.setCategory(null);
    	   return response;
       }
        
        category.setStoreManager(foundUser.get());
        
        response.setCategory(product_categoryRepository.save(category));
        response.setMessage("added successfully");
        return response;
    }

    //2. to get a category
    @Transactional
    public Product_CategoryResponse getCategory(Long id){
        Product_CategoryResponse response = new Product_CategoryResponse();
        Optional<Product_Category> foundCategory = product_categoryRepository.findById(id);
        if(foundCategory.isEmpty()){
            response.setMessage("category not found");
            response.setCategory(null);
            return response;
        }
        response.setMessage("success");
        response.setCategory(foundCategory.get());
        return response;
    }

    //3. to get all categories
    @Transactional
    public List<Product_Category> getAll(){
        return product_categoryRepository.findAll();
    }

    //4. to delete a category
    @Transactional
    public Product_CategoryResponse deleteOne(Long id){
        Product_CategoryResponse response = new Product_CategoryResponse();
        Optional<Product_Category> foundCategory = product_categoryRepository.findById(id);
        if(foundCategory.isEmpty()){
            response.setMessage("failed! category not found.");
            response.setCategory((null));
            return response;
        }
       product_categoryRepository.delete(foundCategory.get());
        response.setMessage("delete sucess");
        response.setCategory(foundCategory.get());
        return response;
    }

    //5. to delete a list of categories
    @Transactional
    public List<Product_CategoryResponse> deleteList(List<Long> idList){
        List<Product_CategoryResponse> list = new ArrayList<>();
        for(Long id: idList){
            list.add(deleteOne(id));
        }
        return list;
    }

    //6. to modify a category.
    @Transactional
    public Product_CategoryResponse modifyOne(Product_Category category){
        Product_CategoryResponse response = new Product_CategoryResponse();
        Optional<Product_Category> foundCategory = product_categoryRepository.findById(category.getId());
        if(foundCategory.isEmpty()){
            response.setMessage("falied! category not found");
            response.setCategory(null);
            return response;
        }
        response.setMessage("success");
        response.setCategory(product_categoryRepository.save(category));
        return response;
    }

}
