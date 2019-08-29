package org.taru.lanqiao.service;

import java.util.List;
import org.taru.lanqiao.dao.OrderDaoImpl;
import org.taru.lanqiao.model.Order;
import org.taru.lanqiao.vo.PageResult;

public class OrderServiceImpl {

	// ��ѯ������������id
	public Order queryOrderById(String id) {
		OrderDaoImpl daoImpl = new OrderDaoImpl();
		return daoImpl.queryById(id);
	}

	// 删除
	public int deletePage(String id) {
		OrderDaoImpl daoImpl = new OrderDaoImpl();
		return daoImpl.deleteById(id);
	}

	// ��������
	public int updateById(String id) {
		OrderDaoImpl daoImpl = new OrderDaoImpl();
		return daoImpl.updateById(id);
	}

	// ��ѯ�����������id
	public List queryDetailId(String id) {
		OrderDaoImpl daoImpl = new OrderDaoImpl();
		return daoImpl.queryId(id);
	}

	// ��ѯ���ж������ҷ�ҳ
	public PageResult queryList(int pageNum, int pageSize) {
		OrderDaoImpl page = new OrderDaoImpl();
		return page.queryListPage(pageNum, pageSize);
	}

	// �����ͳ��ÿ������Ӫҵ����
	public List priceYear(String year) {
		OrderDaoImpl price = new OrderDaoImpl();
		return price.queryPriceById(year);
	}

	// �����ͳ����Ʒ��������
	public List<Order> priceSale(String year) {
		OrderDaoImpl sale = new OrderDaoImpl();
		return sale.querySaleById(year);
	}
	
	//��������TXT
	public String downloadByIdtxt(String orderId) {
		OrderDaoImpl download = new OrderDaoImpl();
		return download.downloadTxt(orderId);
		
	}
	
	/**
	 * 根据用户Id查询历史订单
	 */
	public List<Order> queryHistoryl(String userId) {
		OrderDaoImpl dao = new  OrderDaoImpl();
		 List<Order> list = null;
		try {
			list = dao.queryByUserId(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * 提交订单
	 */
	public String orderCommit(String orderUserId,  String orderTotalPrice,  String orderComment) {
		String fResult = null;
		OrderDaoImpl dao = new OrderDaoImpl();
		try {
			dao.updateOrder(orderUserId, orderTotalPrice, orderComment);
			fResult = "成功";
		} catch (Exception e) {
			fResult = "失败";
		}

		return fResult;
	}
}
