package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Partida;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;


public class PartidasManagerImpl implements PartidasManager {

    private static PartidasManager instance;
    protected List<Partida> partidasList;

    final static Logger logger = Logger.getLogger(PartidasManagerImpl.class);
    public PartidasManagerImpl() {
        this.partidasList = new LinkedList<>();
    }
    public static PartidasManager getInstance(){
        if (instance==null) instance = new PartidasManagerImpl();
        return instance;
    }
    public int size(){
        int ret = this.partidasList.size();
        logger.info("size" + ret);
        return ret;
    }

    public List<Partida> getAllPartidas(){
        return this.partidasList;
    }
    /*
    public List<Partida> getAllPartidasJugador(int idJugador){

    }
    public List<Partida> getAllPartidasMapa(int idMapa){

    }
    */

    public Partida getPartida(int idPartida){
        logger.info("getPartida("+idPartida+")");

        for (Partida p: this.partidasList) {
            if (Objects.equals(p.getId(), idPartida)) {
                logger.info("getPartida("+idPartida+"): "+p);

                return p;
            }
        }
        logger.warn("not found " + idPartida);
        return null;
    }
   public Partida addPartida(Partida p){
        logger.info("new Partida " + p);
        this.partidasList.add(p);
        logger.info("new Partida added");
        return p;
    }
    public Partida addPartida (int nivelActual, int puntos, int idMapa, int idJugador){
       return this.addPartida(new Partida(nivelActual, puntos,idMapa,idJugador));
   }
    @Override
    public void deletePartida(int idPartida){
        Partida p = this.getPartida(idPartida);
        if (p==null){
            logger.warn("not found " + p);
        }
        else logger.info(p + "deleted");
        this.partidasList.remove(p);
    }
    @Override
    public Partida updatePartida(Partida t){
        Partida p = this.getPartida(t.getId());
        if(p!=null){
            logger.info(t+" received");
            p.setId(t.getId());
            p.setIdMapa(t.getIdMapa());
            p.setPuntos(t.getPuntos());
            p.setNivelActual(t.getNivelActual());
            p.setIdJugador(t.getIdJugador());
            logger.info(p + "updated");
        }
        else logger.warn("not found " +t);
        return p;
    }
}