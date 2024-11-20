
/* Drop Tables */

DROP TABLE LineItem;
DROP TABLE ProductDescription;
DROP TABLE Sale;




/* Create Tables */

CREATE TABLE LineItem
(
	ItemId varchar(13) NOT NULL,
	Quantity int,
	ProductDescriptionId varchar(13),
	SaleId int NOT NULL,
	PRIMARY KEY (ItemId)
);


CREATE TABLE ProductDescription
(
	ItemId varchar(13) NOT NULL,
	Price double,
	Description varchar(255),
	PRIMARY KEY (ItemId)
);


CREATE TABLE Sale
(
	Id int NOT NULL UNIQUE,
	OccurredOn date,
	PRIMARY KEY (Id)
);



/* Create Foreign Keys */

ALTER TABLE LineItem
	ADD FOREIGN KEY (ProductDescriptionId)
	REFERENCES ProductDescription (ItemId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE LineItem
	ADD FOREIGN KEY (SaleId)
	REFERENCES Sale (Id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



