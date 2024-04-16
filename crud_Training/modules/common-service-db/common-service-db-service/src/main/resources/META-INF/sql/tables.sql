create table ats_News (
	uuid_ VARCHAR(75) null,
	id_ LONG not null primary key,
	userId LONG,
	title VARCHAR(75) null,
	subTitle VARCHAR(75) null,
	description VARCHAR(75) null,
	status INTEGER
);