package gm.service;

import gm.model.Store;
import gm.repository.DatabaseRepository;
import gm.model.Employee;
import org.bson.Document;

public class EmployeeService {
    private final DatabaseRepository repository;

    public EmployeeService(DatabaseRepository repository) {
        this.repository = repository;
    }

    public String insertEmployee(Employee employee) {
        try {
            return repository.insertEmployee(employee);
        } catch (Exception e) {
            System.err.println("Error al insertar al empleado: " + e.getMessage());
            return null;
        }
    }

    public void getEmployeeById(String employeeId) {
        try {
            Document employee = repository.findEmployeeById(employeeId);
            if (employee != null) {
                System.out.println("Empleado encontrado: " + employee.toJson());
            } else {
                System.out.println("Empleado no encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Error al buscar empleado: " + e.getMessage());
        }
    }

    public void findAllEmployees() {
        try {
            Iterable<Document> employees = repository.findAllEmployees();
            if (!employees.iterator().hasNext()) {
                System.out.println("No se encontraron empleados.");
                return;
            }

            for (Document employee : employees) {
                System.out.println("Empleado encontrado: " + employee.toJson());
            }
        } catch (Exception e) {
            System.err.println("Error al listar empleados: " + e.getMessage());
        }
    }

    public void updateEmployee(String employeeId, Document updateEmployee) {
        try {
            Document existingEmployee = repository.findEmployeeById(employeeId);
            if (existingEmployee == null) {
                System.out.println("Empleado no encontrado, no se puede actualizar.");
                return;
            }

            repository.updateEmployee(employeeId, updateEmployee);
            System.out.println("Empleado actualizado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al actualizar empleado: " + e.getMessage());
        }
    }

    public void deleteEmployeeById(String employeeId) {
        try {
            Document existingEmployee = repository.findEmployeeById(employeeId);
            if (existingEmployee == null) {
                System.out.println("Empleado no encontrado, no se puede eliminar.");
                return;
            }

            repository.deleteEmployeeById(employeeId);
            System.out.println("Empleado eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar empleado: " + e.getMessage());
        }
    }
}

