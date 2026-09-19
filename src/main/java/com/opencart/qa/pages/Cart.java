package com.opencart.qa.pages;

public class Cart {

	int i=1;
	int j=2;
	int sum;
	int mul;
	
	public void sum() {
		sum=i+j;
		System.out.println(sum);
	}
	public void mul() {
		mul=i*j;
		System.out.println(mul);
	}
	
	public static void main(String[] args) {
		Cart crt=new Cart();
		crt.sum();
		crt.mul();
	}

}
