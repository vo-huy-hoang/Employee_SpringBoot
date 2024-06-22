create database employee_manager;
use employee_manager;

create table department (
    id int primary key auto_increment,
    name varchar(100) not null
);

create table employee (
    id int primary key auto_increment,
    name varchar(100) not null,
    birth_day date,
    gender boolean,
    salary double,
    phone_number varchar(50) not null unique,
    department_id int,
    foreign key (department_id) references department(id)
);
insert into department (id, name)
values (1, 'Quản Lý'),
       (2, 'Kế Toán'),
       (3, 'Sale-Marketing'),
       (4, 'Sản Xuất');

insert into employee(name, birth_day, gender, salary, phone_number, department_id)
values  ('Nguyễn Văn A', '1990-01-15', true, 15000000.0, '0935271790', 1),
        ('Nguyễn Văn B', '1991-02-16', false, 16100000.0, '0935271791', 2),
        ('Nguyễn Văn C', '1992-03-17', true, 12100000.0,'0935271792', 3),
        ('Nguyễn Văn D', '1993-04-18', false, 13400000.0, '0935271793', 4),
        ('Nguyễn Văn E', '1993-04-18', true, 13400000.0, '0935271794', 4);


select e.id, e.name, e.birth_day, e.salary, e.phone_number, e.department_id, d.name from employee as e left join department d on e.department_id = d.id;

select e1_0.id,e1_0.birth_day,e1_0.department_id,e1_0.gender,e1_0.name,e1_0.phone_number,e1_0.salary from employee e1_0 where (?='' or ? is null or e1_0.name like concat('%',?,'%') escape '') and (?='' or ? is null or e1_0.birth_day>=?) and (?='' or ? is null or e1_0.birth_day<=?) and (? is null or e1_0.gender=?) and (? is null or (e1_0.salary<=5000000) or (e1_0.salary>5000000 and e1_0.salary<=10000000) or (e1_0.salary>10000000 and e1_0.salary<=15000000) or (e1_0.salary>=15000000)) and (?='' or ? is null or e1_0.phone_number like concat('%',?,'%') escape '') and (? is null or e1_0.department_id=?)