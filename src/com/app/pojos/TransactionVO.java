package com.app.pojos;

import java.util.List;

public class TransactionVO {

	private String sessionToken;
	
	private List<Product> product;
	
	private Address address;

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "TransactionVO [product=" + product + ", address=" + address + "]";
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	
	
	
}
