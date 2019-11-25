

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author edgar espinosa ordoñes
 */
public class TiempoTrabajo extends Thread {

    static int horaspantalla = 00;
    static int minutospantalla = 00;
    static int segundospantalla = 00;
    public static final String direccion = "jdbc:mysql://localhost:3306/aft"; //la conexion al servidor local
    public static final String usuario = "root";
    public static final String contrasena = "";
    PreparedStatement psr;
    String jornadaactual = "";

    public static void RetrasoSegundo() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    public static Connection ConectarAlServidorRe() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(direccion, usuario, contrasena);

        } catch (Exception e) {
        }
        return con;
    }

    public boolean ActualizaJornada() {
        boolean horaguardada = false;
        jornadaactual = ("0" + horaspantalla + ":" + minutospantalla + ":" + segundospantalla);
        Connection con = null;
        try {

            con = ConectarAlServidorRe();

            psr = con.prepareStatement("UPDATE agentes SET trabajado= ? WHERE empleado = ? AND inicio= ?");
            psr.setString(1, jornadaactual);
            psr.setString(2, AFT.usr);
            psr.setString(3, AFT.TiempoInicio);

            int valido = psr.executeUpdate();
            if (valido > 0) {
                System.out.println("hora de entrada guardadas");
                horaguardada = true;
            } else {
                System.out.println("no se guardo hora de entrada reiniciar programa");
                horaguardada = false;
            }
            psr.close();
            con.close();
        } catch (Exception e) {
            System.out.println((e));
        }

        return horaguardada;
    }

    public void run() {

        for (horaspantalla = 0; horaspantalla < 24; horaspantalla++) {
            for (minutospantalla = 0; minutospantalla < 60; minutospantalla++) {
                for (segundospantalla = 0; segundospantalla < 60; segundospantalla++) {
                    System.out.println(horaspantalla + ":" + minutospantalla + ":" + segundospantalla);
                    if (segundospantalla % 2 == 0) {
                    boolean resultado = ActualizaJornada();
                    if (resultado == true) {
                        System.out.println("tiempo de jornada actualizado");
                    } else {
                        System.out.println("tiempo de jornada no actualizado");
                    }
                }
                    RetrasoSegundo();
                }
            }
        }

    }

}
