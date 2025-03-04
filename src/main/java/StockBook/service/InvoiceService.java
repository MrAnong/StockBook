
package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.InvoiceResponse;
import StockBook.model.Expense;
import StockBook.model.Inventory;
import StockBook.model.Invoice;
import StockBook.model.Product;
import StockBook.model.Stock_Request;
import StockBook.model.Stores;
import StockBook.model.Users;
import StockBook.repository.ExpenseRepository;
import StockBook.repository.InvoiceRepository;
import StockBook.repository.ProductRepository;
import StockBook.repository.Stock_RequestRepository;
import StockBook.repository.StoresRepository;
import StockBook.repository.UsersRepository;
import jakarta.transaction.Transactional;

@Service
public class InvoiceService {

	@Autowired
    private InvoiceRepository invoiceRepository;
	
	@Autowired
	private Stock_RequestRepository stock_RequestRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private StoresRepository storesRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private InventoryService inventoryService;

    //1. to add an invoice
    @Transactional
    public InvoiceResponse addInvoice(Invoice invoice){
        InvoiceResponse response = new InvoiceResponse();
        
        //check if the inventory manager exists
        Optional<Users> foundUser = usersRepository.findById(invoice.getFkInventoryManager());
        if(foundUser.isEmpty()) {
        	response.setMessage("failed! Inventory Manager not found");
        	response.setInvoice(null);
        	return response;
        }
        
        //check if the store exists
        Optional<Stores> foundStore = storesRepository.findById(invoice.getFkStore());
        if(foundStore.isEmpty()) {
        	response.setMessage("failed! store not found");
        	response.setInvoice(null);
        	return response;
        }
        
        //set the manager, store and status
        invoice.setStockManager(foundUser.get());
        invoice.setStore(foundStore.get());
        invoice.setStatus("pending");
        
        //save the invoice
        Invoice savedInvoice = invoiceRepository.save(invoice);
        
        //create a temporary list object to hold the requestlist data in the transient invoice object
        List<Stock_Request> list = new ArrayList<>();
        
        float amount = 0;
        
        //for every requestlist item which wasnt saved, attach the invoice id and save it
        for(Stock_Request request: invoice.getStockRequests()) {
        	Optional<Product> item = productRepository.findById(request.getFkProduct());
        	
        	request.setCost(item.get().getUnitPrice() * request.getQuantity());
        	request.setProduct(item.get());
        	request.setFkInvoice(savedInvoice.getId());
        	request.setInvoice(savedInvoice);
        	request.setName(item.get().getName());
        	
        	stock_RequestRepository.save(request);
        	
        	amount = request.getCost() + amount;
        	
        	list.add(request);
        }
        
        //attribute the list we just filled to the requestList object of the invoice and update the invoice
        savedInvoice.setStockRequestsList(list);
        savedInvoice.setAmount(amount);
        
        invoiceRepository.save(savedInvoice);
        
        
        response.setInvoice(savedInvoice);
        response.setMessage("added successfully");
        
        return response;
    }

    //2. to get an invoice
    @Transactional
    public InvoiceResponse getInvoice(Long id){
        InvoiceResponse response = new InvoiceResponse();
        Optional<Invoice> foundInvoice = invoiceRepository.findById(id);
        if(foundInvoice.isEmpty()){
            response.setMessage("invoice not found");
            response.setInvoice(null);
            return response;
        }
        response.setMessage("success");
        response.setInvoice(foundInvoice.get());
        return response;
    }

    //3. to get all invoices
    @Transactional
    public List<Invoice> getAll(){
        return invoiceRepository.findAll();
    }

    //4. to delete an invoice
    @Transactional
    public InvoiceResponse deleteOne(Long id){
        InvoiceResponse response = new InvoiceResponse();
        Optional<Invoice> foundInvoice = invoiceRepository.findById(id);
        if(foundInvoice.isEmpty()){
            response.setMessage("failed! category not found.");
            response.setInvoice((null));
            return response;
        }
        invoiceRepository.delete(foundInvoice.get());
        response.setMessage("delete sucess");
        response.setInvoice(foundInvoice.get());
        return response;
    }

    //5. to delete a list of invoices
    @Transactional
    public List<InvoiceResponse> deleteList(List<Long> idList){
        List<InvoiceResponse> list = new ArrayList<>();
        for(Long id: idList){
            list.add(deleteOne(id));
        }
        return list;
    }

//    //6. to review an invoice.
//    @Transactional
//    public InvoiceResponse modifyOne(Invoice invoice){
//        InvoiceResponse response = new InvoiceResponse();
//        Optional<Invoice> foundInvoice = invoiceRepository.findById(invoice.getId());
//        Optional<Users> foundUser = usersRepository.findById(invoice.getFkStoreManager());
//        Optional<Stores> foundStore = storesRepository.findById(invoice.getFkStore());
//        if(foundInvoice.isEmpty()){
//            response.setMessage("falied! invoice not found");
//            response.setInvoice(null);
//            return response;
//        }
//        if(foundUser.isEmpty()) {
//        	response.setMessage("failed! manager not found");
//        	response.setInvoice(null);
//        	return response;
//        }
//        
//        if(invoice.getStatus() != null) {
//        	if(invoice.getStatus().equals("declined")) {
//        		
//        		
//        		invoice.setStoreManager(foundUser.get());
//        		response.setMessage("invoice declined successfully");
//        		response.setInvoice(invoice);
//        		return response;
//        	}
//        	if(invoice.getStatus().equals("approved")) {
//        		
//        		
//        		Expense expense = new Expense();
//        		
//        		expense.setAmount(invoice.getAmount());
//        		expense.setFkStore(invoice.getFkStore());
//        		
//        		
//        		expense.setStore(foundStore.get());
//        		expense.setFkInvoice(invoice.getId());
//        		expense.setInvoice(foundInvoice.get());
//        		expenseRepository.save(expense);
//        	}
//        }
//        
//        invoice.setStoreManager(foundUser.get());
//        invoice.setStore(foundStore.get());
//        response.setMessage("success! invoice validated and expense recorded.");
//        response.setInvoice(invoiceRepository.save(invoice));
//        return response;
//    }
    
    @Transactional
    public Invoice modifyOne(long id) {
    	Optional<Invoice> foundInvoice = invoiceRepository.findById(id);
    	if(foundInvoice.isEmpty()) {
    		return null;
    	}
    	
    	Invoice invoice = foundInvoice.get();
    	
    	invoice.setStatus("approved");
    	invoiceRepository.save(invoice);
    	
    	Expense expense = new Expense();
		
		expense.setAmount(invoice.getAmount());
		expense.setFkStore(invoice.getFkStore());
		
		Optional<Stores> foundStore = storesRepository.findById(foundInvoice.get().getId());
		if(foundStore.isEmpty()) {
			return null;
		}
		expense.setStore(foundStore.get());
		expense.setFkInvoice(foundInvoice.get().getId());
		expense.setInvoice(foundInvoice.get());
		expenseRepository.save(expense);
		
	List<Stock_Request> request = invoice.getStockRequestsList();
	for(Stock_Request item: request) {
		Optional<Product> foundProduct = productRepository.findById(item.getFkProduct());
		foundProduct.get().setInventoryQuantity(item.getQuantity() + foundProduct.get().getInventoryQuantity());
		productRepository.save(foundProduct.get());
	}
		
		return foundInvoice.get();
    }
}
