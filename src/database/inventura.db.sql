SQLite format 3   @     �                                                               � .�    ih                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     �N�otablenarudzbanarudzbaCREATE TABLE narudzba(    `id` integer not null,    `proizvod` text,    `vrsta` text,    `opis` text,    `datum` date,    `proizvod_id` integer,    `mjesto_id` integer,    foreign key (`proizvod_id`) references proizvod(`id`),    foreign key (`mjesto_id`) references mjesto(`id`),    primary key (`id`)  )�~�OtableproizvodproizvodCREATE TABLE `proizvod`(    `id` integer not null,    `naziv` text,    `kategorija` text,    `datum` date,    `mjesto` text,    `mjesto_id` integer,    foreign key (`mjesto_id`) references mjesto(`id`),    primary key (`id`))��tablemjestomjestoCREATE TABLE `mjesto`(    `id` integer not null,    `naziv` text,    `lokacija` text,    `opis` text,    PRIMARY KEY(`id`))   h ���hM                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           ) )/Dnevni bo   !CumezKuca1Cumezsssss7 %ODjecija sobaKuca1mjesto gdje djeca provode vrijeme +GarazaKuca1Parking za auto$ 3KuhinjaKuca1Mjesto gdje se kuha SpajizKuca1Ostava   � ��~JA8/����                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             * !%asdasdV               
     	     ! 	sadasasdasd2020-09-2Spajiz               2 #!%KompjuterElektronika2020-12-03Djecija soba" !AutoVozilo2020-12-03Garaza- )!SporetBijela tehnika2020-12-03Kuhinja- )!	FriziderBijela tehnika2020-12-03Spajiz    ��a,�                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         3 1!Kuglični cilindarProdajaNebitno2020-09-15( # 	sadasProdajaasdasd2020-09-23 3!AutoKupovinaKupovina automobila2020-09-094 /!RacunarKupovinaKupovina racunara2020-09-092 -!SporetKupovinaKupovina sporeta2020-09-093 /!		FriziderKupovinaKupovina fizidera2020-09-09