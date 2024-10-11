package StockBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import StockBook.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
	
	List<Expense> findByStoreId(long StoreId);
//	
//	// to get the list of transactions carried out by a user of userId during a particular month Month
//		@Query("SELECT s FROM SpendTransaction s WHERE s.user_id = :userId AND MONTH(s.createdAt) = :month")
//		public List<SpendTransaction> findByUserIdAndCreatedAtMonth(@Param("userId") Long userId, @Param("month") Month month);
//		
//		// to get the list of transactions carried out by a user of userId between a specific period of time
//		@Query("SELECT s FROM SpendTransaction s WHERE s.user_id = :userId AND s.createdAt BETWEEN :startDate AND :stopDate")
//		public List<SpendTransaction> findByUserIdAndDateRange(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("stopDate") LocalDate stopDate);
//		
//		// to get the list of transactions done by a particular user
//		@Query("SELECT  s FROM SpendTransaction s WHERE s.user_id = :userId")
//		public List<SpendTransaction> findByUserId(@Param("userId") Long userId);
//		
//		// to get the total amount spent by a user in a particular month
//		@Query("SELECT SUM(s.amount) FROM SpendTransaction s WHERE s.user_id = :userId AND MONTH(s.createdAt) = :month")
//		public BigDecimal findTotalByUserIdAndMonth(@Param("userId") Long userId, @Param("month") Month month);
//		
//		// to get the total amount spent by a particular user
//		@Query("SELECT SUM(s.amount) FROM SpendTransaction s WHERE s.user_id = :userId")
//		public BigDecimal findTotalByUserId(@Param("userId") Long userId);
//		
//		//to get the total amount carried out by user of userid between a specific period of time
//		@Query("SELECT SUM(s.amount) FROM SpendTransaction s WHERE s.user_id = :userId AND s.createdAt BETWEEN :startdate AND :stopDate")
//		public BigDecimal findTotalByUserIdAndDateRange(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("stopDate") LocalDate stopDate);
//		
//		//to get the total amount spent for a particular month in a particular month
//		@Query("SELECT SUM(s.amount) FROM SpendTransaction s WHERE s.user_id = :userId AND s.motif = :motif AND MONTH(s.createdAt) = :month")
//		public BigDecimal findTotalByUserIdANDMotifAndMonth(@Param("userId") Long userId, @Param("motif") String motif, @Param("month") Month month);
//		
//		//to get the total amount spent by a user for a particular motif
//		@Query("SELECT SUM(s.amount) FROM SpendTransaction s WHERE s.user_id = :userId AND s.motif = :motif")
//		public BigDecimal findTotalByUserIdAndMotif(@Param("userId") Long userId, @Param("motif") String motif);
//		
//		//to get the total spent by a user by motif for a given period
//		@Query("SELECT SUM(s.amount) FROM SpendTransaction s WHERE s.user_id = :userId AND s.motif = :motif AND s.createdAt BETWEEN :startDate AND :stopDate")
//		public BigDecimal findTotalByUserIdAndMotifAndDateRange(@Param("userId") Long userId, @Param("motif") String motif, @Param("startDate") LocalDate startDate, @Param("stopDate") LocalDate stopDate);
//		
//		//to get the user's highest spent transaction for a given month
//		@Query("SELECT MAX(s.amount) FROM SpendTransaction s WHERE s.user_id = :userId AND MONTH(s.createdAt) = :month")
//		public BigDecimal findMaxByUserIdAndMonth(@Param("userId") Long userId, @Param("month") Month month);
//		
//		//to get the user's max spent transaction 
//		@Query("SELECT MAX(s.amount) FROM SpendTransaction s WHERE s.user_id = userId")
//		public BigDecimal findMaxByUserId(@Param("userId") Long userId);
//		
//		//to get the user's max spent for a period of time
//		@Query("SELECT MAX(s.amount) FROM SpendTransaction s WHERE s.user_id = :userId AND s.createdAt BETWEEN :startDate AND stopDate")
//		public BigDecimal findMaxByUserIdAndDateRange(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("stopDate") LocalDate stopdate);
//		
//		//to get the user's max spent for a motif during a gicen month
//		@Query("SELECT MAX(s.amount) FROM SpendTransaction s WHERE s.user_id = :userId AND s.motif = :motif AND MONTH(s.createdAt) = :month")
//		public BigDecimal findMaxByUserAndMotifAndMonth(@Param("userId") Long userId, @Param("motif") String motif, @Param("month") Month month);
//		
//		//to get a user's max spent on a particular motif for a period of time
//		@Query("SELECT MAX(s.amount) FROM SpendTransaction s WHERE s.user_id = :userId AND s.motif = :motif AND s.createdAt BETWEEN :startDate AND :stopDate")
//		public BigDecimal findMaxByMotifAndDateRange(@Param("userId") Long userId, @Param("motif") String motif, @Param("startDate") LocalDate startDate, @Param("stopDate") LocalDate stopDate);
//		
//		//to get a user's max spent on a particular motif
//		@Query("SELECT MAX(s.amount) FROM SpendTransaction s WHERE s.user_id = :userId AND s.motif = :motif")
//		public BigDecimal findMaxByMotif(@Param("userId") Long userId, @Param("motif") String motif);
//		
//		//to get all spend transactions carried out during a period of time
//		@Query("SELECT s FROM SpendTransaction s WHERE s.createdAt BETWEEN :startDate AND stopDate")
//		public List<SpendTransaction> findAllByDateRange(@Param("startDate") LocalDate startDate, @Param("stopDate") LocalDate stopDate);

}
