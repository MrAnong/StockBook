package StockBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import StockBook.model.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long>{

}
