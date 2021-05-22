package com.goldenburguer.app.services;

import com.goldenburguer.app.dto.dashboard.DashboardDTO;
import com.goldenburguer.app.dto.dashboard.QntSellingProductDTO;
import com.goldenburguer.app.entities.Customer;
import com.goldenburguer.app.entities.Order;
import com.goldenburguer.app.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final ItemRepository itemRepository;


    public DashboardDTO getDashboard() {
        DashboardDTO dashboard =
                DashboardDTO.builder()
                        //.averageOrderPriceAll()
                        //.averageOrderPriceMonth()
                        //.averageOrderPriceWeek()
                        //.averageOrdersToUser()
                        //.bestSellingProductAll()
                        //.bestSellingProductMonth()
                        //.bestSellingProductWeek()
                        .customerQuantity(getAllCustomer().size())
                        .customerActivedQuantity(getActivedCustomer().size())
                        .orderQuantityAll(getOrdersDeliveredAll().size())
            .orderQuantityThirtyDays(getOrdersDeliveredLastThirtyDays().size())
            .orderQuantitySevenDays(getOrdersDeliveredLastSevenDays().size())
            .build();

        return dashboard;
    }

    public List<Customer> getAllCustomer() {
        List<Customer> customers = customerService.listAllCustomers();
        return customers;
    }

    public List<Customer> getActivedCustomer() {

        List<Customer> customersActived =
                getAllCustomer().stream()
                        .filter(customer -> customer.getStatus() == true)
                        .collect(Collectors.toList());

        return customersActived;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = orderService.listAllOrders();
        return orders;
    }

    public List<Order> getOrdersDeliveredAll() {
        List<Order> orders =
                getAllOrders().stream()
                        .filter(order -> null != order.getFinishedAt())
                        .collect(Collectors.toList());
        return orders;
    }

    public List<Order> getOrdersDeliveredLastSevenDays() {
        LocalDateTime dateLimitFinish = LocalDateTime.now().minusDays(7);
        List<Order> orders = getAllOrders().stream()
                .filter(order -> null != order.getFinishedAt() && isBetween(dateLimitFinish, order.getFinishedAt()))
                .collect(Collectors.toList());
        return orders;
    }

    public List<Order> getOrdersDeliveredLastThirtyDays() {
        LocalDateTime dateLimitFinish = LocalDateTime.now().minusDays(30);
        List<Order> orders = getAllOrders().stream()
                .filter(order -> null != order.getFinishedAt() && isBetween(dateLimitFinish, order.getFinishedAt()))
                .collect(Collectors.toList());
        return orders;
    }


    public Boolean isBetween(LocalDateTime finalRange, LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now();

        if (date.isAfter(finalRange) && date.isBefore(now)) {
            return true;
        }
        return false;
    }

    public List<QntSellingProductDTO> getProductBestSelling(){
       var a =  itemRepository.qntSellingProductDTO();
        return null;

    }


}
