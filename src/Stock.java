/**
 A bank consisting of multiple bank accounts (Object of this class).
 */
public class Stock {
    // all the items in the stock
    Item[] items;

    /**
     * Constructs a store with given number of items
     *
     * @param size the number of items
     */
    public Stock(int size) {
        items = new Item[size];

        // we will add the items from the stockService console
        // add dummy items to test current methods
        for (int i = 0; i < size; i++) {
            String name = Character.toString((char)(i+97));
            // for simplicity, set SKU to the index
            items[i] = new Item(name, i);
        }
    }

    /**
     * Deposits money into a bank account.
     *
     * @param SKU       the item's assigned number
     * @param purchased the amount to add to stock
     */
    public void purchase(int SKU, int purchased) {
        Item item = items[SKU];
        item.purchase(purchased);
    }

    /**
     * Withdraws money from a bank account.
     *
     * @param SKU  the item's assigned number
     * @param sold the amount to remove from stock
     */
    public void sell(int SKU, int sold) {
        // TODO: check if stock is > 0 before sale
        Item item = items[SKU];
        item.sell(sold);
    }

    /**
     * Gets the in stock total of an item.
     *
     * @param SKU the item's assigned number
     * @return the total stock (int)
     */
    public int getStock(int SKU) {
        Item item = items[SKU];
        return item.getStock();
    }

    /**
     * @param SKU
     * @return the name of the item (a string)
     */
    public String itemName(int SKU) {
        if (SKU < items.length){
            Item item = items[SKU];
            return item.itemName();
        }
        return "Not found";
    }

    /**
     * This method will add a new item to the inventory
     * @param name of new item
     * @param qty of new item
     * @returns String containing name and qty of new item
     */

    public Item newItem(String name, int qty){
        // resize for the new item
        Item[] temp = new Item[items.length + 1];

        // assign old index values to the new array
        for (int i = 0; i < items.length; i++) {
            temp[i] = items[i];
        }

        items = temp;

        // set SKU, auto set is to the last index number since we know it is unique
        int SKU = temp.length - 1;
        // create the new item
        Item item = new Item(name, qty, SKU);
        // now add it to the resized array
        items[items.length - 1] = item;

        return item;
    }

}