package shoppingCart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import shoppingCart.model.Cart;
import shoppingCart.utils.JDBCUtils;



public class CartDaoImpl implements CartDao {

	private static final String INSERT_Item_SQL = "INSERT INTO cart"
			+ "  (itemid,itemname,uid,Price) VALUES " + " (?, ?, ?, ?);";

	private static final String SELECT_Items_BY_ID = "select cid,itemid,itemname,uid Price from cart where cid =?";
	private static final String SELECT_ALL_Items = "select * from cart";
	private static final String DELETE_Items_BY_ID = "delete from cart where cid = ?;";
	private static final String UPDATE_Cart = "update cart set itemid=?,itemname = ?,uid=? Price= ? where cid = ?;";

	public CartDaoImpl() {
	}

	@Override
	public void insertItems(Cart cart) throws SQLException {
		System.out.println(INSERT_Item_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Item_SQL)) {
			preparedStatement.setString(1,cart.getItemId());
			preparedStatement.setString(2, cart.getItemName());
			preparedStatement.setString(3,cart.getuid());
			preparedStatement.setString(4, cart.getPrice());
			
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
	}

	@Override
	public Cart selectItems(long ItemsId) {
		Cart cart = null;
		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Items_BY_ID);) {
			preparedStatement.setLong(1, ItemsId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long cid = rs.getLong("cid");
				String itemid =rs.getString("itemid");
				String itemname = rs.getString("itemname");
				String uid = rs.getString("uid");
				String Price = rs.getString("Price");
				
				
				cart = new Cart(cid,itemid, itemname,uid, Price);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return cart;
	}

	@Override
	public List<Cart> selectAllItems() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Cart> carts = new ArrayList<>();

		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Items);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				long cid = rs.getLong("cid");
				String itemid = rs.getString("itemid");
				String itemname = rs.getString("itemname");
				String uid = rs.getString("uid");
				String Price = rs.getString("Price");
				
				carts.add(new Cart(cid,itemid, itemname,uid, Price));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return carts;
	}

	@Override
	public boolean deleteItems(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_Items_BY_ID);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	@Override
	public boolean updateItems(Cart cart) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_Cart);) {
			statement.setString(1, cart.getItemId());
			statement.setString(2, cart.getItemName());
			statement.setString(3, cart.getuid());
			statement.setString(4, cart.getPrice());
			
			statement.setLong(5, cart.getcid());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}
