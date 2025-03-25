package FamiliaFinanzas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class BaseDeDades {

    Connection c;

    Statement query;

    String user, password, databaseName;

    boolean connectat  = false;

    public BaseDeDades(String user, String password, String databaseName) {
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    public void connect(){
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName, user, password);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD! :) ");
            connectat = true;
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public String getInfo(String nColumna, String nTaula, String id, String nClau){
        try {
            String q = "SELECT " + nColumna +
                    " FROM " + nTaula +
                    " WHERE " +nClau+ " = '"+id+"' ";
            System.out.println(q);
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getString(nColumna);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return "NO HA FUNCIONAT";
    }

    public int getNumFilesTaula(String nomTaula){
        String q = "SELECT COUNT(*) AS num FROM "+ nomTaula;
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public String[] getInfoArray(String nTaula, String nColumna){
        int n = getNumFilesTaula(nTaula);
        String[] info = new String[n];
        String q = "SELECT "+ nColumna+
                   " FROM "+ nTaula+
                " ORDER BY "+ nColumna + " ASC";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int f=0;
            while(rs.next()){
                info[f] = rs.getString(1);
                f++;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return info;
    }

    public String [][] getInfoArray2DUsuario(){
        int nf = getNumFilesTaula("usuario");
        String[][] info = new String[nf][3];
        String q = " SELECT NOM, PASSWORD, ROL FROM usuario ORDER BY NOM ASC";
        System.out.println(q);
        try {
            ResultSet rs =  query.executeQuery(q);
                int f = 0;
                while(rs.next()) {
                    info[f][0]=rs.getString("NOM");
                    info[f][0]=rs.getString("PASSWORD");
                    info[f][0]=rs.getString("ROL");
                }
            }catch(Exception e){
                System.out.println(e);
            }
        return info;
    }

    public int getNumFilesMatchQuery(String q){
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    //Retorna el pare d'un motiu
    public String [] getInfoPareMotiu(){
        String qNF = "SELECT COUNT(*) AS num FROM motivo WHERE MOTIVO_PADRE IS NULL ";
        int nf = getNumFilesMatchQuery(qNF);
        String[] info =new String[nf];
        String q = " SELECT ID " +
                   " FROM motivo " +
                   " WHERE MOTIVO_PADRE IS NULL ";
        System.out.println(q);
        try {
            ResultSet rs = query.executeQuery(q);
            int n = 0;
            while (rs.next()){
                info [n] = rs.getString("MOTIVO_PADRE");
                n++;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return info;
    }

    //Retorna dades de 2 taules relacionades
    public String [][] getInfoGastoMotiuPare(){
        String qNF = "SELECT COUNT(*) AS num FROM gasto g, motivo m\n" +
                "WHERE g.MOTIVO_ID = m.MOTIVO_PADRE";
        int nf = getNumFilesMatchQuery(qNF);
        String[][] info = new String[nf][2];
        String q = "SELECT g.IMPORTE, m.MOTIVO_PADRE FROM gasto g, motivo m\n" +
                "WHERE g.MOTIVO_ID = m.MOTIVO_PADRE";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int n = 0;
            while(rs.next()){
                info[n][0]=rs.getString("g.IMPORTE");
                info[n][1]=rs.getString("m.MOTIVO_PADRE");
                n++;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return info;
    }

   public int getImporteUsuari(String n) {
       String q = "SELECT SUM(g.IMPORTE) AS total " +
               " FROM gasto g, gasto_has_usuario gu, usuario u " +
               " WHERE g.ID = gu.GASTO_ID AND gu.USUARIO_NOMBRE = u.NOMBRE AND u.NOMBRE = '"+n+"'";
       System.out.println(q);
       try{
           ResultSet rs = query.executeQuery(q);
           rs.next();
           return rs.getInt("total");
       }catch (Exception e){
           System.out.println(e);
       }
       return 0;
   }

   // Retorna true si el nom i el password son a la taula
    public boolean checkLogIn(String n, String p){
        String q = " SELECT COUNT(*) AS n FROM usuario " +
                " WHERE NOMBRE = '"+ n +"' AND PASSWORD='" + p+"' ";
        System.out.println(q);
        try {
            ResultSet rs = query.executeQuery(q);
            rs.next();
            int nF = rs.getInt("n");
            return nF==1;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //INSERT INTO 'usuario' ('nombre', 'password') VALUES ('Papa', '1975Papa1975')
    public void insertaUsuario(String n, String p, String r){
        String q = "INSERT INTO usuario (NOMBRE, PASSWORD, ROL) " +
                " VALUES ('"+n+"', '"+p+"', '"+r+"')";
        System.out.println(q);
        try {
            query.execute(q);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //eliminar usuari
    //"DELETE FROM usuario WHERE `usuario`.`NOMBRE` = 'Papa'"
    public void deleteUsuario(String n){
        String q = "DELETE FROM usuario WHERE NOMBRE = '"+n+"'";
        System.out.println(q);
        try {
            query.execute(q);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //UPDATE `usuario` SET `PASSWORD` = '1978Mama1978' WHERE `usuario`.`NOMBRE` = 'Mama'
    //Modifica les dades d'un usuari
    public void updatePassword(String nom, String nova){
        String q = " UPDATE usuario SET PASSWORD='"+nova+"' , "+
                " WHERE NOMBRE = '"+nom+"'";
        System.out.println(q);
        try {
            query.execute(q);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void insertaGasto(String id, int i, char c, String t, String m, String p){
        String q = "INSERT INTO gasto (ID, FECHA, IMPORTE, COMPARTIDO, TIPO_ID, MOTIVO_ID, PERIODICITAT) " +
                " VALUES ('"+id+"', '"+i+"', '"+c+"', '"+t+"', '"+m+"', '"+p+"') ";
        System.out.println(q);
        try {
            query.execute(q);
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
