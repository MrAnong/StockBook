package StockBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import StockBook.model.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>{

	List<Income> findByStoreId(long StoreId);
	
	

//	// to get the list of transactions carried out by a user of userId during a particular month Month
//	@Query("SELECT e FROM EarnTransaction e WHERE e.user_id = :userId AND MONTH(e.createdAt) = :month")
//	public List<EarnTransaction> findByUserIdAndCreatedAtMonth(@Param("userId") Long userId, @Param("month") Month month);
//	
//	// to get the list of transactions carried out by a user of userId between a specific period of time
//	@Query("SELECT e FROM EarnTransaction e WHERE e.user_id = :userId AND e.createdAt BETWEEN :startDate AND :stopDate")
//	public List<EarnTransaction> findByUserIdAndDateRange(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("stopDate") LocalDate stopDate);
//	
//	// to get the list of transactions done by a particular user
//	@Query("SELECT  e FROM EarnTransaction e WHERE e.user_id = :userId")
//	public List<EarnTransaction> findByUserId(@Param("userId") Long userId);
//	
//	// to get the total amount earned by a user in a particular month
//	@Query("SELECT SUM(e.amount) FROM EarnTransaction e WHERE e.user_id = :userId AND MONTH(e.createdAt) = :month")
//	public BigDecimal findTotalByUserIdAndMonth(@Param("userId") Long userId, @Param("month") Month month);
//	
//	// to get the total amount earned by a particular user
//	@Query("SELECT SUM(e.amount) FROM EarnTransaction e WHERE e.user_id = :userId")
//	public BigDecimal findTotalByUserId(@Param("userId") Long userId);
//	
//	//to get the total amount earned by user of userid between a specific period of time
//	@Query("SELECT SUM(e.amount) FROM EarnTransaction e WHERE e.user_id = :userId AND e.createdAt BETWEEN :startdate AND :stopDate")
//	public BigDecimal findTotalByUserIdAndDateRange(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("stopDate") LocalDate stopDate);
//	
//	//to get the total amount earned for a particular month in a particular month
//	@Query("SELECT SUM(e.amount) FROM EarnTransaction e WHERE e.user_id = :userId AND e.motif = :motif AND MONTH(e.createdAt) = :month")
//	public BigDecimal findTotalByUserIdANDMotifAndMonth(@Param("userId") Long userId, @Param("motif") String motif, @Param("month") Month month);
//	
//	//to get the total amount earned by a user for a particular motif
//	@Query("SELECT SUM(e.amount) FROM EarnTransaction e WHERE e.user_id = :userId AND e.motif = :motif")
//	public BigDecimal findTotalByUserIdAndMotif(@Param("userId") Long userId, @Param("motif") String motif);
//	
//	//to get the total earned by a user by motif for a given period
//	@Query("SELECT SUM(e.amount) FROM EarnTransaction e WHERE e.user_id = :userId AND e.motif = :motif AND e.createdAt BETWEEN :startDate AND :stopDate")
//	public BigDecimal findTotalByUserIdAndMotifAndDateRange(@Param("userId") Long userId, @Param("motif") String motif, @Param("startDate") LocalDate startDate, @Param("stopDate") LocalDate stopDate);
//	
//	//to get the user's highest earned transaction for a given month
//	@Query("SELECT MAX(e.amount) FROM EarnTransaction e WHERE e.user_id = :userId AND MONTH(e.createdAt) = :month")
//	public BigDecimal findMaxByUserIdAndMonth(@Param("userId") Long userId, @Param("month") Month month);
//	
//	//to get the user's max earned transaction 
//	@Query("SELECT MAX(e.amount) FROM EarnTransaction e WHERE e.user_id = userId")
//	public BigDecimal findMaxByUserId(@Param("userId") Long userId);
//	
//	//to get the user's max earned for a period of time
//	@Query("SELECT MAX(e.amount) FROM EarnTransaction e WHERE e.user_id = :userId AND e.createdAt BETWEEN :startDate AND stopDate")
//	public BigDecimal findMaxByUserIdAndDateRange(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("stopDate") LocalDate stopdate);
//	
//	//to get the user's max earned for a motif during a gicen month
//	@Query("SELECT MAX(e.amount) FROM EarnTransaction e WHERE e.user_id = :userId AND e.motif = :motif AND MONTH(e.createdAt) = :month")
//	public BigDecimal findMaxByUserAndMotifAndMonth(@Param("userId") Long userId, @Param("motif") String motif, @Param("month") Month month);
//	
//	//to get a user's max earned on a particular motif for a period of time
//	@Query("SELECT MAX(e.amount) FROM EarnTransaction e WHERE e.user_id = :userId AND e.motif = :motif AND e.createdAt BETWEEN :startDate AND :stopDate")
//	public BigDecimal findMaxByMotifAndDateRange(@Param("userId") Long userId, @Param("motif") String motif, @Param("startDate") LocalDate startDate, @Param("stopDate") LocalDate stopDate);
//	
//	//to get a user's max earned on a particular motif
//	@Query("SELECT MAX(e.amount) FROM EarnTransaction e WHERE e.user_id = :userId AND e.motif = :motif")
//	public BigDecimal findMaxByMotif(@Param("userId") Long userId, @Param("motif") String motif);
//	
//	//to get all earned transactions carried out during a period of time
//	@Query("SELECT e FROM EarnTransaction e WHERE e.createdAt BETWEEN :startDate AND stopDate")
//	public List<EarnTransaction> findAllByDateRange(@Param("startDate") LocalDate startDate, @Param("stopDate") LocalDate stopDate);
}
