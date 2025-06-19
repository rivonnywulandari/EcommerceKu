package com.bootcamp.latihan.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bootcamp.latihan.entities.Order;
import com.bootcamp.latihan.entities.OrderItem;
import com.bootcamp.latihan.entities.Product;

public class ProductRepository {
	
	private Connection conn;
	public ProductRepository(Connection conn) {
		this.conn = conn;
	}
	
	public List<Product> findAll() throws SQLException {
		String sql = "SELECT * FROM products_table WHERE is_delete is null ";
		List<Product> products = new ArrayList<>();

		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next()) {
			Product product = new Product(
				rs.getInt("id"),
				rs.getString("name"),
				rs.getString("type"),
				rs.getInt("price")
			);
			products.add(product);
		}
		return products;
	}

	
	public Product findById(int id) {
		String sql = "SELECT * FROM products_table WHERE id = '"+ id +"'";
		Product product = null;
		try {
//			Connection conn = DBUtils.getConnection();
//			Connection conn = DBPools.getConnection();
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {	
				product = new Product(rs.getInt("id"), rs.getString("name"),  rs.getString("type"), rs.getInt("price"));
						
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return product;
	}
	
	public void add(Product product) {
	
		String sql = "INSERT INTO products_table (name, type, price) VALUES (?, ?, ?)";

			try {

				PreparedStatement stmt;
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, product.getName());
				stmt.setString(2, product.getType());
				stmt.setInt(3, product.getPrice());

				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
	}
	
	public void update(Product product) {
		
		String sql = "UPDATE products_table SET name=?, type=?, price=? WHERE id=?";

		try {
			
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getType());
			stmt.setInt(3, product.getPrice());
			stmt.setInt(4, product.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void order(Order order) {
	    String sql = "INSERT INTO orders_table (product_id, product_name, price, quantity) VALUES (?, ?, ?, ?)";

	    try {
	        PreparedStatement stmt = conn.prepareStatement(sql);

	        for (OrderItem item : order.getItems()) {
	            stmt.setInt(1, item.getId());
	            stmt.setString(2, item.getName());
	            stmt.setInt(3, item.getPrice());
	            stmt.setInt(4, item.getQuantity());
	            stmt.executeUpdate(); 
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public void delete(int id) {
		String sql = "UPDATE products_table SET is_deleted = 1 WHERE id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
