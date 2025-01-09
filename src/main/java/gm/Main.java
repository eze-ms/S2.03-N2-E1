package gm;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import gm.initializer.DataSeeder;
import gm.repository.DatabaseRepository;
import gm.service.ClientService;
import gm.service.OrderService;
import gm.service.ProductService;
import gm.service.StoreService;
import gm.service.EmployeeService;

public class Main {
    public static void main(String[] args) {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase("foodDeliveryDB");
            DatabaseRepository repository = new DatabaseRepository(database);

            // Crear servicios
            ClientService clientService = new ClientService(repository);
            ProductService productService = new ProductService(repository);
            OrderService orderService = new OrderService(repository);
            StoreService storeService = new StoreService(repository);
            EmployeeService employeeService = new EmployeeService(repository);

            // Inicializar y sembrar datos
            DataSeeder dataSeeder = new DataSeeder(clientService, productService, orderService, storeService, employeeService);
            dataSeeder.seedData();

            System.out.println("Datos iniciales cargados correctamente.");

        } catch (Exception e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}
