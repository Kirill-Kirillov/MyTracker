create table project_test(
    project_id serial PRIMARY KEY,
    project_date date NOT NULL,
    project_name varchar(50) NOT NULL,
    project_type varchar(8) NOT NULL
);

create table duration_test(
    duration_id serial PRIMARY KEY ,
    duration_date date NOT NULL,
    duration_duration decimal NOT NULL,
    duration_project_id int NOT NULL,
    FOREIGN KEY (duration_project_id) REFERENCES project_test(project_id)
);

create table quantity_test(
    quantity_id serial PRIMARY KEY ,
    quantity_date date NOT NULL,
    quantity_quantity decimal NOT NULL,
    quantity_project_id int NOT NULL ,
    FOREIGN KEY (quantity_project_id) REFERENCES project_test(project_id)
);