package shoppingCart.web;

import java.io.IOException;
import java.sql.SQLException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingCart.dao.CartDao;
import shoppingCart.dao.CartDaoImpl;
import shoppingCart.model.Cart;



@WebServlet("/")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartDao cartDAO;

	public void init() {
		cartDAO = new CartDaoImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertItems(request, response);
				break;
			case "/delete":
				deleteItems(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateItems(request, response);
				break;
			case "/list":
				listItems(request, response);
				break;
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listItems(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Cart> listItems = cartDAO.selectAllItems();
		request.setAttribute("listItems", listItems);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		Cart existingItems = cartDAO.selectItems(cid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
		request.setAttribute("Items", existingItems);
		dispatcher.forward(request, response);

	}

	private void insertItems(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String itemid = request.getParameter("itemid");
		String itemname = request.getParameter("itemname");
		String uid = request.getParameter("uid");
		String Price = request.getParameter("Price");
		
		
		/*DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"),df);*/
		
		
		Cart newItem = new Cart(itemid,itemname, uid,Price);
		cartDAO.insertItems(newItem);
		response.sendRedirect("list");
	}

	private void updateItems(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		String itemid=request.getParameter("itemid");
		String itemname = request.getParameter("itemname");
		String uid = request.getParameter("uid");
		String Price = request.getParameter("Price");
		
		//DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		//LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
		
		
		Cart updateCart = new Cart(cid,itemid, itemname,uid, Price);
		
		cartDAO.updateItems(updateCart);
		
		response.sendRedirect("list");
	}

	private void deleteItems(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		cartDAO.deleteItems(cid);
		response.sendRedirect("list");
	}
}
