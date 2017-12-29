package com.netcracker.selyutin.constant;

public interface ExceptionMessage {

    String ENTITY_NOT_FOUND = " not found with id=";

    String FAILED_ADD_ITEM_TO_ORDER = "Can not add orderItem to Order with status ";

    String FAILED_DELETE_ITEM_FROM_ORDER = "Can not remove orderItem from Order with status";

    String FAILED_PAY_ORDER = "Can not pay order with status ";

    String FAILED_DELETE_ORDER = "Can not delete order with status ";
}
