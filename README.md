Advanced Distributed Systems Project
RMI Banking System that does
1. create an account
2. deposit money
3. withdraw money
4. Transfer money
5. Delete Account
6. check Balance
7. Change Pin.

We used JDBC (Java Dabatase Connectivity) to store the account information of a customer. A customer can remotely issue each of the above operations by simply sending a remote reference request to an RMI server which inturn will fetch the data from the database with the help of JDBC.

