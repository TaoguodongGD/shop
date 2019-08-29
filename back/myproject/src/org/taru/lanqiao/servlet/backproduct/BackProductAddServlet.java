package org.taru.lanqiao.servlet.backproduct;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.service.ProductServiceImpl;
import org.taru.lanqiao.service.ProductServiceImpl;
import org.taru.lanqiao.util.DateUtils;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;
import org.taru.lanqiao.vo.PageResult;

@WebServlet("/api/backstage/product/add")
public class BackProductAddServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productCode = req.getParameter("productCode");
		String productName = req.getParameter("productName");
		String productStandard = req.getParameter("productStandard");
		System.out.println(productStandard);
		String productSmallUnit = req.getParameter("productSmallUnit");
		BigDecimal productSmallPrice = new BigDecimal(req.getParameter("productSmallPrice"));
		String productLargerUnit = req.getParameter("productLargerUnit");
		BigDecimal productLargerPrice = new BigDecimal(req.getParameter("productLargerPrice"));
		String productCategoryId = req.getParameter("productCategoryId");
		String productBrandId = req.getParameter("productBrandId");
		String productPhoto = req.getParameter("productPhoto");
		Byte productIsSale = Byte.parseByte(req.getParameter("productIsSale"));
		Byte productIsLack = Byte.parseByte(req.getParameter("productIsLack"));
		Byte productStatus = Byte.parseByte(req.getParameter("productStatus"));
		Integer productOrder = Integer.parseInt(req.getParameter("productOrder"));
		String productDateTime = req.getParameter("productDateTime");
		String productLargerStandard = req.getParameter("productLargerStandard");
		BigDecimal productSuggestPrice = new BigDecimal(req.getParameter("productSuggestPrice"));
		
		Product product = new Product();
		product.setProductCode(productCode);
		product.setProductName(productName);
		product.setProductStandard(productStandard);
		product.setProductSmallUnit(productSmallUnit);
		product.setProductSmallPrice(productSmallPrice);
		product.setProductLargerUnit(productLargerUnit);
		product.setProductLargerPrice(productLargerPrice);
		product.setProductCategoryId(productCategoryId);
		product.setProductBrandId(productBrandId);
		product.setProductPhoto(productPhoto);
		product.setProductIsSale(productIsSale);
		product.setProductIsLack(productIsLack);
		product.setProductStatus(productStatus);
		product.setProductOrder(productOrder);
		try {
			product.setProductDateTime(DateUtils.toDate(productDateTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		product.setProductLargerStandard(productLargerStandard);
		product.setProductSuggestPrice(productSuggestPrice);
		
		ProductServiceImpl impl = new ProductServiceImpl();
		JsonResult jsonResult = null;
		boolean bool = false;
		try {
			bool = impl.addProduct(product);
			if(bool) {
				jsonResult = new JsonResult("200","添加商品成功",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","添加商品异常",null);
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
