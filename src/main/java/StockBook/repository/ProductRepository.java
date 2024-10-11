package StockBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import StockBook.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("SELECT p.costPrice FROM Products p WHERE p.id = :id")
//    float getCostPriceById(@Param("id") long id);

    float findCostPriceById(@Param(value = "id") long id);

}
