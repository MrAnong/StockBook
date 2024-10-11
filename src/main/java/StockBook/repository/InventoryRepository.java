package StockBook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import StockBook.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	@Query("SELECT i FROM Inventory i WHERE i.product.id = :productId")
	Optional<Inventory> findByProductId(@Param("productId") Long productId);
}
