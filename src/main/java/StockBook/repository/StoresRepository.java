package StockBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import StockBook.model.Stores;


@Repository
public interface StoresRepository extends JpaRepository<Stores, Long>{

}
