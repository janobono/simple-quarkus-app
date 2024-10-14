-- customer
CREATE DATABASE customer;
GO

USE customer;
GO

CREATE LOGIN customer WITH PASSWORD = 'customer', CHECK_EXPIRATION = OFF, CHECK_POLICY = OFF;
GO

CREATE USER customer FOR LOGIN customer;
GO

EXEC sp_addrolemember 'db_owner', 'customer';
GO

-- reservation
CREATE DATABASE reservation;
GO

USE reservation;
GO

CREATE LOGIN reservation WITH PASSWORD = 'reservation', CHECK_EXPIRATION = OFF, CHECK_POLICY = OFF;
GO

CREATE USER reservation FOR LOGIN reservation;
GO

EXEC sp_addrolemember 'db_owner', 'reservation';
GO

-- room
CREATE DATABASE room;
GO

USE room;
GO

CREATE LOGIN room WITH PASSWORD = 'room', CHECK_EXPIRATION = OFF, CHECK_POLICY = OFF;
GO

CREATE USER room FOR LOGIN room;
GO

EXEC sp_addrolemember 'db_owner', 'room';
GO
