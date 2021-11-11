DROP TABLE IF EXISTS trades;

CREATE TABLE trades (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  tradeid VARCHAR(200) NOT NULL,
  bookid VARCHAR(200) NOT NULL,
  version INT NOT NULL,
  counterpartyid VARCHAR(200) NOT NULL,
  maturitydate DATE NOT NULL,
  createddate DATE NOT NULL,
  expiry VARCHAR(1)
);