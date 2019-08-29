package org.taru.lanqiao.service;

import java.text.ParseException;

import java.util.List;
import org.taru.lanqiao.dao.CategoryDaoImpl;
import org.taru.lanqiao.dao.ProductDaoImpl;
import org.taru.lanqiao.model.Admin;
import org.taru.lanqiao.model.Category;
import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.vo.PageResult;

import com.google.gson.Gson;

/**
 * 商品业务类
 * 负责查询商品 废弃商品 上架商品 
 * @author liuyue
 *
 */
public class ProductServiceImpl {
	
	/**
	 * 根据商品id查询商品
	 * 
	 */
	public Product queryById (String productId) {
		ProductDaoImpl dao  = new ProductDaoImpl();
		Product product = null;
		try {
			product = dao.queryById(productId);
			System.out.println(product.getProductName());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return product;
	}
	/**
	 * 根据商品name查询商品
	 * 
	 */
	public PageResult queryByName (Object obj,Object obj1,String productName) {
		ProductDaoImpl dao  = new ProductDaoImpl();
		PageResult product = null;
		try {
			product = dao.queryByName(obj,obj1, productName);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return product;
	}
	/**|
	 * 根据类别名称查询商品列表(带分页)
	 * @param obj
	 * @param obj1
	 * @param categoryName
	 * @return
	 */
	public PageResult queryByCategoryId (Object obj ,Object obj1 ,String categoryId) {
		ProductDaoImpl dao  = new ProductDaoImpl();
//		CategoryDaoImpl dao1 = new CategoryDaoImpl ();
//		Category category = dao1.queryById(categoryId);
		PageResult product = null;
		try {
			product =dao.queryByCategoryId(obj,obj1,categoryId);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return product;
	}
	/**
	 * 查询所有商品带分页
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	public PageResult queryAll ( Object... params) throws ParseException {
		ProductDaoImpl dao  = new ProductDaoImpl();
		PageResult result = dao.queryByPage(params);
		return result;
	}
	
	/**
	 * 多条件查询所有商品带分页
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	public PageResult searchQuery ( Object... params) throws ParseException {
		ProductDaoImpl dao  = new ProductDaoImpl();
		PageResult result = dao.searchProductList(params);
		return result;
	}

	
	/*
	 * 	修改商品
	 */
	public boolean update(Product product) {
		boolean bool = false;
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		if(daoImpl.updateProduct(product) > 0) {
			bool = true;
		}
		return bool;
	}
	
	/*
	 * 	修改图片
	 */
	public boolean updatePhoto(String productId,String productPhoto) {
		boolean bool = false;
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		if(daoImpl.updatePhoto(productId, productPhoto) > 0) {
			bool = true;
		}
		return bool;
	}
	
	/*
	 *	 上货
	 */
	public boolean noLack(String productId) {
		boolean bool = false;
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		if(daoImpl.updatenoLack(productId) > 0) {
			bool = true;
		}
		return bool;
	}
	
	/*
	 *	缺货
	 */
	public boolean lack(String productId) {
		boolean bool = false;
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		if(daoImpl.updateLack(productId) > 0) {
			bool = true;
		}
		return bool;
	}
	
	/*
	 * 	商品添加
	 */
	public boolean addProduct(Product product) {
		boolean bool = false;
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		if(daoImpl.addProduct(product) > 0) {
			bool = true;
			System.out.println("服务层"+product.getProductStandard());
		}
		return bool;
	}
	
	/*
	 * 	删除商品
	 */
	public boolean delProduct(String productId) {
		boolean bool = false;
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		if(daoImpl.delProduct(productId) > 0) {
			bool = true;
		}
		return bool;
	}
	
	/*
	 * 	根据Id查询信息    带口味
	 */
	public Product findByIdTaste(String productId) {
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		Product product =  daoImpl.queryByIdTaste(productId);
		return product;
	}
	
	/*
	 * 查询商品
	 */
	public PageResult findAll(int pageNum, int pageSize,String categoryId,String productName,String isLack) {
		ProductDaoImpl daoImpl = new ProductDaoImpl();
		return daoImpl.findAll(pageNum, pageSize, categoryId, productName, isLack);
	}
}
