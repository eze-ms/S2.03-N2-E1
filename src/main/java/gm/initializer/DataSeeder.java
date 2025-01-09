package gm.initializer;

import gm.model.Client;
import gm.model.Product;
import gm.model.Order;
import gm.model.Store;
import gm.model.Employee;
import gm.service.ClientService;
import gm.service.ProductService;
import gm.service.OrderService;
import gm.service.StoreService;
import gm.service.EmployeeService;

import java.util.Map;
import java.util.UUID;

public class DataSeeder {
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;
    private final StoreService storeService;
    private final EmployeeService employeeService;

    public DataSeeder(ClientService clientService, ProductService productService, OrderService orderService,
                      StoreService storeService, EmployeeService employeeService) {
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
        this.storeService = storeService;
        this.employeeService = employeeService;
    }

    public void seedData() {
        // Crear y agregar una tienda
        Store newStore = new Store(UUID.randomUUID().toString(), "Calle Falsa 123", "08001", "Barcelona", "Barcelona");
        storeService.insertStore(newStore);

        // Crear y agregar un cliente
        Client newClient = new Client(UUID.randomUUID().toString(), "Carlos", "Peña", "Calle Mayor", "123", "3B", "08001", "Barcelona", "Barcelona", "678912345", "Sin cebolla");
        clientService.insertClient(newClient);

        // Crear y agregar un producto con el storeId de la tienda recién creada
        Product newProduct = new Product(UUID.randomUUID().toString(),
                "Pizza Margarita", "Deliciosa pizza con tomate y mozzarella",
                "url_image", 9.99, "pizza", newStore.getId());
        productService.insertProduct(newProduct);

        // Crear y agregar un empleado con el storeId de la tienda
        Employee newEmployee = new Employee(UUID.randomUUID().toString(), "Juan", "Lopez", "12345678A", "678901234", "cook", newStore.getId());
        employeeService.insertEmployee(newEmployee);

        // Crear y agregar una orden con el clientId y storeId correspondientes
        Order newOrder = new Order(UUID.randomUUID().toString(), newClient.getId(), newStore.getId(), "2025-01-07T12:30", "home", Map.of("pizza", 2), 20.99, "Sin cebolla", newEmployee.getId(), "2025-01-07T13:00");
        orderService.insertOrder(newOrder);
    }
}
