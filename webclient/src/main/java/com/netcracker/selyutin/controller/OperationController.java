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
import java.util.Map;

@Controller
@RequestMapping(value = "/operations")
public class OperationController {

    private final OrderService orderService;

    private final OfferService offerService;

    private final CategoryService categoryService;

    @Autowired
    public OperationController(OrderService orderService, OfferService offerService, CategoryService categoryService) {
        this.offerService = offerService;
        this.orderService = orderService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView start(HttpSession session, @RequestParam(required = false) String categoryName,
                              @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice) {
        ModelAndView modelAndView = new ModelAndView("operation/main");
        List<Offer> offers = offerService.findWithFilter(createFilter(categoryName, minPrice, maxPrice));
        List<Order> notActivatedOrders = orderService.findCustomerOrdersByStatus(session.getAttribute("mail").toString(), Status.NOT_ACTIVATED);
        List<Order> paidOrders = orderService.findCustomerOrdersByStatus(session.getAttribute("mail").toString(), Status.PAID);
        List<Order> unpaidOrders = orderService.findCustomerOrdersByStatus(session.getAttribute("mail").toString(), Status.UNPAID);
        List<Order> remoteOrders = orderService.findCustomerOrdersByStatus(session.getAttribute("mail").toString(), Status.REMOTE);
        List<Category> categories = categoryService.findAll();
        modelAndView.addObject("offers", offers);
        modelAndView.addObject("notActivatedOrders", notActivatedOrders);
        modelAndView.addObject("paidOrders", paidOrders);
        modelAndView.addObject("unpaidOrders", unpaidOrders);
        modelAndView.addObject("remoteOrders", remoteOrders);
        modelAndView.addObject("order", new Order());
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("categoryName", categoryName);
        modelAndView.addObject("minPrice", minPrice);
        modelAndView.addObject("maxPrice", maxPrice);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView createOrder(@ModelAttribute("order") Order order) {
        orderService.createOrder(order);
        return new ModelAndView("redirect:/operations");
    }

    @GetMapping("/pay/{id}")
    public ModelAndView payOrder(@PathVariable Integer id) {
        orderService.payOrder(id);
        return new ModelAndView("redirect:/operations");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return new ModelAndView("redirect:/operations");
    }

    @GetMapping("/deleteOrderItem")
    public ModelAndView deleteOrderItem(@RequestParam Integer orderId, @RequestParam Integer itemId) {
        orderService.deleteOrderItem(orderId, itemId);
        return new ModelAndView("redirect:/operations");
    }

    @GetMapping("/addOrderItem")
    public ModelAndView addOrderItem(@RequestParam Integer offerId, @RequestParam String orderName, HttpSession session) {
        List<Order> orders = orderService.findCustomerOrdersByStatus(session.getAttribute("mail").toString(), Status.NOT_ACTIVATED);
        for (Order order : orders) {
            if (order.getName().equals(orderName)) {
                orderService.addOrderItem(order.getId(), offerId);
                break;
            }
        }
        return new ModelAndView("redirect:/operations");
    }

    private Map<String, Object> createFilter(String categoryName, Double minPrice, Double maxPrice) {
        Map<String, Object> filter = new HashMap<>();
        filter.put("availability", true);
        if (categoryName != null && !categoryName.equals("--")) {
            filter.put("categoryName", categoryName);
        }
        if (minPrice != null) {
            filter.put("minPrice", minPrice);
        }
        if (maxPrice != null) {
            filter.put("maxPrice", maxPrice);
        }
        return filter;
    }
}
