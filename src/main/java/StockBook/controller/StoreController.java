package StockBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import StockBook.dto.responses.StoresResponse;
import StockBook.model.Stores;
import StockBook.service.StoresService;

@RestController
@RequestMapping("/stockbook/store/")
public class StoreController {

	@Autowired
    private StoresService storesService;

    //1. to add a store
	@PostMapping(value = "addOne", consumes = "application/json")
    public StoresResponse addOne(@RequestBody Stores store){
        return storesService.addStore(store);
    }

    //2. to get a store
    @GetMapping("findOne")
    public StoresResponse getOne(@RequestBody Long id){
        return storesService.getStore(id);
    }

    //3. to get all store
    @GetMapping("findAll")
    public List<Stores> getAll(){
        return storesService.getAll();
    }

    //4. to delete a store
    @DeleteMapping("deleteOne")
    public StoresResponse deleteOne(Long id){
        return storesService.deleteOne(id);
    }

    //5. to delete list
    @DeleteMapping("deleteList")
    public List<StoresResponse> deleteList(List<Long> idList){
        return storesService.deleteList(idList);
    }

    //6. to modify a store
    @PutMapping("update")
    public StoresResponse update(Stores store){
        return storesService.modifyOne(store);
    }
}
