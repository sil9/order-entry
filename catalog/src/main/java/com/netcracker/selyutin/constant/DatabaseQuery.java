package com.netcracker.selyutin.constant;

public class DatabaseQuery {
    public static final String FIND_CATEGORY_BY_NAME = "SELECT c FROM Category c WHERE c.name = :name";
    public static final String FIND_CATEGORY_OFFERS = "SELECT o FROM Offer o " +
                                                      "WHERE o.category = :category";
    public static final String FIND_OFFERS_BY_TAG = "SELECT o FROM Offer as o JOIN o.tags as t WHERE t.id = :id";
    public static final String FIND_AVAILABLE_OFFERS = "SELECT o FROM Offer o WHERE o.availability = :boolean ";
    public static final String FIND_OFFERS_BY_PRICE = "SELECT o FROM Offer o " +
                                                      "WHERE o.price.value between :firstValue AND :secondValue";
    public static final String FIND_TAGS_BY_NAME = "SELECT t FROM Tag t WHERE t.sign = :sign";
    public static final String FIND_TAGS_BY_OFFER = "SELECT t FROM Tag as t JOIN t.offers as o WHERE o.id = :id";

    private DatabaseQuery() {}
}
