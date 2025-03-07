package StockBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import StockBook.model.Stock_Request;


@Repository
public interface Stock_RequestRepository extends JpaRepository<Stock_Request, Long>{

	List<Stock_Request> findByFkInvoice(long id);

}
