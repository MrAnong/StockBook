package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.ExpenseResponse;
import StockBook.dto.responses.IncomeResponse;
import StockBook.model.Expense;
import StockBook.model.Income;
import StockBook.repository.IncomeRepository;
import jakarta.transaction.Transactional;


@Service
public class IncomeService {

	@Autowired
    private IncomeRepository incomeRepository;
	

	//1- to save an income
	@Transactional
	public IncomeResponse saveOne(Income income) {
		incomeRepository.save(income);
		IncomeResponse response = new IncomeResponse();
		
		response.setMessage("Transaction added successfully");
		response.setIncome(income);
		return response;
	}
	
	//2- to modify a saved income
	@Transactional
	public IncomeResponse modifyOne(Income income) {
		IncomeResponse response = new IncomeResponse();
		Optional<Income> foundIncome = incomeRepository.findById(income.getId());
		if(foundIncome.isEmpty()) {
			response.setMessage("Update failed: Transaction not found");
			response.setIncome(null);
			return response;
		}
		incomeRepository.save(foundIncome.get());
		
		response.setMessage("Update successfull");
		response.setIncome(foundIncome.get());
		return response;
	}
	
	//3- to delete a saved income
	@Transactional
	public IncomeResponse deleteOne(Long id) {
		IncomeResponse response = new IncomeResponse();
		Optional<Income> foundIncome = incomeRepository.findById(id);
		if(foundIncome.isEmpty()) {
			response.setMessage("Delete failed: Transaction not found");
			response.setIncome(null);
			return response;
		}
		incomeRepository.deleteById(id);
		
		response.setMessage("Delete successfull");
		response.setIncome(foundIncome.get());
		return response;
	}
	
	
	//4- to delete a list of saved incomes
	@Transactional
	public List<IncomeResponse> deleteList(List<Long> idList){
		IncomeResponse response = new IncomeResponse();
		List<IncomeResponse> list = new ArrayList<IncomeResponse>();
		for(Long id: idList) {
			Optional<Income> foundIncome = incomeRepository.findById(id);
			if(foundIncome.isEmpty()) {
				response.setMessage("Delete failed: Transaction not found");
				response.setIncome(null);
				list.add(response);			
				}
			incomeRepository.deleteById(id);
			response.setMessage("Deleted Successfully");
			response.setIncome(foundIncome.get());
			list.add(response);
		}
		return list;
	}
	
	//5- to get a single income record
	@Transactional
	public IncomeResponse getIncome(long id) {
		IncomeResponse response = new IncomeResponse();
		Optional<Income> foundIncome = incomeRepository.findById(id);
		if(foundIncome.isEmpty()) {
			response.setMessage("failed! expense doesnt exist");
			response.setIncome(null);
			return response;
		}
		response.setMessage("sucessfull");
		response.setIncome(foundIncome.get());
		return response;
	}
	
	//6- to get a list of incomes of a given store
	@Transactional
	public List<Income> getIncomeList(long storeId){
		return incomeRepository.findByStoreId(storeId);
	}
	
	//20- get all incomes
	@Transactional
	public List<Income> getAll(){
		return incomeRepository.findAll();
	}
}
