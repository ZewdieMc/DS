Advanced Distributed Systems Project
RMI Banking System that does
1. create an account
2. deposit money
3. withdraw money
4. Transfer money
5. Delete Account
6. check Balance
7. Change Pin.

We used JDBC (Java Dabatase Connectivity) to store the account information of a customer. A customer can remotely issue each of the above operations by simply sending a remote reference request which contains remote method paramters using a process called marshallig to an RMI server which inturn will fetch the data from the database with the help of JDBC.
Once the database server responds to the RMI server, the RMI server will serve the RMI client by sending the stub of the skeleton of the remote methods it owns. 

