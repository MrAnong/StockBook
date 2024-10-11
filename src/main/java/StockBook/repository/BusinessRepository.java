package StockBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import StockBook.model.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long>{

}
