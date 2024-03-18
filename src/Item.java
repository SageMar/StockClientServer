import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * An item with a given name, an assigned SKU to search by,
 * and a quantity which can be changed through selling and purchasing.
 */
public class Item{
    // fields and attributes
    String name;
    int SKU;
    int quantity;
    // the Lock exists to ensure two+ clients running on
    // one server don't change things at the same time
    private Lock inventoryChangeLock;


    /**
     Constructs an item with no stock quantity and assigns a SKU
     @param name
     */
    public Item(String name, int SKU)
    {
        this.name = name;
        quantity = 0;
        this.SKU = SKU;
        inventoryChangeLock = new ReentrantLock();
    }

    /**
     * Constructs an item with known/provided stock quantity and assigns a SKU
     * @param name
     * @param quantity
     */
    public Item(String name, int quantity, int SKU){
        this.name = name;
        this.SKU = SKU;
        this.quantity = quantity;
        inventoryChangeLock = new ReentrantLock();
    }

    /**
     Adds the newly purchased items into the stock for this item.
     @param newStock the amount being purchased
     */
    public void purchase(int newStock)
    {
        // turn the lock on to avoid multiple additions to stock
        inventoryChangeLock.lock();
        try
        {
            // add the newly purchased qty to what exists
            this.quantity = this.quantity + newStock;
        }
        finally
        {
            // unlock the item
            inventoryChangeLock.unlock();
        }
    }

    /**
     Removes given quantity from the stock after sale
     @param sold the amount which were sold
     */
    public void sell(int sold)
    {
        // lock the item
        inventoryChangeLock.lock();
        try
        {
            // remove the sold items from quantity in stock
            this.quantity = quantity - sold;
        }
        finally
        {
            // unlock the item
            inventoryChangeLock.unlock();
        }
    }

    /**
     Gets the current amount in stock for the item
     @return the current amount
     */
    public int getStock()
    {
        return quantity;
    }

    /**
     * @return the name of a specific SKU item
     */
    public String itemName(){
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", SKU=" + SKU +
                ", quantity=" + quantity +
                '}';
    }

}