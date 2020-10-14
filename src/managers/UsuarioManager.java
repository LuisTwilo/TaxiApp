package managers;

import db.DBManager;
import entities.Usuario;

import java.io.IOException;
import java.util.*;

public class UsuarioManager {
    /**
     * obtiene todos los usuarios en base de datos basado en el Id
     * @return Map<String Id, Usuario>
     */
    public static Map<String, Usuario> obtenerUsuariosPorId(){
        Map<String, Usuario> usuariosMap = new HashMap<>();
        try {
            List<String[]> usuariosFile = DBManager.leerArchivo("usuario");
            for(int i = 1; i < usuariosFile.size(); i++){
                usuariosMap.put(usuariosFile.get(i)[0], new Usuario(usuariosFile.get(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuariosMap;
    }
    /**
     * obtiene todos los usuarios en base de datos basado en la cédula
     * @return Map<String Id, Usuario>
     */
    public static Map<String, Usuario> obtenerUsuariosPorcedula(){
        Map<String, Usuario> usuariosMap = new HashMap<>();
        try {
            List<String[]> usuariosFile = DBManager.leerArchivo("usuario");
            for(int i = 1; i < usuariosFile.size(); i++){
                usuariosMap.put(usuariosFile.get(i)[1], new Usuario(usuariosFile.get(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuariosMap;
    }

    /**
     * obtiene usuario por Id
     * @param idUsuario
     * @return Usuario
     */
    public static Usuario obtenerUsuarioPorId(String idUsuario){
        Map<String, Usuario> usuarios = obtenerUsuariosPorId();
        return usuarios.get(idUsuario);
    }
    /**
     * obtiene usuario por Id
     * @param cedula
     * @return Usuario
     */
    public static Usuario obtenerUsuarioPorcedula(String cedula){
        Map<String, Usuario> usuarios = obtenerUsuariosPorcedula();
        return usuarios.get(cedula);
    }

    public static void guardarUsuario(Usuario usuario) throws IOException {
        List<String[]> usuarios = new ArrayList<>();
        String[] arregloUsuario = {usuario.getIdUsuario(), usuario.getCedula(), usuario.getNombres(),
                usuario.getApellidos(), usuario.getDireccion(), usuario.getTelefono(), usuario.getCelular()};
        usuarios.add(arregloUsuario);
        DBManager.escribirArchivo(usuarios, "usuario", true);
    }

    public static void actualizarUsuario(String idUsuario, String cedula, String direccion, String telefono, String celular) throws Exception {
        List<String[]> listaUsuarios = new ArrayList<>();
        if(cedula != null){
            Map<String, Usuario> usuarios = obtenerUsuariosPorcedula();
            Usuario usuario = usuarios.get(cedula);
            if( usuario == null) {
                throw new Exception("El usuario no está registrado");
            }
            if(direccion != null){
                usuario.setDireccion(direccion);
            }
            if(telefono != null){
                usuario.setTelefono(telefono);
            }
            if(celular != null){
                usuario.setCelular(celular);
            }
            usuarios.replace(cedula, usuario);
            List<Usuario> valoresMapUsuarios = new ArrayList<>(usuarios.values());
            listaUsuarios.add(new String[]{ "ID", "CEDULA","NOMBRES","APELLIDOS","DIRECCION","TELEFONO","CELULAR"});
            for(Usuario u: valoresMapUsuarios){
                listaUsuarios.add(u.usuarioArreglo());
            }
        }else{
            Map<String, Usuario> usuarios = obtenerUsuariosPorId();
            Usuario usuario = usuarios.get(idUsuario);
            if( usuario == null) {
                throw new Exception("El usuario no está registrado");
            }
            if(direccion != null){
                usuario.setDireccion(direccion);
            }
            if(telefono != null){
                usuario.setTelefono(telefono);
            }
            if(celular != null){
                usuario.setCelular(celular);
            }
            usuarios.replace(idUsuario, usuario);
            List<Usuario> valoresMapUsuarios = new ArrayList<>(usuarios.values());
            listaUsuarios.add(new String[]{ "ID", "CEDULA","NOMBRES","APELLIDOS","DIRECCION","TELEFONO","CELULAR"});
            for(Usuario u: valoresMapUsuarios) {
                listaUsuarios.add(u.usuarioArreglo());
            }
        }
        DBManager.escribirArchivo(listaUsuarios, "usuario", false);
    }

    public static void eliminarUsuario(String cedula) throws Exception {
        Map<String, Usuario> usuarios = obtenerUsuariosPorcedula();
        Usuario usuario = usuarios.get(cedula);
        if( usuario == null) {
            throw new Exception("El usuario no está registrado");
        }
        List<String[]> listaUsuarios = new ArrayList<>();
        usuarios.remove(cedula);
        List<Usuario> valoresMapUsuarios = new ArrayList<>(usuarios.values());
        listaUsuarios.add(new String[]{ "ID","CEDULA" ,"NOMBRES","APELLIDOS","DIRECCION","TELEFONO","CELULAR"});
        for(Usuario u: valoresMapUsuarios){
            listaUsuarios.add(u.usuarioArreglo());
        }
        DBManager.escribirArchivo(listaUsuarios, "usuario", false);
    }
}
