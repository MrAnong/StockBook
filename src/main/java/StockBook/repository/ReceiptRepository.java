package StockBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import StockBook.model.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long>{

	List<Receipt> findByFkStore(long id);

}
