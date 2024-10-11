package StockBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import StockBook.model.Business_Category;

@Repository
public interface Business_CategoryRepository extends JpaRepository<Business_Category, Long>{

}
