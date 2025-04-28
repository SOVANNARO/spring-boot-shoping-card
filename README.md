
### dbdiagram

```java
Table Product {
  id bigint [pk, increment]
  name varchar
  price decimal(10,2)
}

Table Cart {
  id bigint [pk, increment]
}

Table CartItem {
  id bigint [pk, increment]
  cart_id bigint
  product_id bigint
  quantity int
}

// Relationships
Ref: CartItem.cart_id > Cart.id
Ref: CartItem.product_id > Product.id
```

