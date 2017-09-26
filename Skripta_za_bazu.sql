BEGIN

CREATE TABLE Country
(
	IDCountry int CONSTRAINT PK_Country PRIMARY KEY IDENTITY,
	Name nvarchar(50) NOT NULL
)
GO

CREATE TABLE City
(
	IDCity int CONSTRAINT PK_City PRIMARY KEY IDENTITY,
	Name nvarchar(50) NOT NULL,
	CountryID int CONSTRAINT FK_Country FOREIGN KEY REFERENCES Country(IDCountry)
)
GO


CREATE TABLE Doctor
(
	IDDoctor int CONSTRAINT PK_Doctor PRIMARY KEY IDENTITY,
	Name nvarchar(50) NOT NULL,
	Surname nvarchar(50) NOT NULL,
	Telephone nvarchar(50) NOT NULL,
	Email nvarchar(25) NOT NULL,
	Specialist bit NULL,
	CityID int CONSTRAINT FK_City FOREIGN KEY REFERENCES City(IDCity)
)
GO

ALTER TABLE Doctor ALTER COLUMN Specialist bit NULL


CREATE TABLE PatientContactInfo
(
	IDPatientContactInfo int CONSTRAINT PK_ContactInfo PRIMARY KEY IDENTITY,
	Telephone1 nvarchar(25) NOT NULL,
	Telephone2 nvarchar(25) NOT NULL,
	TelephoneWork nvarchar(25) NOT NULL,
	TelephoneHome nvarchar(25) NOT NULL,
	Mobile nvarchar(25) NOT NULL,
	Pager nvarchar(25) NOT NULL,
	Fax nvarchar(25) NOT NULL,
	Email nvarchar(25) NOT NULL
)
GO

CREATE TABLE Patient 
(
	IDPatient int CONSTRAINT PK_Patient PRIMARY KEY IDENTITY,
	SSN nvarchar(9) NOT NULL,
	FirstName nvarchar(25) NOT NULL,
	MiddleName nvarchar(25) NOT NULL,
	LastName nvarchar(25) NOT NULL,
	Sex char(1) NOT NULL,
	DateOfBirth date NOT NULL,
	Complaint nvarchar(max) NOT NULL,
	KinFirstName nvarchar(25) NOT NULL,
	KinMiddleName nvarchar(25) NOT NULL,
	KinLastName nvarchar(25) NOT NULL,
	RelationshipToPatient nvarchar(100) NOT NULL,
	PatientContactInfoID int CONSTRAINT FK_MiniRegistrationForm_ContactInfo FOREIGN KEY REFERENCES PatientContactInfo(IDPatientContactInfo)
)
GO

--------------------------------------------------------------------------------------------------
/*
ALTER TABLE Doctor DROP CONSTRAINT FK_City
ALTER TABLE Doctor ADD CONSTRAINT FK_Doctor_City FOREIGN KEY (CityID) REFERENCES City(IDCity)

ALTER TABLE City DROP CONSTRAINT FK_Country
ALTER TABLE City ADD CONSTRAINT FK_City_Country FOREIGN KEY (CountryID) REFERENCES Country(IDCountry)
GO
*/
--------------------------------------------------------------------------------------------------


CREATE TABLE ItemTypes
(
	IDItemType int CONSTRAINT PK_ItemType PRIMARY KEY IDENTITY,
	Name nvarchar(20) NOT NULL
)
GO

CREATE TABLE Item
(
	IDItem int CONSTRAINT PK_Item PRIMARY KEY IDENTITY,
	ItemTypeID int CONSTRAINT FK_Items_ItemType FOREIGN KEY REFERENCES ItemTypes(IDItemType),
	ItemDescription nvarchar(100) NOT NULL,
	Price money NOT NULL
)
GO


CREATE TABLE PatientDetails
(
	IDPatientDetails int CONSTRAINT PK_PatientDetails PRIMARY KEY IDENTITY,
	PatientID int CONSTRAINT FK_PatientDetails_Patient FOREIGN KEY REFERENCES Patient(IDPatient),
	PatientContactInfo int CONSTRAINT FK_PatientDetails_PatientContactInfo FOREIGN KEY REFERENCES PatientContactInfo(IDPatientContactInfo),
	MaritalStatus nvarchar(25) NOT NULL,
	NoOfDependants int NOT NULL,
	Height int NOT NULL,
	Weight_ int NOT NULL,
	BloodType nvarchar(10) NOT NULL,
	Occupation nvarchar(25) NOT NULL,
	GrossAnnualIncome money NOT NULL,
	Vegetarian bit NOT NULL,
	Smoker bit NOT NULL,
	AverageNrCigarettesDay int NULL,
	ConsumingAlcohol bit NOT NULL,
	AverageNrDrinksDay int NULL,
	UsingStimulants nvarchar(50) NOT NULL,
	CoffeeTeaPerDay int NOT NULL,
	SoftDrinksPerDay int NOT NULL,
	HaveRegularMeals bit NOT NULL,
	HomeFoodOrOutside nvarchar(25) NOT NULL,
	Complaint nvarchar(max) NOT NULL,
	Diabetic nvarchar(20) NOT NULL,
	Hypertensive nvarchar(20) NOT NULL,
	CardiacCondition nvarchar(100) NOT NULL,
	RespiratoryCondition nvarchar(100) NOT NULL,
	DigestiveCondition nvarchar(100) NOT NULL,
	OrthopedicCondition nvarchar(100) NOT NULL,
	MuscluarCondition nvarchar(100) NOT NULL,
	NeurologicalCondition nvarchar(100) NOT NULL,
	KnownAllergies nvarchar(100) NOT NULL,
	ReactionToDrugs nvarchar(100) NOT NULL,
	MajorSurgeries nvarchar(500) NOT NULL,
)
GO


CREATE TABLE PatientRecord
(
	IDRecord int CONSTRAINT PK_IDRecord PRIMARY KEY IDENTITY,
	PatientID int CONSTRAINT FK_PatientRecord_Patient FOREIGN KEY REFERENCES Patient(IDPatient) NOT NULL,
	DoctorID int CONSTRAINT FK_PatientRecord_Doctor FOREIGN KEY REFERENCES Doctor(IDDoctor) NOT NULL,
	Diagnosis nvarchar(max) NOT NULL,
	AppointmentDate date CONSTRAINT DF_PatientRecord DEFAULT CONVERT(date,GETDATE()),
	FollowUpAppointment date NULL
)
GO


CREATE TABLE PatientAddress
(
	IDPatientAddress int CONSTRAINT PK_PatientAddress PRIMARY KEY IDENTITY,
	PresentAddress bit NOT NULL,
	KinAddress bit NOT NULL,
	DoorNo int NOT NULL,
	Street nvarchar(25) NOT NULL,
	Area nvarchar(25) NOT NULL,
	CityID int CONSTRAINT FK_PatientAddress_City FOREIGN KEY REFERENCES City(IDCity),
	PatientDetailsID int CONSTRAINT FK_PatientAddress_PatientDetails FOREIGN KEY REFERENCES PatientDetails(IDPatientDetails)
)
GO


CREATE TABLE ItemsPrescribed
(
	ID int CONSTRAINT PK_ItemsPrescribed PRIMARY KEY IDENTITY,
	ItemID int CONSTRAINT FK_ItemsPrescribed_Item FOREIGN KEY REFERENCES Item(IDItem),
	RecordID int CONSTRAINT FK_ItemsPrescribed_PatientRecord FOREIGN KEY REFERENCES PatientRecord(IDRecord)
)
GO


CREATE TABLE CreditCard
(
	IDCreditCard int CONSTRAINT PK_CreditCard PRIMARY KEY IDENTITY,
	CCType int CONSTRAINT FK_CreditCard_CreditCardType FOREIGN KEY REFERENCES CreditCardType(IDCCType),
	CCNumber nvarchar(25) NOT NULL,
	ExpMonth tinyint NOT NULL,
	ExpYear smallint NOT NULL
)
GO


CREATE TABLE Invoice
(
	IDInvoice int CONSTRAINT PK_Invoice PRIMARY KEY IDENTITY,
	InvoiceNr int NOT NULL,
	IssueDate date CONSTRAINT DF_IssueDate DEFAULT GETDATE(),
	PatientRecordID int CONSTRAINT FK_Invoice_PatientRecord FOREIGN KEY REFERENCES PatientRecord(IDRecord),
	PayByCreditCard bit NOT NULL,
	CreditCardID int CONSTRAINT FK_Invoice_CreditCard FOREIGN KEY REFERENCES CreditCard(IDCreditCard)
)
GO


CREATE TABLE InvoiceDetails
(
	IDInvoiceDetails int CONSTRAINT PK_InvoiceDetails PRIMARY KEY IDENTITY,
	InvoiceID int CONSTRAINT FK_InvoiceDetails_Invoice FOREIGN KEY REFERENCES Invoice(IDInvoice),
	ItemID int CONSTRAINT FK_InvoiceDetails_Item FOREIGN KEY REFERENCES Item(IDItem),
	Price money NOT NULL,
	Quantity int NOT NULL,
	TotalPrice AS Price * Quantity
)
GO


CREATE TABLE CreditCardType
(
	IDCCType int CONSTRAINT PK_CreditCardType PRIMARY KEY IDENTITY,
	Type nvarchar(50)
)