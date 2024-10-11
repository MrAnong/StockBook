package StockBook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import StockBook.service.NotificationService;

@RestController
@RequestMapping("/stockbook/notification/")
public class NotificationController {

	@Autowired
    private NotificationService notificationService;
}
