package StockBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import StockBook.repository.NotificationRepository;


@Service
public class NotificationService {

	@Autowired
    private NotificationRepository notificationRepository;
}
