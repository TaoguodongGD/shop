package org.taru.lanqiao.servlet.backproduct;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.service.ProductServiceImpl;
import org.taru.lanqiao.util.BigDecimalUtil;
import org.taru.lanqiao.util.ByteUtil;
import org.taru.lanqiao.util.DateUtils;
import org.taru.lanqiao.util.IntegerUtil;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.util.StringUtil;
import org.taru.lanqiao.vo.JsonResult;

@WebServlet("/api/backstage/product/update")
public class BackProductUpdateServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productId = req.getParameter("productId");
		String productCode = req.getParameter("productCode");
		String productName = req.getParameter("productName");
		String productStandard = req.getParameter("productStandard");
		String productSmallUnit = req.getParameter("productSmallUnit");
		String productSmallPrice = req.getParameter("productSmallPrice");
		String productLargerUnit = req.getParameter("productLargerUnit");
		String productLargerPrice = req.getParameter("productLargerPrice");
		String productCategoryId = req.getParameter("productCategoryId");
		String productBrandId = req.getParameter("productBrandId");
		String productPhoto = req.getParameter("productPhoto");
		Byte productIsSale = ByteUtil.toByte(req.getParameter("productIsSale"));
		Byte productIsLack = ByteUtil.toByte(req.getParameter("productIsLack"));
		String productStatus = req.getParameter("productStatus");
		if(productStatus == "") {
			productStatus = "1";
		}
		Integer productOrder = IntegerUtil.toInt(req.getParameter("productOrder"));
		Date productDateTime = null;
		try {
			productDateTime = DateUtils.toDate(DateUtils.toDate1(req.getParameter("productDateTime")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String productLargerStandard = req.getParameter("productLargerStandard");
		String productSuggestPrice = req.getParameter("productSuggestPrice");

		Product product = new Product();
		product.setProductId(productId);
		product.setProductCode(productCode);
		product.setProductName(productName);
		product.setProductStandard(productStandard);
		product.setProductSmallUnit(productSmallUnit);
		product.setProductSmallPrice(BigDecimalUtil.toBigDecimal(productSmallPrice));
		product.setProductLargerUnit(productLargerUnit);
		product.setProductLargerPrice(BigDecimalUtil.toBigDecimal(productLargerPrice));
		product.setProductCategoryId(productCategoryId);
		product.setProductBrandId(productBrandId);
		product.setProductPhoto(productPhoto);
		product.setProductIsSale(productIsSale);
		product.setProductIsLack(productIsLack);
		product.setProductStatus(ByteUtil.toByte(productStatus));
		product.setProductOrder(productOrder);
		product.setProductDateTime(productDateTime);
		product.setProductLargerStandard(productLargerStandard);
		product.setProductSuggestPrice(BigDecimalUtil.toBigDecimal(productSuggestPrice));
		
		JsonResult jsonResult = null;
		ProductServiceImpl impl = new ProductServiceImpl();
		boolean bool = false;
		try {
			bool = impl.update(product);
			if(bool) {
				jsonResult = new JsonResult("200","修改成功",null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = new JsonResult("500","修改异常",null);
		}
		JsonWriter.writer(resp, jsonResult);
	}
}
