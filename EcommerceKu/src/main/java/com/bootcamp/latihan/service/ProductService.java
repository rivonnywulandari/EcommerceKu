package com.bootcamp.latihan.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.bootcamp.latihan.entities.Order;
import com.bootcamp.latihan.entities.Product;
import com.bootcamp.latihan.repository.ProductRepository;
import com.bootcamp.latihan.repository.DBPools;

public class ProductService {
	private ProductRepository productRepository;
	private Connection conn;
	
	public ProductService(){
		conn = DBPools.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		productRepository = new ProductRepository(conn);
		
	}
	
	public List<Product> findAll() throws SQLException {
		return productRepository.findAll();
	}

	public Product findById(int id) throws SQLException {
		return productRepository.findById(id);
	}

	public void add(Product product) throws SQLException {
		try {
			conn.setAutoCommit(false);
			productRepository.add(product);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}
	}

	public void update(Product product) throws SQLException {
		try {
			conn.setAutoCommit(false);
			productRepository.update(product);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}
	}

	public void delete(int id) throws SQLException {
		try {
			conn.setAutoCommit(false);
			productRepository.delete(id);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}
	}
	
	public void order(Order order) throws SQLException {
		try {
			conn.setAutoCommit(false);
			productRepository.order(order);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}
	}



}
