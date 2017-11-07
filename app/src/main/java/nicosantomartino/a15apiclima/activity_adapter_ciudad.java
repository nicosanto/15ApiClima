package nicosantomartino.a15apiclima;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class activity_adapter_ciudad extends BaseAdapter {

    private ArrayList<Ciudad> lista;

     public activity_adapter_ciudad() { }

    public activity_adapter_ciudad(ArrayList<Ciudad> lista ) {
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView;

        if(view == null){
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_adapter_ciudad,viewGroup,false);
        }else{
            convertView=view;
        }

        Ciudad item = (Ciudad) getItem(i);
        TextView txtNombre = convertView.findViewById(R.id.txtCiudad);
        txtNombre.setText(item.getNombre());
        return convertView;
    }
}
