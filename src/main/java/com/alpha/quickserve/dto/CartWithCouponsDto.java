package com.alpha.quickserve.dto;

import java.util.List;

import com.alpha.quickserve.entity.CartItem;
import com.alpha.quickserve.entity.Coupon;

public class CartWithCouponsDto {
	
	private List<CartItem> cartItems;
    private double cartTotal;
    private List<Coupon> coupons;
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public double getCartTotal() {
		return cartTotal;
	}
	public void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
	}
	public List<Coupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
	public CartWithCouponsDto(List<CartItem> cartItems, double cartTotal, List<Coupon> coupons) {
		super();
		this.cartItems = cartItems;
		this.cartTotal = cartTotal;
		this.coupons = coupons;
	}
	public CartWithCouponsDto() {
		super();
	}
	@Override
	public String toString() {
		return "CartWithCouponsDto [cartItems=" + cartItems + ", cartTotal=" + cartTotal + ", coupons=" + coupons + "]";
	}
    
    
}

