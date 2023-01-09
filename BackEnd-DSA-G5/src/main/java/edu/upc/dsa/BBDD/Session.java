package edu.upc.dsa.BBDD;

import edu.upc.dsa.models.Ingrediente;
import edu.upc.dsa.models.Utensilio;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public interface Session<E> {
    void save(Object entity);
    void close() ;
    Object get(Object entity, Hashtable table);
    void update(Object object);
    public Ingrediente getIngredienteId(Ingrediente in, int idIngrediente);

    public Object getObjectByID(Object theObject, int id);

    public Utensilio getUtensilioId(Utensilio u, int idUtensilio);
    public List<Object> findAllByID(Class theClass1, Class theClass2, int idJugador);
    void delete(Object object);
    List<Object> findAll(Object theClass);
//    List<Object> findAll(Class theClass, HashMap params);
    List<Object> query(String query, Class theClass, HashMap params);
    public Object getByTwoParameters(Class theClass, String byFirstParameter, Object byFirstParameterValue, String bySecondParameter, Object bySecondParameterValue);
    public void updateMoreParametros(Object entity, Hashtable tableSet,Hashtable tableWhere);
}
