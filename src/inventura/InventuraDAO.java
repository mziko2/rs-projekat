package inventura;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InventuraDAO {
    private static InventuraDAO instance;
    private Connection conn;

    //Nazivi upita
    private PreparedStatement dajMjesto, dajNarudzbu, dajProizvod, obrisiMjesto, obrisiNarudzbu, obrisiProizvod,
    nadjiMjesto, nadjiNarudzbu, nadjiProizvod, dodajMjesto, dodajNarudzbu, dodajProizvod, odrediIdMjesta, odrediIdProizvoda,
    odrediIdNarudzbe, izmjeniMjesto, izmjeniProizvod, izmjeniNarudzbu, dajMjesta, dajNarudzbe, dajProizvode, dajKategorijuProizvoda;



    public static InventuraDAO getInstance(){
            if(instance==null) instance = new InventuraDAO();
            return  instance;
    }

    private  InventuraDAO(){
        //Konekcija baze
        try{
            conn=DriverManager.getConnection("jdbc:sqlite:C:/Users/ASUS/IdeaProjects/rs-projekat/src/database/inventura.db.sql");
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

            nadjiMjesto=conn.prepareStatement("Select * from mjesto where id=?");
            nadjiNarudzbu=conn.prepareStatement("select * from narudzba where id=?");
            nadjiProizvod=conn.prepareStatement("Select * from proizvod where id=?");

            dodajMjesto=conn.prepareStatement("insert into mjesto values(?,?,?,?)");
            dodajNarudzbu=conn.prepareStatement("insert into narudzba values(?,?,?,?,?,?,?)");
            dodajProizvod=conn.prepareStatement("insert into proizvod values(?,?,?,?,?,?)");

            odrediIdMjesta=conn.prepareStatement("select MAX(id)+1 from mjesto");
            odrediIdProizvoda=conn.prepareStatement("select max(id)+1 from proizvod");
            odrediIdNarudzbe=conn.prepareStatement("select max(id)+1 from narudzba");

            izmjeniMjesto=conn.prepareStatement("update mjesto set naziv=?, lokacija=?, opis=? where id=?");
            izmjeniNarudzbu=conn.prepareStatement("update narudzba set proizvod=?, vrsta=?, opis=?, datum=?, proizvod_id=?, mjesto_id=? where id=?");
            izmjeniProizvod=conn.prepareStatement("update proizvod set naziv=?, kategorija=?, datum=?, mjesto=?, mjesto_id=? where id=?");

            dajMjesta=conn.prepareStatement("select * from mjesto order by id");
            dajProizvode=conn.prepareStatement("select * from proizvod order by id");
            dajNarudzbu=conn.prepareStatement("select * from narudzba order by id");

            dajKategorijuProizvoda=conn.prepareStatement("select kategorija from proizvod");
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
            ulaz = new Scanner(new FileInputStream("inventura.db.sql"));
            if(ulaz!=null){
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
    public void obrisiMjesto(int id){
        try{
            nadjiMjesto.setInt(1,id);
            ResultSet rs = nadjiMjesto.executeQuery();
            if(!rs.next()) return;
            Mjesto mjesto = dajMjestoIzRs(rs);
            obrisiMjesto.setInt(1,mjesto.getId());
            obrisiMjesto.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void obrisiProizvod(int id){
        try{
            nadjiProizvod.setInt(1,id);
            ResultSet rs = nadjiProizvod.executeQuery();
            if(!rs.next()) return;
            Proizvod proizvod = dajProizvodIzRs(rs);
            obrisiProizvod.setInt(1,proizvod.getId());
            obrisiProizvod.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void obrisiNarudzbu(int id){
        try{
            nadjiNarudzbu.setInt(1,id);
            ResultSet rs = nadjiNarudzbu.executeQuery();
            if(!rs.next()) return;
            Narudzba narudzba = dajNarudzbuIzRs(rs);
            obrisiNarudzbu.setInt(1, narudzba.getId());
            obrisiNarudzbu.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void dodajMjesto(Mjesto mjesto){
        try{
           ResultSet rs = odrediIdMjesta.executeQuery();
           int id=1;
           if(rs.next()) id=rs.getInt(1);

           dodajMjesto.setInt(1,id);
           dodajMjesto.setString(2,mjesto.getNaziv());
           dodajMjesto.setString(3,mjesto.getLokacija());
           dodajMjesto.setString(4,mjesto.getOpis());
           dodajMjesto.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void dodajProizvod(Proizvod proizvod){
        try{
            ResultSet rs = odrediIdProizvoda.executeQuery();
            int id=1;
            if(rs.next()) id=rs.getInt(1);

            dodajProizvod.setInt(1,id);
            dodajProizvod.setString(2,proizvod.getNaziv());
            dodajProizvod.setString(3,proizvod.getKategorija());
            dodajProizvod.setString(4,proizvod.getDatum());
            dodajProizvod.setString(5,proizvod.getMjesto());
            dodajProizvod.setInt(6,proizvod.getMjesto_id());
            dodajProizvod.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void dodajNarudzbu(Narudzba narudzba){
        try{
            ResultSet rs = odrediIdNarudzbe.executeQuery();
            int id=1;
            if(rs.next()) id=rs.getInt(1);

            dodajNarudzbu.setInt(1,id);
            dodajNarudzbu.setString(2,narudzba.getProizvod());
            dodajNarudzbu.setString(3,narudzba.getVrsta());
            dodajNarudzbu.setString(4,narudzba.getOpis());
            dodajNarudzbu.setString(5,narudzba.getDatum());
            dodajNarudzbu.setInt(6,narudzba.getProizvod_id());
            dodajNarudzbu.setInt(7,narudzba.getMjesto_id());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void izmjeniMjesto(Mjesto mjesto){
        try{
            izmjeniMjesto.setString(1,mjesto.getNaziv());
            izmjeniMjesto.setString(2,mjesto.getLokacija());
            izmjeniMjesto.setString(3,mjesto.getOpis());
            izmjeniMjesto.setInt(4,mjesto.getId());
            izmjeniMjesto.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void izmjeniNarudzbu(Narudzba narudzba){
        try{
            izmjeniNarudzbu.setString(1,narudzba.getProizvod());
            izmjeniNarudzbu.setString(2,narudzba.getVrsta());
            izmjeniNarudzbu.setString(3,narudzba.getOpis());
            izmjeniNarudzbu.setString(4,narudzba.getDatum());
            izmjeniNarudzbu.setInt(5,narudzba.getProizvod_id());
            izmjeniNarudzbu.setInt(6,narudzba.getMjesto_id());
            izmjeniNarudzbu.setInt(7,narudzba.getId());
            izmjeniNarudzbu.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void izmjeniProizvod(Proizvod proizvod){
        try{
            izmjeniProizvod.setString(1,proizvod.getNaziv());
            izmjeniProizvod.setString(2,proizvod.getKategorija());
            izmjeniProizvod.setString(3,proizvod.getDatum());
            izmjeniProizvod.setString(4,proizvod.getMjesto());
            izmjeniProizvod.setInt(5,proizvod.getMjesto_id());
            izmjeniProizvod.setInt(6,proizvod.getId());
            izmjeniProizvod.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Mjesto> mjesta(){
        ArrayList<Mjesto> mjesta = new ArrayList<Mjesto>();
        try{
            ResultSet rs = dajMjesta.executeQuery();
            while(rs.next()){
                Mjesto mjesto = dajMjestoIzRs(rs);
                mjesta.add(mjesto);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return mjesta;
    }

    public ArrayList<Narudzba> narudzbe(){
        ArrayList<Narudzba> narudzbe = new ArrayList<Narudzba>();
        try{
            ResultSet rs = dajNarudzbu.executeQuery();
            while(rs.next()){
                Narudzba narudzba = dajNarudzbuIzRs(rs);
                narudzbe.add(narudzba);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return narudzbe;
    }

    public ArrayList<Proizvod> proizvodi(){
        ArrayList<Proizvod> proizvodi = new ArrayList<Proizvod>();
        try{
            ResultSet rs = dajProizvode.executeQuery();
            while(rs.next()){
                Proizvod proizvod = dajProizvodIzRs(rs);
                proizvodi.add(proizvod);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return proizvodi;
    }
    public int dajIdMjesta(String mjesto){
        try{
            nadjiMjesto.setString(1,mjesto);
            ResultSet rs = nadjiMjesto.executeQuery();
            return (rs.getInt(1));
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<String> dajKategorijeProizvoda(){
        ArrayList<String> rezultat = new ArrayList<String>();
        try{
            ResultSet rs = dajKategorijuProizvoda.executeQuery();
            while(rs.next()){
                rezultat.add(rs.getString(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return  rezultat;
    }



}
