package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Department;
import model.entities.Product;

public class Program {
    public static void main(String[] args) {
        ProductDao productDao = DaoFactory.createProductDao();

        System.out.println("Operation test: findById | Class: ProductDaoJDBC");
        prettyPrinting(productDao.findById(2));

        System.out.println("Operation test: findByDepartment | Class: ProductDaoJDBC");
        List<Product> list = new ArrayList<>();
        Department department = new Department(2, null);
        list = productDao.findByDepartment(department);
        for (Product p : list) {
            prettyPrinting(p);
        }

        System.out.println("Operation test: findAll | Class: ProductDaoJDBC");
        
        list = productDao.findAll();
        for (Product p : list) {
            prettyPrinting(p);
        }

        System.out.println("Operation test: insert | Class: ProductDaoJDBC");
        Product product = new Product(
            null,
            "PlayStation 5 Pro",
            6999.99,
            15,
            new Department(1, null)
        );
        productDao.insert(product);
        System.out.println("Confirming inscription of product: " + product.getId() + " named \"" + product.getName() + "\".");
    
        System.out.println("Operation test: deleteById | Class: ProductDaoJDBC");
        productDao.deleteById(product.getId());
    }

    private static void prettyPrinting(Product p) {
        System.out.println(
            "[id = "
            + p.getId() + "]\t[nome = "
            + p.getName() + "], [preco = "
            + p.getPrice() + "], [quantidade = "
            + p.getQuantity() + "], [departamento = "
            + p.getDepartment().getName() + "]"
        );
    }
}
