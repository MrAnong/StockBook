package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import StockBook.dto.responses.Stock_RequestResponse;
import StockBook.model.Stock_Request;
import StockBook.repository.Stock_RequestRepository;
import jakarta.transaction.Transactional;


@Service
public class Stock_RequestService {

	@Autowired
    private Stock_RequestRepository stock_RequestRepository;

    //1. to add a stock_request
    @Transactional
    public Stock_RequestResponse addRequest(Stock_Request stock_request){
        Stock_RequestResponse response = new Stock_RequestResponse();
        response.setStock_Requests(stock_RequestRepository.save(stock_request));
        response.setMessage("added successfully");
        return response;
    }
    
    //2. to add a list of stock requests
    @Transactional
    public List<Stock_RequestResponse> addRequestList(List<Stock_Request> requestList) {
    	 List<Stock_RequestResponse> list = new ArrayList<>();
         for(Stock_Request item: requestList){
             list.add(addRequest(item));
         }
         return list;
    }

    //2. to get a stock_Request
    @Transactional
    public Stock_RequestResponse getStock_Request(Long id){
        Stock_RequestResponse response = new Stock_RequestResponse();
        Optional<Stock_Request> foundRequest = stock_RequestRepository.findById(id);
        if(foundRequest.isEmpty()){
            response.setMessage("Request not found");
            response.setStock_Requests(null);
            return response;
        }
        response.setMessage("success");
        response.setStock_Requests(foundRequest.get());
        return response;
    }

    //3. to get all stock_requests
    @Transactional
    public List<Stock_Request> getAll(){
        return stock_RequestRepository.findAll();
    }

    //4. to delete a stock_request
    @Transactional
    public Stock_RequestResponse deleteOne(Long id){
        Stock_RequestResponse response = new Stock_RequestResponse();
        Optional<Stock_Request> foundRequests = stock_RequestRepository.findById(id);
        if(foundRequests.isEmpty()){
            response.setMessage("failed! Stock_Request not found.");
            response.setStock_Requests((null));
            return response;
        }
        stock_RequestRepository.delete(foundRequests.get());
        response.setMessage("delete sucess");
        response.setStock_Requests(foundRequests.get());
        return response;
    }

    //5. to delete a list of stock_requests
    @Transactional
    public List<Stock_RequestResponse> deleteList(List<Long> idList){
        List<Stock_RequestResponse> list = new ArrayList<>();
        for(Long id: idList){
            list.add(deleteOne(id));
        }
        return list;
    }

    //6. to modify a stock_requests.
    @Transactional
    public Stock_RequestResponse modifyOne(Stock_Request stock_requests){
        Stock_RequestResponse response = new Stock_RequestResponse();
        Optional<Stock_Request> foundRequests = stock_RequestRepository.findById(stock_requests.getId());
        if(foundRequests.isEmpty()){
            response.setMessage("falied! stock_request not found");
            response.setStock_Requests(null);
            return response;
        }
        response.setMessage("success");
        response.setStock_Requests(stock_RequestRepository.save(stock_requests));
        return response;
    }
}
