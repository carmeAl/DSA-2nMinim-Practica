CREATE TABLE if not exists Jugador(
    id int auto_increment primary key,
    nombre varchar(250) not null UNIQUE,
    password varchar(250) not null,
    email varchar(250),
    pais varchar(250),
    dinero double,
    nivel int
);
CREATE TABLE if not exists Ingrediente(
                            id int auto_increment primary key,
                            nombre varchar(250) not null,
                            nivelDesbloqueo int,
                            precio double,
                            urlImagen varchar(250)

);
CREATE TABLE if not exists Utensilio(
                        id int auto_increment primary key,
                        nombre varchar(250) not null,
                        tiempoNivel1 int not null,
                        tiempoNivel2 int not null,
                        tiempoNivel3 int not null,
                        precio double not null,
                        urlImagen varchar(250)
);

CREATE TABLE if not exists IngredientesComprados(
                                      idJugador int not null,
                                      idIngrediente int not null
);
CREATE TABLE if not exists UtensiliosComprados(
                                    idUtensilio int not null,
                                    idJugador int not null,
                                    nivel int,
                                    check ( nivel<4 )
);
CREATE TABLE if not exists Recetas(
    id int not null,
    nombre char(250),
    numPaso int,
    idIngrediente int,
    idUtensilio int
);


INSERT INTO Jugador(nombre, password, email, pais,dinero,nivel) VALUE ('Juan','1111', 'juan@gmail.com', 'España', 100,1);
INSERT INTO Jugador(nombre, password, email, pais,dinero,nivel) VALUE ('Victoria','2222', 'victoria@gmail.com', 'España', 100,1);
INSERT INTO Jugador(nombre, password, email, pais,dinero,nivel) VALUE ('Maria','3333', 'maria@gmail.com', 'Francia', 100,1);

INSERT INTO Ingrediente (nombre, nivelDesbloqueo, precio, urlImagen) VALUE ('Ketchup',3,12,'/ingredientesUtensilios/Ketchup.png');
INSERT INTO Ingrediente (nombre, nivelDesbloqueo, precio, urlImagen) VALUE ('Lechuga',4,13,'/ingredientesUtensilios/Lechuga.png');
INSERT INTO Ingrediente (nombre, nivelDesbloqueo, precio, urlImagen) VALUE ('Pan_abajo',1,0,'/ingredientesUtensilios/Pan_abajo.png');
INSERT INTO Ingrediente (nombre, nivelDesbloqueo, precio, urlImagen) VALUE ('Pan_arriba',1,0,'/ingredientesUtensilios/Pan_arriba.png');
INSERT INTO Ingrediente (nombre, nivelDesbloqueo, precio, urlImagen) VALUE ('Hamburguesa',1,0,'/ingredientesUtensilios/Hamburguesa.png');
INSERT INTO Ingrediente (nombre, nivelDesbloqueo, precio, urlImagen) VALUE ('Cebolla',4,14,'/ingredientesUtensilios/Cebolla.png');
INSERT INTO Ingrediente (nombre, nivelDesbloqueo, precio, urlImagen) VALUE ('Queso',3,15,'/ingredientesUtensilios/Queso.png');
INSERT INTO Ingrediente (nombre, nivelDesbloqueo, precio, urlImagen) VALUE ('Patata',2,16,'/ingredientesUtensilios/Patata.png');
INSERT INTO Ingrediente (nombre, nivelDesbloqueo, precio, urlImagen) VALUE ('Huevo',5,17,'/ingredientesUtensilios/Huevo.png');
INSERT INTO Ingrediente (nombre, nivelDesbloqueo, precio, urlImagen) VALUE ('Vino',5,18,'/ingredientesUtensilios/Vino.png');
INSERT INTO Ingrediente (nombre, nivelDesbloqueo, precio, urlImagen) VALUE ('CocaCola',4,19,'/ingredientesUtensilios/CocaCola.png');
INSERT INTO Ingrediente (nombre, nivelDesbloqueo, precio, urlImagen) VALUE ('Zumo',3,20,'/ingredientesUtensilios/Zumo.png');

INSERT INTO Utensilio (nombre, tiempoNivel1, tiempoNivel2, tiempoNivel3, precio, urlImagen) VALUE ('Cuchillo',15,10,5,50,'/ingredientesUtensilios/Cuchillo.png');
INSERT INTO Utensilio (nombre, tiempoNivel1, tiempoNivel2, tiempoNivel3, precio, urlImagen) VALUE ('Plancha',15,10,5,200,'/ingredientesUtensilios/Plancha.png');
INSERT INTO Utensilio (nombre, tiempoNivel1, tiempoNivel2, tiempoNivel3, precio, urlImagen) VALUE ('Plato',15,10,5,20,'/ingredientesUtensilios/Plato.png');
INSERT INTO Utensilio (nombre, tiempoNivel1, tiempoNivel2, tiempoNivel3, precio, urlImagen) VALUE ('Freidora',15,10,5,100,'/ingredientesUtensilios/Freidora.png');

INSERT INTO IngredientesComprados (idIngrediente, idJugador) VALUE (1,1);
INSERT INTO IngredientesComprados (idIngrediente, idJugador) VALUE (1,2);

INSERT INTO UtensiliosComprados (idUtensilio,idJugador,nivel) VALUE (1,1,1);
INSERT INTO UtensiliosComprados (idUtensilio, idJugador,nivel) VALUE (1,2,1);

INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (1,'Hamburguesa',1,5,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (1,'Plancha',2,null,2);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (1,'Pan_abajo',3,3,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (1,'Pan_arriba',4,4,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (1,'Plato',5,null,3);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (2,'Patata',1,8,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (2,'Cuchillo',2,null,1);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (2,'Freidora',3,null,4);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (2,'Hamburguesa',4,5,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (2,'Plancha',5,null,2);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (2,'Plato',6,null,3);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (3,'Hamburguesa',1,5,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (3,'Plancha',2,null,2);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (3,'Pan_abajo',3,3,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (3,'Ketchup',4,1,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (3,'Queso',5,7,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (3,'Pan_arriba',6,4,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (3,'Plato',7,null,3);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (3,'Zumo',8,12,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (4,'Hamburguesa',1,5,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (4,'Plancha',2,null,2);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (4,'Pan_abajo',3,3,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (4,'Ketchup',4,1,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (4,'Lechuga',5,2,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (4,'Cebolla',6,6,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (4,'Cuchillo',7,null,1);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (4,'Freidora',8,null,4);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (4,'Pan_arriba',9,4,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (4,'Plato',10,null,3);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (4,'Cocacola',11,11,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (5,'Hamburguesa',1,5,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (5,'Huevo',2,9,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (5,'Plancha',3,null,2);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (5,'Cebolla',4,6,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (5,'Cuchillo',5,null,1);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (5,'Freidora',6,null,4);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (5,'Pan_abajo',7,3,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (5,'Lechuga',8,2,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (5,'Queso',9,7,null);
INSERT INTO Recetas (id, nombre, numPaso, idIngrediente, idUtensilio) VALUE (5,'Vino',10,10,null);