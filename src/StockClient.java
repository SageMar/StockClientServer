import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 This program tests the Stock server.
 */
public class StockClient
{
    public static void main(String[] args) throws IOException
    {       // unused port number that's available
        final int SSAP = 8888;
            // localhost means we're connecting to ourselves / our own machine
            // called a loopback to ourselves
            // localhost is a name for ip address 127.0.0.1
        try (Socket s = new Socket("localhost", SSAP))
        {
            // set up the input and outputs
            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();
            // create a scanner for the input
            Scanner in = new Scanner(instream);
            // create a printWriter to print the output
            PrintWriter out = new PrintWriter(outstream);

            // start with a command
            String command = "PURCHASE 1 50";
            // print the message sent to the server into the console
            System.out.println("Sending: " + command);
            // send the command to the server with out.print
            out.print(command + "\n");
            // clear the output so it's ready for future commands
            out.flush();
            // take in the response
            String response = in.nextLine();
            // print out the response to the console
            System.out.println("Receiving: " + response);

            command = "SELL 3 10";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            // double-check the stock qty of 1 is still solid
            command = "INSTOCK 1";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            // check that typos and incorrect commands don't crash the program
            command = "ISTOCK 1";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "ITEM 1";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "NEW 30 SODA";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            // tell the server we are done now
            command = "QUIT";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
        }
    }
}