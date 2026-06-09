DROP DATABASE IF EXISTS forrage;
CREATE DATABASE forrage;

USE forrage;

CREATE TABLE region(
    id_region INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(100)
);

CREATE TABLE district(
    id_district INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(100),
    id_region INT,
    FOREIGN KEY (id_region) REFERENCES region(id_region)
);

CREATE TABLE commune(
    id_commune INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(100),
    id_district INT,
    FOREIGN KEY (id_district) REFERENCES district(id_district)
);

CREATE TABLE client(
    id_client INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100),
    adresse VARCHAR(100),
    contact VARCHAR(100)
);

CREATE TABLE demande(
    id_demande INT PRIMARY KEY AUTO_INCREMENT,
    reference VARCHAR(100) NOT NULL,
    date DATETIME,
    demandeur INT,
    lieu VARCHAR(100),
    commune INT,
    FOREIGN KEY (demandeur) REFERENCES client(id_client),
    FOREIGN KEY (commune) REFERENCES commune(id_commune)
);

CREATE TABLE statut(
    id_statut INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(255) NOT NULL,
    sigle VARCHAR(50) NOT NULL
);

CREATE TABLE statut_demande(
    id_statut_demande INT PRIMARY KEY AUTO_INCREMENT,
    id_demande INT,
    id_statut INT,
    date DATETIME,
    observation TEXT,
    durer_travaille INT,
    FOREIGN KEY (id_demande) REFERENCES demande(id_demande),
    FOREIGN KEY (id_statut) REFERENCES statut(id_statut)
);

CREATE TABLE type_devis(
    id_type INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(255) NOT NULL,
    sigle VARCHAR(50) NOT NULL
);

CREATE TABLE devis(
    id_devis INT PRIMARY KEY AUTO_INCREMENT,
    reference VARCHAR(100) NOT NULL,
    id_demande INT,
    id_type INT,
    date DATETIME,
    observation TEXT,
    FOREIGN KEY (id_demande) REFERENCES demande(id_demande),
    FOREIGN KEY (id_type) REFERENCES type_devis(id_type)
);

CREATE TABLE detail(
    id_detail INT PRIMARY KEY AUTO_INCREMENT,
    id_devis INT,
    libelle VARCHAR(255) NOT NULL,
    quantite INT,
    unite VARCHAR(50),
    prix_unitaire DECIMAL(10, 2),
    FOREIGN KEY (id_devis) REFERENCES devis(id_devis)
);

CREATE TABLE parametre(
    id_parametre INT PRIMARY KEY AUTO_INCREMENT,
    id_statut1 INT,
    id_statut2 INT,
    durer INT,
    alerte VARCHAR(10),
    FOREIGN KEY (id_statut1) REFERENCES statut(id_statut),
    FOREIGN KEY (id_statut2) REFERENCES statut(id_statut)
);

INSERT INTO region (libelle) VALUES 
('Analamanga'), 
('Alaotra-Mangoro');

INSERT INTO district (libelle, id_region) VALUES 
('Antananarivo-Atsimondrano', 1), 
('Antananarivo-Renivohitra', 1), 
('Ambatondrazaka', 2),
('Amparafaravola', 2);

INSERT INTO commune (libelle, id_district) VALUES 
('Ampitatafika', 1), 
('Andranomangay', 1),
('Anosizato', 2),
('Isoraka', 2), 
('Ambatondrazaka I', 3),
('Ambatondrazaka II', 3),
('Amparafaravola I', 4),
('Amparafaravola II', 4);

INSERT INTO client (nom, adresse, contact) VALUES 
('Jean', 'Lot II Bis', '0324567389'), 
('Marie', 'Lot III O9', '0387654321');

INSERT INTO statut (libelle, sigle) VALUES 
('Demande créée', 'DC'),
('Demande refusé', 'DR'),
('Devis étude créé', 'DEC'),
('Devis étude refusé', 'DER'), 
('Devis forrage créé', 'DFC'), 
('Devis forrage refusé', 'DFR');

INSERT INTO type_devis (libelle, sigle) VALUES 
('Devis étude', 'DE'),
('Devis forrage', 'DF');

INSERT INTO parametre (id_statut1, id_statut2, durer, alerte) VALUES 
(1, 3, 350, 'R'),
(1, 3, 200, 'J'),
(3, 5, 800, 'J'),
(3, 5, 900, 'R'),
(1, 5, 850, 'J'),
(1, 5, 10000, 'R');