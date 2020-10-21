package managers;

import db.DBManager;
import entities.Servicio;
import entities.Taxi;
import utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

    public static Map<String, List<Servicio>> obtenerServiciosPorUsuario(){
        Map<String, List<Servicio>> serviciosMap = new HashMap<>();
        List<Servicio> serviciosList = new ArrayList<>();
        try {
            List<String[]> serviciosArchivo = DBManager.leerArchivo("servicio");
            for(int i = 1; i < serviciosArchivo.size(); i++){
                Servicio servicio = new Servicio(serviciosArchivo.get(i));
                serviciosList.add(servicio);
                serviciosMap.put(serviciosArchivo.get(i)[1], serviciosList);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return serviciosMap;
    }

    public static List<Servicio> obtenerServiciosVigenteUsuario(String idUsuario){

        List<Servicio> servicios = obtenerServiciosPorUsuario().get(idUsuario);
        List<Servicio> serviciosPendientes = new ArrayList<>();
        serviciosPendientes = servicios.stream()
               .filter(s -> s.getEstado().equals(Servicio.estadoServicio.Pendiente.toString()))
               .collect(Collectors.toList());
        return serviciosPendientes;
    }

    public static Servicio obtenerServicio(String idTaxi){
        Map<String, Servicio> taxis = obtenerServicios();
        return taxis.get(idTaxi);
    }

    public static void guardarServicio(Servicio servicio) throws Exception {
        if(UsuarioManager.obtenerUsuarioPorId(servicio.getUsuario().getIdUsuario().toString())== null){
            throw new Exception("El usuario no se encuentra registrado");
        }
        List<String[]> servicios = new ArrayList<>();
        String[] arregloServicio = {servicio.getIdServicio(),
                servicio.getUsuario().getIdUsuario(), servicio.getDireccionOrigen(), servicio.getDireccionDestino(),
                DateUtils.DateAStringConFormato(servicio.getServicioFechaHora(), "dd/MM/yyyy HH:mm:ss"),
                String.valueOf(servicio.getServicioDuracion()),
                String.valueOf(servicio.getServicioValor()),  servicio.getTaxi().getPlaca(), Servicio.estadoServicio.Pendiente.name()
        };
        servicios.add(arregloServicio);
        DBManager.escribirArchivo(servicios, "servicio", true);
    }

    public static Servicio actualizarServicio(String idServicio, String estado) throws IOException {
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
        return servicio;

    }

    public static List<Taxi> obtenerTaxisDisponibles(){
        List<Taxi> taxis = new ArrayList<>(TaxiManager.obtenerTaxis().values());
        List<Taxi>taxisConTMVigente = taxis.stream()
                .filter(taxi -> taxi.getFechaVencimientoTecnoMecanica().compareTo(new Date()) > 0)
                .collect(Collectors.toList());

        return taxisConTMVigente.stream()
                .filter(taxi -> !taxiEnOperacion(taxi.getPlaca()))
                .collect(Collectors.toList());
    }

    public static boolean taxiEnOperacion(String placaTaxi) {
        boolean result = false;
        List<Servicio> servicios = new ArrayList<>(obtenerServicios().values());
        List<Servicio> serviciosPendientes = servicios.stream()
                .filter((servicio)-> servicio.getEstado().equals(Servicio.estadoServicio.Pendiente.toString()))
                .collect(Collectors.toList());

        for(Servicio s : serviciosPendientes){
            if(s.getTaxi().getPlaca().equals(placaTaxi)){
                result = true;
            }
        }
        return result;
    }


}
