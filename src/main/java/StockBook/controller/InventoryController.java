package StockBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import StockBook.dto.responses.InventoryResponse;
import StockBook.model.Inventory;
import StockBook.service.InventoryService;


@RestController
@RequestMapping("/stockbook/inventory/")
public class InventoryController {

	@Autowired
    private InventoryService inventoryService;

    //1. to add an inventory
    @PostMapping("add")
    public InventoryResponse addOne(Inventory inventory){
        return inventoryService.addInventory(inventory);
    }

    //2. to get a product inventory
    @GetMapping("findOne")
    public InventoryResponse getOne(Long id){
        return inventoryService.getInventory(id);
    }

    //3. to get all product inventories
    @GetMapping("findAll")
    public List<Inventory> getAll(){
        return inventoryService.getAll();
    }

    //4. to delete a product inventory
    @DeleteMapping("deleteOne")
    public InventoryResponse deleteOne(Long id){
        return inventoryService.deleteOne(id);
    }

    //5. to delete list
    @DeleteMapping("deleteList")
    public List<InventoryResponse> deleteList(List<Long> idList){
        return inventoryService.deleteList(idList);
    }

    //6. to modify a product inventory
    @PutMapping("update")
    public InventoryResponse update(Inventory inventory){
        return inventoryService.modifyOne(inventory);
    }
}
