package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.entity.Category;
import com.netcracker.selyutin.entity.Offer;
import com.netcracker.selyutin.entity.Order;
import com.netcracker.selyutin.entity.Status;
import com.netcracker.selyutin.service.CategoryService;
import com.netcracker.selyutin.service.OfferService;
import com.netcracker.selyutin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/offers")
public class OfferController {

    private OfferService offerService;

    private CategoryService categoryService;

    private OrderService orderService;

    @Autowired
    public OfferController(OfferService offerService, CategoryService categoryService, OrderService orderService) {
        this.offerService = offerService;
        this.categoryService = categoryService;
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ModelAndView getOffer(@PathVariable Integer id, HttpSession session) {
        ModelAndView model = new ModelAndView("offer/offerItem");
        Offer offer = offerService.findById(id);
        List<Offer> offers = offerService.findRelatedOffers(id);
        List<Order> orders = orderService.findCustomerOrdersByStatus(session.getAttribute("mail").toString(), Status.NOT_ACTIVATED);
        model.addObject("offer", offer);
        model.addObject("offers", offers);
        model.addObject("orders", orders);
        return model;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("offer/list");
        List<Offer> offers = offerService.findWithFilter(new HashMap<>());
        model.addObject("offers", offers);
        return model;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView model = new ModelAndView("offer/createForm");
        Offer offer = new Offer();
        List<Category> categories = categoryService.findAll();
        model.addObject("offer", offer);
        model.addObject("categories", categories);
        return model;
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Integer id) {
        ModelAndView model = new ModelAndView("offer/updateForm");
        Offer offer = offerService.findById(id);
        List<Category> categories = categoryService.findAll();
        model.addObject("offer", offer);
        model.addObject("categories", categories);
        return model;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id) {
        offerService.delete(id);
        return new ModelAndView("redirect:/offers/list");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("offer") Offer offer) {
        if (offer.getId() != null) {
            offerService.update(offer);
        } else {
            offerService.create(offer);
        }
        return new ModelAndView("redirect:/offers/list");
    }
}
