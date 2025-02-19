package sample.cafekiosk.unit.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sample.cafekiosk.unit.bevarage.Beverage;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@RequiredArgsConstructor
public class Order {

    private final LocalDateTime localDateTime;
    private final List<Beverage> beverages;
}
