create table USER
(
	ID INT auto_increment primary key not null,
	ACCOUNT_ID VARCHAR(128),
	NAME VARCHAR(64),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT
);