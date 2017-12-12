package com.netcracker.selyutin.dao.impl;


import com.netcracker.selyutin.constant.DatabaseQuery;
import com.netcracker.selyutin.dao.OfferDao;
import com.netcracker.selyutin.entity.Offer;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class OfferDaoImpl extends GenericDao<Offer> implements OfferDao {

    OfferDaoImpl() {
        super(Offer.class);
    }

    @Override
    public List<Offer> findByTag(int id) {
        List<Offer> offers = new ArrayList<>();
        TypedQuery<Offer> query = entityManager.createQuery(DatabaseQuery.FIND_OFFERS_BY_TAG, Offer.class);
        query.setParameter("id", id);
        try {
            offers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offers;
    }

    @Override
    public List<Offer> findAvailable() {
        List<Offer> offers = new ArrayList<>();
        TypedQuery<Offer> query = entityManager.createQuery(DatabaseQuery.FIND_AVAILABLE_OFFERS, Offer.class);
        query.setParameter("boolean", true);
        try {
            offers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offers;
    }

    @Override
    public List<Offer> getByPrice(Double firstValue, Double secondValue) {
        List<Offer> offers = new ArrayList<>();
        TypedQuery<Offer> query = entityManager.createQuery(DatabaseQuery.FIND_OFFERS_BY_PRICE, Offer.class);
        query.setParameter("firstValue", firstValue);
        query.setParameter("secondValue", secondValue);
        try {
            offers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offers;
    }

    @Override
    public List<Offer> getAllWithFilter(Map<String, Object> filters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Offer> criteriaQuery = criteriaBuilder.createQuery(Offer.class);
        Root<Offer> offerRoot = criteriaQuery.from(Offer.class);
        List<Predicate> predicates = new ArrayList<>();
        Object minPrice = filters.get("minPrice");
        if (minPrice != null) {
            Predicate predicate = criteriaBuilder.gt(offerRoot.get("price").get("value"), Double.parseDouble(minPrice.toString()));
            predicates.add(predicate);
        }
        Object maxPrice = filters.get("maxPrice");
        if (maxPrice != null) {
            Predicate predicate = criteriaBuilder.lt(offerRoot.get("price").get("value"), Double.parseDouble(maxPrice.toString()));
            predicates.add(predicate);
        }
        Object categoryName = filters.get("categoryName");
        if (categoryName != null) {
            Predicate predicate = criteriaBuilder.equal(offerRoot.get("category").get("name"), categoryName.toString());
            predicates.add(predicate);
        }
        List<String> tagsNames = (List<String>) filters.get("tagName");
        if (tagsNames != null) {
            Join t = offerRoot.join("tags", JoinType.LEFT);
            for (String tagName : tagsNames) {
                Predicate predicate = criteriaBuilder.equal(t.get("sign"), tagName);
                predicates.add(predicate);
            }
        }
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
