package managers;

import db.DBManager;
import entities.Servicio;
import utils.DateUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioManager {
    public static Map<String, Servicio> obtenerServicios(){
        Map<String, Servicio> serviciosMap = new HashMap<>();
        try {
            List<String[]> serviciosArchivo = DBManager.leerArchivo("servicio");
            for(int i = 1; i < serviciosArchivo.size(); i++){
                serviciosMap.put(serviciosArchivo.get(i)[0], new Servicio(serviciosArchivo.get(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serviciosMap;
    }

    public static Servicio obtenerServicio(String idTaxi){
        Map<String, Servicio> taxis = obtenerServicios();
        return taxis.get(idTaxi);
    }

    public static void guardarServicio(Servicio servicio) throws Exception {
        if(UsuarioManager.obtenerUsuario(servicio.getUsuario().getIdUsuario().toString())== null){
            throw new Exception("El usuario no se encuentra registrado");
        }
        List<String[]> servicios = new ArrayList<>();
        String[] arregloServicio = {servicio.getIdServicio(),
                servicio.getUsuario().getIdUsuario(), servicio.getDireccionOrigen(), servicio.getDireccionDestino(),
                DateUtils.DateAStringConFormato(servicio.getServicioFechaHora(), "dd/MM/yyyy hh:mm:ss"),
                String.valueOf(servicio.getServicioDuracion()),
                String.valueOf(servicio.getServicioValor()),  servicio.getTaxi().getPlaca(), servicio.getEstado()
        };
        servicios.add(arregloServicio);
        DBManager.escribirArchivo(servicios, "servicio", true);
    }


}
