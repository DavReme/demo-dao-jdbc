package model.dao.impl;

import java.util.List;

import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.ProductDao;
import model.entities.Department;
import model.entities.Product;

public class ProductDaoJDBC implements ProductDao {
    private Connection connection;

    public ProductDaoJDBC (Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Product findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(
                "SELECT produto.*, departamento.nome as departamento "
                + "FROM produto INNER JOIN departamento "
                + "ON produto.categoria_id = departamento.id "
                + "WHERE produto.id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Department department = instanciateDepartment(rs);
                Product obj = instaciateProduct(rs, department);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatemente(ps);
            DB.closeResultSet(rs);
        }
    }

    private Product instaciateProduct(ResultSet rs, Department department) throws SQLException {
        Product obj = new Product();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("nome"));
        obj.setPrice(rs.getDouble("preco"));
        obj.setQuantity(rs.getInt("quantidade"));
        obj.setDepartment(department);
        return obj;
    }

    private Department instanciateDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setId(rs.getInt("categoria_id"));
        department.setName(rs.getString("departamento"));
        return department;
    }

    @Override
    public List<Product> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
}
