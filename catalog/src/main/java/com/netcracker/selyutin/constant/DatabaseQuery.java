package com.netcracker.selyutin.constant;

public class DatabaseQuery {
    public static final String FIND_CATEGORY_BY_NAME = "SELECT c FROM Category c WHERE c.name = :name";
    public static final String FIND_CATEGORY_OFFERS = "SELECT o FROM Offer o " +
                                                      "WHERE o.category = :category";

    private DatabaseQuery() {}
}
