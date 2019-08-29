package org.taru.lanqiao.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.taru.lanqiao.model.Detail;
import org.taru.lanqiao.model.Order;
import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.model.Sale;
import org.taru.lanqiao.model.User;
import org.taru.lanqiao.util.BigDecimalUtil;
import org.taru.lanqiao.util.ByteUtil;
import org.taru.lanqiao.util.DateUtils;
import org.taru.lanqiao.util.IdUtil;
import org.taru.lanqiao.util.IntegerUtil;
import org.taru.lanqiao.util.SqlUtil;
import org.taru.lanqiao.vo.PageResult;


public class OrderDaoImpl {

	// ��������������������������������������������id
	public Order queryById(String id) {
		String sql = "select * from user a left join order_list b on a.USER_ID=b.ORDER_USER_ID where order_id=?";
		List<HashMap<String, Object>> list = SqlUtil.executeQuery(sql, id);
		Order order = null;
		User user = null;

		if (list.size() > 0) {
			user = new User();
			user.setUserId(list.get(0).get("USER_ID").toString());
			user.setUserUserName(list.get(0).get("USER_USER_NAME").toString());
			user.setUserPassword(list.get(0).get("USER_PASSWORD").toString());
			user.setUserTelphone(list.get(0).get("USER_TELPHONE").toString());
			user.setUserAddress(list.get(0).get("USER_ADDRESS").toString());
			user.setUserShopName(list.get(0).get("USER_SHOP_NAME").toString());
			user.setUserComment(list.get(0).get("USER_COMMENT").toString());
			user.setUserStatus(ByteUtil.toByte(list.get(0).get("USER_STATUS").toString()));
		}

		if (list.size() > 0) {
			order = new Order();
			try {
				order.setOrderId(list.get(0).get("ORDER_ID").toString());
				order.setOrderUserId(list.get(0).get("ORDER_USER_ID").toString());
				order.setOrderDateTime(DateUtils.toDate(list.get(0).get("ORDER_DATE_TIME").toString()));
				order.setOrderIsSend(ByteUtil.toByte(list.get(0).get("ORDER_IS_SEND").toString()));
				order.setOrderIsValid(ByteUtil.toByte(list.get(0).get("ORDER_IS_VALID").toString()));
				order.setOrderTotalPrice(BigDecimalUtil.toBigDecimal(list.get(0).get("ORDER_TOTAL_PRICE").toString()));
				order.setOrderStatus(ByteUtil.toByte(list.get(0).get("ORDER_STATUS").toString()));
				order.setOrderComment(list.get(0).get("ORDER_COMMENT").toString());
				order.setUser(user);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return order;
	}

	// ��������������������������������������������
	public PageResult queryListPage(int pageNum, int pageSize) {
		String sql = "select * from order_list a left join user b on a.ORDER_USER_ID=b.USER_ID  limit ?,?";
		List<HashMap<String, Object>> list = SqlUtil.executeQuery(sql, (pageNum - 1) * pageSize, pageSize);
		List<Order> list2 = new ArrayList<Order>();
		Order order = null;
		User user = null;
		for (int i = 0; i < list.size(); i++) {
			try {
				order = new Order();
				user = new User();
				user.setUserId(list.get(i).get("USER_ID").toString());
				user.setUserUserName(list.get(i).get("USER_USER_NAME").toString());
				user.setUserPassword(list.get(i).get("USER_PASSWORD").toString());
				user.setUserTelphone(list.get(i).get("USER_TELPHONE").toString());
				user.setUserAddress(list.get(i).get("USER_ADDRESS").toString());
				user.setUserShopName(list.get(i).get("USER_SHOP_NAME").toString());
				user.setUserComment(list.get(i).get("USER_COMMENT").toString());
				user.setUserStatus(ByteUtil.toByte(list.get(i).get("USER_STATUS").toString()));
				

				order.setOrderId(list.get(i).get("ORDER_ID").toString());
				order.setOrderUserId(list.get(i).get("ORDER_USER_ID").toString());
				order.setOrderDateTime(DateUtils.toDate(list.get(i).get("ORDER_DATE_TIME").toString()));
				order.setOrderIsSend(ByteUtil.toByte(list.get(i).get("ORDER_IS_SEND").toString()));
				order.setOrderIsValid(ByteUtil.toByte(list.get(i).get("ORDER_IS_VALID").toString()));
				order.setOrderTotalPrice(BigDecimalUtil.toBigDecimal(list.get(i).get("ORDER_TOTAL_PRICE").toString()));
				order.setOrderStatus(ByteUtil.toByte(list.get(i).get("ORDER_STATUS").toString()));
				order.setOrderComment(list.get(i).get("ORDER_COMMENT").toString());
				order.setUser(user);
				list2.add(order);
			} catch (Exception e) {
				System.out.println("��������������������");
			}
		}
		PageResult pageResult = new PageResult();
		pageResult.setList(list2);
		pageResult.setPageNum(pageNum);
		pageResult.setPageSize(pageSize);

		
		String sql2 = "select count(*) as row from product";
		List<HashMap<String, Object>> countlist = SqlUtil.executeQuery(sql2);
		String count = String.valueOf(countlist.get(0).get("row"));
		int size = Integer.parseInt(count);
		pageResult.setTotal(size);
		pageResult.setPages(size % pageSize == 0 ? size / pageSize : size / pageSize + 1);
		return pageResult;
	}

	// 删除 （修改）
	public int deleteById(String orderid) {
		String sql = "UPDATE order_list SET ORDER_STATUS= '0' WHERE ORDER_ID =? ";
		int t = SqlUtil.executeUpdate(sql, orderid);
		return t;
	}

	// ������������������������
	public int updateById(String orderid) {
		String sql = "UPDATE order_list SET ORDER_IS_SEND= '1' WHERE ORDER_ID =? ";
		int t = SqlUtil.executeUpdate(sql, orderid);
		return t;
	}

	// �����������������������������������������id
	public List<Detail> queryId(String id) {
		String sql = "SELECT * FROM order_detail a LEFT JOIN order_list b ON a.DETAIL_ORDER_ID = b.ORDER_ID LEFT JOIN product c ON a.DETAIL_PRODUCT_ID = c.PRODUCT_ID where order_id =?";
		List<HashMap<String, Object>> list = SqlUtil.executeQuery(sql, id);
		List<Detail> list2=new ArrayList<Detail>();
		Detail detail = null;
		Product product = null;
		if (list.size() > 0) {
			product = new Product();
			try {
				
				product.setProductId(list.get(0).get("PRODUCT_ID").toString());
				product.setProductCode(list.get(0).get("PRODUCT_CODE").toString());
				product.setProductName(list.get(0).get("PRODUCT_NAME").toString());
				product.setProductStandard(list.get(0).get("PRODUCT_STANDARD").toString());
				product.setProductSmallUnit(list.get(0).get("PRODUCT_SMALL_UNIT").toString());
				product.setProductSmallPrice(
						BigDecimalUtil.toBigDecimal(list.get(0).get("PRODUCT_SMALL_PRICE").toString()));
				product.setProductLargerUnit(list.get(0).get("PRODUCT_LARGER_UNIT").toString());
				product.setProductLargerPrice(
						BigDecimalUtil.toBigDecimal(list.get(0).get("PRODUCT_LARGER_PRICE").toString()));
				product.setProductCategoryId(list.get(0).get("PRODUCT_CATEGORY_ID").toString());
				product.setProductBrandId(list.get(0).get("PRODUCT_BRAND_ID").toString());
				product.setProductPhoto(list.get(0).get("PRODUCT_PHOTO").toString());
				product.setProductIsSale(ByteUtil.toByte(list.get(0).get("PRODUCT_IS_SALE").toString()));
				product.setProductIsLack(ByteUtil.toByte(list.get(0).get("PRODUCT_IS_LACK").toString()));
				product.setProductStatus(ByteUtil.toByte(list.get(0).get("PRODUCT_STATUS").toString()));
				product.setProductOrder(IntegerUtil.toInt(list.get(0).get("PRODUCT_ORDER").toString()));
				product.setProductDateTime(DateUtils.toDate(list.get(0).get("PRODUCT_DATE_TIME").toString()));
				product.setProductLargerStandard(list.get(0).get("PRODUCT_LARGER_STANDARD").toString());
				product.setProductSuggestPrice(
						BigDecimalUtil.toBigDecimal(list.get(0).get("PRODUCT_SUGGEST_PRICE").toString()));
			} catch (ParseException e) {

				e.printStackTrace();
			}

		}
		if (list.size() > 0) {
			detail = new Detail();
			try {
				detail.setDetailId(list.get(0).get("DETAIL_ID").toString());
				detail.setDetailDateTime(DateUtils.toDate(list.get(0).get("DETAIL_DATE_TIME").toString()));
				detail.setDetailOrderId(list.get(0).get("DETAIL_ID").toString());
				detail.setDetailProductId(list.get(0).get("DETAIL_PRODUCT_ID").toString());
				detail.setDetailProductCount(IntegerUtil.toInt(list.get(0).get("DETAIL_PRODUCT_COUNT").toString()));
				detail.setDetailProductUnit(list.get(0).get("DETAIL_PRODUCT_UNIT").toString());
				detail.setDetailProductPrice(
						BigDecimalUtil.toBigDecimal(list.get(0).get("DETAIL_PRODUCT_PRICE").toString()));
				detail.setDetailTotalPrice(
						BigDecimalUtil.toBigDecimal(list.get(0).get("DETAIL_TOTAL_PRICE").toString()));
				detail.setDetailStatus(ByteUtil.toByte(list.get(0).get("DETAIL_STATUS").toString()));
				detail.setDetailOrderId(list.get(0).get("DETAIL_ORDER_ID").toString());
				detail.setDetailUserId(list.get(0).get("DETAIL_USER_ID").toString());
				detail.setProduct(product);
				list2.add(detail);
			} catch (ParseException e) {

				e.printStackTrace();
			}

		}
		return list2;
	}


	
	

	// ������������������������TXT
	public String downloadTxt(String orderId) {
		String sql = "select order_id from order_list where order_id=?";
		List<HashMap<String, Object>> list = SqlUtil.executeQuery(sql, orderId);
		return orderId;
	}
	
	// 查询历史订单根据id
		public List<Order> queryByUserId(String userId) throws ParseException {
			String sql = "select * from user a left join order_list b on a.USER_ID=b.ORDER_USER_ID where ORDER_IS_SEND = 1  and b.ORDER_USER_ID =?";
			List<HashMap<String, Object>> list = null;
			List<Order> orderList = new ArrayList<Order>();
			Order order = null;
			User user = null;
			try {
				list = SqlUtil.executeQuery(sql, userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					HashMap<String, Object> map = list.get(i);
					user = new User();
					order = new Order();
					user.setUserId(map.get("USER_ID").toString());
					user.setUserUserName(map.get("USER_USER_NAME").toString());
					user.setUserPassword(map.get("USER_PASSWORD").toString());
					user.setUserTelphone(map.get("USER_TELPHONE").toString());
					user.setUserAddress(map.get("USER_ADDRESS").toString());
					user.setUserShopName(map.get("USER_SHOP_NAME").toString());
					user.setUserComment(map.get("USER_COMMENT").toString());
					user.setUserStatus(ByteUtil.toByte(map.get("USER_STATUS").toString()));

					order.setOrderId(map.get("ORDER_ID").toString());
					order.setOrderUserId(map.get("ORDER_USER_ID").toString());
					order.setOrderDateTime(DateUtils.toDate(map.get("ORDER_DATE_TIME").toString()));
					order.setOrderIsSend(ByteUtil.toByte(map.get("ORDER_IS_SEND").toString()));
					order.setOrderIsValid(ByteUtil.toByte(map.get("ORDER_IS_VALID").toString()));
					order.setOrderTotalPrice(BigDecimalUtil.toBigDecimal(map.get("ORDER_TOTAL_PRICE").toString()));
					order.setOrderStatus(ByteUtil.toByte(map.get("ORDER_STATUS").toString()));
					order.setOrderComment(map.get("ORDER_COMMENT").toString());
					order.setUser(user);

					orderList.add(order);
					// System.out.println(order.toString());
				}
			}
			return orderList;
		}

		// 提交订单

		public void updateOrder(String orderUserId, String orderTotalPrice, String orderComment) {
			int list1 = 0;
			int list2 = 0;
			String time = IdUtil.getDateId();
			Date dadaTime = null;
			try {
				dadaTime = DateUtils.toDate(IdUtil.getDateId());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			String sql = "update order_detail set detail_order_id = ?  where detail_order_id is null and DETAIL_USER_ID = ?";
			String sql1 = "insert into order_list (order_id,ORDER_USER_ID,ORDER_DATE_TIME,ORDER_IS_SEND,ORDER_IS_VALID,ORDER_TOTAL_PRICE,ORDER_STATUS,ORDER_COMMENT)  value(?,?,?,1,1,?,1,?) ";
			try {
				list1 = SqlUtil.executeUpdate(sql, time, orderUserId);
				list2 = SqlUtil.executeUpdate(sql1, time, orderUserId, dadaTime, orderTotalPrice, orderComment);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		// 按年度统计每个季度营业收入
		public List queryPriceById(String year) {
			String sql="SELECT\r\n" + 
					"	MONTHS,\r\n" + 
					"	sum( x.SUMER ) SUMER \r\n" + 
					"FROM\r\n" + 
					"	(\r\n" + 
					"SELECT DISTINCT\r\n" + 
					"	CONCAT( YEAR ( a.MONTHS ), QUARTER ( a.MONTHS ) ) MONTHS,\r\n" + 
					"	sum( a.SUMER ) SUMER \r\n" + 
					"FROM\r\n" + 
					"	(\r\n" + 
					"SELECT\r\n" + 
					"	temp.date AS MONTHS,\r\n" + 
					"	COALESCE ( u.unmber, 0 ) AS SUMER \r\n" + 
					"FROM\r\n" + 
					"	(\r\n" + 
					"SELECT\r\n" + 
					"	adddate( '2018-01-01', SUMER.id ) AS 'date' \r\n" + 
					"FROM\r\n" + 
					"	(\r\n" + 
					"SELECT\r\n" + 
					"	n1.i + n10.i * 10 + n100.i * 100 AS id \r\n" + 
					"FROM\r\n" + 
					"	num n1\r\n" + 
					"	CROSS JOIN num AS n10\r\n" + 
					"	CROSS JOIN num AS n100 \r\n" + 
					"	) AS SUMER \r\n" + 
					"WHERE\r\n" + 
					"	adddate( '2018-01-01', SUMER.id ) <= date_add( '2018-01-01', INTERVAL 23 MONTH ) \r\n" + 
					"	) temp\r\n" + 
					"	LEFT JOIN ( SELECT LEFT ( DETAIL_DATE_TIME, 10 ) AS udate, sum( DETAIL_TOTAL_PRICE ) unmber FROM order_detail GROUP BY udate ) u ON temp.date = u.udate \r\n" + 
					"ORDER BY\r\n" + 
					"	temp.date \r\n" + 
					"	) a \r\n" + 
					"GROUP BY\r\n" + 
					"	MONTHS \r\n" + 
					"	) x \r\n" + 
					"	where MONTHS like ?\"%\" GROUP BY MONTHS";
			List<HashMap<String, Object>> list = SqlUtil.executeQuery(sql, year);
			List<Sale> list2 = new ArrayList<Sale>();
			Sale  sale= null;
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					sale = new Sale();
					try {
						sale.setSumer(BigDecimalUtil.toBigDecimal(list.get(i).get("SUMER").toString()));
						sale.setMonths(list.get(i).get("MONTHS").toString());
						
						list2.add(sale);
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					
				}
			}
			return list2;
		}

		// 按年度统计商品销售排行
		public List querySaleById(String year) {
			String sql = "select * from (SELECT sum(a.DETAIL_TOTAL_PRICE) SUMER ,a.PRODUCT_NAME FROM (select * from product a left join order_detail b on a.PRODUCT_ID=b.DETAIL_PRODUCT_ID where  DETAIL_ORDER_ID is not null and PRODUCT_STATUS='1') a where  a.DETAIL_DATE_TIME like ?\"%\" GROUP BY DETAIL_PRODUCT_ID ORDER BY SUMER ) x where x.SUMER>'6000'";
			List<HashMap<String, Object>> list = SqlUtil.executeQuery(sql, year);
			List<Sale> list2 = new ArrayList<Sale>();
			Sale sale = null;
			Product product = null;
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					sale = new Sale();
					sale.setProductName(list.get(i).get("PRODUCT_NAME").toString());
					sale.setSumer(BigDecimalUtil.toBigDecimal(list.get(i).get("SUMER").toString()));
					list2.add(sale);
				}
			}
			return list2;
		}
}
