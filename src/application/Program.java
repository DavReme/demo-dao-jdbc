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

        System.out.println("Operation test: findByDepartment | Class: Product");
        List<Product> list = new ArrayList<>();
        Department department = new Department(2, null);
        list = productDao.findByDepartment(department);
        for (Product p : list) {
            prettyPrinting(p);
        }
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
