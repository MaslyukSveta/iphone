package com.product.iphone.worker;
import com.product.iphone.item.OrderItemDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CreateOrderRequest {
    @Singular
    private List<OrderItemDTO> items;

    public CreateOrderRequest() {
        this.items = new ArrayList<>();
    }

    public CreateOrderRequest(List<OrderItemDTO> items) {
        this.items = items;
    }

    // Getters and Setters
    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "items=" + items +
                '}';
    }
}

