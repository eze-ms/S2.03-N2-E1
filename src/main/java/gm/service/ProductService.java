package gm.service;

import gm.repository.DatabaseRepository;
import org.bson.Document;
import gm.model.Product;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProductService {
    private final DatabaseRepository repository;

    // Definir las categorías válidas en un conjunto
    private static final Set<String> validCategories = new HashSet<>(Arrays.asList("pizza", "hamburguesa", "bebida"));

    public ProductService(DatabaseRepository repository) {
        this.repository = repository;
    }


    public String insertProduct(Product product) {
        try {
            return repository.insertProduct(product);
        } catch (Exception e) {
            System.err.println("Error al insertar el producto: " + e.getMessage());
            return null;
        }
    }

    public void getProductById(String productId) {
        try {
            Document product = repository.findProductById(productId);
            if (product != null) {
                System.out.println("Producto encontrado: " + product.toJson());
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Error al buscar producto: " + e.getMessage());
        }
    }

    public void findAllProducts() {
        try {
            Iterable<Document> products = repository.findAllProducts();
            if (!products.iterator().hasNext()) {
                System.out.println("No se encontraron productos.");
                return;
            }

            for (Document product : products) {
                System.out.println("Producto encontrado: " + product.toJson());
            }
        } catch (Exception e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
    }

    public void updateProduct(String productId, Document updateProduct) {
        try {
            Document existingProduct = repository.findProductById(productId);
            if (existingProduct == null) {
                System.out.println("Producto no encontrado, no se puede actualizar.");
                return;
            }

            repository.updateProduct(productId, updateProduct);
            System.out.println("Producto actualizado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    public void deleteProductById(String productId) {
        try {
            Document existingProduct = repository.findProductById(productId);
            if (existingProduct == null) {
                System.out.println("Producto no encontrado, no se puede eliminar.");
                return;
            }

            repository.deleteProductById(productId);
            System.out.println("Producto eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
        }
    }
}
