package sample.cafekiosk.unit;

import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.bevarage.Americano;

import static org.junit.jupiter.api.Assertions.*;

class CafekioskTest {

    @Test
    void add() {
        Cafekiosk cafekiosk = new Cafekiosk();
        cafekiosk.add(new Americano());

        System.out.println(">>> 음료 수 : " + cafekiosk.getBeverages());
        System.out.println(">>> 음료 : " + cafekiosk.getBeverages().get(0).getName());

    }

}