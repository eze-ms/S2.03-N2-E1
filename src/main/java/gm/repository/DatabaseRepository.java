package gm.repository;

import java.util.UUID;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import gm.model.*;
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
                        .append("category", product.getCategory())
                        .append("storeId", product.getStoreId());

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
                        .append("category", product.getCategory())
                        .append("storeId", product.getStoreId());

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

    //! Métodos stores
    public String insertStore(Store store) {
        try {
            MongoCollection<Document> storeCollection = database.getCollection("store");

            // Buscar si ya existe una tienda con la misma dirección y código postal
            Document existingStore = storeCollection.find(
                    new Document("address", store.getAddress()).append("postalCode", store.getPostalCode())
            ).first();

            Document dbStore;
            String storeId;

            if (existingStore != null) {
                storeId = existingStore.getString("id");
                System.out.println("Esta tienda ya existe con id: " + storeId);

                // Actualizar la tienda
                dbStore = new Document("address", store.getAddress())
                        .append("postalCode", store.getPostalCode())
                        .append("city", store.getCity())
                        .append("province", store.getProvince());

                storeCollection.updateOne(
                        new Document("id", storeId),
                        new Document("$set", dbStore)
                );
                System.out.println("Tienda actualizada correctamente.");
            } else {
                // Crear una nueva tienda
                storeId = UUID.randomUUID().toString(); // Generación de UUID para la tienda
                dbStore = new Document("id", storeId)
                        .append("address", store.getAddress())
                        .append("postalCode", store.getPostalCode())
                        .append("city", store.getCity())
                        .append("province", store.getProvince());

                storeCollection.insertOne(dbStore);
                System.out.println("Tienda insertada con id: " + storeId);
            }

            return storeId;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar o actualizar la tienda.");
            return null;
        }
    }

    public Document findStoreById(String storeId) {
        MongoCollection<Document> storeCollection = database.getCollection("store");
        return storeCollection.find(new Document("id", storeId)).first();
    }

    public FindIterable<Document> findAllStores() {
        MongoCollection<Document> storeCollection = database.getCollection("store");
        return storeCollection.find();
    }

    public void updateStore(String storeId, Document updateStore) {
        MongoCollection<Document> storeCollection = database.getCollection("store");
        storeCollection.updateOne(
                new Document("id", storeId),
                new Document("$set", updateStore)
        );
        System.out.println("Tienda actualizada correctamente.");
    }

    public void deleteStoreById(String storeId) {
        MongoCollection<Document> storeCollection = database.getCollection("store");
        storeCollection.deleteOne(new Document("id", storeId));
        System.out.println("Tienda eliminada correctamente.");
    }


    //! Métodos employee
    public String insertEmployee(Employee employee) {
        try {
            MongoCollection<Document> employeeCollection = database.getCollection("employee");

            // Buscar si ya existe un empleado con el mismo NIF o teléfono
            Document existingEmployee = employeeCollection.find(
                    new Document("nif", employee.getNif()).append("phone", employee.getPhone())
            ).first();

            Document dbEmployee;
            String employeeId;

            if (existingEmployee != null) {
                employeeId = existingEmployee.getString("id");
                System.out.println("Este empleado ya existe con id: " + employeeId);

                // Actualizar el empleado
                dbEmployee = new Document("firstName", employee.getFirstName())
                        .append("lastName", employee.getLastName())
                        .append("nif", employee.getNif())
                        .append("phone", employee.getPhone())
                        .append("role", employee.getRole())
                        .append("storeId", employee.getStoreId());

                employeeCollection.updateOne(
                        new Document("id", employeeId),
                        new Document("$set", dbEmployee)
                );
                System.out.println("Empleado actualizado correctamente.");
            } else {
                // Crear un nuevo empleado
                employeeId = UUID.randomUUID().toString(); // Generación de UUID para el empleado
                dbEmployee = new Document("id", employeeId)
                        .append("firstName", employee.getFirstName())
                        .append("lastName", employee.getLastName())
                        .append("nif", employee.getNif())
                        .append("phone", employee.getPhone())
                        .append("role", employee.getRole())
                        .append("storeId", employee.getStoreId());

                employeeCollection.insertOne(dbEmployee);
                System.out.println("Empleado insertado con id: " + employeeId);
            }

            return employeeId;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar o actualizar el empleado.");
            return null;
        }
    }

    public Document findEmployeeById(String employeeId) {
        MongoCollection<Document> employeeCollection = database.getCollection("employee");
        return employeeCollection.find(new Document("id", employeeId)).first();
    }

    public FindIterable<Document> findAllEmployees() {
        MongoCollection<Document> employeeCollection = database.getCollection("employee");
        return employeeCollection.find();
    }

    public void updateEmployee(String employeeId, Document updateEmployee) {
        MongoCollection<Document> employeeCollection = database.getCollection("employee");
        employeeCollection.updateOne(
                new Document("id", employeeId),
                new Document("$set", updateEmployee)
        );
        System.out.println("Empleado actualizada correctamente.");
    }

    public void deleteEmployeeById(String employeeId) {
        MongoCollection<Document> employeeCollection = database.getCollection("employee");
        employeeCollection.deleteOne(new Document("id", employeeId));
        System.out.println("Empleado eliminada correctamente.");
    }
}
