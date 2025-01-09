package gm.service;

import gm.repository.DatabaseRepository;
import gm.model.Store;
import org.bson.Document;

public class StoreService {
    private final DatabaseRepository repository;

    public StoreService(DatabaseRepository repository) {
        this.repository = repository;
    }

    public String insertStore(Store store) {
        try {
            return repository.insertStore(store);
        } catch (Exception e) {
            System.err.println("Error al insertar la tienda: " + e.getMessage());
            return null;
        }
    }

    public void getStoreById(String storeId) {
        try {
            Document store = repository.findStoreById(storeId);
            if (store != null) {
                System.out.println("Tienda encontrado: " + store.toJson());
            } else {
                System.out.println("Tienda no encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Error al buscar tienda: " + e.getMessage());
        }
    }

    public void findAllStores() {
        try {
            Iterable<Document> stores = repository.findAllStores();
            if (!stores.iterator().hasNext()) {
                System.out.println("No se encontraron tiendas.");
                return;
            }

            for (Document store : stores) {
                System.out.println("Tienda encontrada: " + store.toJson());
            }
        } catch (Exception e) {
            System.err.println("Error al listar tiendas: " + e.getMessage());
        }
    }

    public void updateStore(String storeId, Document updateStore) {
        try {
            Document existingStore = repository.findStoreById(storeId);
            if (existingStore == null) {
                System.out.println("Tienda no encontrado, no se puede actualizar.");
                return;
            }

            repository.updateStore(storeId, updateStore);
            System.out.println("Tienda actualizado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al actualizar tienda: " + e.getMessage());
        }
    }

    public void deleteStoreById(String storeId) {
        try {
            Document existingStore = repository.findStoreById(storeId);
            if (existingStore == null) {
                System.out.println("Tienda no encontrada, no se puede eliminar.");
                return;
            }

            repository.deleteStoreById(storeId);
            System.out.println("Tienda eliminada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar tienda: " + e.getMessage());
        }
    }

}
