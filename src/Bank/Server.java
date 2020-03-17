package Bank;

import java.rmi.*;
import java.rmi.registry.*;

public class Server {

    private static String HOSTNAME = "localhost";
    private static Registry registry;
    private static String registry_port = "1099";
    private static String remote_ob_name;

    public Server() throws Exception {
        this("Bank");
        BankImpl bank = new BankImpl();

        Naming.rebind("rmi://" + HOSTNAME + ":" + registry_port + "/" + remote_ob_name, bank);
        System.out.println("RMI server is running.");

    }

    public Server(String name) {
        remote_ob_name = name;
    }

    //registry must be started before binding an object into it.
    public static void startRegistry(int port) throws Exception {
        registry = LocateRegistry.createRegistry(Integer.parseInt(registry_port));
    }

    public static void main(String... args) throws Exception {
        

        startRegistry(Integer.parseInt(registry_port));
        
        Server s = new Server();

    }
}
