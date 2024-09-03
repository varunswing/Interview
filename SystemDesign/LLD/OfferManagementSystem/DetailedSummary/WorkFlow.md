## Workflow Example

Step-by-step process during a flash sale:

**User Login:**

User logs in through UserService.

**Viewing Flash Sale Product:**

User retrieves product and flash sale details via ProductService and FlashSaleService.

**Adding to Cart:**

User adds product to cart using CartService.
FlashSaleService and InventoryService reserve the required stock atomically.
A timeout is set for the reservation (e.g., 10 minutes).

**Checkout and Order Creation:**

User proceeds to checkout.
OrderService creates an order after validating cart and confirming inventory reservation.

**Payment Processing:**

PaymentService processes payment.
Upon successful payment, InventoryService deducts the stock permanently.
OrderService updates order status to CONFIRMED.

**Handling Failures:**

If payment fails or times out, InventoryService releases the reserved stock.
OrderService updates order status to CANCELLED.