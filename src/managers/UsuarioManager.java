package managers;

import db.DBManager;
import entities.Usuario;

import java.io.IOException;
import java.util.*;

public class UsuarioManager {
    /**
     * obtiene todos los usuarios en base de datos
     * @return Map<String Id, Usuario>
     */
    public static Map<String, Usuario> obtenerUsuarios(){
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
     * obtiene usuario por Id
     * @param idUsuario
     * @return Usuario
     */
    public static Usuario obtenerUsuario(String idUsuario){
        Map<String, Usuario> usuarios = obtenerUsuarios();
        return usuarios.get(idUsuario);
    }

    public static void guardarUsuario(Usuario usuario) throws IOException {
        List<String[]> usuarios = new ArrayList<>();
        String[] arregloUsuario = {usuario.getIdUsuario(), usuario.getNombres(),
                usuario.getApellidos(), usuario.getDireccion(), usuario.getTelefono(), usuario.getCelular()};
        usuarios.add(arregloUsuario);
        DBManager.escribirArchivo(usuarios, "usuario", true);
    }

    public static void actualizarUsuario(String idUsuario, String direccion, String telefono, String celular) throws Exception {
        Map<String, Usuario> usuarios = obtenerUsuarios();
        List<String[]> listaUsuarios = new ArrayList<>();
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
        listaUsuarios.add(new String[]{ "ID","NOMBRES","APELLIDOS","DIRECCION","TELEFONO","CELULAR"});
        for(Usuario u: valoresMapUsuarios){
            listaUsuarios.add(u.usuarioArreglo());
        }
        DBManager.escribirArchivo(listaUsuarios, "usuario", false);
    }

    public static void eliminarUsuario( String idUsuario ) throws Exception {
        Map<String, Usuario> usuarios = obtenerUsuarios();
        Usuario usuario = usuarios.get(idUsuario);
        if( usuario == null) {
            throw new Exception("El usuario no está registrado");
        }
        List<String[]> listaUsuarios = new ArrayList<>();
        usuarios.remove(idUsuario);
        List<Usuario> valoresMapUsuarios = new ArrayList<>(usuarios.values());
        listaUsuarios.add(new String[]{ "ID","NOMBRES","APELLIDOS","DIRECCION","TELEFONO","CELULAR"});
        for(Usuario u: valoresMapUsuarios){
            listaUsuarios.add(u.usuarioArreglo());
        }
        DBManager.escribirArchivo(listaUsuarios, "usuario", false);
    }
}
