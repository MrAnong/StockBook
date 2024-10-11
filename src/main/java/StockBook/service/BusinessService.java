package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.BusinessResponse;
import StockBook.model.Business;
import StockBook.model.Business_Category;
import StockBook.model.Users;
import StockBook.repository.BusinessRepository;
import StockBook.repository.Business_CategoryRepository;
import StockBook.repository.UsersRepository;
import jakarta.transaction.Transactional;

@Service
public class BusinessService {

	@Autowired
    private BusinessRepository businessRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private Business_CategoryRepository business_CategoryRepository;

	//to add a business
	@Transactional
	public BusinessResponse addBusiness(Business business) {
	    BusinessResponse response = new BusinessResponse();
	    
	    // Set initial status
	    business.setStatus("active");
	    
	    // Fetch the category and user based on foreign keys
	    Optional<Business_Category> foundCategory = business_CategoryRepository.findById(business.getFkCategory());
	    Optional<Users> foundUser = usersRepository.findById(business.getFkOwner());

	    // Check if category is present
	    Business_Category category = foundCategory.orElseThrow(() -> 
	        new RuntimeException("Business Category not found with ID: " + business.getFkCategory()));
	    
	    // Check if user is present
	    Users user = foundUser.orElseThrow(() -> 
	        new RuntimeException("User not found with ID: " + business.getFkOwner()));

	    // Set the relationships
	    business.setBusinessCategory(category);
	    business.setBusinessOwner(user);

	    // Save the business entity
	    businessRepository.save(business);
	    
	    response.setBusiness(business);
	    response.setMessage("Business added successfully");
	    
	    return response;
	}

    //2. to get a business
    @Transactional
    public BusinessResponse getBusiness(Long id){
        BusinessResponse response = new BusinessResponse();
        Optional<Business> foundBusiness = businessRepository.findById(id);
        if(foundBusiness.isEmpty()){
            response.setMessage("category not found");
            response.setBusiness(null);
            return response;
        }
        response.setMessage("success");
        response.setBusiness(foundBusiness.get());
        return response;
    }

    //3. to get all businesses
    @Transactional
    public List<Business> getAll(){
        return businessRepository.findAll();
    }

    //4. to delete a business
    @Transactional
    public BusinessResponse deleteOne(Long id){
        BusinessResponse response = new BusinessResponse();
        Optional<Business> foundBusiness = businessRepository.findById(id);
        if(foundBusiness.isEmpty()){
            response.setMessage("failed! business not found.");
            response.setBusiness((null));
            return response;
        }
        
        businessRepository.delete(foundBusiness.get());
        response.setMessage("delete sucess");
        response.setBusiness(foundBusiness.get());
        return response;
    }

    //5. to delete a list of businesses
    @Transactional
    public List<BusinessResponse> deleteList(List<Long> idList){
        List<BusinessResponse> list = new ArrayList<>();
        for(Long id: idList){
            list.add(deleteOne(id));
        }
        return list;
    }

    //6. to modify a business.
    @Transactional
    public BusinessResponse updateBusiness(Business updatedBusiness) {
        BusinessResponse response = new BusinessResponse();
        
        // Find the existing business by ID
        Optional<Business> existingBusinessOpt = businessRepository.findById(updatedBusiness.getId());
        
        if (existingBusinessOpt.isEmpty()) {
        	
        	 response.setMessage("Failed! Business not found.");
             response.setBusiness(null);
             return response;
        }
            Business existingBusiness = existingBusinessOpt.get();
            
            // Update fields only if they are not null in the incoming request
            if (updatedBusiness.getName() != null) {
                existingBusiness.setName(updatedBusiness.getName());
            }
            if (updatedBusiness.getType() != null) {
                existingBusiness.setType(updatedBusiness.getType());
            }
            if (updatedBusiness.getDescription() != null) {
                existingBusiness.setDescription(updatedBusiness.getDescription());
            }
            if(updatedBusiness.getFkCategory() > 0) {
            	existingBusiness.setFkCategory(updatedBusiness.getFkCategory());
            }
            
            // Save the updated business entity
            businessRepository.save(existingBusiness);
            
            response.setMessage("Update successful");
            response.setBusiness(existingBusiness);
            return response;
        } 
           
}
