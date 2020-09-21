package db;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DBManager {
    private static String ruta = "C:\\Users\\luis\\Documents\\TaxiApp\\resources";

    public static List<String[]> leerArchivo(String archivo) throws IOException {
        String rutaArchivo = ruta +"\\"+archivo+".csv";
        File archivoCSV = new File(rutaArchivo);
        String row;
        List<String[]> data = new ArrayList<>();
        if(archivoCSV.isFile()){
            BufferedReader csvReader = new BufferedReader(new FileReader(rutaArchivo));
            while ((row = csvReader.readLine())!= null){
                data.add(row.split(","));
            }
        }
        return data;
    }

    public static void escribirArchivo(List<String[]> datos, String archivo, boolean actualizar) throws IOException {
        String rutaArchivo = ruta +"\\"+archivo+".csv";
        FileWriter archivoDB = new FileWriter(rutaArchivo, actualizar);
        try (PrintWriter pw = new PrintWriter(archivoDB)) {
            datos.stream()
                    .map( d -> convertirACSV(d))
                    .forEach(pw::println);
        }
    }



    /**
     * convierte la información a formato csv
     * @param datos
     * @return
     */
    private static String convertirACSV(String[] datos) {
        return Stream.of(datos)
                .map(d -> formatearCaracteresEspeciales(d))
                .collect(Collectors.joining(","));
    }

    /**
     * quita los carateres especiales
     * @param datos
     * @return String - String sin carateres especiales
     */
    private static String formatearCaracteresEspeciales(String datos) {
        String resultado = datos.replaceAll("\\R", " ");
        if (datos.contains(",") || datos.contains("\"") || datos.contains("'")) {
            datos = datos.replace("\"", "\"\"");
            resultado = "\"" + datos + "\"";
        }
        return resultado;

    }
}
