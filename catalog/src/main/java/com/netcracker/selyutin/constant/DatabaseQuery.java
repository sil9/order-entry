package com.netcracker.selyutin.constant;

public interface DatabaseQuery {

    String FIND_CATEGORY_BY_NAME = "SELECT c FROM Category c WHERE c.name = :name";

    String FIND_CATEGORY_OFFERS = "SELECT o FROM Offer o WHERE o.category = :category";

    String FIND_OFFERS_BY_TAG = "SELECT o FROM Offer as o JOIN o.tags as t WHERE t.id = :id";

    String FIND_AVAILABLE_OFFERS = "SELECT o FROM Offer o WHERE o.availability = :boolean ";

    String FIND_OFFERS_BY_PRICE = "SELECT o FROM Offer o WHERE o.price.value between :firstValue AND :secondValue";

    String FIND_TAGS_BY_NAME = "SELECT t FROM Tag t WHERE t.sign = :sign";

    String FIND_TAGS_BY_OFFER = "SELECT t FROM Tag as t JOIN t.offers as o WHERE o.id = :id";
}
