package com.kwb.saller;

import com.kwb.entity.Order;
import com.kwb.saller.repository.OrderRepository;
import com.kwb.saller.service.OrderService;
import com.kwb.saller.service.VerificationOrderService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VerifyTest {

    @Autowired
    private VerificationOrderService verificationOrderService;

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    @Qualifier("readorderRepository")
    private OrderRepository readorderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void mkVerificationFileTest() {
        Date day = new GregorianCalendar(2017, 0, 1).getTime();
        Calendar cal = new GregorianCalendar(2017, 0, 1);
        System.err.println(day);
        Date day2 = cal.getTime();
        verificationOrderService.mkVerificationFile("kwb", day);
    }

    @Test
    public void saveChanOrdersTest() {
        Date day = new GregorianCalendar(2017, 0, 1).getTime();
        verificationOrderService.saveChanOrders("kwb", day);
    }

    @Test
    public void verifyOrderTest() {
        Date day = new GregorianCalendar(2017, 0, 1).getTime();
        List<String> errors =  verificationOrderService.verifyOrder("kwb", day);
        errors.forEach(str -> System.err.println(str));
    }

    @Test
    public void findAllTest() {
        List<Order> orders1 = orderRepository.findAll();
        System.out.println("------------------------- m-OrderRepository start------------------------------");
        orders1.forEach(order -> System.err.println(order));
        System.out.println("-------------------------m-OrderRepository stop-------------------------------");

        List<Order> orders2 = readorderRepository.findAll();
        System.out.println("------------------------- s-OrderRepository start------------------------------");
        orders2.forEach(order -> System.err.println(order));
        System.out.println("-------------------------s-OrderRepository stop-------------------------------");
    }
}
