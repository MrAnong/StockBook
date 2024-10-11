package StockBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import StockBook.model.Product_Category;

@Repository
public interface Product_CategoryRepository extends JpaRepository<Product_Category, Long>{

}
