package StockBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import StockBook.dto.responses.SuppliersResponse;
import StockBook.model.Suppliers;
import StockBook.service.SuppliersService;

@RestController
@RequestMapping("/stockbook/supplier/")
public class SupplierController {

    @Autowired
    private SuppliersService suppliersService;

    //1. to add a supplier
    @PostMapping("add")
    public SuppliersResponse addOne(Suppliers supplier){
        return suppliersService.addSupplier(supplier);
    }

    //2. to get a supplier
    @GetMapping("findOne")
    public SuppliersResponse getOne(Long id){
        return suppliersService.getSupplier(id);
    }

    //3. to get all supplier
    @GetMapping("findAll")
    public List<Suppliers> getAll(){
        return suppliersService.getAll();
    }

    //4. to delete a supplier
    @DeleteMapping("deleteOne")
    public SuppliersResponse deleteOne(Long id){
        return suppliersService.deleteOne(id);
    }

    //5. to delete list
    @DeleteMapping("deleteList")
    public List<SuppliersResponse> deleteList(List<Long> idList){
        return suppliersService.deleteList(idList);
    }

    //6. to modify a supplier
    @PutMapping("update")
    public SuppliersResponse update(Suppliers supplier){
        return suppliersService.modifyOne(supplier);
    }
}
