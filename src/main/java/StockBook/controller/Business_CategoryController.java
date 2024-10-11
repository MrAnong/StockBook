package StockBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import StockBook.dto.responses.Business_CategoryResponse;
import StockBook.model.Business_Category;
import StockBook.service.Business_CategoryService;


@RestController
@RequestMapping("/stockbook/business_category/")
public class Business_CategoryController {
	
	@Autowired
    private Business_CategoryService business_categoryService;

    //1. to add a category
    @PostMapping("add")
    public Business_CategoryResponse addOne(Business_Category category){
        return business_categoryService.addCategory(category);
    }

    //2. to get a category
    @GetMapping("findOne")
    public Business_CategoryResponse getOne(Long id){
        return business_categoryService.getCategory(id);
    }

    //3. to get all categories
    @GetMapping("findAll")
    public List<Business_Category> getAll(){
        return business_categoryService.getAll();
    }

    //4. to delete a category
    @DeleteMapping("deleteOne")
    public Business_CategoryResponse deleteOne(Long id){
        return business_categoryService.deleteOne(id);
    }

    //5. to delete list
    @DeleteMapping("deleteList")
    public List<Business_CategoryResponse> deleteList(List<Long> idList){
        return business_categoryService.deleteList(idList);
    }

    //6. to modify a category
    @PutMapping("update")
    public Business_CategoryResponse update(Business_Category category){
        return business_categoryService.modifyOne(category);
    }

}
