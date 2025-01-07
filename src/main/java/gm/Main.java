package gm;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import gm.model.Order;
import gm.model.Product;
import gm.model.Client;
import gm.repository.DatabaseRepository;
import gm.service.ClientService;
import gm.service.ProductService;
import gm.service.OrderService;

import java.util.Map;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = client.getDatabase("foodDeliveryDB");
            DatabaseRepository repository = new DatabaseRepository(database);

            // Servicios
            ClientService clientService = new ClientService(repository);
            ProductService productService = new ProductService(repository);
            OrderService orderService = new OrderService(repository);

            // Crear cliente
            Client newClient = new Client(UUID.randomUUID().toString(), "Carlos", "Peña", "Calle Mayor", "123", "3B", "08001", "Barcelona", "Barcelona", "678912345", "Sin cebolla");
            String clientId = repository.insertClient(newClient);  // Insertar el cliente

            // Métodos del servicio de cliente usando el clientId generado
            clientService.findAllClients();
            clientService.getClientById(clientId);

            // Crear e insertar un producto
            Product newProduct = productService.createProduct("Pizza Margarita", "Deliciosa pizza con tomate y mozzarella", "url_image", 9.99, "pizza");
            String productId = productService.insertProduct(newProduct);  // Insertamos el producto con todos sus atributos

            // Métodos del servicio de producto usando el productId generado
            productService.findAllProducts();
            productService.getProductById(productId);

            // Crear e insertar una nueva orden
            Order order = new Order(UUID.randomUUID().toString(), clientId, "storeId", "2025-01-07T12:30", "home", Map.of("pizza", 2), 20.99, "Sin cebolla", "deliveryEmployeeId", "2025-01-07T13:00");
            String orderId = orderService.insertOrder(order);  // Insertamos la orden y recuperamos el orderId generado

            // Usar la orderId generada para operaciones adicionales
            orderService.findAllOrders();
            orderService.getOrderById(orderId);

        } catch (Exception e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}
