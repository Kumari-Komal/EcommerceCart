package shoppingCart.dao;

import java.sql.SQLException;
import java.util.List;

import shoppingCart.model.Cart;

public interface CartDao {

	void insertItems(Cart cart) throws SQLException;

	Cart selectItems(long ItemsId);

	List<Cart> selectAllItems();

	boolean deleteItems(int id) throws SQLException;

	boolean updateItems(Cart cart) throws SQLException;

}