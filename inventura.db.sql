begin transaction;
create table if not exists `mjesto`(
    `id` integer not null,
    `naziv` text,
    `lokacija` text,
    `opis` text,
    PRIMARY KEY(`id`)
);
insert into `mjesto` values (1,'Spajiz','Kuca1','Ostava');
insert into `mjesto` values(2,'Kuhinja','Kuca1','Mjesto gdje se kuha');
insert into `mjesto` values (3,'Garaza','Kuca1','Parking za auto');
insert into `mjesto` values (4,'Djecija soba','Kuca1','mjesto gdje djeca provode vrijeme');
create table if not exists `proizvod`(
    `id` integer not null,
    `naziv` text,
    `kategorija` text,
    `datum` date,
    `mjesto` text,
    `mjesto_id` integer,
    foreign key (`mjesto_id`) references mjesto(`id`),
    primary key (`id`)
);
insert into `proizvod` values (1,'Frizider','Bijela tehnika','2020-12-03','Spajiz',1);
insert into `proizvod` values (2,'Sporet','Bijela tehnika','2020-12-03','Kuhinja',2);
insert into `proizvod` values (3,'Auto','Vozilo','2020-12-03','Garaza',3);
insert into `proizvod` values (4,'Kompjuter','Elektronika','2020-12-03','Djecija soba',4);
create table if not exists narudzba(
    `id` integer not null,
    `proizvod` text,
    `vrsta` text,
    `opis` text,
    `datum` date,
    `proizvod_id` integer,
    `mjesto_id` integer,
    foreign key (`proizvod_id`) references proizvod(`id`),
    foreign key (`mjesto_id`) references mjesto(`id`),
    primary key (`id`)
  );
  insert into `narudzba` values (1,'Frizider','Kupovina','Kupovina fizidera','2020-14-09',1,1);
  insert into `narudzba` values (2,'Sporet','Kupovina','Kupovina sporeta','2020-14-09',2,2);
  insert into `narudzba` values (3,'Racunar','Kupovina','Kupovina racunara','2020-14-09',4,4);
  insert into `narudzba` values (4,'Auto','Kupovina','Kupovina automobila','2020-14-09',3,3);
  COMMIT;