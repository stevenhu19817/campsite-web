package com.Product.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Arrays;

import com.oracle.webservices.internal.api.EnvelopeStyle;

public class ProductVO implements Serializable {
	private Integer product_no;
	private String product_name;
	private String product_sort;
	private Integer admin_id;
	public Integer getProduct_no() {
		return product_no;
	}


	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public String getProduct_sort() {
		return product_sort;
	}


	public void setProduct_sort(String product_sort) {
		this.product_sort = product_sort;
	}


	public Integer getAdmin_id() {
		return admin_id;
	}


	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}


	private Integer price;
	private Integer inventory;
	private Integer situation;
	private String descript;
	private byte[] picture1;
	private byte[] picture2;
	private byte[] picture3;
	
	
	public ProductVO() {
		super();
	}


	public Integer getProductno() {
		return product_no;
	}
	
	public void setProductno(Integer productno) {
		this.product_no = productno;
	}

	public Integer getSituation() {
		return situation;
	}


	public void setSituation(Integer situation) {
		this.situation = situation;
	}



	public String getPname() {
		return product_name;
	}

	public void setPname(String pname) {
		this.product_name = pname;
	}

	public String getPsort() {
		return product_sort;
	}

	public void setPsort(String psort) {
		this.product_sort = psort;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public byte[] getPicture1() {
		return picture1;
	}

	public void setPicture1(byte[] picture1) {
		this.picture1 = picture1;
	}
	

	public byte[] getPicture2() {
		return picture2;
	}

	public void setPicture2(byte[] picture2) {
		this.picture2 = picture2;
	}

	public byte[] getPicture3() {
		return picture3;
	}

	public void setPicture3(byte[] picture3) {
		this.picture3 = picture3;
	}


	@Override
	public String toString() {
		return "ProductVO [product_no=" + product_no + ", product_name=" + product_name + ", product_sort="
				+ product_sort + ", price=" + price + ", inventory=" + inventory +", situation=" + situation + ", descript=" + descript
				+ ", picture1=" + Arrays.toString(picture1) + ", picture2=" + Arrays.toString(picture2) + ", picture3="
				+ Arrays.toString(picture3) + "]";
	}
	
	

}
