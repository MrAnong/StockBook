package StockBook.dto.responses;

import StockBook.model.Expense;

public class ExpenseResponse {

	private String message;
	
	private Expense expense;

	public ExpenseResponse() {
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}
}
