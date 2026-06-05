DROP TABLE IF EXISTS note;
DROP TABLE IF EXISTS studenti;
DROP TABLE IF EXISTS profesori;
DROP TABLE IF EXISTS materii;

CREATE TABLE studenti (
                          id_student VARCHAR(50) PRIMARY KEY,
                          nume VARCHAR(100) NOT NULL,
                          email VARCHAR(100) NOT NULL
);

CREATE TABLE profesori (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nume VARCHAR(100) NOT NULL,
                           email VARCHAR(100) NOT NULL,
                           salariu DOUBLE,
                           specializare VARCHAR(100)
);

CREATE TABLE materii (
                         id_materie INT AUTO_INCREMENT PRIMARY KEY,
                         nume_materie VARCHAR(100) NOT NULL,
                         nr_credite INT NOT NULL
);

CREATE TABLE note (
                      id_nota INT AUTO_INCREMENT PRIMARY KEY,
                      valoare INT NOT NULL,
                      data_acordarii VARCHAR(20),
                      id_student VARCHAR(50),
                      id_materie INT,
                      FOREIGN KEY (id_student) REFERENCES studenti(id_student) ON DELETE CASCADE,
                      FOREIGN KEY (id_materie) REFERENCES materii(id_materie) ON DELETE CASCADE
);