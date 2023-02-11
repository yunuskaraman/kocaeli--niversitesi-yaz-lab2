/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muzikdosyam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YunusKARAMAN
 */
public class VeritabanıSistemi extends javax.swing.JFrame {

    /**
     * Creates new form VeritabanıSistemi
     */
    
    
      ResultSet rs;
      String sorgu;
      String[][] kolonlar = {{"ulke_id","ulke_adi"}, {"tur_id","tur_adi"},
          {"alb_id","album_adi","tur_id","tarih"},{"san_id","sanatci_adi","ulke_id"},
          {"id","sar_id","alb_id","san_id","durum"},{"sar_id","sarki_adi","suresi","dinlenme_sayisi"},
          {"kul_id","kullanici_adi","sifre","premium","durum","email"},{"id","kul_id","sar_iliski_id"},
          {"id","kul_id","tkul_id"}};
      
    Object[] satirlar = new Object[6];
    
    public VeritabanıSistemi() {
        initComponents();
        veritabani.baglan();
        sqlUlke();
        sqlTur();
        sqlAlbum();
        sqlSanatci();
        sqlSarkiIliski();
        sqlSarki();
        sqlKullanici();
        sqlKullaniciSarki();
        sqlKullaniciTakip();
    }

    
    void sqlUlke(){
       
      
        DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(kolonlar[0]);


        sorgu= "select * from ulke";
       
        rs=veritabani.listele(sorgu);
        
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                satirlar[0]=rs.getString("ulke_id");
                satirlar[1]=rs.getString("ulke_adi");
               
                
                model.addRow(satirlar);
            }
            jtable_ulke.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    void sqlTur(){

        //String[] kolonlar=;
        DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(kolonlar[1]);


        sorgu= "select * from tur";
       
        rs=veritabani.listele(sorgu);
        
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                satirlar[0]=rs.getString("tur_id");
                satirlar[1]=rs.getString("sarki_turu");
                model.addRow(satirlar);
            }
            jtable_tur.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
  
            
    void sqlAlbum(){

        
        DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(kolonlar[2]);


        sorgu= "select * from album";
       
        rs=veritabani.listele(sorgu);
        
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                satirlar[0]=rs.getString("alb_id");
                satirlar[1]=rs.getString("album_adi");
                satirlar[2]=rs.getString("tur_id");
                satirlar[3]=rs.getString("tarih");
                model.addRow(satirlar);
            }
            jtable_album.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    void sqlSanatci(){

        //String[] kolonlar=;
        DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(kolonlar[3]);


        sorgu= "select * from sanatci";
   
        rs=veritabani.listele(sorgu);
        
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                satirlar[0]=rs.getString("san_id");
                satirlar[1]=rs.getString("sanatci_adi");
                satirlar[2]=rs.getString("ulke_id");
                
                model.addRow(satirlar);
            }
            jtable_sanatci.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void  sqlSarkiIliski(){
        DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(kolonlar[4]);


        sorgu= "select * from sarki_iliski";
       
        rs=veritabani.listele(sorgu);
       
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                satirlar[0]=rs.getString("id");
                satirlar[1]=rs.getString("sar_id");
                satirlar[2]=rs.getString("alb_id");
                satirlar[3]=rs.getString("san_id");
                satirlar[4]=rs.getString("durum");
                model.addRow(satirlar);
            }
            jtable_sarki_iliski.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void  sqlSarki(){
        DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(kolonlar[5]);


        sorgu= "select * from sarki";
       
        rs=veritabani.listele(sorgu);
       
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                satirlar[0]=rs.getString("sar_id");
                satirlar[1]=rs.getString("sarki_adi");
                satirlar[2]=rs.getString("suresi");
                satirlar[3]=rs.getString("dinlenme_sayisi");
               
                model.addRow(satirlar);
            }
            jtable_sarki.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    void  sqlKullanici(){
        DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(kolonlar[6]);


        sorgu= "select * from kullanici";
       
        rs=veritabani.listele(sorgu);
       
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                satirlar[0]=rs.getString("kul_id");
                satirlar[1]=rs.getString("kullanici_adi");
                satirlar[2]=rs.getString("sifre");
                satirlar[3]=rs.getString("premium");
                satirlar[4]=rs.getString("durum");
                satirlar[5]=rs.getString("email");
                
                model.addRow(satirlar);
            }
            jtable_kullanici.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void  sqlKullaniciSarki(){
        
        DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(kolonlar[7]);

        sorgu= "select * from kullanici_sarki order by kul_id";
       
        rs=veritabani.listele(sorgu);
      
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                satirlar[0]=rs.getString("id");
                satirlar[1]=rs.getString("kul_id");
                satirlar[2]=rs.getString("sar_iliski_id");
                
                model.addRow(satirlar);
            }
            jtable_kullanici_sarki.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void  sqlKullaniciTakip(){
        
        DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(kolonlar[8]);

        sorgu= "select * from kullanici_takip order by kul_id";
       
        rs=veritabani.listele(sorgu);
      
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                satirlar[0]=rs.getString("id");
                satirlar[1]=rs.getString("kul_id");
                satirlar[2]=rs.getString("tkul_id");
                
                model.addRow(satirlar);
            }
            jtable_takipEdilen.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtable_ulke = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtable_tur = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtable_album = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtable_sanatci = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtable_sarki_iliski = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtable_sarki = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtable_kullanici = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtable_kullanici_sarki = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jtable_takipEdilen = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtable_ulke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null},
                {null, ""},
                {null, null},
                {null, null}
            },
            new String [] {
                "ulke_id", "ulke_adi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtable_ulke.setToolTipText("");
        jScrollPane1.setViewportView(jtable_ulke);

        jtable_tur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jtable_tur);

        jtable_album.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jtable_album);

        jtable_sanatci.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jtable_sanatci);

        jtable_sarki_iliski.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jtable_sarki_iliski);

        jtable_sarki.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jtable_sarki);

        jtable_kullanici.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jtable_kullanici);

        jtable_kullanici_sarki.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(jtable_kullanici_sarki);

        jtable_takipEdilen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(jtable_takipEdilen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VeritabanıSistemi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VeritabanıSistemi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VeritabanıSistemi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VeritabanıSistemi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VeritabanıSistemi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jtable_album;
    private javax.swing.JTable jtable_kullanici;
    private javax.swing.JTable jtable_kullanici_sarki;
    private javax.swing.JTable jtable_sanatci;
    private javax.swing.JTable jtable_sarki;
    private javax.swing.JTable jtable_sarki_iliski;
    private javax.swing.JTable jtable_takipEdilen;
    private javax.swing.JTable jtable_tur;
    private javax.swing.JTable jtable_ulke;
    // End of variables declaration//GEN-END:variables
}
