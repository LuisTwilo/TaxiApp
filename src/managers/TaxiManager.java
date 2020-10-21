package managers;

import db.DBManager;
import entities.Taxi;
import utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxiManager {

    public static Map<String, Taxi> obtenerTaxis(){
        Map<String, Taxi> taxisMap = new HashMap<>();
        try {
            List<String[]> taxisArchivo = DBManager.leerArchivo("taxi");
            for(int i = 1; i < taxisArchivo.size(); i++){
                taxisMap.put(taxisArchivo.get(i)[0], new Taxi(taxisArchivo.get(i)));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return taxisMap;
    }

    public static Taxi obtenerTaxi(String taxiPLaca){
        Map<String, Taxi> taxis = obtenerTaxis();
        return taxis.get(taxiPLaca);
    }

    public static void guardarTaxi(Taxi taxi) throws Exception {
        if(obtenerTaxi(taxi.getPlaca()) != null) {
            throw new Exception("El taxi que intenta registrar ya está en el sistema");
        }
        List<String[]> taxis = new ArrayList<>();
        String[] arregloTaxi = {validarPlaca(taxi.getPlaca()), taxi.getMarca(),taxi.getModelo(),taxi.getCedulaConductor(),
                taxi.getPoliza(), taxi.getSoat(), DateUtils.DateAStringConFormato(taxi.getFechaVencimientoTecnoMecanica(), "dd/MM/yyyy")};
        taxis.add(arregloTaxi);
        DBManager.escribirArchivo(taxis, "taxi", true);
    }

    public static void eliminarTaxi(String taxiPLaca) throws Exception {
        Map<String, Taxi> taxis = obtenerTaxis();
        Taxi taxi = taxis.get(taxiPLaca);
        if( taxi == null) {
            throw new Exception("El taxi no está registrado");
        }
        List<String[]> listaTaxis = new ArrayList<>();
        taxis.remove(taxiPLaca);
        List<Taxi> valoresMapTaxis = new ArrayList<>(taxis.values());
        listaTaxis.add(new String[]{ "PLACA","MARCA","MODELO","CONDUCTOR","POLIZA","SOAT", "VENCIMIENTO_TM"});
        for(Taxi t: valoresMapTaxis){
            listaTaxis.add(t.taxiArreglo());
        }
        DBManager.escribirArchivo(listaTaxis, "taxi", false);
    }

    public static String validarPlaca(String placa) throws Exception {
        String placaFormateada;
        if(placa == null){
            throw new Exception("Placa no puede estar vacío");
        }else{
            placaFormateada = formatearPlaca(placa);
            if(!placaFormateada.matches("[A-Z]{3}[0-9]{3}")){
                throw new Exception("La placa no es válida");
            }
            if(obtenerTaxi(placaFormateada) != null){
                throw new Exception("La placa ya está registrada en el sistema");
            }
        }
        return placaFormateada;
    }

    public static String formatearPlaca(String placa){
        return placa.trim().replaceAll("[ .-]", "").toUpperCase();
    }

}
