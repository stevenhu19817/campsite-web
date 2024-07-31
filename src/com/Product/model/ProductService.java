package com.Product.model;

import java.util.List;

public class ProductService {
	
	private Product_interface dao;
	
	public ProductService() {
		dao = new  ProductJDBCDAO();
	}
	
	public  ProductVO addProduct(String product_name, String product_sort, Integer price, Integer inventory, Integer admin_id, Integer situation, String descript, byte[] picture1, byte[] picture2, byte[] picture3) {
		
		 ProductVO productVO = new  ProductVO();
		
		productVO.setPname(product_name);
		productVO.setPsort(product_sort);
		productVO.setPrice(price);
		productVO.setInventory(inventory);
		productVO.setAdmin_id(admin_id);
		productVO.setSituation(situation);
		productVO.setDescript(descript);
		productVO.setPicture1(picture1);
		productVO.setPicture2(picture2);
		productVO.setPicture3(picture3);
		dao.insert(productVO);
		
		return productVO;		
	}
	
	public  ProductVO updateProduct(Integer product_no, String product_name, String product_sort, Integer price, 
					Integer inventory, Integer admin_id, Integer situation, String descript, byte[] picture1, byte[] picture2, byte[] picture3) {
		
		 ProductVO productVO = new ProductVO();
		
		productVO.setProductno(product_no);
		productVO.setPname(product_name);
		productVO.setPsort(product_sort);
		productVO.setPrice(price);
		productVO.setInventory(inventory);
		productVO.setAdmin_id(admin_id);
		productVO.setSituation(situation);
		productVO.setDescript(descript);
		productVO.setPicture1(picture1);
		productVO.setPicture2(picture2);
		productVO.setPicture3(picture3);
		dao.update(productVO);
		
		return productVO;
	}
	
	public void deleteProduct(Integer product_no) {
		dao.delete(product_no);
				
	}
	
	public ProductVO getOneproduct(Integer product_no) {
		return dao.findByPrimaryKey(product_no);
	}
	
	public List< ProductVO> getAll(){
		return dao.getAll();
	}
	
	public ProductVO getOneproductName(String pname) {
		return dao.findBypname(pname);
	}

}

