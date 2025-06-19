package com.bootcamp.latihan.entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items = new ArrayList<>();
    public void addItem(OrderItem item) {
        items.add(item);
    }
    public List<OrderItem> getItems() {
        return items;
    }
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
    
    
    
}



