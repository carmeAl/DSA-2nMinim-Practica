package edu.upc.dsa.BBDD;

import edu.upc.dsa.models.Ingrediente;
import edu.upc.dsa.models.Utensilio;
import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;


public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT(entity);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            //pstm.setObject(1, 0);
            int i = 1;

            for (String field : ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            pstm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Object get(Object entity, Hashtable table) {
        String selectQuery = QueryHelper.createQuerySELECT(entity,table);
        PreparedStatement pstm = null;
        List<Object> ListObject = new ArrayList<Object>();
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, 1);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            while (rs.next()) {
                Class clase = entity.getClass();
                Object o = clase.newInstance();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    ObjectHelper.setter(o, rs.getMetaData().getColumnName(i), rs.getObject(i));
                ListObject.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return ListObject;

    }

    public void update(Object object) {

        String updateQuery = QueryHelper.createQueryUPDATE(object);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(updateQuery);
            String field;
            int i =1;
            while (i<ObjectHelper.getFields(object).length){
                field = ObjectHelper.getFields(object)[i];
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }
            pstm.setObject(i++, ObjectHelper.getter(object, ObjectHelper.getFields(object)[0]));
            pstm.executeQuery();

        }  catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(Object object) {

    }

    public List<Object> findAll(Object theClass) {
        String selectQuery = QueryHelper.createQuerySELECTAll(theClass);
        PreparedStatement pstm = null;
        List<Object> ListObject = new ArrayList<Object>();
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, 1);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            while (rs.next()) {
                Class clase = theClass.getClass();
                Object o = clase.newInstance();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    ObjectHelper.setter(o, rs.getMetaData().getColumnName(i), rs.getObject(i));
                ListObject.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return ListObject;
    }

//    public List<Object> findAll(Class theClass, HashMap params) {
//        return null;
//    }

    @Override
    public List<Object> findAllByID(Class theClass1, Class theClass2, int idJugador) {
        String selectQuery = QueryHelper.createQuerySELECTAllByIDJugador(idJugador, theClass1, theClass2);
        PreparedStatement pstm = null;
        List<Object> ListObject = new ArrayList<Object>();
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, 1);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            while (rs.next()) {
                Class clase = theClass1;
                Object o = clase.newInstance();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    ObjectHelper.setter(o, rs.getMetaData().getColumnName(i), rs.getObject(i));
                ListObject.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return ListObject;
    }



    public Ingrediente getIngredienteId(Ingrediente in, int idIngrediente) {
        String selectQuery = QueryHelper.createQuerySELECT(in);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, idIngrediente);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    ObjectHelper.setter(in, rs.getMetaData().getColumnName(i), rs.getObject(i));
            }
            return in;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Utensilio getUtensilioId(Utensilio u, int idUtensilio) {
        String selectQuery = QueryHelper.createQuerySELECT(u);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, idUtensilio);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    ObjectHelper.setter(u, rs.getMetaData().getColumnName(i), rs.getObject(i));
            }
            return u;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getObjectByID(Object theObject, int id) {
        String selectQuery = QueryHelper.createQuerySELECT(theObject);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, id);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()){
                for (int i=1;i<=rs.getMetaData().getColumnCount();i++)
                    ObjectHelper.setter(theObject,rs.getMetaData().getColumnName(i),rs.getObject(i));
            }
            return theObject;

        }  catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }


    public Object getByTwoParameters(Class theClass, String byFirstParameter, Object byFirstParameterValue, String bySecondParameter, Object bySecondParameterValue) {

        String selectQuery = QueryHelper.createQuerySELECTbyTwoParameters(theClass, (String) byFirstParameterValue, (String) bySecondParameterValue);


        PreparedStatement pstm = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        try {
            Object object = theClass.getDeclaredConstructor().newInstance();

            pstm = conn.prepareStatement(selectQuery);

            pstm.setObject(1, byFirstParameter);
            pstm.setObject(2, bySecondParameter);
            pstm.executeQuery();
            rs = pstm.getResultSet();

            if (rs.next()) {

                rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(object, field, rs.getObject(i));
                }
                return object;

            } else {
                return null;
            }

        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateMoreParametros(Object entity, Hashtable tableSet,Hashtable tableWhere) {

        String updateQuery = QueryHelper.createQueryUPDATEmoreParametros(entity,tableSet,tableWhere);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(updateQuery);
            pstm.executeQuery();

        }  catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
