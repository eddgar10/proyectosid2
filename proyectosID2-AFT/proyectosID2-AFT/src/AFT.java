/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edgar espinosa ordoñes
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.*;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;

public class AFT extends javax.swing.JFrame {

    public static final String URL = "jdbc:mysql://localhost:3306/aft"; //la conexion al servidor local
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    PreparedStatement ps;
    static String usr;
    static String psw;
    static String TiempoInicio;
    static String TiempoSalida;
    static String jornadatotal;
    static String idproyecto;
    static String fecha;
    TiempoTrabajo t = new TiempoTrabajo();

    public AFT() {
        initComponents();
    }
//METODO CONEXION A BD

    public static Connection ConectarAlServidor() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
        }
        return con;
    }
//METODO PARA GENERAR LA FECHA ACTUAL

    public String Fecha() {
        Date fechaActual = new Date();
        Date date = Calendar.getInstance().getTime();
        //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(date);
        System.out.println(strDate);
        return strDate;
    }

//METODO PARA GENERAR LA HORA ACTUAL
    public String Hora() {
        Date fechaActual = new Date();
        Date date = Calendar.getInstance().getTime();
        //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String strDate = dateFormat.format(date);
        System.out.println(strDate);
        return strDate;
    }

//METODO PARA CONSULTAR EXISTENCIA DE EMPLEADO
    public boolean ConsultaExistencia(String usuario, String contr, String idproy) {
        //CONSULTA DE USUARIO EXISTENTE EN LA BASE COMO NUMPERO DE EMPLEADO
        boolean existencia = false;
        Connection con = null;
        ResultSet rs = null;
        try {
            con = ConectarAlServidor();
            //String consulta = "SELECT * FROM usuarios WHERE proyecto = ? AND usr= ? AND pswd= ?";
            ps = con.prepareStatement("SELECT * FROM usuarios WHERE usr= ? AND pswd= ? AND proyecto=?");
            ps.setString(1, usuario);
            ps.setString(2, contr);
            ps.setString(3, idproy);
            rs = ps.executeQuery();
            boolean respuesta = rs.next();

            if (respuesta) {
                System.out.println("conectado");
                existencia = true;
            } else {
                existencia = false;
            }

            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println((e));
        }
        return existencia;
    }

//METODO PARA GUARDAR HORA DE ENTRADA
    public boolean GuardarEntrada(String usuario, String idproy) {
        boolean horaguardada = false;
        Connection con = null;
        TiempoInicio = Hora();
        fecha = Fecha();
        try {
            con = ConectarAlServidor();
            //ps = con.prepareStatement("INSERT INTO conexion (proyecto,usr,fecha,tinicio,tfin,trabajado) VALUES (?,?,?,?,?,?);");
            ps = con.prepareStatement("INSERT INTO agentes (proyecto,empleado,fecha,inicio,fin,trabajado) VALUES (?,?,?,?,?,?);");
            ps.setString(1, idproyecto);
            ps.setString(2, usr);
            ps.setString(3, fecha);
            ps.setString(4, TiempoInicio);
            ps.setString(5, "");
            ps.setString(6, "");
            

            int valido = ps.executeUpdate();

            if (valido > 0) {
                System.out.println("hora de entrada guardadas");
                horaguardada = true;
            } else {
                System.out.println("no se guardo hora de entrada reiniciar programa");
                horaguardada = false;
            }

            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println(("ERROR EN METODO GUARDARENTRADA" + (e)));
        }
        return horaguardada;
    }

//METODO PARA GUARDAR HORA DE SALIDA
    public boolean GuardarSalida(String usuario) {
        boolean horaguardada = false;
        Connection con = null;
        TiempoSalida = Hora();
        try {
            con = ConectarAlServidor();
            ps = con.prepareStatement("UPDATE agentes SET fin = ? WHERE empleado = ? AND proyecto= ? AND inicio= ?");
            ps.setString(1, TiempoSalida);
            ps.setString(2, usr);
            ps.setString(3, idproyecto);
            ps.setString(4, TiempoInicio);

            int valido = ps.executeUpdate();

            if (valido > 0) {
                System.out.println("hora de entrada guardadas");
                horaguardada = true;
            } else {
                System.out.println("no se guardo hora de entrada reiniciar programa");
                horaguardada = false;
            }

            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println((e));
        }
        return horaguardada;
    }

//METODO PARA GUARDAR JORNADA TRABAJADA
    public boolean GuardaJornada(String jornadatotal) {

        boolean jornadaguardada = false;
        Connection con = null;
        try {

            con = ConectarAlServidor();

            ps = con.prepareStatement("UPDATE agentes SET trabajado= ? WHERE empleado = ? AND inicio= ? AND fin= ? AND proyecto= ?");
            ps.setString(1, jornadatotal);
            ps.setString(2, usr);
            ps.setString(3, TiempoInicio);
            ps.setString(4, TiempoSalida);
            ps.setString(5, idproyecto);

            int valido = ps.executeUpdate();
            if (valido > 0) {
                System.out.println("Jornada guardada");
                jornadaguardada = true;
            } else {
                System.out.println("no se guardo el tiempo trabajado");
                jornadaguardada = false;
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println((e));
        }

        return jornadaguardada;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        iniciarsesion = new javax.swing.JButton();
        cerrarsesion = new javax.swing.JButton();
        contra = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        proyecto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("AFT-Proyectos ID+1");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Arial Unicode MS", 1, 12)); // NOI18N
        setResizable(false);

        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });

        jLabel1.setText("USUARIO");

        jLabel2.setText("CONTRASEÑA");

        iniciarsesion.setText("Iniciar Sesión");
        iniciarsesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarsesionActionPerformed(evt);
            }
        });

        cerrarsesion.setText("Cerrar Sesión");
        cerrarsesion.setEnabled(false);
        cerrarsesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarsesionActionPerformed(evt);
            }
        });

        contra.setText("contra");
        contra.setToolTipText("");
        contra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contraActionPerformed(evt);
            }
        });

        jLabel3.setText("AFT - PROYECTOS I+D1");

        jLabel5.setText("PROYECTO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(31, 31, 31))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(28, 28, 28)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(proyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cerrarsesion)
                                .addComponent(contra, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(iniciarsesion))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proyecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addComponent(cerrarsesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iniciarsesion)
                .addGap(49, 49, 49))
        );

        getAccessibleContext().setAccessibleName("AFT-Proyectos I+D 1");

        pack();
    }// </editor-fold>//GEN-END:initComponents

//BOTON INICIAR SESION
    private void iniciarsesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarsesionActionPerformed

        //LECTURA DE USUARIO Y CONTRASEÑA
        usr = (usuario.getText().toLowerCase()); //cadena a minusculas
        psw = (contra.getText().toLowerCase()); //cadena a minusculas
        idproyecto = (proyecto.getText().toUpperCase()); //cadena a mayusculas

        //CONSULTAR QUE EL USUARIO EXISTE EN LA BASE DE DATOS
        boolean existe = ConsultaExistencia(usr, psw, idproyecto);
        if (existe == true) {
            System.out.println("usuario existente");
            //GUARDAR LA HORA DE ENTRADA DEL USUARIO EN BD 
            boolean guardaentrada = GuardarEntrada(usr, idproyecto);
            if (guardaentrada == true) {
                JOptionPane.showMessageDialog(null, "Inicio de sesion correcto: " + TiempoInicio);
                //INICIO DE CONTEO DE CONEXION
                t.start();
                cerrarsesion.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "error en entrada vuelve a ejecutar el AFT");
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(null, "numero de empleado inexistente Verifica tus datos");
            dispose();

        }
        iniciarsesion.setEnabled(false);
        proyecto.setEditable(false);
        proyecto.setText(idproyecto);
        usuario.setEditable(false);
        usuario.setText(usr);
        contra.setEditable(false);
        jLabel3.setText("HORA DE INICIO: " + TiempoInicio);

        cerrarsesion.enable(true);
    }//GEN-LAST:event_iniciarsesionActionPerformed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuarioActionPerformed
//BOTON CERRAR SESION
    private void cerrarsesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarsesionActionPerformed
        boolean salida = GuardarSalida(usr);
        if (salida) {
            JOptionPane.showMessageDialog(null, "Registro de salida guardado" + TiempoSalida);

        } else {
            JOptionPane.showMessageDialog(null, "Error en registro de salida, reportar con administracion");
        }
        //FIN DE CONTEO DE CONEXION 
        jornadatotal = ("0" + t.horaspantalla + ":" + t.minutospantalla + ":" + t.segundospantalla);
        GuardaJornada(jornadatotal);
        t.stop();
        dispose();
    }//GEN-LAST:event_cerrarsesionActionPerformed

    private void contraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contraActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AFT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AFT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AFT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AFT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AFT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cerrarsesion;
    private javax.swing.JPasswordField contra;
    private javax.swing.JButton iniciarsesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField proyecto;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
