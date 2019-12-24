
package derstakip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ali Mert
 */
public class Ders1 {  
    
    private Connection con;
    String host = "jdbc:derby://localhost:1527/JavaDB";
    String uName = "alimert";
    String uPass= "....";

   public Ders1(){
    
   try{

        con = DriverManager.getConnection( host, uName, uPass );

      /*  Statement stmt = con.createStatement();
        String SQL1 = "SELECT * FROM ALIMERT.MAT";
        String SQL2 = "SELECT * FROM ALIMERT.FIZIK";
        String SQL3 = "SELECT * FROM ALIMERT.TURKCE";
        String SQL4 = "SELECT * FROM ALIMERT.TARIH";
      
        ResultSet rs = stmt.executeQuery( SQL1 );
        ResultSet rs = stmt.executeQuery( SQL2 );
        ResultSet rs = stmt.executeQuery( SQL3 );
        ResultSet rs = stmt.executeQuery( SQL4 );

        while(rs.next( )){
        String TC = rs.getString("TC");
        String isim = rs.getString("ISIM");
        String dogum_tarihi = rs.getString("DOGUM_TARIHI");
        String cinsiyet = rs.getString("CINSIYET");
        String hastalik = rs.getString("HASTALIK");
        String durum = rs.getString("DURUM");

        System.out.println( TC + " " + isim + " " + dogum_tarihi + " " + cinsiyet + " "+ hastalik + " " + durum);
        }*/
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
   }
    

public void Konu_Ekle(String dersKonusuEkleDersAdi, String dersKonusuEkleDersKonusu) throws SQLException{
        //System.out.println(dersKonusuEkleDersAdi);
       if (dersKonusuEkleDersAdi.equals("MATEMATIK") ) {
           Statement stmt = con.createStatement();
       String SQL = String.format("INSERT INTO ALIMERT.MAT VALUES( '%s')", dersKonusuEkleDersKonusu);
       stmt.executeUpdate( SQL );
       }
       else if (dersKonusuEkleDersAdi.equals("FIZIK") ) {
           Statement stmt = con.createStatement();
       String SQL = String.format("INSERT INTO ALIMERT.FIZIK VALUES( '%s')", dersKonusuEkleDersKonusu);
       stmt.executeUpdate( SQL );
       }
       else if (dersKonusuEkleDersAdi.equals("TURKCE")) {
           Statement stmt = con.createStatement();
       String SQL = String.format("INSERT INTO ALIMERT.TURKCE VALUES( '%s')", dersKonusuEkleDersKonusu);
       stmt.executeUpdate( SQL );
       }
       else if (dersKonusuEkleDersAdi.equals("TARIH") ) {
           Statement stmt = con.createStatement();
       String SQL = String.format("INSERT INTO ALIMERT.TARIH VALUES( '%s')", dersKonusuEkleDersKonusu);
       stmt.executeUpdate( SQL );
       }
}
public String[] Konu_Goster() throws SQLException{
    
        int i=0;
        int konu_sayisi = konu_adet_al();
        String[] kayitli_konu = new String[konu_sayisi];
        Statement stmt = con.createStatement();
        String SQL = "SELECT FIZIKKONU FROM ALIMERT.FIZIK";
        ResultSet rs = stmt.executeQuery( SQL );
        while(rs.next()){
            if(i != konu_sayisi){
                kayitli_konu[i] = rs.getString(1);
                i++;
            }
            else
                break;
        }
        return kayitli_konu;
    }    
public int konu_adet_al() throws SQLException{
        int kayitli_konu_sayisi=0;
        Statement stmt = con.createStatement();
        String SQL = "SELECT COUNT(*) FROM ALIMERT.FIZIK";
        ResultSet rs = stmt.executeQuery( SQL );
        if(rs.next())
            kayitli_konu_sayisi = rs.getInt(1);
        return kayitli_konu_sayisi;
    }
public void Ders_Ekle( String dersEkleDersAdi1, String dersTuru1, int dersSaati1) throws SQLException{
        Statement stmt = con.createStatement();
    String SQL = String.format("INSERT INTO DERSLER VALUES( '%s', '%s', %d)", dersEkleDersAdi1, dersTuru1, dersSaati1);
    stmt.executeUpdate( SQL );
}
public int Ders_Sayisi1() throws SQLException{
    int ders_sayisi=0;
    Statement stmt = con.createStatement();
    String SQL = "SELECT COUNT(*) FROM ALIMERT.DERSLER";
    ResultSet rs = stmt.executeQuery(SQL);
    if(rs.next())
        ders_sayisi = rs.getInt(1);
    return ders_sayisi;
    }
public float ders_saat_ortalama() throws SQLException{
        int saat=0;
        float saat_toplami=0;
        float saat_ortalama=0;

        Statement stmt = con.createStatement();
        String SQL = "SELECT SAAT FROM ALIMERT.DERSLER";
        ResultSet rs = stmt.executeQuery( SQL );
        while(rs.next()){
            saat=rs.getInt(1);
            saat_toplami+=saat;
        }
        saat_ortalama= saat_toplami/(Ders_Sayisi1());
        return saat_ortalama;
    }
public float ders_saat_toplami() throws SQLException{
        int saat=0;
        float saat_toplami=0;
        float saat_ortalama=0;

        Statement stmt = con.createStatement();
        String SQL = "SELECT SAAT FROM ALIMERT.DERSLER";
        ResultSet rs = stmt.executeQuery( SQL );
        while(rs.next()){
            saat=rs.getInt(1);
            saat_toplami+=saat;
        }
        saat_ortalama= saat_toplami/(Ders_Sayisi1());
        return saat_toplami;
    }
public int sozel_sayisi() throws SQLException{
        int sozelSayac=0;
        Statement stmt = con.createStatement();
        String SQL = String.format("select TUR from ALIMERT.DERSLER where TUR = 'SÖZ'");
        ResultSet rs = stmt.executeQuery( SQL );
        while(rs.next()){
            sozelSayac+=1;
        }
        return sozelSayac;
    }
public int sayisal_sayisi() throws SQLException{
        int sayisalSayac=0;
        Statement stmt = con.createStatement();
        String SQL = String.format("select TUR from ALIMERT.DERSLER where TUR = 'SAY'");
        ResultSet rs = stmt.executeQuery( SQL );
        while(rs.next()){
            sayisalSayac+=1;
        }
        return sayisalSayac;
    }
}
