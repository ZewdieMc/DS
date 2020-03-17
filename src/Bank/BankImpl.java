package Bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankImpl extends UnicastRemoteObject implements BankInterface {

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    private double balance;

    private String username;
    private String password;

    public BankImpl() throws RemoteException {
        super();
        try {
            Class.forName("com.mysql.jdbc.Driver");//add the deriver name here.
            conn = DriverManager.getConnection("jdbc:mysql://localhost/Bankrmi", "root", "youaremine123**");

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public boolean login(String username, String password) throws RemoteException {
        String query = "select * from users where username=? and password=?";
        this.password = password;
        this.username = username;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            rs = pst.executeQuery();

            if (rs.next()) {
                String usern = rs.getString("username");
                String pass = rs.getString("password");
                if (usern.equals(username) && pass.equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public double checkBalance(String accNumber) throws RemoteException {
        try {
            if (login(username, password)) {
                String query = "select balance from users where accNumber = ?";
                pst = conn.prepareStatement(query);
                pst.setString(1, accNumber);
                rs = pst.executeQuery();

                if (rs.next()) {
                    balance = rs.getDouble("balance");
                }
            }
        } catch (RemoteException | SQLException e) {
            System.out.println(e);
        }
        return balance;
    }

    @Override
    public void withdraw(String accNumber, double amount) throws RemoteException {
        try {
            if (login(username, password)) {
                balance = checkBalance(accNumber);
                balance -= amount;
                String sql = "update users set balance = ? where accNumber = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, Double.toString(balance));
                pst.setString(2, accNumber);
                pst.execute();
                System.out.println(amount + " ETB is withdrawn from your account.");
            }
        } catch (RemoteException | SQLException e) {
        }
    }

    @Override
    public void deposit(String accNumber, double amount) throws RemoteException {
        try {
            if (login(username, password)) {
                balance = checkBalance(accNumber);
                balance += amount;
                String sql = "update users set balance = ? where accNumber = ?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, Double.toString(balance));
                pst.setString(2, accNumber);

                pst.execute();
                System.out.println(amount + " ETB is deposited into your account.");
            }
        } catch (RemoteException | SQLException e) {
        }

    }

  

    @Override
    public void transfer(String from, String to, double amount) throws RemoteException {
        withdraw(from, amount);
        deposit(to, amount);
    }

    @Override
    public boolean register(String fname, String lname, String uname, String pass, String accNumber, double balance) throws RemoteException {
        try {
            String qry = "insert into users (firstname, lastname, username, password, accNumber, balance)values(?,?,?,?,?,?)";
            pst = conn.prepareStatement(qry);
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, uname);
            pst.setString(4, pass);
            pst.setString(5, accNumber);
            pst.setDouble(6, balance);
            if (pst.execute());
            System.out.println(fname + " " + lname + " registered successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public void deleteAccount(String accountNumber) throws RemoteException {
        try {
            String qury = "delete from users where accNumber = ?";
            pst = conn.prepareStatement(qury);
            pst.setString(1, accountNumber);
            pst.execute();
            
        } catch (Exception e) {
        }
    }

}
