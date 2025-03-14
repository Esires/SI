package FamiliaFinanzas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class DataBase {

    Connection c;

    Statement query;

    String user, password, databaseName;

    boolean connectat  = false;

    public DataBase( String user, String password, String databaseName) {
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
                   " FROM"+ nTaula+
                " ORDER BY"+ nColumna + " ASC";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int f=0;
            while(rs.next()){
                info[f] = rs.getString(0);
                f++;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return info;
    }

}
