package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Cat;

public class CatDao {
	
	private Connection connection;
	private final String GET_CATS_QUERY = "Select * from cats";
	private final String GET_CAT_INFO_QUERY = "Select * from cats where id = ?";
	private final String CREATE_NEW_CAT_QUERY = "Insert into cats(name, color, hat) values(?, ?, ?)";
	private final String DELETE_CAT_QUERY = "Delete from cats where id = ?";
	private final String UPDATE_CAT_QUERY = "Update cats set name = ?, color = ?, hat = ? where id = ?";
	
	public CatDao() {
		connection = DBConnection.getConnection();
		
	}
	
	public List<Cat> getCats() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_CATS_QUERY).executeQuery();
		List<Cat> cats = new ArrayList<Cat>();
		
		while (rs.next()) {
			cats.add(populateCats(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
		
		return cats;
	}
	
	public Cat getCatInfoById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_CAT_INFO_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateCats(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
	}
	
	public void createCat(String catName, String color, String typeOfHat) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_CAT_QUERY);
		ps.setString(1, catName);
		ps.setString(2, color);
		ps.setString(3, typeOfHat);
		ps.executeUpdate();
	}
	
	public void deleteCatById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_CAT_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void updateCatById(String catName, String color, String typeOfHat, int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_CAT_QUERY);
		ps.setString(1, catName);
		ps.setString(2, color);
		ps.setString(3, typeOfHat);
		ps.setInt(4, id);
		ps.executeUpdate();
	}
	
	private Cat populateCats(int catId, String name, String color, String typeOfHat) {
		return new Cat(catId, name, color, typeOfHat);
		
	}

}
