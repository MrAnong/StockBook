package StockBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import StockBook.dto.responses.Product_CategoryResponse;
import StockBook.model.Product_Category;
import StockBook.service.Product_CategoryService;

@RestController
@RequestMapping("/stockbook/product_category/")
public class Product_CategoryController {

	@Autowired
    private Product_CategoryService product_CategoryService;

    //1. to add a category
    @PostMapping("add")
    public Product_CategoryResponse addOne(Product_Category category){
        return product_CategoryService.addCategory(category);
    }

    //2. to get a category
    @GetMapping("findOne")
    public Product_CategoryResponse getOne(Long id){
        return product_CategoryService.getCategory(id);
    }

    //3. to get all categories
    @GetMapping("findAll")
    public List<Product_Category> getAll(){
        return product_CategoryService.getAll();
    }

    //4. to delete a category
    @DeleteMapping("deleteOne")
    public Product_CategoryResponse deleteOne(Long id){
        return product_CategoryService.deleteOne(id);
    }

    //5. to delete list
    @DeleteMapping("deleteList")
    public List<Product_CategoryResponse> deleteList(List<Long> idList){
        return product_CategoryService.deleteList(idList);
    }

    //6. to modify a category
    @PutMapping("update")
    public Product_CategoryResponse update(Product_Category category){
        return product_CategoryService.modifyOne(category);
    }
}
