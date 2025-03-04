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

import StockBook.dto.responses.InventoryResponse;
import StockBook.model.Inventory;
import StockBook.service.InventoryService;


@RestController
@RequestMapping("/stockbook/inventory/")
public class InventoryController {

	@Autowired
    private InventoryService inventoryService;


    //2. to get a product inventory
    @PostMapping("findOne")
    public InventoryResponse getOne(@RequestParam Long fkProduct){
        return inventoryService.getInventory(fkProduct);
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

    //6. to update a product inventory
    @PostMapping("update")
    public InventoryResponse update(@RequestBody Inventory inventory){
        return inventoryService.updateInventory(inventory);
    }
    
  //6. to subtract a product inventory
    @PostMapping("subtract")
    public InventoryResponse subtract(@RequestBody Inventory inventory){
        return inventoryService.subtractInventory(inventory);
    }
}
