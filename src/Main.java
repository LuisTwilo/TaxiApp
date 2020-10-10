import entities.Servicio;
import entities.Taxi;
import entities.Usuario;
import managers.ServicioManager;
import managers.TaxiManager;
import managers.UsuarioManager;
import utils.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static managers.ServicioManager.obtenerTaxisDisponibles;


public class Main {
    public static void main(String[] args) throws Exception {
        /*String[] serString = {UUID.randomUUID().toString(),"423c9181-d422-4900-b534-06d7c2cec93f", "callejón diagon", "av siempre viva", DateUtils.DateAStringConFormato(new Date(), "dd/MM/yyyy HH:mm:ss"),"","", "ABC123"};
        Servicio ser = new Servicio(serString);
        ServicioManager.guardarServicio(ser);*/
        //ServicioManager.actualizarServicio("763e89b0-4a13-45cc-94b4-785c2725d42e", "Cumplido");
        //UsuarioManager.eliminarUsuario("9675e7b5-25e1-477a-923c-98b0b9937082");
        String[] taxStr = {"Des123", "kiwi", "2008", "124", "55896", "1586", "11/08/2020"};
        Taxi tax = new Taxi(taxStr);
        TaxiManager.guardarTaxi(tax);
        /*String[] user = {UUID.randomUUID().toString(),"oso josé", "londoño larrarte","el dorado", "1234567", "3001234569"};
        Usuario userObj = new Usuario(user);
        UsuarioManager.guardarUsuario(userObj);*/
        //UsuarioManager.actualizarUsuario("9675e7b5-25e1-477a-923c-98b0b9937082", null, "4152", null);
        //TaxiManager.eliminarTaxi("ABC123");
        //List<Taxi> disponibles = obtenerTaxisDisponibles();
        //for(Taxi t : disponibles){
        //    System.out.println(t.getPlaca());
        //}
    }
}
