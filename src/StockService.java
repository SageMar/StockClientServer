import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 Executes Simple Stock Access Protocol commands
 from a socket.
 */
public class StockService implements Runnable
{
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private Stock stock;

    /**
     Constructs a service object that processes commands
     from a socket for a Stock.
     @param aSocket the socket
     @param aStock the bank
     */
    public StockService(Socket aSocket, Stock aStock)
    {
        s = aSocket;
        stock = aStock;
    }

    public void run()
    {
        try
        {
            try
            {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());
                doService();
            }
            finally
            {
                s.close();
            }
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     Executes all commands until the QUIT command or the
     end of input.
     */
    public void doService() throws IOException
    {
        while (true)
        {
            if (!in.hasNext()) { return; }
            String command = in.next();
            if (command.equals("QUIT")) { return; }
            else { executeCommand(command); }
        }
    }

    /**
     Executes a single command.
     @param command the command to execute
     */
    public void executeCommand(String command)
    {
        int SKU = in.nextInt();
        if (command.equals("PURCHASE"))
        {
            // updates stock for new purchase count
            int amount = in.nextInt();
            stock.purchase(SKU, amount);
            // clear the output for the next command
            out.flush();
        }
        else if (command.equals("SELL"))
        {
            // updates stock for after sale
            int amount = in.nextInt();
            // return the SKU and the qty of this item in the stock
            stock.sell(SKU, amount);
            out.flush();
        }
        else if (command.equals("ITEM"))
        {
            // returns the SKU and the name of the item in stock
            // assign the name to a variable
            String name = stock.itemName(SKU);
            // print out the return
            out.println(SKU + " " + name);
            out.flush();
        }
        else if (command.equals("NEW"))
        {
            out.println("hey");
            // adds a new item to the stock
            int amount = in.nextInt();
            String name = in.next();
            SKU = (stock.newItem(name, amount).SKU);
            out.flush();
        }
        else if (!command.equals("INSTOCK"))
        {
            out.println("Invalid command");
            out.flush();
            return;
        }
        // returns the amount in stock
        out.println("E:" + SKU + " " + stock.getStock(SKU));
        out.flush();
    }
}