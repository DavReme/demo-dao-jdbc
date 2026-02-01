package model.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                Product product = instaciateProduct(rs, department);
                return product;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatemente(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Product> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();

        try {
            ps = connection.prepareStatement(
                "SELECT produto.*, departamento.nome as departamento FROM produto "
                + "INNER JOIN departamento "
                + "ON produto.categoria_id = departamento.id"
            );
            rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(instaciateProduct(rs, instanciateDepartment(rs)));
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatemente(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Product> findByDepartment(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();

        try {
            ps = connection.prepareStatement(
                "SELECT produto.*, departamento.nome as departamento "
                + "FROM produto INNER JOIN departamento "
                + "ON produto.categoria_id = departamento.id "
                + "WHERE departamento.id = ? "
                + "ORDER BY nome");
            ps.setInt(1, department.getId());
            rs = ps.executeQuery();

            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department d = map.get(rs.getInt("categoria_id"));
                if (d == null) {
                    d = instanciateDepartment(rs);
                    map.put(rs.getInt("categoria_id"), d);
                }
                list.add(instaciateProduct(rs, d));
            }
        
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatemente(ps);
            DB.closeResultSet(rs);
        }
    }

    private Product instaciateProduct(ResultSet rs, Department department) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("nome"));
        product.setPrice(rs.getDouble("preco"));
        product.setQuantity(rs.getInt("quantidade"));
        product.setDepartment(department);
        return product;
    }

    private Department instanciateDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setId(rs.getInt("categoria_id"));
        department.setName(rs.getString("departamento"));
        return department;
    }
}
