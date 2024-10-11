package StockBook.dto.responses;

import StockBook.model.Income;

public class IncomeResponse {

private String message;
	
	private Income income;

	public IncomeResponse() {
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}
	
	
}
