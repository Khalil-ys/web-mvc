--create table students (
--    id int not null auto_increment,
--    name varchar(20),
--    surname varchar(20),
--    student_class int,
--    birthday date,
--    email varchar(20),
--    course_code varchar(20),
--    phone varchar(20),
--    primary key (id));

insert into students(name,surname,student_class,birthday,email,course_code,phone) values
('Roberto','Carlos',5,'1995-05-07','carlos@mail.com','MY_004','055-888-6524'),
('Cristiano','Ronaldo',6,'1859-04-11','ronaldo@mail.com','MY_007','010-587-6985'),
('Diyego','Ronaldo',4,'1994-01-03','diyego@mail.com','MY_009','011-254-8532'),
('Devid','Beckhem',2,'1991-04-08','devid@mail.com','MY_001','070-854-6412'),
('Frank','Ribery',8,'1996-08-07','ribery@mail.com','MY_005','050-587-9624');

create table users (
username varchar(25),
password varchar(150),
enabled int,
primary key(username)
);
 insert into users (username,password,enabled) values
 ('yusif','{noop}12',1),
 ('xalil','{noop}123',1),
 ('memmed','{noop}1234',1),
 ('veli','{bcrypt}$2a$12$gYFryL1fOUvrHpVuc4DIZOv/h/NKHJunX./Gl8lGdzzA0oZxIE5lC',1);

 create table authorities(
 username varchar(50),
 authority varchar(50)
 );
 insert into authorities (username,authority) values
 ('yusif','read:all:students'),
 ('yusif','open:new:student:page'),
 ('yusif','save:student'),

 ('xalil','read:all:students'),
 ('xalil','open:edit:student:page'),
 ('xalil','open:new:student:page'),
 ('xalil','delete:student'),
 ('xalil','search:student'),
 ('xalil','save:student'),

 ('memmed','read:all:students'),
 ('memmed','open:edit:student:page'),
 ('memmed','save:student'),
 ('memmed','delete:student'),

 ('veli','read:all:students'),
 ('veli','open:new:student:page'),
 ('veli','save:student');

 insert into sectors (name) values
 ('Az'),('Turk'),('Alman'),('Rus'),('Ingilis'),('Fransiz');

 insert into programing_languages (name) values
 ('Java'),('Python'),('JavaScript'),('C#'),('C++'),('Scala'),('Pascal'),('PHP');
















