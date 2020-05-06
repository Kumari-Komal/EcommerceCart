package shoppingCart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import shoppingCart.model.Cart;
import shoppingCart.utils.JDBCUtils;



public class CartDaoImpl implements CartDao {

	private static final String INSERT_Item_SQL = "INSERT INTO carts"
			+ "  (ProductName, Price, description, target_date) VALUES " + " (?, ?, ?, ?);";

	private static final String SELECT_Items_BY_ID = "select id,ProductName, Price, description, target_date from carts where id =?";
	private static final String SELECT_ALL_Items = "select * from carts";
	private static final String DELETE_Items_BY_ID = "delete from carts where id = ?;";
	private static final String UPDATE_Cart = "update carts set ProductName = ?, Price= ?, description =?, target_date =? where id = ?;";

	public CartDaoImpl() {
	}

	@Override
	public void insertItems(Cart cart) throws SQLException {
		System.out.println(INSERT_Item_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Item_SQL)) {
			preparedStatement.setString(1, cart.getProductName());
			preparedStatement.setString(2, cart.getPrice());
			preparedStatement.setString(3, cart.getDescription());
			preparedStatement.setDate(4, JDBCUtils.getSQLDate(cart.getTargetDate()));
			
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
				long id = rs.getLong("id");
				String ProductName = rs.getString("ProductName");
				String Price = rs.getString("Price");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				
				cart = new Cart(id, ProductName, Price, description, targetDate);
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
				long id = rs.getLong("id");
				String ProductName = rs.getString("ProductName");
				String Price = rs.getString("Price");
				String description = rs.getString("description");
				LocalDate targetDate = rs.getDate("target_date").toLocalDate();
				
				carts.add(new Cart(id, ProductName, Price, description, targetDate));
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
			statement.setString(1, cart.getProductName());
			statement.setString(2, cart.getPrice());
			statement.setString(3, cart.getDescription());
			statement.setDate(4, JDBCUtils.getSQLDate(cart.getTargetDate()));
			
			statement.setLong(5, cart.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}
