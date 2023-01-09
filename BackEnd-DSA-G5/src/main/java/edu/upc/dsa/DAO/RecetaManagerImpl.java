package edu.upc.dsa.DAO;

import edu.upc.dsa.models.Receta;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class RecetaManagerImpl implements RecetaManager{
    private static RecetaManager instance;
    protected List<Receta> recetas;
    final static Logger logger = Logger.getLogger(RecetaManagerImpl.class);

    public RecetaManagerImpl(){
        this.recetas=new LinkedList<>();
    }

    public static RecetaManager getInstance(){

        if(instance == null)
            instance = new RecetaManagerImpl();

        return instance;
    }
    public int size(){
        return recetas.size();
    }

    @Override
    public List<Receta> getAllRecetas() {

        return this.recetas;
    }

    @Override
    public Receta getReceta (int idReceta){
        logger.info("getReceta("+idReceta+")");

        for (Receta r: this.recetas) {
            if (r.getId()==idReceta) {
                logger.info("getReceta("+idReceta+"): "+r);

                return r;
            }
        }
        logger.warn("not found " + idReceta);
        return null;
    }

    @Override
    public Receta addReceta(String nombre, int numPaso, double premioDinero, int premioPuntos) {
        Receta r = new Receta(nombre,numPaso, premioDinero, premioPuntos);
        logger.info("new Receta " + r);
        this.recetas.add(r);
        logger.info("Se ha añadido una Receta");
        return r;
    }

    @Override
    public void deleteReceta(int idReceta) {
        Receta r = this.getReceta(idReceta);
        if (r==null){
            logger.warn("not found "+r);
        } else {
            logger.info(r+" deleted");
        }
        this.recetas.remove(r);
    }

    @Override
    public int getNumPaso(int idReceta) {
        Receta r = this.getReceta(idReceta);
        if (r==null) {
            logger.warn("not found "+r);
            return-1;
        }
        else{
            return r.getNumPaso();
        }
    }

    @Override
    public double getPremioDinero(int idReceta) {
        Receta r = this.getReceta(idReceta);
        if (r==null) {
            logger.warn("not found "+r);
            return-1;
        }
        else{
            return r.getPremioDinero();
        }
    }

    @Override
    public int getPremioPuntos(int idReceta) {
        Receta r = this.getReceta(idReceta);
        if (r==null) {
            logger.warn("not found "+r);
            return-1;
        }
        else{
            return r.getPremioPuntos();
        }
    }

    @Override
    public Receta putReceta (Receta receta) {
        Receta r = this.getReceta(receta.getId());
        if (r!=null){
            logger.info(r+" rebut!");
            r.setNombre(receta.getNombre());
            r.setId(receta.getId());
            r.setNumPaso(receta.getNumPaso());
            r.setPremioDinero(receta.getPremioDinero());
            r.setPremioPuntos(receta.getPremioPuntos());
            logger.info(r+" update");
        } else {
            logger.warn("not found "+r);
        }
        return r;
    }
}
