package edu.upc.dsa.util;

import edu.upc.dsa.models.Ingrediente;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.*;

public class QueryHelper {
    final static Logger logger = Logger.getLogger(QueryHelper.class);

    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        Class theClass = entity.getClass();

        String [] fields = ObjectHelper.getFields(entity);


        for(String s:fields){
            sb.append(s).append(", ");
        }
        sb=sb.replace(sb.length()-2,sb.length(),"");

        //sb.append(") VALUES (?");
        sb.append(") VALUES (");

        for (String s:fields) {
            sb.append("?, ");
        }
        sb=sb.replace(sb.length()-2,sb.length(),"");

        sb.append(")");

        return sb.toString();
    }

    public static String createQuerySELECT(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }
    public static String createQuerySELECTsearchJugador(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE nombre = ? and password = ?");

        return sb.toString();
    }
    public static String createQuerySELECTbyTwoParameters(Class theClass, String byFirstParameter, String bySecondParameter) {

        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(theClass.getSimpleName());
        sb.append(" WHERE " + byFirstParameter + " = ?");
        sb.append(" AND " + bySecondParameter + " = ?");

        return sb.toString();
    }

    public static String createQuerySELECTAll(Object theClass) {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getClass().getSimpleName());

        return sb.toString();
    }

    public static String createQuerySELECTAllByIDJugador(int idJugador, Class theClass1, Class theClass2) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass1.getSimpleName());
        sb.append(" WHERE id= ANY (SELECT id").append(theClass1.getSimpleName()).append(" FROM ").append(theClass2.getSimpleName());
        sb.append(" WHERE idJugador=").append(idJugador).append(")");
        return sb.toString();
    }

    public static String createQueryUPDATE(Object entity) {

        String [] fields = ObjectHelper.getFields(entity);
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("SET ");
        String field;
        int i =1;
        while (i<fields.length){
            field = fields[i];

            if (i>1)
                sb.append(" = ?, ");
            sb.append(field);
            i++;
        }
        sb.append(" = ?");
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

    public static String createQueryUPDATEmoreParametros(Object entity, Hashtable tableSet,Hashtable tableWhere) {

        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("SET ");

        Enumeration<String > e = tableSet.keys();
        String  key = e.nextElement();
        sb.append(key).append("=").append(tableSet.get(key));
        while (e.hasMoreElements()) {
            key = e.nextElement();
            sb.append(" AND ").append(key).append("=").append(tableSet.get(key));
        }

        sb.append(" WHERE ");
        e = tableWhere.keys();

        key = e.nextElement();
        sb.append(key).append("=").append(tableWhere.get(key));
        while (e.hasMoreElements()) {
            key = e.nextElement();
            sb.append(" AND ").append(key).append(" = ").append(tableWhere.get(key));
        }
        logger.info(sb.toString());
        return sb.toString();
    }


    public static String createQueryUPDATEbyParameter(Object entity, String parameter) {
return null;
    }
    public static String createQuerySELECT(Object entity, Hashtable table){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ");
        Enumeration<String > e = table.keys();

        String  key = e.nextElement();
        if(table.get(key).getClass().equals(String.class)){
            sb.append(key).append(" = '").append(table.get(key)).append("'");
        }
        else {
            sb.append(key).append(" = ").append(table.get(key));
        }
        while (e.hasMoreElements()) {
            key = e.nextElement();
            if(table.get(key).getClass().equals(String.class)){
                sb.append(" AND ").append(key).append(" = '").append(table.get(key)).append("'");
            }
            else {
                sb.append(" AND ").append(key).append(" = ").append(table.get(key));
            }
        }
logger.info(sb.toString());
        return sb.toString();
    }





}
