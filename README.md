# BankSimu
Online Banking System

`This project is an online banking system, where there are 3 types of players involved, customers, bank employees and managers:
-Customers can make transfers between themselves, check transactions and available balance, change their password.
-Employees can add customers to the system, make withdrawals or deposits for customers
-The Manager (root) has the power to see all customers and employees in the system, being able to add, delete or modify customers or employees.

To run the System:
- Compiles server.java "javac server.java"
The project's main class is located in server.java
- runs the "java server" server
In Linux you need to run with sudo "sudo java server"
While Windows, only allow the application on the firewall
- Now in the browser, enter your local IP or "localhost:80"
- Login Information-> the root account: ID:0 or ID:root Password:1234 


About the system:
- The System operates an HTTP server on port 80
- The system uses Java in the backend
- To present data in the browser, it uses HTML, CSS and pure Javascript
- Transactions made in this bank are kept on blockchain and using sha256
- Passwords are stored in sha256

System Database:
- This system uses no-sql itself, where the data is stored in CSV (comma separated value)
- Data from all system users is stored in ./DATA/mainData.dat
- global transactions are stored in ./DATA/historico.dat and each transaction is checked to validate the blockchain
- when a customer is referenced, a copy of that transaction is saved in the customer's history in ./DATA/clientes/_ID_CUSTOMER.dat
and this personal history of each customer is used only to speed up presentation of the transaction history for the respective customer
- customers deleted from the system, their data and history are stored in ./deleted/users.dat
- *** For more information about the database, consult "notas.txt" ***
