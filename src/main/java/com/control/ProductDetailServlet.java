package com.control;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CartDAO;
import com.dao.FeatureDAO;
import com.dao.FeatureTypeDAO;
import com.dao.ProductDAO;
import com.dao.SubImageDAO;
import com.model.Feature;
import com.model.FeatureType;
import com.model.Product;
import com.model.SubImage;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductDAO productDAO= new ProductDAO();
	SubImageDAO subImageDAO = new SubImageDAO();
	FeatureDAO featureDAO = new FeatureDAO();
	FeatureTypeDAO featureTypeDAO = new FeatureTypeDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ShowProduct(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void SessionExample (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String address = "/CartServlet";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	
	private void ShowProduct (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String address = "product-detail.jsp";
		int id = Integer.parseInt(request.getParameter("id"));
		List<SubImage> subImages = subImageDAO.getSubImage(id);
		Product product = productDAO.getProductByID(id);
		request.setAttribute("subImages", subImages);
		request.setAttribute("product", product);
		Map<FeatureType, List<Feature>> featureMap = featureDAO.getFeatureMapByProductID(id);
		request.setAttribute("featureMap", featureMap);
		Map<String, String> productImageByFeatureName = featureDAO.getProductImageByFeatureNameMap(id);
		request.setAttribute("productImageByFeatureNameMap", productImageByFeatureName);
		String currentFeature = request.getParameter("currentFeature");
		if(currentFeature==null)
		{
			currentFeature = "basic";
			request.setAttribute("currentFeature", currentFeature);
		}
		else
		{
			Feature feature = featureDAO.getFeatureByName(currentFeature);
			request.setAttribute("currentImage", feature.getImage());
			request.setAttribute("currentFeature", currentFeature);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
