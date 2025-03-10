package sample.cafekiosk.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.bevarage.Americano;
import sample.cafekiosk.unit.bevarage.Latte;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class CafekioskTest {

//    @Test
//    void add() {
//        Cafekiosk cafekiosk = new Cafekiosk();
//        cafekiosk.add(new Americano());
//
//        System.out.println(">>> 음료 수 : " + cafekiosk.getBeverages());
//        System.out.println(">>> 음료 : " + cafekiosk.getBeverages().get(0).getName());
//    }

//    @DisplayName("음료 1개 추가 테스트")
    @DisplayName("음료 1개를 추가하면 주문 목록에 담긴다.")
    @Test
    void add() {
        Cafekiosk cafekiosk = new Cafekiosk();
        cafekiosk.add(new Americano());

        assertThat(cafekiosk.getBeverages()).hasSize(1);
        assertThat(cafekiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void addSeveralBeverages() {
        Cafekiosk cafekiosk = new Cafekiosk();
        cafekiosk.add(new Americano(), 2);

        assertThat(cafekiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
        assertThat(cafekiosk.getBeverages().get(1).getName()).isEqualTo("아메리카노");
    }

    @Test
    void addZeroBeverage() {
        Cafekiosk cafekiosk = new Cafekiosk();
        assertThatThrownBy(() -> cafekiosk.add(new Americano(),0))
                                .isInstanceOf(IllegalArgumentException.class)
                                .hasMessage("카페 음료는 0잔을 주문할 수 없습니다");
    }

    @Test
    void remove() {
        Cafekiosk cafekiosk = new Cafekiosk();
        Americano americano = new Americano();

        cafekiosk.add(americano);
        assertThat(cafekiosk.getBeverages()).hasSize(1);

        cafekiosk.remove(americano);
        assertThat(cafekiosk.getBeverages()).isEmpty();
    }

    @Test
    void clear() {
        Cafekiosk cafekiosk = new Cafekiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        cafekiosk.add(americano);
        cafekiosk.add(latte);
        assertThat(cafekiosk.getBeverages()).hasSize(2);

        cafekiosk.clear();
        assertThat(cafekiosk.getBeverages()).isEmpty();
    }

    @DisplayName("주문 목록에 담긴 상품들의 총 금액을 계산할 수 있다")
    @Test
    void calculateTotalPrice() {
        //given
        Cafekiosk cafekiosk = new Cafekiosk();
        Americano americano = new Americano();
        Latte latte = new Latte();

        cafekiosk.add(americano);
        cafekiosk.add(latte);

        //when
        int totalPrice = cafekiosk.calculateTotalPrice();

        //then
        assertThat(totalPrice).isEqualTo(8500);

    }



    @Test
    void createOrder() {
        Cafekiosk cafekiosk = new Cafekiosk();
        Americano americano = new Americano();
        cafekiosk.add(americano);

        Order order = cafekiosk.createOrder();

        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void createOrderWithCurrentTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2025,1,1,10,0);

        Cafekiosk cafekiosk = new Cafekiosk();
        Americano americano = new Americano();
        cafekiosk.add(americano);

        Order order = cafekiosk.createOrder();

        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    void createOrderOutSide() {
        LocalDateTime localDateTime = LocalDateTime.of(2025,1,1,07,0);

        Cafekiosk cafekiosk = new Cafekiosk();
        Americano americano = new Americano();
        cafekiosk.add(americano);

        Order order = cafekiosk.createOrder();

        assertThatThrownBy(() -> cafekiosk.createOrder(localDateTime))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요");

    }


}