create table tr_Employee (
	uuid_ VARCHAR(75) null,
	empId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	dob DATE null,
	gender VARCHAR(75) null,
	email VARCHAR(75) null,
	address VARCHAR(75) null
);

create table tr_Student (
	uuid_ VARCHAR(75) null,
	studentId LONG not null primary key,
	name VARCHAR(75) null,
	dob DATE null,
	gender VARCHAR(75) null
);

create table tr_Teacher (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	name VARCHAR(75) null,
	address VARCHAR(75) null
);