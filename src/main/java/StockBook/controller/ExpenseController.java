package StockBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import StockBook.dto.responses.ExpenseResponse;
import StockBook.model.Expense;
import StockBook.service.ExpenseService;

@RestController
@RequestMapping("/stockbook/expense/")
public class ExpenseController {

	@Autowired
    private ExpenseService expenseService;
	
	//1. to add an expense
    @PostMapping("add")
    public ExpenseResponse addOne(Expense expense){
        return expenseService.saveOne(expense);
    }

    //2. to get an expense
    @GetMapping("findOne")
    public ExpenseResponse getOne(Long id){
        return expenseService.getExpense(id);
    }
    
  // to get an expense list of a particular store
    @PostMapping("findList")
    public List<Expense> findList(@RequestParam long id){
        return expenseService.getExpenseList(id);
    }
    
    @PostMapping("findTotal")
    public float totalExpense(@RequestParam long id) {
    	List<Expense> list = findList(id);
    	float total = 0;
    	
    	for(Expense expense: list) {
    		total = total + expense.getAmount();
    	}
    	return total;
    }

    //3. to get all expenses
    @GetMapping("findAll")
    public List<Expense> getAll(){
        return expenseService.getAll();
    }

    //4. to delete an expense
    @DeleteMapping("deleteOne")
    public ExpenseResponse deleteOne(Long id){
        return expenseService.deleteOne(id);
    }

    //5. to delete an expense list
    @DeleteMapping("deleteList")
    public List<ExpenseResponse> deleteList(List<Long> idList){
        return expenseService.deleteList(idList);
    }

    //6. to modify an expense
    @PutMapping("update")
    public ExpenseResponse update(Expense expense){
        return expenseService.modifyOne(expense);
    }
}
