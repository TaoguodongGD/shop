package org.taru.lanqiao.service;

import java.util.List;

import org.taru.lanqiao.dao.CarDaoImpl;
import org.taru.lanqiao.model.Detail;
import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.vo.PageResult;



public class CarServiceImpl {
	// 查询购物车功能
	public List<Product> queryCarList(String uid) {
		CarDaoImpl Impl = new CarDaoImpl();
		return Impl.carProductId(uid);
	}

	// 删除购物车
	public int deleteCar(String detailId,String uid) {
		CarDaoImpl Impl = new CarDaoImpl();
		return Impl.carDeleteId(detailId,uid);
	}

	// 加入购物车
	public int insertCar(Detail detail) {
		CarDaoImpl Impl = new CarDaoImpl();
		return Impl.carAddId(detail);
	}
	
	
	// 查询用户购买过的商品
	public PageResult pageCar(String uid,int pageNum, int pageSize) {
		CarDaoImpl page = new CarDaoImpl();
		return page.carUserById(uid, pageNum, pageSize);
	}
}
