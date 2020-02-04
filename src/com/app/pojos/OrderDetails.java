package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetails {
	@Id
	@Column(name = "order_deatils_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderDeatilsId;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "payment_method")
	private String  paymentMethod;

	public long getOrderDeatilsId() {
		return orderDeatilsId;
	}

	public void setOrderDeatilsId(long orderDeatilsId) {
		this.orderDeatilsId = orderDeatilsId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = d;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderDeatilsId=" + orderDeatilsId + ", product=" + product + ", order=" + order
				+ ", price=" + price + ", paymentMethod=" + paymentMethod + "]";
	}

}
