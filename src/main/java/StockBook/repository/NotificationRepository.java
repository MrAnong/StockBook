package StockBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import StockBook.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
