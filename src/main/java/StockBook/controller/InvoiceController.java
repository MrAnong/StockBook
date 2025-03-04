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

import StockBook.dto.responses.InvoiceResponse;
import StockBook.model.Invoice;
import StockBook.service.InvoiceService;

@RestController
@RequestMapping("/stockbook/invoice/")
public class InvoiceController {

	@Autowired
    private InvoiceService invoiceService;

    //1. to add an invoice
    @PostMapping("add")
    public InvoiceResponse addOne(@RequestBody Invoice invoice){
        return invoiceService.addInvoice(invoice);
    }

    //2. to get an invoice
    @PostMapping("findOne")
    public InvoiceResponse getOne(@RequestParam Long id){
        return invoiceService.getInvoice(id);
    }

    //3. to get all invoices
    @GetMapping("findAll")
    public List<Invoice> getAll(){
        return invoiceService.getAll();
    }

    //4. to delete an invoice
    @DeleteMapping("deleteOne")
    public InvoiceResponse deleteOne(Long id){
        return invoiceService.deleteOne(id);
    }

    //5. to delete list
    @DeleteMapping("deleteList")
    public List<InvoiceResponse> deleteList(List<Long> idList){
        return invoiceService.deleteList(idList);
    }

    //6. to modify an invoice
    @PutMapping("update")
    public Invoice update(@RequestParam long id){
        return invoiceService.modifyOne(id);
    }
}
