package edu.upc.dsa.DAO;

import edu.upc.dsa.BBDD.FactorySession;
import edu.upc.dsa.BBDD.Session;
import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.util.*;

public class UtensilioManagerImpl implements UtensilioManager{
    private static UtensilioManager instance;
    protected List<Utensilio> utensilios;
    final static Logger logger = Logger.getLogger(UtensilioManagerImpl.class);

    public UtensilioManagerImpl(){
        this.utensilios=new LinkedList<>();
    }

    public static UtensilioManager getInstance(){

        if(instance == null)
            instance = new UtensilioManagerImpl();

        return instance;
    }

    /*
    @Override
    public List<Utensilio> getAllUtensilios() {

        return this.utensilios;
    }*/

    @Override
    public List<Utensilio> getAllUtensilios() {
            Session session = null;
            List<Utensilio> listaUtensilio = new ArrayList<Utensilio>();

            try{
                session = FactorySession.openSession();
                listaUtensilio = (ArrayList<Utensilio>) session.findAll(new Utensilio());
            }
            catch(Exception e){
                e.printStackTrace();
                listaUtensilio = null;
            }

            finally {
                session.close();
            }
            return listaUtensilio;
    }

    @Override
    public Utensilio getUtensilio (int idUtensilio){
        logger.info("getUtensilio("+idUtensilio+")");

        for (Utensilio u: this.utensilios) {
            if (Objects.equals(u.getId(), idUtensilio)) {
                logger.info("getUtensilio("+idUtensilio+"): "+u);

                return u;
            }
        }
        logger.warn("not found " + idUtensilio);
        return null;
    }

    @Override
    public Utensilio addUtensilio(String nombreUtensilio, int tiempoNivel1, int tiempoNivel2, int tiempoNivel3, double precioUtensilio) {
        Utensilio u = new Utensilio(nombreUtensilio,tiempoNivel1, tiempoNivel2, tiempoNivel3, precioUtensilio);
        logger.info("new Utensilio " + u);
        this.utensilios.add(u);
        logger.info("Se ha añadido un Utensilio");
        return u;
    }

    @Override
    public void deleteUtensilio(int idUtensilio) {
        Utensilio u = this.getUtensilio(idUtensilio);
        if (u==null){
            logger.warn("not found "+u);
        } else {
            logger.info(u+" deleted");
        }
        this.utensilios.remove(u);
    }

    @Override
    public int getTiempoNivel1(int idUtensilio) {
        Utensilio u = this.getUtensilio(idUtensilio);
        if (u==null) {
            logger.warn("not found "+u);
            return-1;
        }
        else{
            return u.getTiempoNivel1();
        }
    }

    @Override
    public int getTiempoNivel2(int idUtensilio) {
        Utensilio u = this.getUtensilio(idUtensilio);
        if (u==null) {
            logger.warn("not found "+u);
            return-1;
        }
        else{
            return u.getTiempoNivel2();
        }
    }

    @Override
    public int getTiempoNivel3(int idUtensilio) {
        Utensilio u = this.getUtensilio(idUtensilio);
        if (u==null) {
            logger.warn("not found "+u);
            return-1;
        }
        else{
            return u.getTiempoNivel3();
        }
    }
    @Override
    public Utensilio putUtensilio (Utensilio utensilio) {
        Utensilio u = this.getUtensilio(utensilio.getId());
        if (u!=null){
            logger.info(u+" rebut!");
            u.setNombre(utensilio.getNombre());
            u.setId(utensilio.getId());
            u.setTiempoNivel1(utensilio.getTiempoNivel1());
            u.setTiempoNivel2(utensilio.getTiempoNivel2());
            u.setTiempoNivel3(utensilio.getTiempoNivel3());
            logger.info(u+" update");
        } else {
            logger.warn("not found "+u);
        }
        return u;
    }

    @Override
    public int size() {
        return utensilios.size();
    }

    /*
    @Override
    public List<Utensilio> listaUtensiliosComprados(int idJugador) {
        Session session = null;
        List<Utensilio> listaUtensilios = new ArrayList<Utensilio>();
        try {
            session = FactorySession.openSession();
            listaUtensilios=session.findAllByID(Utensilio.class, UtensiliosMejorados.class,idJugador);


        } catch (Exception e) {
            e.printStackTrace();
            listaUtensilios = null;
        } finally {
            session.close();
            return listaUtensilios;
        }
    }*/

    @Override
    public List<UtensiliosComprados> listaUtensiliosComprados(int idJugador) {
        Session session = null;
        List<UtensiliosComprados> listaUtensilios = new ArrayList<UtensiliosComprados>();
        try {
            session = FactorySession.openSession();
            Hashtable<String,Integer> table=new Hashtable<>();
            table.put("idJugador",idJugador);
            listaUtensilios= (List<UtensiliosComprados>) session.get(new UtensiliosComprados(), table);


        } catch (Exception e) {
            e.printStackTrace();
            listaUtensilios = null;
        } finally {
            session.close();
            return listaUtensilios;
        }
    }


    @Override
    public double getPrecioUtensilio(int idUtensilio) {
        Session session = null;
        Utensilio u = new Utensilio();
        try {
            session = FactorySession.openSession();
            u = (Utensilio) session.getUtensilioId(u,idUtensilio);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return u.getPrecio();
    }

    @Override
    public int comprarUtensilio(Jugador j, int idUtensilio, int nivelUtensilio){

        Session session = null;
        int error =-1;
        try {
            double precioUtensilio = getPrecioUtensilio(idUtensilio);
            double dinero=j.getDinero();//Buscamos el dinero que tiene el usuario
            double dineroRestante = dinero-precioUtensilio;
            if(dineroRestante>=0) {
                session = FactorySession.openSession();
                Jugador jug = new Jugador (j.getNombre(), j.getPassword(),j.getEmail(),j.getPais(),dineroRestante);
                session.update(jug);
                UtensiliosComprados NuevoUtensilio = new UtensiliosComprados(idUtensilio, jug.getId(), nivelUtensilio);
                session.save(NuevoUtensilio);
                error = 0;
            }
            else
            {
                error=-1;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return error;

    }

    @Override
    public int postUtensilioComprado(int idJugador, int idUtensilio) {
            logger.info("getJugador("+idJugador+")");
            Session session = null;
            Jugador jugador = null;
            Utensilio utensilio = null;
            List<Jugador>  listJugador=new ArrayList<>();
            List<Utensilio>  listUtensilio=new ArrayList<>();
            Hashtable<String,String> tableSet=new Hashtable<>();
            Hashtable<String,Integer> tableWhere=new Hashtable<>();
            try {

                session = FactorySession.openSession();

                tableSet.put("nivel","nivel+1");
                tableWhere.put("idJugador",idJugador);
                tableWhere.put("idUtensilio",idUtensilio);

                Hashtable<String,Integer> jugadorTable=new Hashtable<>();
                jugadorTable.put("id", idJugador);
                listJugador = (List<Jugador>) session.get(new Jugador(),jugadorTable);

            /*Ingrediente ingredienteSeleccionado = new Ingrediente();
            ingredienteSeleccionado.setId(idIngrediente);*/
                Hashtable<String,Integer> utensilioTable=new Hashtable<>();
                utensilioTable.put("id", idUtensilio);
                listUtensilio = (List<Utensilio>) session.get(new Utensilio(),utensilioTable);

                if (listJugador.get(0)!=null && listUtensilio.get(0) != null){
                    utensilio = listUtensilio.get(0);
                    jugador = listJugador.get(0);
                    logger.info(jugador+" rebut!");
                    int nivel=getNivelUtensilio(idJugador,idUtensilio);

                    if(nivel<3 && nivel!=0){
                        if(jugador.getDinero() >= utensilio.getPrecio()){
                            // session = FactorySession.openSession();
                            double dineroJugador = jugador.getDinero();
                            double precioUtensilio = utensilio.getPrecio();
                            double dineroRestante = dineroJugador - precioUtensilio;

                            jugador.setDinero(dineroRestante);
                            session.update(jugador);
                            session.updateMoreParametros(new UtensiliosComprados(),tableSet,tableWhere);
                            //session.save(ic);

                            return 201;
                        }
                        else {
                            logger.error("El jugador no tiene suficiente dinero para comprar el utensilio");
                            return 501;
                        }

                    } else{
                        logger.error("El Utensilio ya esta en el nivel maximo");
                        return 502;
                    }


                } else {
                    logger.error("El jugador o el utensilio no existe");
                    return 404;
                }
            }
            catch (Exception e) {
                logger.warn("not found ");
                e.printStackTrace();
            }
            finally {
                session.close();
            }

            return 404;
    }

    public int getNivelUtensilio(int idJugador,int idUtensilio){
        Session session = null;
        UtensiliosComprados uc = new UtensiliosComprados();
        try {
            session = FactorySession.openSession();
            Hashtable <String,Integer> table=new Hashtable<>();
            table.put("idJugador",idJugador);
            table.put("idUtensilio",idUtensilio);
            List<Object> list= (List<Object>) session.get(new UtensiliosComprados(),table);
            uc = (UtensiliosComprados) list.get(0);
            return uc.getNivel();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return 0;
    }


}
