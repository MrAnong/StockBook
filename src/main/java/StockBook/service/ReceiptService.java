package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.InvoiceResponse;
import StockBook.dto.responses.ReceiptResponse;
import StockBook.model.Income;
import StockBook.model.Invoice;
import StockBook.model.Product;
import StockBook.model.Receipt;
import StockBook.model.ReceiptItem;
import StockBook.model.Stock_Request;
import StockBook.model.Stores;
import StockBook.model.Users;
import StockBook.repository.IncomeRepository;
import StockBook.repository.ProductRepository;
import StockBook.repository.ReceiptItemRepository;
import StockBook.repository.ReceiptRepository;
import StockBook.repository.StoresRepository;
import StockBook.repository.UsersRepository;
import jakarta.transaction.Transactional;

@Service
public class ReceiptService {

	@Autowired
    private ReceiptRepository receiptRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private StoresRepository storesRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ReceiptItemRepository receiptItemRepository;
	
	@Autowired
	private IncomeRepository incomeRepository;

    //1. to add a Receipts
    @Transactional
    public ReceiptResponse addReceipt(Receipt receipt){
        ReceiptResponse response = new ReceiptResponse();
        
        //check if the Teller exists
        Optional<Users> foundUser = usersRepository.findById(receipt.getFkTeller());
        if(foundUser.isEmpty()) {
        	response.setMessage("failed! Inventory Manager not found");
        	response.setReceipts(null);
        	return response;
        }
        
        //check if the store exists
        Optional<Stores> foundStore = storesRepository.findById(receipt.getFkStore());
        if(foundStore.isEmpty()) {
        	response.setMessage("failed! store not found");
        	response.setReceipts(null);
        	return response;
        }
        
        //set the manager, store and status
        receipt.setTeller(foundUser.get());
        receipt.setStore(foundStore.get());
        
        //save the invoice
        Receipt savedReceipt = receiptRepository.save(receipt);
        
        //create a temporary list object to hold the requestlist data in the transient invoice object
        List<ReceiptItem> list = new ArrayList<>();
        
        float amount = 0;
        
        //for every receiptItem which wasnt saved, attach the receipt id and save it
        for(ReceiptItem items: receipt.getReceiptItemList()) {
        	Optional<Product> foundProduct = productRepository.findById(items.getFkProduct());
        	
        	items.setAmount(foundProduct.get().getUnitPrice() * items.getQuantity());
        	items.setProduct(foundProduct.get());
        	items.setFkReceipt(savedReceipt.getId());
        	items.setReceipt(savedReceipt);
        	
        	receiptItemRepository.save(items);
        	
        	amount = items.getAmount() + amount;
        	
        	list.add(items);
        }
        
        //attribute the list we just filled to the requestList object of the invoice and update the invoice
        savedReceipt.setReceiptItems(list);
        savedReceipt.setTotal(amount);
        
        receiptRepository.save(savedReceipt);
        
        Income income = new Income();
        
        income.setAmount(savedReceipt.getTotal());
        income.setFkReceipt(savedReceipt.getId());
        income.setReceipt(savedReceipt);
        income.setFkStore(savedReceipt.getFkStore());
        income.setStore(savedReceipt.getStore());
        
        incomeRepository.save(income);
        
        response.setReceipts(savedReceipt);
        response.setMessage("added successfully");
        
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
    public List<Receipt> getAll(long id){
        return receiptRepository.findByFkStore(id);
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
