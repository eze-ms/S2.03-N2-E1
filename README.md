# Food Delivery Management Project

## 📄 Description
This project manages the process of food delivery orders using a MongoDB database. It performs CRUD (Create, Read, Update, Delete) operations for client, product, order, employee, and store data, facilitating the management and traceability of orders made by customers.

### Features
1. **Database Connection:**
    - MongoDB is used to manage collections related to orders, clients, products, employees, and stores.
2. **Data Model:**
    - **Client:**
        - Contains basic client information (name, address, phone number, etc.).
    - **Product:**
        - Includes details about the products (name, description, category, price, etc.).
    - **Order:**
        - Each order contains details about the food ordered, delivery type (home or pickup), and the relationships with the client, store, and employee who managed it.
    - **Employee:**
        - Information about the employees managing the orders (name, role, assigned store).
    - **Store:**
        - Each store manages several orders and products.
3. **Queries:**
    - Queries to retrieve clients, products, stores, and employees.
    - Complex queries, such as retrieving all orders associated with a specific client or store.

4. **Relationships:**
    - **Client and Order:** One client can place many orders.
    - **Order and Product:** An order can contain multiple products.
    - **Product and Store:** A product belongs to a store.
    - **Employee and Order:** Each order has an employee responsible for it.

---

## 🔧 Running the Project
1. Ensure the database is set up properly in MongoDB.
2. Run the Main.java file to insert initial data and test CRUD operations for clients, products, and orders.
3. Check that the collections and documents are created correctly and that queries return expected results.

---

## 📈 Architecture
### Repository
- The repository handles the direct CRUD operations on the database.
- Methods like insertClient, insertProduct, insertOrder, findClientById, etc., manage interactions with MongoDB.

### Services
- **Each service focuses on a specific set of operations for an entity:**
  - ClientService: Manages client creation, reading, updating, and deletion.
  - ProductService: Manages products available for orders.
  - OrderService: Handles the orders placed by clients.


- **Services delegate CRUD operations to repositories.**

---

## ✨ Additional Features
The model is scalable and allows for the addition of future features like:
- Order and client analysis.
- Inventory management for products.
- Sales report generation.

---

## 📝 Project Structure
### Models
1. **Client:** Represents a client with fields like name, address, phone number, and others.
2. **Product:** Represents a product with fields like name, description, category, and others.
3. **Order:** Represents an order placed by a client, with details like delivery type, products ordered, total price, and others.
4. **Employee:** Represents an employee who handles orders, with fields like name, phone number, and role (delivery or cook).
5. **Store:** Represents a store that manages products and orders.

### Repositories
- **DatabaseRepository:** Handles CRUD operations directly with the database.
  - Methods like **insertClient**, **insertProduct**, **insertOrder** **handle insertions**, **updates**, and queries for entities.
  
### Services
- **ClientService:** Provides business logic for managing clients, using repository methods.
- **ProductService:** Manages the products available in the store, using the repository for CRUD operations.
- **OrderService:** Handles the orders placed by clients, including validation logic and order processing.

---

## 📈 Workflow
1. Clients place orders, which are registered in the system as Order entities.
2. The products available are managed by the Product entity.
3. Stores manage products and orders, while employees are responsible for fulfilling the orders.
4. Data integrity is ensured through validations in both the services and repositories.

---

## 📌 Conclusion
This project provides a modular, flexible, and scalable structure for managing food delivery orders. Using MongoDB to store data, it allows for efficient querying and maintains a clear flow of relationships between clients, products, orders, employees, and stores.

---

## 💻 Technologies Used
- **MongoDB**
- **Recommended IDE:** IntelliJ IDEA, Visual Studio Code, or any Java and MongoDB-compatible IDE.

---

## 📊 Requirements
- **MongoDB:** Running and configured.
- **Mongo Java Driver:** Library to handle data with MongoDB.

---

## 🛠️ Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/eze-ms/food-delivery-management

---

© 2025. Developed by Ezequiel Macchi Seoane
