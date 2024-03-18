import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 A server that executes the Simple Inventory Access Protocol.
 Recommended to always run this prior to the client.
 @author SageMarkwardt
 */
public class StockServer
{
    public static void main(String[] args) throws IOException
    {
        // set up stock to work with, we'll default to 10 items
        int DEFAULT_SIZE = 10;
        Stock stock = new Stock(DEFAULT_SIZE);
        // set up the port
        final int SSAP_PORT = 8888;
        ServerSocket server = new ServerSocket(SSAP_PORT);
        // let the console know the server is running and ready
        System.out.println("Waiting for clients to connect...");

        while (true)
        {
            // create socket
            Socket s = server.accept();
            System.out.println("Client connected.");
            // create stock service using socket, sets up input/output streams
            StockService service = new StockService(s, stock);
            // send to thread - lets work in parallel with other clients
            Thread t = new Thread(service);
            t.start();
        }
    }
}