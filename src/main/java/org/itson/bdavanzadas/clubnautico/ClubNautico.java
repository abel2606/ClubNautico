/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.itson.bdavanzadas.clubnautico;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.bdavanzadas.clubnauticodominio.Socio;
import org.itson.bdavanzadas.clubnauticopersitencia.conexion.Conexion;
import org.itson.bdavanzadas.clubnauticopersitencia.conexion.IConexion;
import org.itson.bdavanzadas.clubnauticopersitencia.daos.ISociosDAO;
import org.itson.bdavanzadas.clubnauticopersitencia.daos.SociosDAO;
import org.itson.bdavanzadas.clubnauticopersitencia.dtos.SocioNuevoDTO;
import org.itson.bdavanzadas.clubnauticopersitencia.excepciones.PersistenciaException;

/**
 *
 * @author Laboratorios
 */
public class ClubNautico {

    static final Logger logger = Logger.getLogger(ClubNautico.class.getName());

    public static void main(String[] args) throws PersistenciaException {
        String cadenaConexion = "jdbc:mysql://localhost/club_nautico_10am";
        String usuario = "root";
        String contrasenia = "Abel123";

        IConexion conexion = new Conexion(cadenaConexion, usuario, contrasenia);
        ISociosDAO sociosDao = new SociosDAO(conexion);
        
        
        SociosForm sociosFomr = new SociosForm(sociosDao);
        sociosFomr.setVisible(true);
//        SocioNuevoDTO socioNuevo = new SocioNuevoDTO();
//        socioNuevo.setNombre("Juanito");
//        socioNuevo.setCorreo("juanito@gmail.com");
//        socioNuevo.setTelefono("6473838384");
//                
//      
//        try {
//            Socio socioAgregado = sociosDao.agregar(socioNuevo);
//            logger.log(Level.INFO,socioAgregado.toString());
//        } catch (PersistenciaException e) {
//            logger.log(Level.SEVERE, null, e);
//        }


        try {     
            List<Socio> listaSocios = sociosDao.consultar();
            listaSocios.forEach(socio -> System.out.println(socio));
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE,null,ex);
        }

    }
}
