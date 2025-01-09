package gm.service;

import gm.repository.DatabaseRepository;
import gm.model.Client;
import org.bson.Document;

public class ClientService {
    private final DatabaseRepository repository;

    public ClientService(DatabaseRepository repository) {
        this.repository = repository;
    }

    public String insertClient(Client client) {
        try {
            return repository.insertClient(client);
        } catch (Exception e) {
            System.err.println("Error al insertar el cliente: " + e.getMessage());
            return null;
        }
    }

    public void getClientById(String clientId) {
        try {
            Document client = repository.findClientById(clientId);
            if (client != null) {
                System.out.println("Cliente encontrado: " + client.toJson());
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
        }
    }

    public void findAllClients() {
        try {
            Iterable<Document> clients = repository.findAllClients();
            if (!clients.iterator().hasNext()) {
                System.out.println("No se encontraron clientes.");
                return;
            }

            for (Document client : clients) {
                System.out.println("Cliente encontrado: " + client.toJson());
            }
        } catch (Exception e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
    }

    public void updateClient(String clientId, Document updateClient) {
        try {
            Document existingClient = repository.findClientById(clientId);
            if (existingClient == null) {
                System.out.println("Cliente no encontrado, no se puede actualizar.");
                return;
            }

            repository.updateClient(clientId, updateClient);
            System.out.println("Cliente actualizado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    public void deleteClientById(String clientId) {
        try {
            Document existingClient = repository.findClientById(clientId);
            if (existingClient == null) {
                System.out.println("Cliente no encontrado, no se puede eliminar.");
                return;
            }

            repository.deleteClientById(clientId);
            System.out.println("Cliente eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
        }
    }
}
