package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.Business_CategoryResponse;
import StockBook.model.Business_Category;
import StockBook.repository.Business_CategoryRepository;
import jakarta.transaction.Transactional;

@Service
public class Business_CategoryService {
	
	@Autowired
    private Business_CategoryRepository business_categoryRepository;

    //1. to add a category
    @Transactional
    public Business_CategoryResponse addCategory(Business_Category category){
        Business_CategoryResponse response = new Business_CategoryResponse();
        response.setCategory(business_categoryRepository.save(category));
        response.setMessage("added successfully");
        return response;
    }

    //2. to get a category
    @Transactional
    public Business_CategoryResponse getCategory(Long id){
        Business_CategoryResponse response = new Business_CategoryResponse();
        Optional<Business_Category> foundCategory = business_categoryRepository.findById(id);
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
    public List<Business_Category> getAll(){
        return business_categoryRepository.findAll();
    }

    //4. to delete a category
    @Transactional
    public Business_CategoryResponse deleteOne(Long id){
        Business_CategoryResponse response = new Business_CategoryResponse();
        Optional<Business_Category> foundCategory = business_categoryRepository.findById(id);
        if(foundCategory.isEmpty()){
            response.setMessage("failed! category not found.");
            response.setCategory((null));
            return response;
        }
        business_categoryRepository.delete(foundCategory.get());
        response.setMessage("delete sucess");
        response.setCategory(foundCategory.get());
        return response;
    }

    //5. to delete a list of categories
    @Transactional
    public List<Business_CategoryResponse> deleteList(List<Long> idList){
        List<Business_CategoryResponse> list = new ArrayList<>();
        for(Long id: idList){
            list.add(deleteOne(id));
        }
        return list;
    }

    //6. to modify a category.
    @Transactional
    public Business_CategoryResponse modifyOne(Business_Category category){
        Business_CategoryResponse response = new Business_CategoryResponse();
        Optional<Business_Category> foundCategory = business_categoryRepository.findById(category.getId());
        if(foundCategory.isEmpty()){
            response.setMessage("falied! category not found");
            response.setCategory(null);
            return response;
        }
        response.setMessage("success");
        response.setCategory(business_categoryRepository.save(category));
        return response;
    }

}
