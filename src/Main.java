import entities.Servicio;
import entities.Taxi;
import entities.Usuario;
import managers.ServicioManager;
import managers.TaxiManager;
import managers.UsuarioManager;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;


public class Main {
    public static void main(String[] args) throws Exception {
        /*String[] serString = {UUID.randomUUID().toString(),"861eb47e-241e-4106-9d88-d336e0776df5", "callejón diagon", "av siempre viva", "ABC123"};
        Servicio ser = new Servicio(serString);
        ServicioManager.guardarServicio(ser);*/
        //UsuarioManager.eliminarUsuario("9675e7b5-25e1-477a-923c-98b0b9937082");
        String[] taxStr = {"ABC123", "pollito", "1258", "123456789", "458", "125", "12/08/2021"};
        Taxi tax = new Taxi(taxStr);
        TaxiManager.guardarTaxi(tax);
        /*String[] user = {UUID.randomUUID().toString(),"oso josé", "londoño larrarte","el dorado", "1234567", "3001234569"};
        Usuario userObj = new Usuario(user);
        UsuarioManager.guardarUsuario(userObj);*/
        //UsuarioManager.actualizarUsuario("9675e7b5-25e1-477a-923c-98b0b9937082", null, "4152", null);
        //TaxiManager.eliminarTaxi("ABC123");
    }
}
