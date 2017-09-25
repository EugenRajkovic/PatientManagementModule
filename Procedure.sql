--Procedure za bazu VirgoHospital

ALTER PROC usp_GetAllDoctors
AS
BEGIN
	SELECT d.IDDoctor, d.Name, d.Surname, d.Telephone, d.Email, d.Specialist, c.Name as CityName
	FROM Doctor d
	INNER JOIN City c ON c.IDCity = d.CityID 
END
GO



CREATE PROC usp_InsertDoctor
	@Name nvarchar(50),
	@Surname nvarchar(50),
	@Telephone nvarchar(50),
	@Email nvarchar(50),
	@Specialist bit,
	@CityID int
AS
IF EXISTS (SELECT * FROM Doctor d WHERE d.Name = @Name AND d.Surname = @Surname)
	BEGIN
		RETURN 0
	END
ELSE 
	BEGIN
		INSERT INTO Doctor(Name, Surname, Telephone, Email, Specialist, CityID)
		VALUES(@Name, @Surname, @Telephone, @Email, @Specialist, @CityID)
	END
GO


CREATE PROC usp_GetCities
AS
BEGIN
	SELECT c.IDCity, c.Name FROM City c
END
GO



ALTER PROC usp_DeleteDoctor
	@IDDoctor int
AS
BEGIN
	DELETE FROM Doctor WHERE IDDoctor = @IDDoctor

	DECLARE @max int
		SELECT @max = max(IDDoctor) FROM Doctor
		IF @max IS NUll   --check when max is returned as null
		SET @max = 0
		DBCC CHECKIDENT ('Doctor', RESEED, @max)
END
GO



CREATE PROC usp_UpdateDoctor
	@IDDoctor int,
	@Name nvarchar(50),
	@Surname nvarchar(50),
	@Telephone nvarchar(50),
	@Email nvarchar(50),
	@Specialist bit,
	@CityID int
AS
BEGIN
	UPDATE Doctor 
	SET Name = @Name, Surname = @Surname, Telephone = @Telephone, Email = @Email, Specialist = @Specialist, CityID = @CityID
	WHERE IDDoctor = @IDDoctor
END
GO


ALTER PROC usp_MiniForm
	@FirstName nvarchar(25),
	@MiddleName nvarchar(25),
	@LastName nvarchar(25),
	@Sex char(1),
	@DateOfBirth date,
	@Complaint nvarchar(max),
	@KinFirstName nvarchar(25),
	@KinMiddleName nvarchar(25),
	@KinLastName nvarchar(25),
	@RelationshipToPatient nvarchar(100),
	@Telephone1 nvarchar(25),
	@Telephone2 nvarchar(25)

AS
IF EXISTS(SELECT * FROM Patient p WHERE p.FirstName = @FirstName AND p.MiddleName = @MiddleName AND p.LastName = @LastName)
	BEGIN
		RETURN 0
	END
ELSE
	BEGIN
		INSERT INTO PatientContactInfo(Telephone1, Telephone2)
		VALUES (@Telephone1, @Telephone2)

		DECLARE @IDPatientContact int
		SET @IDPatientContact = SCOPE_IDENTITY()

		INSERT INTO Patient(FirstName, MiddleName, LastName, Sex, DateOfBirth, Complaint, KinFirstName, KinMiddleName, KinLastName, RelationshipToPatient, PatientContactInfoID)
		VALUES(@FirstName, @MiddleName, @LastName, @Sex, @DateOfBirth, @Complaint, @KinFirstName, @KinMiddleName, @KinLastName, @RelationshipToPatient, @IDPatientContact)
	END
GO


CREATE PROC usp_PatientInfo
AS
	BEGIN
		SELECT p.IDPatient, p.FirstName, p.MiddleName, p.LastName FROM Patient p
	END
GO



ALTER PROC usp_ComprehensiveForm
	@PatientID int, 
	@PresentAddress bit,
	@KinAddress bit,
	@Street nvarchar(25),
	@DoorNo int,
	@Area nvarchar(25),
	@CityID int,
	@PresentAddressKin bit,
	@KinAddressKin bit,
	@StreetKin nvarchar(25),
	@DoorNoKin int,
	@AreaKin nvarchar(25),
	@CityIDKin int,
	@TelephoneWork nvarchar(25),
	@TelephoneHome nvarchar(25),
	@Mobile nvarchar(25),
	@Pager nvarchar(25),
	@Fax nvarchar(25),
	@Email nvarchar(25),
	@MaritalStatus nvarchar(25), 
	@NoOfDependants int,
	@Height int, 
	@Weight int, 
	@BloodType nvarchar(10), 
	@Occupation nvarchar(25), 
	@GrossAnnualIncome decimal(18,2), 
	@Vegetarian bit, 
	@Smoker bit, 
	@AverageNrCigarettesDay int, 
	@ConsumingAlcohol bit, 
	@AverageNrDrinksDay int, 
	@UsingStimulants nvarchar(50), 
	@CoffeeTeaPerDay int, 
	@SoftDrinksPerDay int, 
	@HaveRegularMeals bit, 
	@HomeFoodOrOutside nvarchar(25), 
	@Complaint nvarchar(max), 
	@Diabetic nvarchar(20), 
	@Hypertensive nvarchar(20), 
	@CardiacCondition nvarchar(100), 
	@RespiratoryCondition nvarchar(100), 
	@DigestiveCondition nvarchar(100), 
	@OrthopedicCondition nvarchar(100), 
	@MuscluarCondition nvarchar(100), 
	@NeurologicalCondition nvarchar(100), 
	@KnownAllergies nvarchar(100), 
	@ReactionToDrugs nvarchar(100), 
	@MajorSurgeries nvarchar(500)
AS
IF NOT EXISTS(SELECT * FROM Patient p WHERE p.IDPatient = @PatientID)
	BEGIN
		RETURN 0
	END
ELSE
	BEGIN
		DECLARE @PatientContactID int
		SELECT @PatientContactID = p.PatientContactInfoID FROM Patient p WHERE p.IDPatient = @PatientID

		INSERT INTO PatientDetails (PatientID, PatientContactInfoID, MaritalStatus, NoOfDependants, Height, Weight_, BloodType, Occupation, GrossAnnualIncome, 
		Vegetarian, Smoker, AverageNrCigarettesDay, ConsumingAlcohol, AverageNrDrinksDay, UsingStimulants, CoffeeTeaPerDay, SoftDrinksPerDay, HaveRegularMeals, 
		HomeFoodOrOutside, Complaint, Diabetic, Hypertensive, CardiacCondition, RespiratoryCondition, DigestiveCondition, OrthopedicCondition, MuscluarCondition, 
		NeurologicalCondition, KnownAllergies, ReactionToDrugs, MajorSurgeries)
		VALUES(@PatientID,@PatientContactID,@MaritalStatus,@NoOfDependants,@Height,@Weight,@BloodType,@Occupation,@GrossAnnualIncome,@Vegetarian,@Smoker,@AverageNrCigarettesDay,
				@ConsumingAlcohol,@AverageNrDrinksDay,@UsingStimulants,@CoffeeTeaPerDay,@SoftDrinksPerDay,@HaveRegularMeals,@HomeFoodOrOutside,@Complaint,@Diabetic,@Hypertensive,
				@CardiacCondition,@RespiratoryCondition,@DigestiveCondition,@OrthopedicCondition,@MuscluarCondition,@NeurologicalCondition,@KnownAllergies,@ReactionToDrugs,@MajorSurgeries)


		INSERT INTO PatientAddress(PresentAddress, KinAddress, DoorNo, Street, Area, CityID, PatientID)
		VALUES(@PresentAddress,@KinAddress,@DoorNo,@Street,@Area,@CityID, @PatientID)

		INSERT INTO PatientAddress(PresentAddress, KinAddress, DoorNo, Street, Area, CityID, PatientID)
		VALUES(@PresentAddressKin,@KinAddressKin,@DoorNoKin,@StreetKin,@AreaKin,@CityIDKin, @PatientID)

		/*INSERT INTO PatientContactInfo(TelephoneWork, TelephoneHome, Mobile, Pager, Fax, Email)
		VALUES(@TelephoneWork,@TelephoneHome,@Mobile,@Pager,@Fax,@Email)*/

		UPDATE PatientContactInfo
		SET TelephoneWork = @TelephoneWork, TelephoneHome = @TelephoneHome, Mobile = @Mobile, Pager = @Pager, Fax = @Fax, Email = @Email
		WHERE IDPatientContactInfo = @PatientContactID
	END
GO



ALTER PROC usp_CompletePatientInfo
	@FirstName nvarchar(25),
	@MiddleName nvarchar(25),
	@LastName nvarchar(25),
	@Sex char(1),
	@DateOfBirth date,
	@Complaint nvarchar(max),
	@KinFirstName nvarchar(25),
	@KinMiddleName nvarchar(25),
	@KinLastName nvarchar(25),
	@RelationshipToPatient nvarchar(100),
	@PatientID int,
	@PresentAddress bit,
	@KinAddress bit,
	@Street nvarchar(25),
	@DoorNo int,
	@Area nvarchar(25),
	@CityID int,
	@PresentAddressKin bit,
	@KinAddressKin bit,
	@StreetKin nvarchar(25),
	@DoorNoKin int,
	@AreaKin nvarchar(25),
	@CityIDKin int,
	@Telephone1 nvarchar(25),
	@Telephone2 nvarchar(25),
	@TelephoneWork nvarchar(25),
	@TelephoneHome nvarchar(25),
	@Mobile nvarchar(25),
	@Pager nvarchar(25),
	@Fax nvarchar(25),
	@Email nvarchar(25),	 
	@MaritalStatus nvarchar(25), 
	@NoOfDependants int,
	@Height int, 
	@Weight int, 
	@BloodType nvarchar(10), 
	@Occupation nvarchar(25), 
	@GrossAnnualIncome decimal(18,2), 
	@Vegetarian bit, 
	@Smoker bit, 
	@AverageNrCigarettesDay int, 
	@ConsumingAlcohol bit, 
	@AverageNrDrinksDay int, 
	@UsingStimulants nvarchar(50), 
	@CoffeeTeaPerDay int, 
	@SoftDrinksPerDay int, 
	@HaveRegularMeals bit, 
	@HomeFoodOrOutside nvarchar(25), 
	@ComplaintBig nvarchar(max), 
	@Diabetic nvarchar(20), 
	@Hypertensive nvarchar(20), 
	@CardiacCondition nvarchar(100), 
	@RespiratoryCondition nvarchar(100), 
	@DigestiveCondition nvarchar(100), 
	@OrthopedicCondition nvarchar(100), 
	@MuscluarCondition nvarchar(100), 
	@NeurologicalCondition nvarchar(100), 
	@KnownAllergies nvarchar(100), 
	@ReactionToDrugs nvarchar(100), 
	@MajorSurgeries nvarchar(500)
AS
IF EXISTS(SELECT * FROM Patient p WHERE p.FirstName = @FirstName AND p.MiddleName = @MiddleName AND p.LastName = @LastName)
	BEGIN
		RETURN 0
	END
ELSE
	BEGIN
		INSERT INTO PatientContactInfo(Telephone1, Telephone2, TelephoneWork, TelephoneHome, Mobile, Pager, Fax, Email)
		VALUES (@Telephone1, @Telephone2, @TelephoneWork, @TelephoneHome, @Mobile, @Pager, @Fax, @Email)

		DECLARE @PatientContactID int
		SET @PatientContactID = SCOPE_IDENTITY()

		INSERT INTO Patient(FirstName, MiddleName, LastName, Sex, DateOfBirth, Complaint, KinFirstName, KinMiddleName, KinLastName, RelationshipToPatient, PatientContactInfoID)
		VALUES(@FirstName, @MiddleName, @LastName, @Sex, @DateOfBirth, @Complaint, @KinFirstName, @KinMiddleName, @KinLastName, @RelationshipToPatient, @PatientContactID)

		INSERT INTO PatientAddress(PresentAddress, KinAddress, DoorNo, Street, Area, CityID, PatientID)
		VALUES(@PresentAddress,@KinAddress,@DoorNo,@Street,@Area,@CityID, @PatientID)

		INSERT INTO PatientAddress(PresentAddress, KinAddress, DoorNo, Street, Area, CityID, PatientID)
		VALUES(@PresentAddressKin,@KinAddressKin,@DoorNoKin,@StreetKin,@AreaKin,@CityIDKin, @PatientID)

		INSERT INTO PatientDetails (PatientID, PatientContactInfoID, MaritalStatus, NoOfDependants, Height, Weight_, BloodType, Occupation, GrossAnnualIncome, 
		Vegetarian, Smoker, AverageNrCigarettesDay, ConsumingAlcohol, AverageNrDrinksDay, UsingStimulants, CoffeeTeaPerDay, SoftDrinksPerDay, HaveRegularMeals, 
		HomeFoodOrOutside, Complaint, Diabetic, Hypertensive, CardiacCondition, RespiratoryCondition, DigestiveCondition, OrthopedicCondition, MuscluarCondition, 
		NeurologicalCondition, KnownAllergies, ReactionToDrugs, MajorSurgeries)
		VALUES(@PatientID,@PatientContactID,@MaritalStatus,@NoOfDependants,@Height,@Weight,@BloodType,@Occupation,@GrossAnnualIncome,@Vegetarian,@Smoker,@AverageNrCigarettesDay,
				@ConsumingAlcohol,@AverageNrDrinksDay,@UsingStimulants,@CoffeeTeaPerDay,@SoftDrinksPerDay,@HaveRegularMeals,@HomeFoodOrOutside,@Complaint,@Diabetic,@Hypertensive,
				@CardiacCondition,@RespiratoryCondition,@DigestiveCondition,@OrthopedicCondition,@MuscluarCondition,@NeurologicalCondition,@KnownAllergies,@ReactionToDrugs,@MajorSurgeries)
	END
GO



CREATE PROC usp_GetItems
AS
BEGIN
	SELECT i.IDItem, i.ItemDescription, i.Price
	FROM Item i
END
GO



CREATE PROC usp_CreateNewRecord
	@PatientID int,
	@DoctorID int,
	@Diagnosis nvarchar(max),
	@FollowUpAppointment date
AS
IF NOT EXISTS(SELECT * FROM Patient WHERE IDPatient = @PatientID)
	BEGIN
		RETURN 0
	END
ELSE
	BEGIN
		INSERT INTO PatientRecord(PatientID, DoctorID, Diagnosis,FollowUpAppointment)
		VALUES(@PatientID, @DoctorID, @Diagnosis, @FollowUpAppointment)
	END
GO



ALTER PROC usp_PrescribeItem
	@ItemID int
AS
IF NOT EXISTS(SELECT * FROM Item WHERE IDItem = @ItemID)
	BEGIN
		RETURN 0
	END
ELSE
	BEGIN
		DECLARE @RecordID int
		SELECT @RecordID = max(IDRecord) FROM PatientRecord

		INSERT INTO ItemsPrescribed(ItemID, RecordID)
		VALUES(@ItemID, @RecordID)
	END
GO



ALTER PROC usp_GetPatientRecords
	@DoctorID int
AS
IF NOT EXISTS(SELECT * FROM PatientRecord WHERE DoctorID = @DoctorID)
	BEGIN
		RETURN 0
	END
ELSE
	BEGIN
		SELECT pr.IDRecord as IDRecord, p.FirstName as FirstName, p.LastName as LastName, pr.AppointmentDate as AppointmentDate FROM PatientRecord pr
		INNER JOIN Patient p ON p.IDPatient = pr.PatientID
		WHERE IDRecord IN 
		(
			SELECT IDRecord 
			FROM PatientRecord
			WHERE DoctorID = @DoctorID
		)
	END
GO


ALTER PROC usp_GetPatientAppointments
	@PatientID int,
	@DoctorID int
AS
BEGIN
	SELECT IDRecord, AppointmentDate, FollowUpAppointment, Diagnosis FROM PatientRecord WHERE PatientID = @PatientID AND DoctorID = @DoctorID 
END
GO



CREATE PROC usp_GetPatientsByDoctorID
	@DoctorID int
AS
IF NOT EXISTS(SELECT * FROM PatientRecord WHERE DoctorID = @DoctorID)
	BEGIN
		RETURN 0
	END
ELSE
	BEGIN
		SELECT DISTINCT p.IDPatient as IDPatient, p.FirstName as FirstName, p.MiddleName as MiddleName, p.LastName as LastName
		FROM Patient p 
		INNER JOIN PatientRecord pr ON p.IDPatient = pr.PatientID
		WHERE IDRecord IN 
		(
			SELECT IDRecord 
			FROM PatientRecord
			WHERE DoctorID = @DoctorID
		)
	END
GO


CREATE PROC usp_GetPrescribedItemsForPatient
	@PatientID int
AS
IF NOT EXISTS(SELECT * FROM PatientRecord WHERE PatientID = @PatientID)
	BEGIN
		RETURN 0
	END
ELSE
	BEGIN
		SELECT i.IDItem as IDItem, pr.AppointmentDate as AppointmentDate, pr.Diagnosis as Diagnosis, i.ItemDescription as ItemDescription, i.Price as Price
		FROM PatientRecord pr
		INNER JOIN ItemsPrescribed ip ON pr.IDRecord = ip.RecordID
		INNER JOIN Item i ON i.IDItem = ip.ItemID
		WHERE pr.PatientID = @PatientID	
	END
GO


CREATE PROC usp_UpdatePatientInfo
	@IDPatient int,
	@FirstName nvarchar(25),
	@MiddleName nvarchar(25),
	@LastName nvarchar(25),
	@Sex char(1),
	@DateOfBirth date,
	@Complaint nvarchar(max),
	@KinFirstName nvarchar(25),
	@KinMiddleName nvarchar(25),
	@KinLastName nvarchar(25),
	@RelationshipToPatient nvarchar(100),
	@PatientID int,
	@PresentAddress bit,
	@KinAddress bit,
	@Street nvarchar(25),
	@DoorNo int,
	@Area nvarchar(25),
	@CityID int,
	@PresentAddressKin bit,
	@KinAddressKin bit,
	@StreetKin nvarchar(25),
	@DoorNoKin int,
	@AreaKin nvarchar(25),
	@CityIDKin int,
	@Telephone1 nvarchar(25),
	@Telephone2 nvarchar(25),
	@TelephoneWork nvarchar(25),
	@TelephoneHome nvarchar(25),
	@Mobile nvarchar(25),
	@Pager nvarchar(25),
	@Fax nvarchar(25),
	@Email nvarchar(25),	 
	@MaritalStatus nvarchar(25), 
	@NoOfDependants int,
	@Height int, 
	@Weight int, 
	@BloodType nvarchar(10), 
	@Occupation nvarchar(25), 
	@GrossAnnualIncome decimal(18,2), 
	@Vegetarian bit, 
	@Smoker bit, 
	@AverageNrCigarettesDay int, 
	@ConsumingAlcohol bit, 
	@AverageNrDrinksDay int, 
	@UsingStimulants nvarchar(50), 
	@CoffeeTeaPerDay int, 
	@SoftDrinksPerDay int, 
	@HaveRegularMeals bit, 
	@HomeFoodOrOutside nvarchar(25), 
	@ComplaintBig nvarchar(max), 
	@Diabetic nvarchar(20), 
	@Hypertensive nvarchar(20), 
	@CardiacCondition nvarchar(100), 
	@RespiratoryCondition nvarchar(100), 
	@DigestiveCondition nvarchar(100), 
	@OrthopedicCondition nvarchar(100), 
	@MuscluarCondition nvarchar(100), 
	@NeurologicalCondition nvarchar(100), 
	@KnownAllergies nvarchar(100), 
	@ReactionToDrugs nvarchar(100), 
	@MajorSurgeries nvarchar(500)
AS
IF EXISTS(SELECT * FROM Patient p WHERE p.IDPatient = @IDPatient)
	BEGIN
		RETURN 0
	END
ELSE
	BEGIN
		UPDATE PatientContactInfo
		SET Telephone1 = @Telephone1, Telephone2 = @Telephone2, TelephoneWork = @TelephoneWork, TelephoneHome = @TelephoneHome, Mobile = @Mobile, Pager = @Pager, Fax = @Fax, Email = @Email
		WHERE IDPatientContactInfo IN 
		(SELECT p.PatientContactInfoID
	     FROM Patient p
		 WHERE p.IDPatient = @IDPatient)

		UPDATE Patient
		SET FirstName = @FirstName, MiddleName = @MiddleName, LastName = @LastName, Sex = @Sex, DateOfBirth = @DateOfBirth, Complaint = @Complaint, KinFirstName = @KinFirstName, 
			KinMiddleName = @KinMiddleName, KinLastName = @KinLastName, RelationshipToPatient = @RelationshipToPatient
		WHERE IDPatient = @IDPatient

		UPDATE PatientAddress
		SET PresentAddress = @PresentAddress, KinAddress = @KinAddress, DoorNo = @DoorNo, Street = @Street, Area = @Area, CityID = @CityID
		WHERE PatientID = @IDPatient AND PresentAddress = 1

		UPDATE PatientAddress
		SET PresentAddress = @PresentAddressKin, KinAddress = @KinAddressKin, DoorNo = @DoorNoKin, Street = @StreetKin, Area = @AreaKin, CityID = @CityIDKin
		WHERE PatientID = @IDPatient AND PresentAddress = 0

		UPDATE PatientDetails
		SET MaritalStatus = @MaritalStatus, NoOfDependants = @NoOfDependants, Height = @Height, Weight_ = @Weight, BloodType = @BloodType, Occupation = @Occupation, 
			GrossAnnualIncome = @GrossAnnualIncome, Vegetarian = @Vegetarian, Smoker = @Smoker, AverageNrCigarettesDay = @AverageNrCigarettesDay, ConsumingAlcohol = @ConsumingAlcohol,
			AverageNrDrinksDay = @AverageNrDrinksDay, UsingStimulants = @UsingStimulants, CoffeeTeaPerDay = @CoffeeTeaPerDay, SoftDrinksPerDay = @SoftDrinksPerDay, 
			HaveRegularMeals = @HaveRegularMeals, HomeFoodOrOutside = @HomeFoodOrOutside, Complaint = @Complaint, Diabetic = @Diabetic, Hypertensive = @Hypertensive, 
			CardiacCondition = @CardiacCondition, RespiratoryCondition = @RespiratoryCondition, DigestiveCondition = @DigestiveCondition, OrthopedicCondition = @OrthopedicCondition,
			MuscluarCondition = @MuscluarCondition, NeurologicalCondition = @NeurologicalCondition, KnownAllergies = @KnownAllergies, ReactionToDrugs = @ReactionToDrugs, 
			MajorSurgeries = @MajorSurgeries
		WHERE PatientID = @IDPatient
	END
GO



