package com.netcracker.selyutin.constant;

public interface DatabaseQuery {

    String FIND_ALL_CUSTOMER_ORDERS = "SELECT o FROM t_order as o WHERE o.customerEmail = :email";
}
