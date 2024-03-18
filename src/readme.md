Simple Stock Access Protocol (SSAP)
This is a simple practice program which goes over creating a Stock Server on a 
personal machine, which interacts with a StockClient who will call on the methods
the Server is prepared to process. Using this program we are able to maintain
a basic stock/inventory system. 

| Client Request | Server Response              | Description                                 |
|----------------|------------------------------|---------------------------------------------|
| INSTOCK n      | n and the stock amount       | Get the stock of item n                     |
| SELL n a       | n and the new stock quantity | Withdraw a quantity of item n               |
| PURCHASE n a   | n and the new stock quantity | Purchase a quantity of item n               |
| ITEM a         | a qty and the string name    | Returns the name of item with SKU n         |
| NEW n a        | n and the new balance        | Add new item n of a quantity into the stock |
| QUIT           | none                         | Quits the connection                        |

