CREATE TABLE flash_sales (
    flash_sale_id VARCHAR(36) PRIMARY KEY,
    product_id VARCHAR(36) NOT NULL,
    discounted_price DECIMAL(10, 2) NOT NULL,
    available_quantity INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);
