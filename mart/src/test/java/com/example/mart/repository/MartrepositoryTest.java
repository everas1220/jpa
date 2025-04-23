package com.example.mart.repository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entity.CategoryItem;
import com.example.mart.entity.Delivery;
import com.example.mart.entity.Item;
import com.example.mart.entity.Member;
import com.example.mart.entity.Order;
import com.example.mart.entity.OrderItem;
import com.example.mart.entity.constant.DeliveryStatus;
import com.example.mart.entity.constant.OrderStatus;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MartrepositoryTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    public void testMemberInsert() {
        IntStream.rangeClosed(1, 5).forEach(i -> {
            Member member = Member.builder()
                    .name("user" + i)
                    .city("서울" + i)
                    .street("724-11" + i)
                    .zipcode("1650" + i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    public void testItemInsert() {
        IntStream.rangeClosed(1, 5).forEach(i -> {
            Item item = Item.builder()
                    .name("티셔츠" + i)
                    .price(i * 20000)
                    .stockQuantity(i * 5)
                    .build();
            itemRepository.save(item);
        });
    }

    // 주문하다 : Order + OrderItem insert
    @Test
    public void testOrderInsert() {
        Order order = Order.builder()
                .member(Member.builder().id(1L).build())
                .orderDate(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDER)
                .build();
        orderRepository.save(order);

        // 주문과 관련된 상품은 OrderItem 삽입
        OrderItem orderItem = OrderItem.builder()
                .item(itemRepository.findById(2L).get())
                .order(order)
                .orderPrice(39000)
                .count(1)
                .build();
        orderItemRepository.save(orderItem);
        orderItem = OrderItem.builder()
                .item(itemRepository.findById(3L).get())
                .order(order)
                .orderPrice(39000)
                .count(1)
                .build();
        orderItemRepository.save(orderItem);
    }

    // 조회
    @Transactional
    @Test
    public void testRead1() {
        // 주문 조회(주문번호 이용)
        Order order = orderRepository.findById(2L).get();
        System.out.println(order);

        // 주문자 정보 조회
        System.out.println(order.getMember());
    }

    @Transactional
    @Test
    public void testRead2() {
        // 특정회원의 주문 내역 전체 조회
        Member member = memberRepository.findById(2L).get();
        System.out.println(member.getOrders());
    }

    @Transactional
    @Test
    public void testRead3() {
        // 주문상품의 정보를 조회
        OrderItem orderItem = orderItemRepository.findById(1L).get();
        System.out.println(orderItem); // (id=1, orderPrice=19600, count=1)

        // 주문 상품의 상품명 조회
        System.out.println(orderItem.getItem().getName());

        // 상품을 주문한 고객 정보를 조회
        Member member = orderItem.getOrder().getMember();
        System.out.println(member);
    }

    @Transactional
    @Test
    public void testRead4() {
        // 주문을 통해 주문아이템 조회
        Order order = orderRepository.findById(6L).get();
        System.out.println(order);

        order.getOrderItems().forEach(item -> System.out.println(item));
    }

    // 삭제
    @Test
    public void testDelete() {
        // memberRepository.deleteById(5L); 하위정보가 없기 때문에 쉽게 삭제할수있음
        // memberRepository.deleteById(1L); // 하지만 하위정보가 있는 상위 메소드의 경우엔 오류가 뜸

        // member id로 주문 찾아오기

        // 상품주문 취소
        // 주문 취소
        // 멤버 제거
        memberRepository.deleteById(1L);
    }

    // 배송정보 입력
    @Test
    public void testDeliveryInsert() {
        Delivery delivery = Delivery.builder()
                .zipcode("15011")
                .city("부산")
                .street("120-11")
                .deliveryStatus(DeliveryStatus.READY)
                .build();
        deliveryRepository.save(delivery);

        // 주문과 연결
        Order order = orderRepository.findById(2L).get();
        order.setDelivery(delivery);
        orderRepository.save(order);
    }

    @Test
    public void testDeliveryRead1() {
        System.out.println(deliveryRepository.findById(1L));
    }

    // 배송과 관련있는 주문 조회(X) => 양방향
    @Transactional
    @Test
    public void testDeliveryRead2() {
        // 배송 조회
        Delivery delivery = deliveryRepository.findById(1L).get();
        System.out.println("주문 조회" + delivery.getOrder());
        System.out.println("주문자 조회" + delivery.getOrder().getMember());
        System.out.println("주문 아이템 조회" + delivery.getOrder().getOrderItems());

    }

    @Test
    public void testDeliveryInsert2() {
        Delivery delivery = Delivery.builder()
                .zipcode("15011")
                .city("부산")
                .street("120-11")
                .deliveryStatus(DeliveryStatus.READY)
                .build();
        // deliveryRepository.save(delivery);

        // 주문과 연결
        Order order = orderRepository.findById(3L).get();
        order.setDelivery(delivery);
        orderRepository.save(order);
    }

    @Test
    public void deleteTest() {
        // order를 지우면서 배송정보 삭제, 주문상품 제거
        orderRepository.deleteById(1L);
    }


    Item item = Item.builder().name("Tv").price(25000000).stockQuantity(category1).build();
    categoryItemRepository.save(item1);
    
    CategoryItem categoryItem = CategoryItem.builder().category(category1).item(item1).build();
    categoryItemRepository.save(categoryItem);

    iteam1 = item.builder().name("콩나물").price(1200).stockQuantity(5).build;
    itemRepository.save(item);

    categoryItem = CategoryItem.builder().Category(category2).item






}