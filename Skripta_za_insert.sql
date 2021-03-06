
--Skripta za insert podataka

INSERT INTO Country (Name)
VALUES ('Hrvatska')

INSERT INTO City (Name, CountryID) VALUES ('Zagreb',1)
INSERT INTO City (Name, CountryID) VALUES ('Split',1)
INSERT INTO City (Name, CountryID) VALUES ('Rijeka',1)
INSERT INTO City (Name, CountryID) VALUES ('Osijek',1)


INSERT INTO Doctor(Name, Surname, Telephone, Email)
SELECT TOP 20 Ime, Prezime, Email, Telefon FROM AdventureWorksOBP.dbo.Kupac 
--Insert any 20 name,surname, telephone and email parameters from your database.

--Deleting all records from certain table (cant use truncate because of foreign keys)
---------------------------------------
--DELETE FROM Patient
--DBCC CHECKIDENT ('Patient',RESEED, 0)
---------------------------------------

INSERT INTO ItemTypes(Name) VALUES('Pregled')
INSERT INTO ItemTypes(Name) VALUES('Lijek')
INSERT INTO ItemTypes(Name) VALUES('Test')

INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(3,'Angiogram',300)
INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(3,'Blood culture',100)
INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(3,'Throat culture',50)
INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(3,'Breast MRI',500)
INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(3,'Paternity test',1000)

INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(2,'Hexetidinum',89.99)
INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(2,'Thiaminum',99.99)
INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(2,'Clotrimazolum',199.99)
INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(2,'Ibuprofenum',19.99)
INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(2,'Paracetamolum',39.99)

INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(1,'Knee checkup',100)
INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(1,'Head scan',600)
INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(1,'Eye exam',100)
INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(1,'Lungs exam',300)
INSERT INTO Item(ItemTypeID, ItemDescription, Price) VALUES(1,'Colonoscopy',200)