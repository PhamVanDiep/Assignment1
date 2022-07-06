drop database country_city;
create database country_city;

use country_city;

drop table if exists `Contient`;
create table `Contient`(
	`id` varchar(10) primary key,
	`name` varchar(50)
);

drop table if exists `Country`;
create table `Country`(
	`id` varchar(10) primary key,
    `name` varchar(50),
    `contient` varchar(10),
    `surfaceArea` double,
	`population` int,
    `gnp` double
);

drop table if exists `City`;
create table `City`(
	`id` int primary key,
    `name` varchar(50),
    `population` int,
    `country_id` varchar(10)
);

drop table if exists `Capital`;
create table `Capital`(
	`country_id` varchar(10),
    `city_id` int
);
-- alter table Country drop constraint fk_country_contient;
alter table Country add constraint fk_country_contient foreign key (contient) references Contient(id);

alter table City add constraint fk_city_country foreign key (country_id) references Country(id);

alter table Capital add constraint pk_capital_country foreign key (country_id) references Country(id);

alter table Capital add constraint pk_capital_city foreign key (city_id) references City(id);

insert into Contient values ('ChauMy' , 'Chau My'), ('ChauAu' , 'Chau Au'), ('ChauPhi' , 'Chau Phi'), ('ChauA' , 'Chau A'), ('ChauUc' , 'Chau Dai Duong');

insert into Country values ('USA', 'USA', 'ChauMy', 15, 300, 1000),
							('Can', 'Canada', 'ChauMy', 17, 250, 800),
                            ('Fra', 'Phap', 'ChauAu', 9, 70, 750),
                            ('Ger', 'Duc', 'ChauAu', 10, 75, 850),
                            ('Egy', 'Ai Cap', 'ChauPhi', 11, 65, 400),
                            ('Nig', 'Nigieria', 'ChauPhi', 12, 60, 420),
                            ('Chi', 'Trung Quoc', 'ChauA', 16, 1800, 1200),
                            ('Jap', 'Nhat Ban', 'ChauA', 9, 100, 900),
                            ('New', 'Newzealand', 'ChauUc', 8, 60, 450),
                            ('Aus', 'Austrailia', 'ChauUc', 14, 100, 950);
                            
insert into City values (1, 'Wasington', 90, 'USA'),
						(2, 'Newyork', 80, 'USA'),
                        (3, 'CanadaA', 85, 'Can'),
                        (4, 'CanadaB', 95, 'Can'),
                        (5, 'Paris', 20, 'Fra'),
                        (6, 'Boreadux', 18, 'Fra'),
                        (7, 'Munich', 19, 'Ger'),
                        (8, 'Berlin', 15, 'Ger'),
                        (9, 'Ai Cap 1', 9, 'Egy'),
                        (10, 'Ai Cap 2', 8, 'Egy'),
                        (11, 'Nigieria 1', 10, 'Nig'),
                        (12, 'Nigieria 2', 8, 'Nig'),
                        (13, 'Bac kinh', 15, 'Chi'),
                        (14, 'Nam kinh', 9, 'Chi'),
                        (15, 'Tokyo', 12, 'Jap'),
                        (16, 'Osaka', 13, 'Jap'),
                        (17, 'Newzealand 1', 10, 'New'),
                        (18, 'Newzealand 2', 8, 'New'),
                        (19, 'Sysney', 20, 'Aus'),
                        (20, 'Austraylia 2', 11, 'Aus');
                        
insert into Capital values ('USA', 1),
							('Can', 3),
                            ('Fra', 5),
                            ('Ger', 7),
                            ('Egy', 9),
                            ('Nig', 11),
                            ('Chi', 13),
                            ('Jap', 15),
                            ('New', 17),
                            ('Aus', 19);
                            
insert into City values (21, 'Thuong Hai', 20, 'Chi'),
						(22, 'Tham Quyen', 25, 'Chi');
insert into City values (23, 'Hong Kong', 25, 'Chi');

insert into Country values ('Vie', 'Viet Nam', 'ChauA', 16, 100, 888);

select * from Country;

-- 2.1. tim thanh pho dong dan nhat cua moi quoc gia
select Country.name as Country_name, City.name as City_name, City.population
from Country, City
where Country.id = City.country_id
	and City.population = (select max(population) from City where Country.id = City.country_id);
-- best solution
select country_id, max(population) from City group by country_id;

-- 2.2. Tim thanh pho dong dan nhat cua moi luc dia
select Contient.name, Country.name, City.name, temp.max
from Contient, City, Country, (select Contient.id, max(City.population) as max
								from City, Country, Contient 
								where City.country_id = Country.id 
									and Country.contient = Contient.id
								group by Contient.id) as temp
where Contient.id = Country.contient
	and Country.id = City.country_id
    and temp.id = Contient.id 
    and City.population = temp.max;
    
-- select Contient.id, max(City.population) 
-- from City, Country, Contient 
-- where City.country_id = Country.id 
-- 	and Country.contient = Contient.id
-- group by Contient.id;
                                                            
-- 2.3. Tim thanh pho la thu do va dong dan nhat
select City.id, City.name
from City, Country, Capital
where City.country_id = Country.id
	and City.id = Capital.city_id
    and Country.id = Capital.country_id
order by City.population desc
limit 1;

-- 2.4. Tim thanh pho la thu do va dong dan nhat cua moi luc dia
select Contient.name, Country.name, City.name, temp.max
from Contient, City, Country, (select Contient.id, max(City.population) as max
								from City, Country, Contient, Capital
								where City.country_id = Country.id 
									and Country.contient = Contient.id
                                    and Capital.city_id = City.id
								group by Contient.id) as temp
where Contient.id = Country.contient
	and Country.id = City.country_id
    and temp.id = Contient.id 
    and City.population = temp.max;

-- 2.5. Sap xep cac quoc gia theo so luong thanh pho giam dan
select Country.name, count(City.id) as so_luong_thanh_pho
from Country, City
where Country.id = City.country_id
group by Country.name
order by so_luong_thanh_pho desc;

-- 2.6. Sắp xếp các quốc gia theo mật độ dân số theo thứ tự giảm dần bỏ qua các quốc gia có dân số bằng không.
select Country.name, (sum(City.population)/Country.surfaceArea) as mat_do_dan_so
from Country, City
where Country.id = City.country_id
group by Country.name, Country.surfaceArea
having sum(City.population) > 0
order by mat_do_dan_so desc;