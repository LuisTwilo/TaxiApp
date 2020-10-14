package gui;

import entities.Usuario;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VerUsuariosPanel {
    private JTable tablaUsuarios;
    private JPanel rootPanel;
    private JScrollPane scrollPane;

    public VerUsuariosPanel(Map<String, Usuario> usuarios) {

        List<Usuario> valoresMapUsuarios = new ArrayList<>(usuarios.values());

        String columnas[] = { "ID","NOMBRES","APELLIDOS","DIRECCION","TELEFONO","CELULAR"};
        String[][] datos= new String[valoresMapUsuarios.size()][columnas.length];

        for(int i = 0; i < valoresMapUsuarios.size(); i++ ){
            datos[i] = valoresMapUsuarios.get(i).usuarioArreglo();
        }

        this.tablaUsuarios = new JTable(datos, columnas);
        this.scrollPane = new JScrollPane(this.tablaUsuarios);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void setRootPanel(JPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    public JTable getTablaUsuarios() {
        return tablaUsuarios;
    }

    public void setTablaUsuarios(JTable tablaUsuarios) {
        this.tablaUsuarios = tablaUsuarios;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
}
