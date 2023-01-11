package edu.upc.dsa.proyecto;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import edu.upc.dsa.proyecto.api.Client;
import edu.upc.dsa.proyecto.api.CookWithMeAPI;
import edu.upc.dsa.proyecto.models.FAQs;

public class MyAdapterFaqs extends RecyclerView.Adapter<MyAdapterFaqs.ViewHolder> {

    private List<FAQs> listaFAQs;
    public FAQs faqs;
    CookWithMeAPI gitHub;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public class ViewHolder extends RecyclerView.ViewHolder { // inner class , se encarga de guardar las referencias
        // each data item is just a string in this case
        public TextView TextViewPregunta, TextViewRespuesta;
        public View layout;

        public ViewHolder(View v) { //le pasamos la vista reciclada y nos la guardamos
            super(v);
            layout = v;
            TextViewPregunta = (TextView) v.findViewById(R.id.nombreObjeto);
            TextViewRespuesta = (TextView) v.findViewById(R.id.precioObjeto);;
            gitHub= Client.getClient().create(CookWithMeAPI.class);

        }
    }
    public MyAdapterFaqs() {
        listaFAQs = new ArrayList<>();
    }

    public void setData(List<FAQs> myDataset) { //muestro los contributors que me llegan
        listaFAQs = myDataset;
        notifyDataSetChanged(); //avisamos al android que algo ha cambiado
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapterFaqs.ViewHolder onCreateViewHolder(ViewGroup parent, //onCreate al hacer scroll
                                                               int viewType) {
        // create a new view, convierte xml en layout
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout_faqs, parent, false); //pasamos el nombre del layout y lo colgamos en el parent

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v); //creamos un viewholder pasandole la vista
        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        faqs = listaFAQs.get(position); //me guardo la posición
        //recorrido para ver si está comprado

        final String pregunta = faqs.getPregunta(); //recupero el login del contributor
        holder.TextViewPregunta.setText(pregunta);

        final String respuesta = faqs.getRespuesta(); //recupero el login del contributor
        holder.TextViewRespuesta.setText(respuesta);

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listaFAQs.size();
    }

}