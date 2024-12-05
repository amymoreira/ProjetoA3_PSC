CREATE DATABASE cadastro_db;
USE cadastro_db;

CREATE TABLE registrar_consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente VARCHAR(100),                  
    tipo VARCHAR(100),                          
    data date,                 
    cpf VARCHAR(14)                    
);

CREATE TABLE consultar_consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente VARCHAR(100),                  
    buscar VARCHAR(100)                    
);

CREATE TABLE listas_consultas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente VARCHAR(100) NOT NULL,
    tipo_consulta VARCHAR(50),
    data DATE
);



INSERT INTO registrar_consulta (paciente, tipo, data, cpf)
VALUES 
('João Silva', 'Cardiologia', '2024-12-05', '123.456.789-00'),
('Maria Oliveira', 'Pediatria', '2024-12-06', '987.654.321-00'),
('Pedro Santos', 'Ortopedia', '2024-12-07', '456.789.123-00');



INSERT INTO consultar_consulta (paciente, buscar)
VALUES 
('João Silva', 'Cardiologia'),
('Maria Oliveira', 'Pediatria'),
('Pedro Santos', 'Ortopedia');


INSERT INTO listas_consultas (paciente, tipo_consulta, data)
VALUES 
('João Silva', 'Cardiologia', '2024-12-05'),
('Maria Oliveira', 'Pediatria', '2024-12-06'),
('Pedro Santos', 'Ortopedia', '2024-12-07');



select*from registrar_consulta;
select*from consultar_consulta;
select*from  listas_consultas;