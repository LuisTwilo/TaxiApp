package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date convertirStringADate(String s) throws ParseException {
        return  new SimpleDateFormat("dd/MM/yyyy").parse(s);
    }

    public static String DateAStringConFormato(Date fecha, String formato){
        if(fecha == null)return null;
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(fecha);
    }


}
