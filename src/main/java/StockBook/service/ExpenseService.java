package StockBook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.dto.responses.ExpenseResponse;
import StockBook.model.Expense;
import StockBook.repository.ExpenseRepository;
import jakarta.transaction.Transactional;


@Service
public class ExpenseService {

	 @Autowired
	    private ExpenseRepository expenseRepository;
		
		//////////// USER USE CASES ///////////
		
		//1- to save a spend transaction
		@Transactional
		public ExpenseResponse saveOne(Expense expense) {
			expenseRepository.save(expense);
			ExpenseResponse response = new ExpenseResponse();
			
			response.setMessage("Transaction added successfully");
			response.setExpense(expense);
			return response;
		}
		
		//2- to modify a saved spend transaction
		@Transactional
		public ExpenseResponse modifyOne(Expense expense) {
			ExpenseResponse response = new ExpenseResponse();
			Optional<Expense> foundTransaction = expenseRepository.findById(expense.getId());
			if(foundTransaction.isEmpty()) {
				response.setMessage("Update failed: Transaction not found");
				response.setExpense(null);
				return response;
			}
			expenseRepository.save(foundTransaction.get());
			
			response.setMessage("Update successfull");
			response.setExpense(foundTransaction.get());
			return response;
		}
		
		//3- to delete a saved spend transaction
		@Transactional
		public ExpenseResponse deleteOne(Long id) {
			ExpenseResponse response = new ExpenseResponse();
			Optional<Expense> foundTransaction = expenseRepository.findById(id);
			if(foundTransaction.isEmpty()) {
				response.setMessage("Delete failed: Transaction not found");
				response.setExpense(null);
				return response;
			}
			expenseRepository.deleteById(id);
			
			response.setMessage("Delete successfull");
			response.setExpense(foundTransaction.get());
			return response;
		}
		
		
		//4- to delete a list of saved spend transactions
		@Transactional
		public List<ExpenseResponse> deleteList(List<Long> idList){
			ExpenseResponse response = new ExpenseResponse();
			List<ExpenseResponse> list = new ArrayList<ExpenseResponse>();
			for(Long id: idList) {
				Optional<Expense> foundTransaction = expenseRepository.findById(id);
				if(foundTransaction.isEmpty()) {
					response.setMessage("Delete failed: Transaction not found");
					response.setExpense(null);
					list.add(response);			
					}
				expenseRepository.deleteById(id);
				response.setMessage("Deleted Successfully");
				response.setExpense(foundTransaction.get());
				list.add(response);
			}
			return list;
		}
		
		//5- to get a single expense record
		@Transactional
		public ExpenseResponse getExpense(long id) {
			ExpenseResponse response = new ExpenseResponse();
			Optional<Expense> foundExpense = expenseRepository.findById(id);
			if(foundExpense.isEmpty()) {
				response.setMessage("failed! expense doesnt exist");
				response.setExpense(null);
				return response;
			}
			response.setMessage("sucessfull");
			response.setExpense(foundExpense.get());
			return response;
		}
		
		//6- to get a list of expenses of a given store
		@Transactional
		public List<Expense> getExpenseList(long storeId){
			return expenseRepository.findByStoreId(storeId);
		}
//		
//		//5- to get all user spend transactions for a given month ( user-id, month)
//		@Transactional
//		public List<SpendTransaction> getByMonth(Long userId, Month month){
//			return spendTransactionRepository.findByUserIdAndCreatedAtMonth(userId, month);
//		}
//		
//		//6- to get all user spend transactions for a period between X and Y ( user-id, X, Y )
//		@Transactional
//		public List<SpendTransaction> getByRange(Long userId, LocalDate startDate, LocalDate stopDate){
//			return spendTransactionRepository.findByUserIdAndDateRange(userId, startDate, stopDate);
//		}
//		
//		//7- to get all user spend transactions for all the months ( user-id,loop all months )
//		@Transactional
//		public List<SpendTransaction> getByUserId(Long userId){
//			return spendTransactionRepository.findByUserId(userId);	}
//		
//		//8- to get the total amount in a user's spend transaction per month ( user-id, month )
//		@Transactional
//		public BigDecimal getTotalPerMonth(Long userId, Month month) {
//			return spendTransactionRepository.findTotalByUserIdAndMonth(userId, month);
//		}
//		
//		//9- to get the total amount in a user's spend transaction for all the months ( user-is, loop all months )
//		@Transactional
//		public BigDecimal getTotal(Long userId) {
//			return spendTransactionRepository.findTotalByUserId(userId);
//		}
//		
//		//10- to get the total amount in a user's spend transaction for a period between X and Y ( user-id, X, Y )
//		@Transactional
//		public BigDecimal getTotalByRange(Long userId, LocalDate startDate, LocalDate stopDate) {
//			return spendTransactionRepository.findTotalByUserIdAndDateRange(userId, startDate, stopDate);
//		}
//		
//		//11- to get the total amount in a user's spend transaction of a particular motif for a given month ( user-id, motif, month )
//		@Transactional
//		public BigDecimal getTotalByMotifPerMonth(Long userId, String motif, Month month) {
//			return spendTransactionRepository.findTotalByUserIdANDMotifAndMonth(userId,  motif,  month);
//		}
//		
//		//12- to get the total amount in a user's spend transaction of a particular motif for all months ( user-id, motif, loop all months )
//		@Transactional
//		public BigDecimal getTotalByMotif(Long userId, String motif) {
//			return spendTransactionRepository.findTotalByUserIdAndMotif(userId, motif);
//		}
//		
//		//13- to get the total amount in a user's spend transaction of a particular motif between a period X and Y ( user-id, motif, X and Y )
//		@Transactional
//		public BigDecimal getToTalByMotifByRange(Long userId, String motif, LocalDate startDate, LocalDate stopDate) {
//			return spendTransactionRepository.findTotalByUserIdAndMotifAndDateRange(userId, motif, startDate, stopDate);
//		}
//		
//		//14- to get the user's highest spend transaction amount for a given month ( user-id, month )
//		@Transactional
//		public BigDecimal getMaxByMonth(Long userId, Month month) {
//			return spendTransactionRepository.findMaxByUserIdAndMonth(userId, month);
//		}
//		
//		//15- to get the user's highest spend transaction amount for all the months ( user-id, loop the months )
//		@Transactional
//		public BigDecimal getMax(Long userId) {
//			return spendTransactionRepository.findMaxByUserId(userId);
//		}
//		
//		//16- to get the user's highest spend transaction amount between period X and Y ( user-id, X, Y )
//		@Transactional
//		public BigDecimal getMaxByRange(Long userId, LocalDate startDate, LocalDate stopDate) {
//			return spendTransactionRepository.findMaxByUserIdAndDateRange(userId, startDate, stopDate);
//		}
//		
//		//17- to get the max spent for a particular motif during a given month
//		@Transactional
//		public BigDecimal getMaxByMotifByMonth(Long userId, String motif, Month month) {
//			return spendTransactionRepository.findMaxByUserAndMotifAndMonth(userId, motif, month);
//		}
//		
//		//18- to get the max spent for a particular motif during a given period of time
//		@Transactional
//		public BigDecimal getMaxByMotifByRange(Long userId, String motif, LocalDate startDate, LocalDate stopDate) {
//			return spendTransactionRepository.findMaxByMotifAndDateRange(userId, motif, startDate, stopDate);
//		}
//		
//		//19- to get the max spent for a particular motif 
//		@Transactional
//		public BigDecimal getMaxByMotif(Long userId, String motif) {
//			return spendTransactionRepository.findMaxByMotif(userId, motif);
//		}
//		
//		
//		
//		////////// ADMIN USE CASES //////////
//		
		//20- get all expenses
		@Transactional
		public List<Expense> getAll(){
			return expenseRepository.findAll();
		}
//		
//		//21- get all transactions between period X and Y ( start, stop )
//		@Transactional
//		public List<SpendTransaction> getAllByDateRange(LocalDate startDate, LocalDate stopdate){
//			return spendTransactionRepository.findAllByDateRange(startDate, stopdate);
//		}
}
