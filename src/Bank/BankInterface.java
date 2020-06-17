
package Bank;


public interface BankInterface extends java.rmi.Remote {

    public boolean login(String username, String password) throws java.rmi.RemoteException;

    public double checkBalance(String accNumber) throws java.rmi.RemoteException;

    public void withdraw(String accNumber, double amount) throws java.rmi.RemoteException;

    public void deposit(String accNumber, double amount) throws java.rmi.RemoteException;

    public void deleteAccount(String accountNumber) throws java.rmi.RemoteException;
    public boolean changePin(String newPin, String oldPin,String account) throws java.rmi.RemoteException;

    public void transfer(String from, String to, double amount) throws java.rmi.RemoteException;

    public boolean register(String fname, String lname, String uname, String pass, String accNumber, double balance) throws java.rmi.RemoteException;

}
