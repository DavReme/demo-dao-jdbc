package application;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Department;
import model.entities.Product;

public class Program {
    public static void main(String[] args) {
        ProductDao productDao = DaoFactory.createProductDao();

        Product product = productDao.findById(3);
        System.out.println(product);
    }
}
