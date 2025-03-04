package StockBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import StockBook.dto.responses.ProductResponse;
import StockBook.model.Product;
import StockBook.service.ProductService;

@RestController
@RequestMapping("/stockbook/product")
public class ProductController {

	@Autowired
    private ProductService productService;

    //1. to add a category
    @PostMapping(value = "/add", consumes = "application/json")
    public ProductResponse addOne(@RequestBody Product products){
    	
    	System.out.println("test brunu----------------------");
        return productService.addProducts(products);
    }

    //2. to get a category
    @GetMapping("findOne")
    public ProductResponse getOne(@RequestParam long id){
        return productService.getProducts(id);
    }

    //3. to get all categories
    @GetMapping("findAll")
    public List<Product> getAll(){
        return productService.getAll();
    }

    //4. to delete a category
    @DeleteMapping("deleteOne")
    public ProductResponse deleteOne(int id){
        return productService.deleteOne(id);
    }

    //5. to delete list
    @DeleteMapping("deleteList")
    public List<ProductResponse> deleteList(List<Integer> idList){
        return productService.deleteList(idList);
    }

    //6. to modify a category
    @PutMapping("update")
    public ProductResponse update(Product products){
        return productService.modifyOne(products);
    }
}
