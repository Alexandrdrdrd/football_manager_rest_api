
CREATE TABLE if not exists  my_db.team   (
                            id int NOT NULL AUTO_INCREMENT,
                            name varchar(55),
                            budget double,
                            sales_percentage int,
                            PRIMARY KEY (id)
);

CREATE TABLE if not exists my_db.player  (
                              id int NOT NULL AUTO_INCREMENT,
                              name varchar(15),
                              surname varchar(25),
                              age int,
                              experience int,
                              team_id int,
                              PRIMARY KEY (id),
                              FOREIGN KEY (team_id) REFERENCES my_db.team(id));