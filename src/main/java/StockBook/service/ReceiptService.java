package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.ReceiptResponse;
import StockBook.model.Receipt;
import StockBook.repository.ReceiptRepository;
import jakarta.transaction.Transactional;

@Service
public class ReceiptService {

	@Autowired
    private ReceiptRepository receiptRepository;

    //1. to add a Receipts
    @Transactional
    public ReceiptResponse addReceipts(Receipt receipt){
        ReceiptResponse response = new ReceiptResponse();
        response.setReceipts(receiptRepository.save(receipt));
        response.setMessage("added successfully");
        
        //TODO: call the additemlist method and pass the item list together with the receipt id
        return response;
    }

    //2. to get a receipts
    @Transactional
    public ReceiptResponse getReceipts(Long id){
        ReceiptResponse response = new ReceiptResponse();
        Optional<Receipt> foundReceipts = receiptRepository.findById(id);
        if(foundReceipts.isEmpty()){
            response.setMessage("category not found");
            response.setReceipts(null);
            return response;
        }
        response.setMessage("success");
        response.setReceipts(foundReceipts.get());
        return response;
    }

    //3. to get all receipts
    @Transactional
    public List<Receipt> getAll(){
        return receiptRepository.findAll();
    }

    //4. to delete a receipts
    @Transactional
    public ReceiptResponse deleteOne(Long id){
        ReceiptResponse response = new ReceiptResponse();
        Optional<Receipt> foundReceipts = receiptRepository.findById(id);
        if(foundReceipts.isEmpty()){
            response.setMessage("failed! category not found.");
            response.setReceipts((null));
            return response;
        }
        receiptRepository.delete(foundReceipts.get());
        response.setMessage("delete sucess");
        response.setReceipts(foundReceipts.get());
        return response;
    }

    //5. to delete a list of receipts
    @Transactional
    public List<ReceiptResponse> deleteList(List<Long> idList){
        List<ReceiptResponse> list = new ArrayList<>();
        for(Long id: idList){
            list.add(deleteOne(id));
        }
        return list;
    }

    //6. to modify a receipts.
    @Transactional
    public ReceiptResponse modifyOne(Receipt receipts){
        ReceiptResponse response = new ReceiptResponse();
        Optional<Receipt> foundReceipts = receiptRepository.findById(receipts.getId());
        if(foundReceipts.isEmpty()){
            response.setMessage("falied! category not found");
            response.setReceipts(null);
            return response;
        }
        response.setMessage("success");
        response.setReceipts(receiptRepository.save(receipts));
        return response;
    }
}
