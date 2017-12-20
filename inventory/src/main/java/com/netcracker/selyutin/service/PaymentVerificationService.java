package com.netcracker.selyutin.service;

import com.netcracker.selyutin.constant.LoggerConstant;
import com.netcracker.selyutin.dao.OrderDao;
import com.netcracker.selyutin.entity.Status;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class PaymentVerificationService {

    private static final Logger LOGGER = LogManager.getLogger(PaymentVerificationService.class);

    private final OrderDao orderDao;

    @Autowired
    public PaymentVerificationService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Scheduled(fixedRate = 300000)
    @Transactional
    public void changeOrderStatusToUnpaid() {
        LOGGER.info("Start search orders...");
        orderDao.changeOrderStatus(LocalDate.now(), Status.PAID, Status.UNPAID);
        LOGGER.info(LoggerConstant.TRANSACTION_SUCCEEDED);
    }
}
