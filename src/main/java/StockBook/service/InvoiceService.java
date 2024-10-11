package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.InvoiceResponse;
import StockBook.model.Invoice;
import StockBook.repository.InvoiceRepository;
import jakarta.transaction.Transactional;

@Service
public class InvoiceService {

	@Autowired
    private InvoiceRepository invoiceRepository;

    //1. to add an invoice
    @Transactional
    public InvoiceResponse addInvoice(Invoice invoice){
        InvoiceResponse response = new InvoiceResponse();
        response.setInvoice(invoiceRepository.save(invoice));
        response.setMessage("added successfully");
        
        //TODO: call the addrequestList method and pass the stockrequestList together with the invoice id
        return response;
    }

    //2. to get an invoice
    @Transactional
    public InvoiceResponse getInvoice(Long id){
        InvoiceResponse response = new InvoiceResponse();
        Optional<Invoice> foundInvoice = invoiceRepository.findById(id);
        if(foundInvoice.isEmpty()){
            response.setMessage("category not found");
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

    //6. to modify an invoice.
    @Transactional
    public InvoiceResponse modifyOne(Invoice invoice){
        InvoiceResponse response = new InvoiceResponse();
        Optional<Invoice> foundInvoice = invoiceRepository.findById(invoice.getId());
        if(foundInvoice.isEmpty()){
            response.setMessage("falied! category not found");
            response.setInvoice(null);
            return response;
        }
        response.setMessage("success");
        response.setInvoice(invoiceRepository.save(invoice));
        return response;
    }
}
