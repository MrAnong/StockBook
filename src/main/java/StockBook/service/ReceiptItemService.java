package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.ReceiptItemResponse;
import StockBook.model.ReceiptItem;
import StockBook.model.Stock_Request;
import StockBook.repository.ReceiptItemRepository;
import jakarta.transaction.Transactional;

@Service
public class ReceiptItemService {

	@Autowired
    private ReceiptItemRepository receiptItemRepository;


    //1. to add a ReceiptItem
    @Transactional
    public ReceiptItemResponse addReceiptItem(ReceiptItem receiptItem){
        ReceiptItemResponse response = new ReceiptItemResponse();
        response.setReceiptItems(receiptItemRepository.save(receiptItem));
        response.setMessage("added successfully");
        return response;
    }
    
    //2. to add a list of receipt items
    @Transactional
    public List<ReceiptItemResponse> addItemList(List<ReceiptItem> itemList) {
    	 List<ReceiptItemResponse> list = new ArrayList<>();
         for(ReceiptItem item: itemList){
             list.add(addReceiptItem(item));
         }
         return list;
    }

    //3. to get a receiptItem
    @Transactional
    public ReceiptItemResponse getReceiptItem(Long id){
        ReceiptItemResponse response = new ReceiptItemResponse();
        Optional<ReceiptItem> foundItems = receiptItemRepository.findById(id);
        if(foundItems.isEmpty()){
            response.setMessage("Item not found");
            response.setReceiptItems(null);
            return response;
        }
        response.setMessage("success");
        response.setReceiptItems(foundItems.get());
        return response;
    }

    //4. to get all ReceiptItems
    @Transactional
    public List<ReceiptItem> getAll(){
        return receiptItemRepository.findAll();
    }

    //5. to delete a receiptItems
    @Transactional
    public ReceiptItemResponse deleteOne(Long id){
        ReceiptItemResponse response = new ReceiptItemResponse();
        Optional<ReceiptItem> foundItems = receiptItemRepository.findById(id);
        if(foundItems.isEmpty()){
            response.setMessage("failed! Item not found.");
            response.setReceiptItems((null));
            return response;
        }
        receiptItemRepository.delete(foundItems.get());
        response.setMessage("delete sucess");
        response.setReceiptItems(foundItems.get());
        return response;
    }

    //6. to delete a list of receiptItems
    @Transactional
    public List<ReceiptItemResponse> deleteList(List<Long> idList){
        List<ReceiptItemResponse> list = new ArrayList<>();
        for(Long id: idList){
            list.add(deleteOne(id));
        }
        return list;
    }

    //7. to modify a receiptItem.
    @Transactional
    public ReceiptItemResponse modifyOne(ReceiptItem receiptItem){
        ReceiptItemResponse response = new ReceiptItemResponse();
        Optional<ReceiptItem> foundItems = receiptItemRepository.findById(receiptItem.getId());
        if(foundItems.isEmpty()){
            response.setMessage("falied! item not found");
            response.setReceiptItems(null);
            return response;
        }
        response.setMessage("success");
        response.setReceiptItems(receiptItemRepository.save(receiptItem));
        return response;
    }
    
    //3. to get all stock_requests
    @Transactional
    public List<ReceiptItem> getAllReceipt(long id){
        return receiptItemRepository.findByFkReceipt(id);
    }
}
