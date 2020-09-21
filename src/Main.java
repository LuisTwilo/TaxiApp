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
import java.util.UUID;


public class Main {
    public static void main(String[] args) throws Exception {
        String[] serString = {UUID.randomUUID().toString(),"423c9181-d422-4900-b534-06d7c2cec93f", "callejón diagon", "av siempre viva", DateUtils.DateAStringConFormato(new Date(), "dd/MM/yyyy HH:mm:ss"),"","", "ABC123"};
        Servicio ser = new Servicio(serString);
        ServicioManager.guardarServicio(ser);
        ServicioManager.actualizarServicio("027cd67e-1500-4cd5-9ea3-c243b5d6dda3", "Cumplido");
        //UsuarioManager.eliminarUsuario("9675e7b5-25e1-477a-923c-98b0b9937082");
        /*String[] taxStr = {"ABC123", "pollito", "1258", "123456789", "458", "125", "12/08/2021"};
        Taxi tax = new Taxi(taxStr);
        TaxiManager.guardarTaxi(tax);*/
        /*String[] user = {UUID.randomUUID().toString(),"oso josé", "londoño larrarte","el dorado", "1234567", "3001234569"};
        Usuario userObj = new Usuario(user);
        UsuarioManager.guardarUsuario(userObj);*/
        //UsuarioManager.actualizarUsuario("9675e7b5-25e1-477a-923c-98b0b9937082", null, "4152", null);
        //TaxiManager.eliminarTaxi("ABC123");
    }
}
