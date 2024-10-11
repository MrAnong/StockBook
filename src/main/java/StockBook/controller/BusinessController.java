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

import StockBook.dto.responses.BusinessResponse;
import StockBook.model.Business;
import StockBook.service.BusinessService;


@RestController
@RequestMapping("/stockbook/business/")
public class BusinessController {

	@Autowired
    private BusinessService businessService;

    //1. to add a business
    @PostMapping("add")
    public BusinessResponse addOne(@RequestBody Business business){
        return businessService.addBusiness(business);
    }

    //2. to get a business
    @GetMapping("findOne")
    public BusinessResponse getOne(@RequestParam Long id){
        return businessService.getBusiness(id);
    }

    //3. to get all businesses
    @GetMapping("findAll")
    public List<Business> getAll(){
        return businessService.getAll();
    }

    //4. to delete a business
    @DeleteMapping("deleteOne")
    public BusinessResponse deleteOne(@RequestParam Long id){
        return businessService.deleteOne(id);
    }

    //5. to delete list
    @DeleteMapping("deleteList")
    public List<BusinessResponse> deleteList(List<Long> idList){
        return businessService.deleteList(idList);
    }

    //6. to modify a category
    @PutMapping("update")
    public BusinessResponse update(@RequestBody Business business){
        return businessService.updateBusiness(business);
    }
}
