package managers;

import db.DBManager;
import entities.Servicio;
import entities.Taxi;
import entities.Usuario;
import utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServicioManager {
    public static Map<String, Servicio> obtenerServicios(){
        Map<String, Servicio> serviciosMap = new HashMap<>();
        try {
            List<String[]> serviciosArchivo = DBManager.leerArchivo("servicio");
            for(int i = 1; i < serviciosArchivo.size(); i++){
                serviciosMap.put(serviciosArchivo.get(i)[0], new Servicio(serviciosArchivo.get(i)));
            }
        } catch (IOException | ParseException e) {
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

    public static void actualizarServicio(String idServicio, String estado) throws IOException {
        Map<String, Servicio> serviciosMap = obtenerServicios();
        List<String[]> listaServicios = new ArrayList<>();
        Servicio servicio = serviciosMap.get(idServicio);
        if(estado.equals(Servicio.estadoServicio.Cancelado.name())){
            servicio.setEstado(estado);
        }else{
            servicio.setEstado(estado);
            Long duracion = TimeUnit.MINUTES.convert(new Date().getTime() - servicio.getServicioFechaHora().getTime() , TimeUnit.MILLISECONDS);
            servicio.setServicioDuracion(duracion);
            servicio.setServicioValor((double) (1000*duracion));
        }
        serviciosMap.replace(idServicio, servicio);
        List<Servicio> servicios = new ArrayList<>(serviciosMap.values());
        listaServicios.add(new String[]{ "ID","ID_USUARIO","ORIGEN","DESTINO","FECHA_SERVICIO","DURACION","VALOR","TAXI","ESTADO"});
        for(Servicio s: servicios){
            listaServicios.add(s.servicioArreglo());
        }
        DBManager.escribirArchivo(listaServicios, "servicio", false);

    }

    public static List<Taxi> obtenerTaxisDisponibles(){
        List<Taxi> taxis = (List<Taxi>) TaxiManager.obtenerTaxis().values();
        List<Taxi>taxisConTMVigente = taxis.stream()
                .filter(taxi -> taxi.getFechaVencimientoTecnoMecanica().compareTo(new Date()) > 0)
                .collect(Collectors.toList());

        return taxisConTMVigente.stream()
                .filter(taxi -> !taxiEnOperacion(taxi))
                .collect(Collectors.toList());
    }

    private static boolean taxiEnOperacion(Taxi taxi) {
        boolean result = false;
        List<Servicio> servicios = (List<Servicio>) obtenerServicios().values();
        List<Servicio> serviciosPendientes = servicios.stream()
                .filter((servicio)-> servicio.getEstado().equals("Pendiente"))
                .collect(Collectors.toList());

        for(Servicio s : serviciosPendientes){
            if(s.getTaxi().getPlaca().equals(taxi.getPlaca())){
                result = true;
            }
        }
        return result;
    }


}
