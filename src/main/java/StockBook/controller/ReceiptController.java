package StockBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import StockBook.dto.responses.ReceiptResponse;
import StockBook.model.Receipt;
import StockBook.service.ReceiptService;

@RestController
@RequestMapping("/stockbook/receipt/")
public class ReceiptController {

	@Autowired
    private ReceiptService receiptService;

    //1. to add a category
    @PostMapping("add")
    public ReceiptResponse addOne(Receipt receipts){
        return receiptService.addReceipts(receipts);
    }

    //2. to get a receipts
    @GetMapping("findOne")
    public ReceiptResponse getOne(Long id){
        return receiptService.getReceipts(id);
    }

    //3. to get all receipts
    @GetMapping("findAll")
    public List<Receipt> getAll(){
        return receiptService.getAll();
    }

    //4. to delete a receipts
    @DeleteMapping("deleteOne")
    public ReceiptResponse deleteOne(Long id){
        return receiptService.deleteOne(id);
    }

    //5. to delete list
    @DeleteMapping("deleteList")
    public List<ReceiptResponse> deleteList(List<Long> idList){
        return receiptService.deleteList(idList);
    }

    //6. to modify a receipts
    @PutMapping("update")
    public ReceiptResponse update(Receipt receipts){
        return receiptService.modifyOne(receipts);
    }
}
