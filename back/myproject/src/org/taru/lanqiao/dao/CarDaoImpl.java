package org.taru.lanqiao.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.taru.lanqiao.model.Detail;
import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.util.BigDecimalUtil;
import org.taru.lanqiao.util.ByteUtil;
import org.taru.lanqiao.util.DateUtils;
import org.taru.lanqiao.util.IntegerUtil;
import org.taru.lanqiao.util.SqlUtil;
import org.taru.lanqiao.util.StringUtil;
import org.taru.lanqiao.vo.PageResult;

public class CarDaoImpl {
	// 查询购物车功能
	public List carProductId(String uid) {
		String sql = "select * from order_detail a left join product b on a.DETAIL_PRODUCT_ID=b.PRODUCT_ID where DETAIL_STATUS= '1' and DETAIL_ORDER_ID is null and DETAIL_USER_ID=?";
		List<HashMap<String, Object>> list = SqlUtil.executeQuery(sql, uid);
		List<Detail> list2 = new ArrayList<Detail>();
		Product product = null;
		Detail detail = null;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				product = new Product();
				detail = new Detail();
				try {
					product.setProductId(list.get(i).get("PRODUCT_ID").toString());
					product.setProductCode(list.get(i).get("PRODUCT_CODE").toString());
					product.setProductName(list.get(i).get("PRODUCT_NAME").toString());
					product.setProductStandard(list.get(i).get("PRODUCT_STANDARD").toString());
					product.setProductSmallUnit(list.get(i).get("PRODUCT_SMALL_UNIT").toString());
					product.setProductSmallPrice(
							BigDecimalUtil.toBigDecimal(list.get(i).get("PRODUCT_SMALL_PRICE").toString()));
					product.setProductLargerUnit(list.get(i).get("PRODUCT_LARGER_UNIT").toString());
					product.setProductLargerPrice(
							BigDecimalUtil.toBigDecimal(list.get(i).get("PRODUCT_LARGER_PRICE").toString()));
					product.setProductCategoryId(list.get(i).get("PRODUCT_CATEGORY_ID").toString());
					product.setProductBrandId(list.get(i).get("PRODUCT_BRAND_ID").toString());
					product.setProductPhoto(list.get(i).get("PRODUCT_PHOTO").toString());
					product.setProductIsSale(ByteUtil.toByte(list.get(i).get("PRODUCT_IS_SALE").toString()));
					product.setProductIsLack(ByteUtil.toByte(list.get(i).get("PRODUCT_IS_LACK").toString()));
					product.setProductStatus(ByteUtil.toByte(list.get(i).get("PRODUCT_STATUS").toString()));
					product.setProductOrder(IntegerUtil.toInt(list.get(i).get("PRODUCT_ORDER").toString()));
					product.setProductDateTime(DateUtils.toDate(list.get(i).get("PRODUCT_DATE_TIME").toString()));
					product.setProductLargerStandard(list.get(i).get("PRODUCT_LARGER_STANDARD").toString());
					product.setProductSuggestPrice(
							BigDecimalUtil.toBigDecimal(list.get(i).get("PRODUCT_SUGGEST_PRICE").toString()));
					
					
					
					detail.setDetailId(list.get(0).get("DETAIL_ID").toString());
					detail.setDetailDateTime(DateUtils.toDate(list.get(0).get("DETAIL_DATE_TIME").toString()));
					
					detail.setDetailProductId(list.get(i).get("DETAIL_PRODUCT_ID").toString());
					detail.setDetailProductCount(IntegerUtil.toInt(list.get(i).get("DETAIL_PRODUCT_COUNT").toString()));
					detail.setDetailProductUnit(list.get(i).get("DETAIL_PRODUCT_UNIT").toString());
					detail.setDetailProductPrice(
							BigDecimalUtil.toBigDecimal(list.get(i).get("DETAIL_PRODUCT_PRICE").toString()));
//					detail.setDetailTotalPrice(
//							BigDecimalUtil.toBigDecimal(list.get(i).get("DETAIL_TOTAL_PRICE").toString()));
					detail.setDetailStatus(ByteUtil.toByte(list.get(i).get("DETAIL_STATUS").toString()));
					detail.setDetailUserId(list.get(i).get("DETAIL_USER_ID").toString());
					detail.setProduct(product);
				} catch (Exception e) {

					e.printStackTrace();
				}
				list2.add(detail);
			}
		}
		return list2;
	}

	// 删除购物车
	public int carDeleteId(String detailId,String userId) {
		String sql = "UPDATE (order_detail a left join product b on a.DETAIL_PRODUCT_ID=b.PRODUCT_ID) SET DETAIL_STATUS= '0' WHERE DETAIL_ID=? and  DETAIL_USER_ID=?";//加UID
		int t = SqlUtil.executeUpdate(sql, detailId,userId);
		return t;
	}

	// 加入购物车
	public int carAddId(Detail detail) {
		String sql = "INSERT INTO order_detail(DETAIL_ID,DETAIL_PRODUCT_ID,DETAIL_PRODUCT_COUNT,DETAIL_PRODUCT_UNIT,DETAIL_PRODUCT_PRICE,DETAIL_COMMENT,DETAIL_USER_ID,DETAIL_TOTAL_PRICE,DETAIL_DATE_TIME,DETAIL_STATUS)  VALUES (UUID(),?,?,?,?,?,?,?,SYSDATE(),1)";

		String detailProductId = detail.getDetailProductId();
		String detailProductCount = StringUtil.toString(detail.getDetailProductCount());
		String detailProductUnit = detail.getDetailProductUnit();
		String detailProductPrice = StringUtil.toString(detail.getDetailProductPrice());
		String detailComment = detail.getDetailComment();

		int Price = IntegerUtil.toInt(detail.getDetailProductPrice());
		int Count = IntegerUtil.toInt(detail.getDetailProductCount());

		String detailTotalPrice = StringUtil.toString(Price * Count);

		String detailUserId = detail.getDetailUserId();

		int t = SqlUtil.executeUpdate(sql, detailProductId, detailProductCount, detailProductUnit, detailProductPrice,
				detailComment, detailUserId, detailTotalPrice);
		return t;
	}

	// 查询用户购买过的商品
	public PageResult carUserById(String uid, int pageNum, int pageSize) {
		String sql = "select * from order_detail a left join product b on a.DETAIL_PRODUCT_ID=b.PRODUCT_ID where DETAIL_ORDER_ID is not null and DETAIL_USER_ID=? limit ?,?";
		List<HashMap<String, Object>> list = SqlUtil.executeQuery(sql, uid, (pageNum - 1) * pageSize, pageSize);
		List<Detail> list2 = new ArrayList<Detail>();
		Detail detail = null;
		Product product = null;
		if (list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				try {
					detail = new Detail();
					product = new Product();

					product.setProductId(list.get(i).get("PRODUCT_ID").toString());
					product.setProductCode(list.get(i).get("PRODUCT_CODE").toString());
					product.setProductName(list.get(i).get("PRODUCT_NAME").toString());
					product.setProductStandard(list.get(i).get("PRODUCT_STANDARD").toString());
					product.setProductSmallUnit(list.get(i).get("PRODUCT_SMALL_UNIT").toString());
					product.setProductSmallPrice(
							BigDecimalUtil.toBigDecimal(list.get(i).get("PRODUCT_SMALL_PRICE").toString()));
					product.setProductLargerUnit(list.get(i).get("PRODUCT_LARGER_UNIT").toString());
					product.setProductLargerPrice(
							BigDecimalUtil.toBigDecimal(list.get(i).get("PRODUCT_LARGER_PRICE").toString()));
					product.setProductCategoryId(list.get(i).get("PRODUCT_CATEGORY_ID").toString());
					product.setProductBrandId(list.get(i).get("PRODUCT_BRAND_ID").toString());
					product.setProductPhoto(list.get(i).get("PRODUCT_PHOTO").toString());
					product.setProductIsSale(ByteUtil.toByte(list.get(i).get("PRODUCT_IS_SALE").toString()));
					product.setProductIsLack(ByteUtil.toByte(list.get(i).get("PRODUCT_IS_LACK").toString()));
					product.setProductStatus(ByteUtil.toByte(list.get(i).get("PRODUCT_STATUS").toString()));
					product.setProductOrder(IntegerUtil.toInt(list.get(i).get("PRODUCT_ORDER").toString()));
					product.setProductDateTime(DateUtils.toDate(list.get(i).get("PRODUCT_DATE_TIME").toString()));
					product.setProductLargerStandard(list.get(i).get("PRODUCT_LARGER_STANDARD").toString());
					product.setProductSuggestPrice(
							BigDecimalUtil.toBigDecimal(list.get(i).get("PRODUCT_SUGGEST_PRICE").toString()));

					detail.setDetailDateTime(DateUtils.toDate(list.get(i).get("DETAIL_DATE_TIME").toString()));
					detail.setDetailOrderId(list.get(i).get("DETAIL_ID").toString());
					detail.setDetailProductId(list.get(i).get("DETAIL_PRODUCT_ID").toString());
					detail.setDetailProductCount(IntegerUtil.toInt(list.get(i).get("DETAIL_PRODUCT_COUNT").toString()));
					detail.setDetailProductUnit(list.get(i).get("DETAIL_PRODUCT_UNIT").toString());
					detail.setDetailProductPrice(
							BigDecimalUtil.toBigDecimal(list.get(i).get("DETAIL_PRODUCT_PRICE").toString()));
					detail.setDetailTotalPrice(
							BigDecimalUtil.toBigDecimal(list.get(i).get("DETAIL_TOTAL_PRICE").toString()));
					detail.setDetailStatus(ByteUtil.toByte(list.get(i).get("DETAIL_STATUS").toString()));
					detail.setDetailUserId(list.get(i).get("DETAIL_USER_ID").toString());
					detail.setProduct(product);
					list2.add(detail);

				} catch (ParseException e) {
					System.out.println("分页错误");
				}
			}
		}
		PageResult pageResult = new PageResult();
		pageResult.setList(list2);
		pageResult.setPageNum(pageNum);
		pageResult.setPageSize(pageSize);

		String sql2 = "select count(*) as row from order_detail a left join product b on a.DETAIL_PRODUCT_ID=b.PRODUCT_ID";
		List<HashMap<String, Object>> countlist = SqlUtil.executeQuery(sql2);
		String count = String.valueOf(countlist.get(0).get("row"));
		int size = Integer.parseInt(count);
		pageResult.setTotal(size);
		pageResult.setPages(size % pageSize == 0 ? size / pageSize : size / pageSize + 1);
		return pageResult;
	}

}
