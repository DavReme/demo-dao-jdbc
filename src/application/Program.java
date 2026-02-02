package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.ProductDao;
import model.entities.Department;
import model.entities.Product;

public class Program {
    public static void main(String[] args) {
        ProductDao productDao = DaoFactory.createProductDao();
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        List<Product> list = new ArrayList<>();
        List<Department> depList = new ArrayList<>();

        System.out.println("Operation test: findById | Class: ProductDaoJDBC");
        prettyPrinting(productDao.findById(2));

        System.out.println("Operation test: findByDepartment | Class: ProductDaoJDBC");
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

        System.out.println("Operation test: update | Class: ProductDaoJDBC");
        Product updateProduct = productDao.findById(2);
        updateProduct.setPrice(3999.99);
        productDao.update(updateProduct);
        System.out.println("Confirming updating of product: " + updateProduct.getId() + " named \"" + updateProduct.getName() + "\".");

        System.out.println();
        System.out.println("===== Department Test =====");

        System.out.println("Operation test: insert | Class: DepartmentDaoJDBC");
        Department depObj = new Department (
            null,
            "Jogos"
        );
        departmentDao.insert(depObj);
        System.out.println("Confirming inscription of department: " + depObj.getId() + " named \"" + depObj.getName() + "\".");

        System.out.println("Operation test: update | Class: DepartmentDaoJDBC");
        depObj = departmentDao.findById(depObj.getId());
        depObj.setName("Discos");
        departmentDao.update(depObj);
        System.out.println("Confirming update of department: " + depObj.getId() + " named \"" + depObj.getName() + "\".");


        System.out.println("Operation test: findById | Class: DepartmentDaoJDBC");
        System.out.println(departmentDao.findById(1));

        System.out.println("Operation test: listAll | Class: DepartmentDaoJDBC");
        depList = departmentDao.findAll();
        for (Department d : depList) {
            System.out.println(d);
        }

        System.out.println("Operation test: delete | Class: DepartmentDaoJDBC");
        departmentDao.deleteById(depObj.getId());
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
