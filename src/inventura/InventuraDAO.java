package inventura;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class InventuraDAO {
    private static InventuraDAO instance;
    private Connection conn;

    //Nazivi upita
    private PreparedStatement dajMjesto, dajNarudzbu, dajProizvod, obrisiMjesto, obrisiNarudzbu, obrisiProizvod,
    nadjiMjesto, nadjiNarudzbu, nadjiProizvod;



    public static InventuraDAO getInstance(){
            if(instance==null) instance = new InventuraDAO();
            return  instance;
    }

    private  InventuraDAO(){
        //Konekcija baze
        try{
            conn=DriverManager.getConnection("jdbc:sqlite:C:/Users/ASUS/IdeaProjects/rs-projekat/src/database/inventura");
        }catch(SQLException e){
            e.printStackTrace();
        }
        //Test baze
        try{
            dajNarudzbu=conn.prepareStatement("SELECT * FROM narudzba WHERE id=?");
        }catch(SQLException e){
            regenerisiBazu();
            try{
                dajNarudzbu=conn.prepareStatement("SELECT * FROM narudzba WHERE id=?");
            }catch(SQLException e1){
                e1.printStackTrace();
            }
        }
        //Upiti za bazu
        try{
            dajMjesto=conn.prepareStatement("Select * from mjesto where id=?");
            dajProizvod=conn.prepareStatement("Select * from proizvod where id=?");
            obrisiMjesto=conn.prepareStatement("DELETE from mjesto where id=?");
            obrisiNarudzbu=conn.prepareStatement("Delete from narudzba where id=?");
            obrisiProizvod=conn.prepareStatement("Delete from proizvod where id=?");
            nadjiMjesto=conn.prepareStatement("Select * from mjesto where naziv=?");
            //nadjiNarudzbu=conn.prepareStatement("select * from narudzba where opis=?");
            nadjiProizvod=conn.prepareStatement("Select * from proizvod where naziv=?");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void removeInstance(){
        if(instance==null) return;
        instance.close();
        instance=null;
    }

    public void close(){
        try{
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }



    private void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("inventura.db"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if ( sqlUpit.charAt( sqlUpit.length()-1 ) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void vratiBazuNaDefault() throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM mjesto");
        stmt.executeUpdate("DELETE FROM narudzba");
        stmt.executeUpdate("Delete from proizvod");

        regenerisiBazu();
    }
    private Narudzba dajNarudzbuIzRs(ResultSet rs) throws  SQLException{
        return new Narudzba(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
    }
    public Narudzba dajNarudzbu(int id){
        try{
            dajNarudzbu.setInt(1,id);
            ResultSet rs = dajNarudzbu.executeQuery();
            if(!rs.next()) return null;
            return dajNarudzbuIzRs(rs);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    private Mjesto dajMjestoIzRs(ResultSet rs) throws SQLException {
        return new Mjesto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
    }
    public Mjesto dajMjesto(int id){
        try{
            dajMjesto.setInt(1,id);
            ResultSet rs = dajMjesto.executeQuery();
            if(!rs.next()) return null;
            return dajMjestoIzRs(rs);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    private Proizvod dajProizvodIzRs(ResultSet rs) throws SQLException{
        return new Proizvod(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
    }
    public Proizvod dajProizvod(int id){
        try{
            dajProizvod.setInt(1,id);
            ResultSet rs=dajProizvod.executeQuery();
            if(!rs.next()) return null;
            return dajProizvodIzRs(rs);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public void obrisiMjesto(String nazivMjesta){
        try{
            nadjiMjesto.setString(1,nazivMjesta);
            ResultSet rs = nadjiMjesto.executeQuery();
            if(!rs.next()) return;
            Mjesto mjesto = dajMjestoIzRs(rs);
            obrisiMjesto.setInt(1,mjesto.getId());
            obrisiMjesto.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void obrisiProizvod(String nazivProizvoda){
        try{
            nadjiProizvod.setString(1,nazivProizvoda);
            ResultSet rs = nadjiProizvod.executeQuery();
            if(!rs.next()) return;
            Proizvod proizvod = dajProizvodIzRs(rs);
            obrisiProizvod.setInt(1,proizvod.getId());
            obrisiProizvod.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }





}
