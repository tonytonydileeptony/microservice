  CREATE TABLE IF NOT EXISTS CUSTOMER (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mobile_number VARCHAR(20) NOT NULL,
    created_at DATE NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_by VARCHAR(100),
    updated_at DATE
  );


  CREATE TABLE IF NOT EXISTS accounts (
    customer_id INT NOT NULL,
    account_number INT AUTO_INCREMENT PRIMARY KEY,
    account_type VARCHAR(100) NOT NULL,
    branch_address VARCHAR(100) NOT NULL,
    created_at DATE NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_by VARCHAR(100) DEFAULT NULL,
    updated_at DATE DEFAULT NULL
  );
