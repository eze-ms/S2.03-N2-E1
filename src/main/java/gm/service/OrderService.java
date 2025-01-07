package gm.service;

import gm.model.Order;
import gm.repository.DatabaseRepository;
import org.bson.Document;

public class OrderService {
    private final DatabaseRepository repository;

    public OrderService(DatabaseRepository repository) {
        this.repository = repository;
    }

    public String insertOrder(Order order) {
        try {
            return repository.insertOrder(order);
        } catch (Exception e) {
            System.err.println("Error al insertar la orden: " + e.getMessage());
            return null;
        }
    }

    public void getOrderById(String orderId) {
        try {
            Document order = repository.findOrderById(orderId);
            if (order != null) {
                System.out.println("Orden encontrada: " + order.toJson());
            } else {
                System.out.println("Orden no encontrada.");
            }
        } catch (Exception e) {
            System.err.println("Error al buscar orden: " + e.getMessage());
        }
    }

    public void findAllOrders() {
        try {
            Iterable<Document> orders = repository.findAllOrders();
            if (!orders.iterator().hasNext()) {
                System.out.println("No se encontraron órdenes.");
                return;
            }

            for (Document order : orders) {
                System.out.println("Orden encontrada: " + order.toJson());
            }
        } catch (Exception e) {
            System.err.println("Error al listar orden: " + e.getMessage());
        }
    }

    public void updateOrder(String orderId, Document updateOrder) {
        try {
            Document existingOrder = repository.findOrderById(orderId);
            if (existingOrder == null) {
                System.out.println("Orden no encontrada, no se puede actualizar.");
                return;
            }

            repository.updateOrder(orderId, updateOrder);
            System.out.println("Orden actualizada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al actualizar orden: " + e.getMessage());
        }
    }

    public void deleteOrderById(String orderId) {
        try {
            Document existingOrder = repository.findOrderById(orderId);
            if (existingOrder == null) {
                System.out.println("Orden no encontrada, no se puede eliminar.");
                return;
            }

            repository.deleteOrderById(orderId);
            System.out.println("Orden eliminada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar orden: " + e.getMessage());
        }
    }
}
