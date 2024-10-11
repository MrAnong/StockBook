package StockBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import StockBook.model.Suppliers;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Long>{

}
