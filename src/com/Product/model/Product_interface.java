package com.Product.model;

import java.util.List;

public interface Product_interface {
	public void insert(ProductVO productVO);
    public void update(ProductVO productVO);
    public void delete(Integer productno);
    public ProductVO findByPrimaryKey(Integer productno);
    public ProductVO findBypname (String pname);
    public List<ProductVO> getAll();
}
