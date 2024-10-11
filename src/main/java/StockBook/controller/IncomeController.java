package StockBook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import StockBook.dto.responses.IncomeResponse;
import StockBook.model.Income;
import StockBook.service.IncomeService;


@RestController
@RequestMapping("/stockbook/income/")
public class IncomeController {

	@Autowired
    private IncomeService incomeService;
	
	//1. to add an income
    @PostMapping("add")
    public IncomeResponse addOne(Income income){
        return incomeService.saveOne(income);
    }

    //2. to get an income
    @GetMapping("findOne")
    public IncomeResponse getOne(Long id){
        return incomeService.getIncome(id);
    }
    
  // to get an income list of a particular store
    @DeleteMapping("findList")
    public List<Income> findList(long storeId){
        return incomeService.getIncomeList(storeId);
    }

    //3. to get all income
    @GetMapping("findAll")
    public List<Income> getAll(){
        return incomeService.getAll();
    }

    //4. to delete an income
    @DeleteMapping("deleteOne")
    public IncomeResponse deleteOne(Long id){
        return incomeService.deleteOne(id);
    }

    //5. to delete an income list
    @DeleteMapping("deleteList")
    public List<IncomeResponse> deleteList(List<Long> idList){
        return incomeService.deleteList(idList);
    }

    //6. to modify an income
    @PutMapping("update")
    public IncomeResponse update(Income income){
        return incomeService.modifyOne(income);
    }
}
