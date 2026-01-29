package application;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Department;
import model.entities.Product;

public class Program {
    public static void main(String[] args) {
        Department obj = new Department(1, "Books");
        Product product = new Product(21, "Resident Evil Requiem", 349.90, 25, obj);
        
        ProductDao productDao = DaoFactory.createProductDao();

        System.out.println(product);
    }
}
