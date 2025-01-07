package gm.repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import gm.model.Client;
import gm.model.Order;
import gm.model.Product;
import org.bson.Document;

public class DatabaseRepository {
    private final MongoDatabase database;

    public DatabaseRepository(MongoDatabase database) {
        this.database = database;
    }

    public void testConnection() {
        System.out.println("Connected to database: " + database.getName());
    }

    //! Métodos clientes
    public String insertClient(Client client) {
        try {
            MongoCollection<Document> clientCollection = database.getCollection("client");

            // Buscar si el cliente ya existe por email o teléfono
            Document existingClient = clientCollection.find(new Document("phoneNumber", client.getPhoneNumber())).first();
            String clientId;

            if (existingClient != null) {
                clientId = existingClient.getString("id");
                System.out.println("Cliente ya existe con id: " + clientId);

                // Actualizar el cliente
                Document updatedClient = new Document("firstName", client.getFirstName())
                        .append("lastName", client.getLastName())
                        .append("street", client.getStreet())
                        .append("number", client.getNumber())
                        .append("floor", client.getFloor())
                        .append("postalCode", client.getPostalCode())
                        .append("city", client.getCity())
                        .append("province", client.getProvince())
                        .append("phoneNumber", client.getPhoneNumber())
                        .append("additionalNote", client.getAdditionalNote());

                clientCollection.updateOne(
                        new Document("id", clientId),
                        new Document("$set", updatedClient)
                );
                System.out.println("Cliente actualizado correctamente.");
            } else {
                clientId = UUID.randomUUID().toString(); // Generación de UUID
                Document newClient = new Document("id", clientId)
                        .append("firstName", client.getFirstName())
                        .append("lastName", client.getLastName())
                        .append("street", client.getStreet())
                        .append("number", client.getNumber())
                        .append("floor", client.getFloor())
                        .append("postalCode", client.getPostalCode())
                        .append("city", client.getCity())
                        .append("province", client.getProvince())
                        .append("phoneNumber", client.getPhoneNumber())
                        .append("additionalNote", client.getAdditionalNote());

                clientCollection.insertOne(newClient);
                System.out.println("Cliente insertado con id: " + clientId);
            }

            return clientId;  // Devolver el clientId generado o encontrado
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar o actualizar el cliente.");
            return null;
        }
    }

    public Document findClientById(String clientId) {
        MongoCollection<Document> clientCollection = database.getCollection("client");
        return clientCollection.find(new Document("id", clientId)).first();
    }

    public FindIterable<Document> findAllClients() {
        MongoCollection<Document> clientCollection = database.getCollection("client");
        return clientCollection.find();
    }

    public void updateClient(String clientId, Document updatedClient) {
        MongoCollection<Document> clientCollection = database.getCollection("client");
        clientCollection.updateOne(
                new Document("id", clientId),
                new Document("$set", updatedClient)
        );
        System.out.println("Cliente actualizado correctamente.");
    }

    public void deleteClientById(String clientId) {
        MongoCollection<Document> clientCollection = database.getCollection("client");
        clientCollection.deleteOne(new Document("id", clientId));
        System.out.println("Cliente eliminado correctamente.");
    }

    //! Métodos products
    public String insertProduct(Product product) {
        try {
            MongoCollection<Document> productCollection = database.getCollection("product");

            // Buscar por nombre y categoría para evitar duplicados
            Document existingProduct = productCollection.find(
                    new Document("name", product.getName()).append("category", product.getCategory())
            ).first();
            String productId;

            if (existingProduct != null) {
                productId = existingProduct.getString("id");
                System.out.println("Producto ya existe con id: " + productId);

                Document updatedProduct = new Document("name", product.getName())
                        .append("description", product.getDescription())
                        .append("imageUrl", product.getImageUrl())
                        .append("price", product.getPrice())
                        .append("category", product.getCategory());

                productCollection.updateOne(
                        new Document("id", productId),
                        new Document("$set", updatedProduct)
                );
                System.out.println("Producto actualizado correctamente.");
            } else {
                productId = UUID.randomUUID().toString(); // Generación de UUID
                Document newProduct = new Document("id", productId)
                        .append("name", product.getName())
                        .append("description", product.getDescription())
                        .append("imageUrl", product.getImageUrl())
                        .append("price", product.getPrice())
                        .append("category", product.getCategory());

                productCollection.insertOne(newProduct);
                System.out.println("Producto insertado con id: " + productId);
            }

            return productId;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar o actualizar el producto.");
            return null;
        }
    }

    public Document findProductById(String productId) {
        MongoCollection<Document> productCollection = database.getCollection("product");
        return productCollection.find(new Document("id", productId)).first();
    }

    public FindIterable<Document> findAllProducts() {
        MongoCollection<Document> productCollection = database.getCollection("product");
        return productCollection.find();
    }

    public void updateProduct(String productId, Document updateProduct) {
        MongoCollection<Document> productCollection = database.getCollection("product");
        productCollection.updateOne(
                new Document("id", productId),
                new Document("$set", updateProduct)
        );
        System.out.println("Producto actualizado correctamente.");
    }

    public void deleteProductById(String productId) {
        MongoCollection<Document> productCollection = database.getCollection("product");
        productCollection.deleteOne(new Document("id", productId));
        System.out.println("Producto eliminado correctamente.");
    }

    //! Métodos orders
    public String insertOrder(Order order) {
        try {
            MongoCollection<Document> orderCollection = database.getCollection("order");

            // Buscar si ya existe una orden
            Document existingOrder = orderCollection.find(
                    new Document("clientId", order.getClientId()).append("storeId", order.getStoreId()).append("dateTime", order.getDateTime())
            ).first();

            Document dbOrder;
            String orderId;

            if (existingOrder != null) {
                orderId = existingOrder.getString("id");
                System.out.println("Esta orden ya existe con id: " + orderId);

                // Actualizar la orden
                dbOrder = new Document("clientId", order.getClientId())
                        .append("storeId", order.getStoreId())
                        .append("dateTime", order.getDateTime())
                        .append("deliveryType", order.getDeliveryType())
                        .append("productsQuantity", order.getProductsQuantity())
                        .append("totalPrice", order.getTotalPrice())
                        .append("additionalNote", order.getAdditionalNote())
                        .append("deliveryEmployeeId", order.getDeliveryEmployeeId())
                        .append("deliveryDateTime", order.getDeliveryDateTime());

                orderCollection.updateOne(
                        new Document("id", orderId),
                        new Document("$set", dbOrder)
                );
                System.out.println("Orden actualizada correctamente.");
            } else {
                // Crear una nueva orden
                orderId = UUID.randomUUID().toString(); // eneración de UUID para la orden
                dbOrder = new Document("id", orderId)
                        .append("clientId", order.getClientId())
                        .append("storeId", order.getStoreId())
                        .append("dateTime", order.getDateTime())
                        .append("deliveryType", order.getDeliveryType())
                        .append("productsQuantity", order.getProductsQuantity())
                        .append("totalPrice", order.getTotalPrice())
                        .append("additionalNote", order.getAdditionalNote())
                        .append("deliveryEmployeeId", order.getDeliveryEmployeeId())
                        .append("deliveryDateTime", order.getDeliveryDateTime());

                orderCollection.insertOne(dbOrder);
                System.out.println("Orden insertada con id: " + orderId);
            }

            return orderId;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar o actualizar la orden.");
            return null;
        }
    }

    public Document findOrderById(String orderId) {
        MongoCollection<Document> orderCollection = database.getCollection("order");
        return orderCollection.find(new Document("id", orderId)).first();
    }

    public FindIterable<Document> findAllOrders() {
        MongoCollection<Document> orderCollection = database.getCollection("order");
        return orderCollection.find();
    }

    public void updateOrder(String orderId, Document updateOrder) {
        MongoCollection<Document> orderCollection = database.getCollection("order");
        orderCollection.updateOne(
                new Document("id", orderId),
                new Document("$set", updateOrder)
        );
        System.out.println("Orden actualizada correctamente.");
    }

    public void deleteOrderById(String orderId) {
        MongoCollection<Document> orderCollection = database.getCollection("order");
        orderCollection.deleteOne(new Document("id", orderId));
        System.out.println("Orden eliminada correctamente.");
    }

}
