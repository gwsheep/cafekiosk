package sample.cafekiosk.spring.api.service.product.response;

import jakarta.persistence.*;
import lombok.Getter;
import sample.cafekiosk.spring.domain.product.ProductSellingType;
import sample.cafekiosk.spring.domain.product.ProductType;

@Getter
public class ProducetResponse {

    private Long id;
    private String productNumber;
    private ProductType type;
    private ProductSellingType sellingType;
    private String name;
    private int price;


}
