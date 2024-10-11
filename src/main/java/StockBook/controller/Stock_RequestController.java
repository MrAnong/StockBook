package StockBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import StockBook.dto.responses.Stock_RequestResponse;
import StockBook.model.Stock_Request;
import StockBook.service.Stock_RequestService;

@RestController
@RequestMapping("/stockbook/stock_request/")
public class Stock_RequestController {

	@Autowired
    private Stock_RequestService stock_RequestService;

    //1. to add a stock_requests
    @PostMapping("add")
    public Stock_RequestResponse addOne(Stock_Request stock_requests){
        return stock_RequestService.addRequest(stock_requests);
    }

    //2. to get a stock_request
    @GetMapping("findOne")
    public Stock_RequestResponse getOne(Long id){
        return stock_RequestService.getStock_Request(id);
    }

    //3. to get all stock_requests
    @GetMapping("findAll")
    public List<Stock_Request> getAll(){
        return stock_RequestService.getAll();
    }

    //4. to delete a stock_request
    @DeleteMapping("deleteOne")
    public Stock_RequestResponse deleteOne(Long id){
        return stock_RequestService.deleteOne(id);
    }

    //5. to delete list
    @DeleteMapping("deleteList")
    public List<Stock_RequestResponse> deleteList(List<Long> idList){
        return stock_RequestService.deleteList(idList);
    }

    //6. to modify a stock_request
    @PutMapping("update")
    public Stock_RequestResponse update(Stock_Request stock_request){
        return stock_RequestService.modifyOne(stock_request);
    }
}
