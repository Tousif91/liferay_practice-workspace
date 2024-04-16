create index IX_650E48D5 on tr_Employee (groupId);
create index IX_BFB6E374 on tr_Employee (name[$COLUMN_LENGTH:75$]);
create index IX_70F62E29 on tr_Employee (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_27562AEB on tr_Employee (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_16F35E5F on tr_Student (name[$COLUMN_LENGTH:75$]);
create index IX_5BACEF14 on tr_Student (uuid_[$COLUMN_LENGTH:75$]);

create index IX_5804914D on tr_Teacher (uuid_[$COLUMN_LENGTH:75$]);