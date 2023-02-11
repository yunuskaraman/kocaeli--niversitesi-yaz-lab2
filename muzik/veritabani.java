/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muzikdosyam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author YunusKARAMAN
 */
public class veritabani {
    
    static String url = "jdbc:postgresql://localhost:5432/Muzik";
    static Connection conn = null;
    static Statement st;
    static void baglan(){
        try {
            conn=DriverManager.getConnection(url,"postgres","karaman34");
            st = conn.createStatement();
            System.out.println("Bağlantı gerçekleşti");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    static ResultSet listele(String sorgu){
        try {
            
            ResultSet rs= st.executeQuery(sorgu);
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        
    }
    
    static void kayitEkle(String sorgu){
        try {
            st.executeUpdate(sorgu);
            System.out.println("kayıt başarı ile eklendi.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    static void kayitGuncelle(String sorgu){
        try {
            st.executeUpdate(sorgu);
            System.out.println("kayıt başarı ile Güncellendi.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    static void KayitSil(String sorgu){
        try {
            st.executeUpdate( sorgu);
            System.out.println("kayıt başarı ile silindi.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
}
