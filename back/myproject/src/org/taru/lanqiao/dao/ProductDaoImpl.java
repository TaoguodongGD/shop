package org.taru.lanqiao.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.taru.lanqiao.model.Admin;
import org.taru.lanqiao.model.Category;
import org.taru.lanqiao.model.OtherData;
import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.util.BigDecimalUtil;
import org.taru.lanqiao.util.ByteUtil;
import org.taru.lanqiao.util.DateUtils;
import org.taru.lanqiao.util.IntegerUtil;
import org.taru.lanqiao.util.SqlUtil;
import org.taru.lanqiao.util.StringUtil;
import org.taru.lanqiao.vo.PageResult;

import com.google.gson.Gson;


/**
 * 商品Dao 商品的增删改查
 * 
 * @author liuyue
 *
 */
public class ProductDaoImpl {
	//查询所有的商品不带分页
	public List<Product> getAll() throws ParseException {
		List<Product> list = new ArrayList<>();
		List<HashMap<String, Object>> list1 = null;
		Product product = null;
		String sql = "select * from product ";
		try {
			list1 = SqlUtil.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				product = new Product();
				HashMap<String, Object> map = list1.get(i);
				product.setProductId(map.get("PRODUCT_ID") == null ? null : map.get("PRODUCT_ID").toString());
				product.setProductCode(map.get("PRODUCT_CODE") == null ? null : map.get("PRODUCT_CODE").toString());
				product.setProductName(map.get("PRODUCT_NAME") == null ? null : map.get("PRODUCT_NAME").toString());
				product.setProductStandard(
						map.get("PRODUCT_STANDARD") == null ? null : map.get("PRODUCT_STANDARD").toString());
				product.setProductSmallUnit(
						map.get("PRODUCT_SMALL_UNIT") == null ? null : map.get("PRODUCT_SMALL_UNIT").toString());
				product.setProductSmallPrice(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SMALL_PRICE").toString()));
				// System.out.println(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SMALL_PRICE").toString()));
				product.setProductLargerUnit(
						map.get("PRODUCT_LARGER_UNIT") == null ? null : map.get("PRODUCT_LARGER_UNIT").toString());
				product.setProductLargerPrice(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_LARGER_PRICE").toString()));
				product.setProductCategoryId(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				product.setProductBrandId(StringUtil.toString(map.get("PRODUCT_BRAND_ID")));
				product.setProductPhoto(StringUtil.toString(map.get("PRODUCT_PHOTO")));
				product.setProductIsSale(ByteUtil.toByte(map.get("PRODUCT_IS_SLAE")));
				product.setProductStatus(ByteUtil.toByte(map.get("PRODUCT_STATUS")));
				product.setProductIsLack(ByteUtil.toByte(map.get("PRODUCT_IS_LACK")));
				product.setProductOrder(IntegerUtil.toInt(map.get("PRODUCT_ORDER")));
				// System.out.println(DateUtils.toDate(map.get("PRODUCT_DATE_TIME").toString()));
				product.setProductDateTime(DateUtils.toDate(map.get("PRODUCT_DATE_TIME").toString()));
				product.setProductLargerStandard(StringUtil.toString(map.get("PRODUCT_LARGER_STANDARD")));
				product.setProductSuggestPrice(
						BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SUGGEST_PRICE").toString()));
				CategoryDaoImpl dao1 = new CategoryDaoImpl();
				Category a = dao1.queryById(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				product.setCategory(a);
				list.add(product);

			}
		}
		return list;
	}
//根据商品名称查询商品
	public PageResult queryByName(Object... params) throws ParseException {
		int pageNum = 1;
		int pageSize = 10;
		PageResult pageResult = new PageResult();
		List<Product> list = new ArrayList<>();
		List<HashMap<String, Object>> list1 = null;
		Product product = null;
		String productName= null;
		String name=null;
		String sql = "select * from product where product_name  like  ?  limit ?,? ";
		Object sum[] = new Object[params.length];
		try {
			// 获得页数和页面大小
			if (params != null) {// 是否传入数据
				for (int i = 0; i < params.length; i++) {
					if (params[i] != null) {// 传入的数据是否是空
						sum[i] = params[i];
						// System.out.println(sum[i]);
					}
				}
				// 判断传入了那个数据
				if (sum[0] != null || sum[1] != null || sum[2] != null) {
					if (sum[0] != null) {
						pageNum = IntegerUtil.toInt(sum[0]);
					}
					if (sum[1] != null) {
						pageSize = IntegerUtil.toInt(sum[1]);
					}
					if (sum[2] != null) {
						productName= StringUtil.toString(sum[2]);
						name = "%" + productName + "%";
					}
				}
			}

			list1 = SqlUtil.executeQuery(sql, name,(pageNum - 1) * pageSize, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				product = new Product();
				HashMap<String, Object> map = list1.get(i);
				product.setProductId(map.get("PRODUCT_ID") == null ? null : map.get("PRODUCT_ID").toString());
				product.setProductCode(map.get("PRODUCT_CODE") == null ? null : map.get("PRODUCT_CODE").toString());
				product.setProductName(map.get("PRODUCT_NAME") == null ? null : map.get("PRODUCT_NAME").toString());
				product.setProductStandard(
						map.get("PRODUCT_STANDARD") == null ? null : map.get("PRODUCT_STANDARD").toString());
				product.setProductSmallUnit(
						map.get("PRODUCT_SMALL_UNIT") == null ? null : map.get("PRODUCT_SMALL_UNIT").toString());
				product.setProductSmallPrice(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SMALL_PRICE").toString()));
				// System.out.println(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SMALL_PRICE").toString()));
				product.setProductLargerUnit(
						map.get("PRODUCT_LARGER_UNIT") == null ? null : map.get("PRODUCT_LARGER_UNIT").toString());
				product.setProductLargerPrice(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_LARGER_PRICE").toString()));
				product.setProductCategoryId(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				product.setProductBrandId(StringUtil.toString(map.get("PRODUCT_BRAND_ID")));
				product.setProductPhoto(StringUtil.toString(map.get("PRODUCT_PHOTO")));
				product.setProductIsSale(ByteUtil.toByte(map.get("PRODUCT_IS_SLAE")));
				product.setProductStatus(ByteUtil.toByte(map.get("PRODUCT_STATUS")));
				product.setProductIsLack(ByteUtil.toByte(map.get("PRODUCT_IS_LACK")));
				product.setProductOrder(IntegerUtil.toInt(map.get("PRODUCT_ORDER")));
				// System.out.println(DateUtils.toDate(map.get("PRODUCT_DATE_TIME").toString()));
				product.setProductDateTime(DateUtils.toDate(map.get("PRODUCT_DATE_TIME").toString()));
				product.setProductLargerStandard(StringUtil.toString(map.get("PRODUCT_LARGER_STANDARD")));
				product.setProductSuggestPrice(
						BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SUGGEST_PRICE").toString()));
				CategoryDaoImpl dao1 = new CategoryDaoImpl();
				Category a = dao1.queryById(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				product.setCategory(a);
				list.add(product);
			}
		}
		pageResult.setList(list);
		pageResult.setPageNum(pageNum);
		pageResult.setPageSize(pageSize);
		String sql2 = "select count(*) as  row from product   where product_name like  ? ";
		List<HashMap<String, Object>> countList = SqlUtil.executeQuery(sql2,name);
		String count = countList.get(0).get("row").toString();
		int size = Integer.valueOf(count);
		pageResult.setPages(size % pageSize == 0 ? size / pageSize : size / pageSize + 1);
		pageResult.setTotal(size);
		// pageResult.setPages(pages);
		return pageResult;

	}
	
	//多条件查询商品
	public PageResult searchProductList(Object... params) {
		//初始化页数
		int pageNum = 1;
		int pageSize = 16;
		
		
		PageResult pageResult = new PageResult();
		List<Product> list = new ArrayList<>();
		List<HashMap<String, Object>> list1 = null;
		Product product = null;
		String categoryId= null;
	
		Byte productIsLack = null;
		String orderStr = null;
		
		StringBuffer  sql = new StringBuffer();
		sql.append("select * from product where  1=1 ");
		
       Object obj1  [] = null;;
		try {
			
				// 判断传入了那个数据
					
					if (params[0] != null) {
						pageNum = IntegerUtil.toInt(params[0]);
					}
					if (params[1] != null) {
						pageSize = IntegerUtil.toInt(params[1]);
					}
					if (params[2] != null) {
						categoryId= StringUtil.toString(params[2]);
						sql.append("and  product_category_Id = "+"\'"+categoryId+"\'");
					}
					System.out.println("params[3]:"+params[3]);
					if (params[3] != "") {
						productIsLack= ByteUtil.toByte(params[3]);
						sql.append("and    product_Is_Lack ="+productIsLack);
					}
					if (params[4] != null) {
						orderStr= StringUtil.toString(params[4]);
						sql.append("  "+"order by  product_small_price   "+"  "+orderStr+"  ");
					}
				sql.append("  "+"limit ?,?");
			String sqll = sql.toString();

			list1 = SqlUtil.executeQuery(sqll,(pageNum - 1) * pageSize, pageSize);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
			
				product = new Product();
				HashMap<String, Object> map = list1.get(i);
				product.setProductId(map.get("PRODUCT_ID") == null ? null : map.get("PRODUCT_ID").toString());
				product.setProductCode(map.get("PRODUCT_CODE") == null ? null : map.get("PRODUCT_CODE").toString());
				
				product.setProductName(map.get("PRODUCT_NAME") == null ? null : map.get("PRODUCT_NAME").toString());
				
				product.setProductStandard(
						map.get("PRODUCT_STANDARD") == null ? null : map.get("PRODUCT_STANDARD").toString());
				
				product.setProductSmallUnit(
						map.get("PRODUCT_SMALL_UNIT") == null ? null : map.get("PRODUCT_SMALL_UNIT").toString());
				
				product.setProductSmallPrice(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SMALL_PRICE").toString()));
				// System.out.println(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SMALL_PRICE").toString()));
				
				product.setProductLargerUnit(
						map.get("PRODUCT_LARGER_UNIT") == null ? null : map.get("PRODUCT_LARGER_UNIT").toString());
				
				product.setProductLargerPrice(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_LARGER_PRICE").toString()));
				
				product.setProductCategoryId(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				
				product.setProductBrandId(StringUtil.toString(map.get("PRODUCT_BRAND_ID")));
				
				product.setProductPhoto(StringUtil.toString(map.get("PRODUCT_PHOTO")));
				
				product.setProductIsSale(ByteUtil.toByte(map.get("PRODUCT_IS_SLAE")));
				
				product.setProductStatus(ByteUtil.toByte(map.get("PRODUCT_STATUS")));
				
				product.setProductIsLack(ByteUtil.toByte(map.get("PRODUCT_IS_LACK")));
				
				product.setProductOrder(IntegerUtil.toInt(map.get("PRODUCT_ORDER")));
				// System.out.println(DateUtils.toDate(map.get("PRODUCT_DATE_TIME").toString()));
				
				try {
					product.setProductDateTime(DateUtils.toDate(map.get("PRODUCT_DATE_TIME").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				product.setProductLargerStandard(StringUtil.toString(map.get("PRODUCT_LARGER_STANDARD")));
				product.setProductSuggestPrice(
						BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SUGGEST_PRICE").toString()));
				CategoryDaoImpl dao1 = new CategoryDaoImpl();
			
				Category a = dao1.queryById(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				
				product.setCategory(a);
				list.add(product);
			}
			
			pageResult.setList(list);
			pageResult.setPageNum(pageNum);
			pageResult.setPageSize(pageSize);
			String sql2 = "select count(*) as  row from product   where product_category_Id =   ? ";
			List<HashMap<String, Object>> countList = SqlUtil.executeQuery(sql2,categoryId);
			String count = countList.get(0).get("row").toString();
			int size = Integer.valueOf(count);
			pageResult.setPages(size % pageSize == 0 ? size / pageSize : size / pageSize + 1);
			pageResult.setTotal(size);
			// pageResult.setPages(pages);
			
		}
		return pageResult;
		

		
		
		
		
		
		

	}

			
	
	
//根据商品Id查询商品 不带口味
	public Product queryById(String productId) throws ParseException {
		Product product = null;
		List<HashMap<String, Object>> list1 = null;
		String sql = "select * from product where product_id = ? ";
		try {
			list1 = SqlUtil.executeQuery(sql, productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				product = new Product();
				HashMap<String, Object> map = list1.get(i);
				product.setProductId(map.get("PRODUCT_ID") == null ? null : map.get("PRODUCT_ID").toString());
				product.setProductCode(map.get("PRODUCT_CODE") == null ? null : map.get("PRODUCT_CODE").toString());
				product.setProductName(map.get("PRODUCT_NAME") == null ? null : map.get("PRODUCT_NAME").toString());
				product.setProductStandard(
						map.get("PRODUCT_STANDARD") == null ? null : map.get("PRODUCT_STANDARD").toString());
				product.setProductSmallUnit(
						map.get("PRODUCT_SMALL_UNIT") == null ? null : map.get("PRODUCT_SMALL_UNIT").toString());
				product.setProductSmallPrice(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SMALL_PRICE").toString()));
				// System.out.println(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SMALL_PRICE").toString()));
				product.setProductLargerUnit(
						map.get("PRODUCT_LARGER_UNIT") == null ? null : map.get("PRODUCT_LARGER_UNIT").toString());
				product.setProductLargerPrice(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_LARGER_PRICE").toString()));
				product.setProductCategoryId(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				product.setProductBrandId(StringUtil.toString(map.get("PRODUCT_BRAND_ID")));
				product.setProductPhoto(StringUtil.toString(map.get("PRODUCT_PHOTO")));
				product.setProductIsSale(ByteUtil.toByte(map.get("PRODUCT_IS_SLAE")));
				product.setProductStatus(ByteUtil.toByte(map.get("PRODUCT_STATUS")));
				product.setProductIsLack(ByteUtil.toByte(map.get("PRODUCT_IS_LACK")));
				product.setProductOrder(IntegerUtil.toInt(map.get("PRODUCT_ORDER")));
				// System.out.println(DateUtils.toDate(map.get("PRODUCT_DATE_TIME").toString()));
				product.setProductDateTime(DateUtils.toDate(map.get("PRODUCT_DATE_TIME").toString()));
				product.setProductLargerStandard(StringUtil.toString(map.get("PRODUCT_LARGER_STANDARD")));
				product.setProductSuggestPrice(
						BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SUGGEST_PRICE").toString()));
				CategoryDaoImpl dao1 = new CategoryDaoImpl();
				Category a = dao1.queryById(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				product.setCategory(a);
			}
		}
		return product;
	}
	//查询根据类别Id商品详情带分页
	public PageResult queryByCategoryId(Object... params) throws ParseException {
		int pageNum = 1;
		int pageSize = 3;
		String categoryId = null;
		PageResult pageResult = new PageResult();
		List<Product> list = new ArrayList<>();
		List<HashMap<String, Object>> list1 = null;
		Product product = null;
		String sql = "select * from product  where    product_category_id= ?    limit ?,? ";
		Object sum[] = new Object[params.length];
		try {
			// 获得页数和页面大小
			if (params != null) {// 是否传入数据
				for (int i = 0; i < params.length; i++) {
					if (params[i] != null) {// 传入的数据是否是空
						sum[i] = params[i];
						// System.out.println(sum[i]);
					}
				}
				// 判断传入了那个数据
				if (sum[0] != null || sum[1] != null || sum[2] != null) {
					if (sum[0] != null) {
						pageNum = IntegerUtil.toInt(sum[0]);
					}
					if (sum[1] != null) {
						pageSize = IntegerUtil.toInt(sum[1]);
					}
					if (sum[2] != null) {
						categoryId = StringUtil.toString(sum[2]);
					}

				}

			}
			list1 = SqlUtil.executeQuery(sql,categoryId, (pageNum - 1) * pageSize, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				product = new Product();
				HashMap<String, Object> map = list1.get(i);
				product.setProductId(map.get("PRODUCT_ID") == null ? null : map.get("PRODUCT_ID").toString());
				product.setProductCode(map.get("PRODUCT_CODE") == null ? null : map.get("PRODUCT_CODE").toString());
				product.setProductName(map.get("PRODUCT_NAME") == null ? null : map.get("PRODUCT_NAME").toString());
				product.setProductStandard(
						map.get("PRODUCT_STANDARD") == null ? null : map.get("PRODUCT_STANDARD").toString());
				product.setProductSmallUnit(
						map.get("PRODUCT_SMALL_UNIT") == null ? null : map.get("PRODUCT_SMALL_UNIT").toString());
				product.setProductSmallPrice(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SMALL_PRICE").toString()));
				// System.out.println(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SMALL_PRICE").toString()));
				product.setProductLargerUnit(
						map.get("PRODUCT_LARGER_UNIT") == null ? null : map.get("PRODUCT_LARGER_UNIT").toString());
				product.setProductLargerPrice(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_LARGER_PRICE").toString()));
				product.setProductCategoryId(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				product.setProductBrandId(StringUtil.toString(map.get("PRODUCT_BRAND_ID")));
				product.setProductPhoto(StringUtil.toString(map.get("PRODUCT_PHOTO")));
				product.setProductIsSale(ByteUtil.toByte(map.get("PRODUCT_IS_SLAE")));
				product.setProductStatus(ByteUtil.toByte(map.get("PRODUCT_STATUS")));
				product.setProductIsLack(ByteUtil.toByte(map.get("PRODUCT_IS_LACK")));
				product.setProductOrder(IntegerUtil.toInt(map.get("PRODUCT_ORDER")));
				// System.out.println(DateUtils.toDate(map.get("PRODUCT_DATE_TIME").toString()));
				product.setProductDateTime(DateUtils.toDate(map.get("PRODUCT_DATE_TIME").toString()));
				product.setProductLargerStandard(StringUtil.toString(map.get("PRODUCT_LARGER_STANDARD")));
				product.setProductSuggestPrice(
						BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SUGGEST_PRICE").toString()));
				CategoryDaoImpl dao1 = new CategoryDaoImpl();
				Category a = dao1.queryById(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				product.setCategory(a);
				list.add(product);
			}
		}
		pageResult.setList(list);
		pageResult.setPageNum(pageNum);
		pageResult.setPageSize(pageSize);

		String sql2 = "select count(*) as  row from product  where   product_category_id= ? ";
		List<HashMap<String, Object>> countList = SqlUtil.executeQuery(sql2,categoryId);
		String count = countList.get(0).get("row").toString();
		int size = Integer.valueOf(count);
		pageResult.setPages(size % pageSize == 0 ? size / pageSize : size / pageSize + 1);
		pageResult.setTotal(size);
		// pageResult.setPages(pages);
		return pageResult;
	}
//查询商品详情带分页
	public PageResult queryByPage(Object... params) throws ParseException {
		int pageNum = 1;
		int pageSize = 3;
		PageResult pageResult = new PageResult();
		List<Product> list = new ArrayList<>();
		List<HashMap<String, Object>> list1 = null;
		Product product = null;
		String sql = "select * from product where product_status=1 limit ?,?";
		Integer sum[] = new Integer[params.length];
		try {
			// 获得页数和页面大小
			if (params != null) {// 是否传入数据
				for (int i = 0; i < params.length; i++) {
					if (params[i] != null) {// 传入的数据是否是空
						sum[i] = IntegerUtil.toInt(params[i]);
						// System.out.println(sum[i]);
					}
				}
				// 判断传入了那个数据
				if (sum[0] != null || sum[1] != null) {
					if (sum[0] != null) {
						pageNum = sum[0];	
					}
					if (sum[1] != null) {
						pageSize = sum[1];
					}
				}

			}
			list1 = SqlUtil.executeQuery(sql, (pageNum - 1) * pageSize, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				product = new Product();
				HashMap<String, Object> map = list1.get(i);
				product.setProductId(map.get("PRODUCT_ID") == null ? null : map.get("PRODUCT_ID").toString());
				product.setProductCode(map.get("PRODUCT_CODE") == null ? null : map.get("PRODUCT_CODE").toString());
				product.setProductName(map.get("PRODUCT_NAME") == null ? null : map.get("PRODUCT_NAME").toString());
				product.setProductStandard(
						map.get("PRODUCT_STANDARD") == null ? null : map.get("PRODUCT_STANDARD").toString());
				product.setProductSmallUnit(
						map.get("PRODUCT_SMALL_UNIT") == null ? null : map.get("PRODUCT_SMALL_UNIT").toString());
				product.setProductSmallPrice(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SMALL_PRICE").toString()));
				// System.out.println(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SMALL_PRICE").toString()));
				product.setProductLargerUnit(
						map.get("PRODUCT_LARGER_UNIT") == null ? null : map.get("PRODUCT_LARGER_UNIT").toString());
				product.setProductLargerPrice(BigDecimalUtil.toBigDecimal(map.get("PRODUCT_LARGER_PRICE").toString()));
				product.setProductCategoryId(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				product.setProductBrandId(StringUtil.toString(map.get("PRODUCT_BRAND_ID")));
				product.setProductPhoto(StringUtil.toString(map.get("PRODUCT_PHOTO")));
				product.setProductIsSale(ByteUtil.toByte(map.get("PRODUCT_IS_SLAE")));
				product.setProductStatus(ByteUtil.toByte(map.get("PRODUCT_STATUS")));
				product.setProductIsLack(ByteUtil.toByte(map.get("PRODUCT_IS_LACK")));
				product.setProductOrder(IntegerUtil.toInt(map.get("PRODUCT_ORDER")));
				// System.out.println(DateUtils.toDate(map.get("PRODUCT_DATE_TIME").toString()));
				product.setProductDateTime(DateUtils.toDate(map.get("PRODUCT_DATE_TIME").toString()));
				product.setProductLargerStandard(StringUtil.toString(map.get("PRODUCT_LARGER_STANDARD")));
				product.setProductSuggestPrice(
						BigDecimalUtil.toBigDecimal(map.get("PRODUCT_SUGGEST_PRICE").toString()));
				CategoryDaoImpl dao1 = new CategoryDaoImpl();
				Category a = dao1.queryById(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				product.setCategory(a);
				list.add(product);
			}
		}
		pageResult.setList(list);
		pageResult.setPageNum(pageNum);
		pageResult.setPageSize(pageSize);

		String sql2 = "select count(*) as  row from where product_status=1 product ";
		List<HashMap<String, Object>> countList = SqlUtil.executeQuery(sql2);
		String count = countList.get(0).get("row").toString();
		int size = Integer.valueOf(count);
		pageResult.setPages(size % pageSize == 0 ? size / pageSize : size / pageSize + 1);
		pageResult.setTotal(size);
		// pageResult.setPages(pages);
		return pageResult;
	}

//	public static void main(String[] args) {
//		ProductDaoImpl m = new ProductDaoImpl();
//		List<Product> a = null;
//		try {
//			a = m.getAll();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Gson gson = new Gson();
//		String json = gson.toJson(a);
//		System.out.println(json);
//
//	}
	/*
	 *	商品的更新
	 */
	public int updateProduct(Product product) {
		String sql = "update product set PRODUCT_CODE=?,PRODUCT_NAME=?,PRODUCT_STANDARD=?,PRODUCT_SMALL_UNIT=?,PRODUCT_SMALL_PRICE=?,PRODUCT_LARGER_UNIT=?"
				+ ",PRODUCT_LARGER_PRICE=?,PRODUCT_CATEGORY_ID=?,PRODUCT_BRAND_ID=?,PRODUCT_PHOTO=?,PRODUCT_IS_SALE=?,PRODUCT_IS_LACK=?,PRODUCT_STATUS=?,PRODUCT_ORDER=?,PRODUCT_DATE_TIME=?"
				+ ",PRODUCT_LARGER_STANDARD=?,PRODUCT_SUGGEST_PRICE=? where PRODUCT_ID = ?";
		int res = SqlUtil.executeUpdate(sql,product.getProductCode(),product.getProductName(),product.getProductStandard(),product.getProductSmallUnit()
				,product.getProductSmallPrice(),product.getProductLargerUnit(),product.getProductLargerPrice(),product.getProductCategoryId(),
				product.getProductBrandId(),product.getProductPhoto(),product.getProductIsSale(),product.getProductIsLack(),product.getProductStatus(),product.getProductOrder(),
				product.getProductDateTime(),product.getProductLargerStandard(),product.getProductSuggestPrice(),product.getProductId());
		SqlUtil.close();
		return res;
	}
	
	/*
	 * 	商品更新（图片）
	 */
	public int updatePhoto(String productId,String productPhoto) {
		String sql = "update product set PRODUCT_PHOTO = ? where PRODUCT_ID = ?";
		int res = SqlUtil.executeUpdate(sql, productPhoto, productId);
		SqlUtil.close();
		return res;
	}
	
	/*
	 * 	商品更新（不缺货状态）
	 */
	public int updatenoLack(String productId) {
		String sql = "update product set PRODUCT_IS_LACK = 0 where PRODUCT_ID = ?";
		int res = SqlUtil.executeUpdate(sql, productId);
		SqlUtil.close();
		return res;
	}
	
	/*
	 * 	商品更新(缺货状态)
	 */
	public int updateLack(String productId) {
		String sql = "update product set PRODUCT_IS_LACK = 1 where PRODUCT_ID = ?";
		int res = SqlUtil.executeUpdate(sql, productId);
		SqlUtil.close();
		return res;
	}
	
	/*
	 * 	商品添加
	 */
	public int addProduct(Product product) {
		String sql = "insert into product (PRODUCT_ID,PRODUCT_CODE,PRODUCT_NAME,PRODUCT_STANDARD,PRODUCT_SMALL_UNIT,PRODUCT_SMALL_PRICE,"
				+ "PRODUCT_LARGER_UNIT,PRODUCT_LARGER_PRICE,PRODUCT_CATEGORY_ID,PRODUCT_BRAND_ID,PRODUCT_PHOTO,PRODUCT_IS_SALE,PRODUCT_IS_LACK,"
				+ "PRODUCT_STATUS,PRODUCT_ORDER,PRODUCT_DATE_TIME,PRODUCT_LARGER_STANDARD,PRODUCT_SUGGEST_PRICE) values (UUID(),?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?)";
		int res = SqlUtil.executeUpdate(sql, product.getProductCode(),product.getProductName(),product.getProductStandard(),product.getProductSmallUnit()
				,product.getProductSmallPrice(),product.getProductLargerUnit(),product.getProductLargerPrice(),product.getProductCategoryId(),
				product.getProductBrandId(),product.getProductPhoto(),product.getProductIsSale(),product.getProductIsLack(),product.getProductStatus(),product.getProductOrder(),
				product.getProductDateTime(),product.getProductLargerStandard(),product.getProductSuggestPrice());
		SqlUtil.close();
		return res;
	}
	
	/*
	 * 	删除商品（修改商品的状态）
	 */
	public int delProduct(String productId) {
		String sql = "update product set PRODUCT_STATUS = 0 where PRODUCT_ID = ?";
		int res = SqlUtil.executeUpdate(sql, productId);
		SqlUtil.close();
		return res;
	}
	
	/*
	 * 根据ID查询商品  带口味
	 */
	public Product queryByIdTaste(String productId) {
		String sql = "select * from product where PRODUCT_ID = ?";
		List<HashMap<String, Object>> list = SqlUtil.executeQuery(sql, productId);
		Product product = null;
		if(list.size() > 0) {
			product = new Product();
			HashMap<String, Object> map = list.get(0);
			product.setProductId(StringUtil.toString(map.get("PRODUCT_ID")));
			product.setProductCode(StringUtil.toString(map.get("PRODUCT_CODE")));
			product.setProductName(StringUtil.toString(map.get("PRODUCT_NAME")));
			product.setProductStandard(StringUtil.toString(map.get("PRODUCT_STANDARD")));	System.out.println("4");		
			product.setProductSmallUnit(StringUtil.toString(map.get("PRODUCT_SMALL_UNIT")));
			product.setProductSmallPrice(BigDecimalUtil.toBigDecimal(StringUtil.toString(map.get("PRODUCT_SMALL_PRICE"))));
			product.setProductLargerUnit(StringUtil.toString(map.get("PRODUCT_LARGER_UNIT")));
			product.setProductLargerPrice(BigDecimalUtil.toBigDecimal(StringUtil.toString(map.get("PRODUCT_LARGER_PRICE"))));
			product.setProductCategoryId(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
			product.setProductBrandId(StringUtil.toString(map.get("PRODUCT_BRAND_ID")));
			product.setProductPhoto(StringUtil.toString(map.get("PRODUCT_PHOTO")));
			product.setProductIsSale(ByteUtil.toByte(StringUtil.toString(map.get("PRODUCT_IS_SALE"))));
			product.setProductIsLack(ByteUtil.toByte(StringUtil.toString(map.get("PRODUCT_IS_LACK"))));
			product.setProductStatus(ByteUtil.toByte(StringUtil.toString(map.get("PRODUCT_STATUS"))));
			product.setProductOrder(IntegerUtil.toInt(StringUtil.toString(map.get("PRODUCT_ORDER"))));
			try {
				System.out.println("我得到的时间："+(map.get("PRODUCT_DATE_TIME").toString()));
				product.setProductDateTime(DateUtils.toDate((map.get("PRODUCT_DATE_TIME").toString())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			product.setProductLargerStandard(StringUtil.toString(map.get("PRODUCT_LARGER_STANDARD")));
			product.setProductSuggestPrice(BigDecimalUtil.toBigDecimal(StringUtil.toString(map.get("PRODUCT_SUGGEST_PRICE"))));
		}
		
		//查询商品类中的口味
		String sql2 = "select * from other_data where data_product_id = ?";
		List<HashMap<String, Object>> list2 = SqlUtil.executeQuery(sql2, productId);
		List<OtherData> tastes = null;
		if(list2.size() > 0) {
			tastes = new ArrayList<OtherData>();
			for(int i = 0 ; i < list2.size() ; i++) {
				OtherData taste = new OtherData();
				taste.setDataId(StringUtil.toString(list2.get(i).get("data_id")));
				taste.setDataName(StringUtil.toString(list2.get(i).get("data_name")));
				taste.setDataProductId(StringUtil.toString(list2.get(i).get("data_product_id")));
				taste.setDataValue(StringUtil.toString(list2.get(i).get("data_value")));
				taste.setValues(taste.split());
				tastes.add(taste);
			}
		}
		product.setParams(tastes);
		SqlUtil.close();
		return product;
	}
	
	/*
	 * 查询全部商品
	 */
	public PageResult findAll(int pageNum,int pageSize,String categoryId ,String productName,String isLack) {
		if(pageNum <= 0) {
			pageNum = 1;
		}
		if(pageSize <= 0) {
			pageSize = 8;
		}
		List params = new ArrayList();
		StringBuffer str = new StringBuffer("select p.*,c.CATEGORY_NAME,c.CATEGORY_ORDER,c.CATEGORY_STATUS,od.data_id,od.data_name,od.data_value from product p "
				+ "left join category c on c.CATEGORY_ID = p.PRODUCT_CATEGORY_ID "
				+ "left join other_data od on od.data_product_id = p.PRODUCT_ID "
				+ "where 1=1");
		if(categoryId != "") {
			str.append(" and PRODUCT_CATEGORY_ID = ?");
			params.add(categoryId);
		}
		if(productName != "") {
			str.append(" and PRODUCT_NAME = ?");
			params.add(productName);
		}
		if(isLack != "") {
			str.append(" and PRODUCT_IS_LACK = ?");
			params.add(isLack);
		}
		String sql = str.append(" limit ?,?").toString();
		List<HashMap<String, Object>> list = null;
		
		PageResult pageResult = null;
		if(params.size() > 0) {
			list = SqlUtil.executeQuery(sql, params.get(0),(pageNum-1)*pageSize,pageSize);
		}else {
			list = SqlUtil.executeQuery(sql,(pageNum - 1)*pageSize,pageNum);
		}
		List<Product> products = null;
		if(list.size() > 0) {
			pageResult = new PageResult();
			products = new ArrayList<Product>();
			for(HashMap<String, Object> map : list) {
				Product product = new Product();
				product.setProductId(StringUtil.toString(map.get("PRODUCT_ID")));
				product.setProductCode(StringUtil.toString(map.get("PRODUCT_CODE")));
				product.setProductName(StringUtil.toString(map.get("PRODUCT_NAME")));
				product.setProductStandard(StringUtil.toString(map.get("PRODUCT_STANDARD")));
				product.setProductSmallUnit(StringUtil.toString(map.get("PRODUCT_SMALL_UNIT")));
				product.setProductSmallPrice(new BigDecimal(StringUtil.toString(map.get("PRODUCT_SMALL_PRICE"))));
				product.setProductLargerUnit(StringUtil.toString(map.get("PRODUCT_LARGER_UNIT")));
				product.setProductLargerPrice(new BigDecimal(StringUtil.toString(map.get("PRODUCT_LARGER_PRICE"))));
				product.setProductCategoryId(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				product.setProductBrandId(StringUtil.toString(map.get("PRODUCT_BRAND_ID")));
				product.setProductPhoto(StringUtil.toString(map.get("PRODUCT_PHOTO")));
				product.setProductIsSale(Byte.valueOf(StringUtil.toString(map.get("PRODUCT_IS_SALE"))));
				product.setProductIsLack(Byte.valueOf(StringUtil.toString(map.get("PRODUCT_IS_LACK"))));
				product.setProductStatus(Byte.valueOf(StringUtil.toString(map.get("PRODUCT_STATUS"))));
				product.setProductOrder(Integer.valueOf(StringUtil.toString(map.get("PRODUCT_ORDER"))));
				try {
					product.setProductDateTime(DateUtils.toDate(StringUtil.toString(map.get("PRODUCT_DATE_TIME"))));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				product.setProductLargerStandard(StringUtil.toString(map.get("PRODUCT_LARGER_STANDARD")));
				product.setProductSuggestPrice(BigDecimalUtil.toBigDecimal(StringUtil.toString(map.get("PRODUCT_SUGGEST_PRICE"))));
				
				Category category = new Category();
				category.setCategoryId(StringUtil.toString(map.get("PRODUCT_CATEGORY_ID")));
				category.setCategoryName(StringUtil.toString(map.get("CATEGORY_NAME")));
				category.setCategoryOrder(IntegerUtil.toInt(map.get("CATEGORY_ORDER")));
				category.setCategoryStatus(ByteUtil.toByte(map.get("CATEGORY_STATUS")));
				
				product.setCategory(category);
				
				OtherData data = new OtherData();
				data.setDataId(StringUtil.toString(map.get("data_id")));
				data.setDataName(StringUtil.toString(map.get("data_name")));
				data.setDataProductId(StringUtil.toString(map.get("PRODUCT_ID")));
				data.setDataValue(StringUtil.toString(map.get("data_value")));
				data.setValues(data.split());
				
				List<OtherData> datas = new ArrayList<OtherData>();
				datas.add(data);
				
				product.setParams(datas);
				products.add(product);
			}
			pageResult.setList(products);
			pageResult.setPageNum(pageNum);
			pageResult.setPageSize(pageSize);
		}
		
		StringBuffer str2 = new StringBuffer("select count(*) as row from product p "
				+ "left join category c on c.CATEGORY_ID = p.PRODUCT_CATEGORY_ID "
				+ "left join other_data od on od.data_product_id = p.PRODUCT_ID "
				+ "where 1=1");
		if(categoryId != "") {
			str2.append(" or PRODUCT_CATEGORY_ID = ?");
		}
		if(productName != "") {
			str2.append(" or PRODUCT_NAME = ?");
		}
		if(isLack != "") {
			str2.append(" or PRODUCT_IS_LACK = ?");
		}
		String sql2 = str2.toString();
		List<HashMap<String, Object>> list2 = null;
		if(params.size() > 0) {
			list2 = SqlUtil.executeQuery(sql2,params.get(0));
		}else {
			list2 = SqlUtil.executeQuery(sql2);
		}
		String row = StringUtil.toString(list2.get(0).get("row"));
		int count = IntegerUtil.toInt(row);
		pageResult.setTotal(count);
		pageResult.setPages(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
		return pageResult;
	}
}
