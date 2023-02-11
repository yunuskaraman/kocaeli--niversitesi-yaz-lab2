/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muzikdosyam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YunusKARAMAN
 */

public class TakipMuzikListesi extends javax.swing.JFrame {

    /**
     * Creates new form TakipMuzikListesi
     */
    
    ArrayList<Integer> sarki_iliski_Id = new ArrayList<Integer>();
     ArrayList<Integer> sarki_id = new ArrayList<Integer>();
    public TakipMuzikListesi() {
        initComponents();
        
        sqlListele();
        
    }
    
    
    
     void sqlListele(){
        
        TakipEdilen frm = new TakipEdilen();
        System.out.println(frm.tId);
        
        sarki_iliski_Id.clear();
        sarki_id.clear();
        
        DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        String[] kolonlar= {"Şarki Adi","Sanatçı","Süresi","Dinlenme Sayisi","Şarki Türü","Albüm Adi","Ülke Adi","Tarih"};
        Object[] satirlar = new Object[8];
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(kolonlar);

        ResultSet rs;
        String sorgu= "select sarki.sarki_adi,sanatci.sanatci_adi,sarki.suresi,sarki.dinlenme_sayisi,tur.sarki_turu,"+
        "album.album_adi,ulke.ulke_adi,album.tarih,kullanici.kul_id,kullanici_sarki.sar_iliski_id,sarki.sar_id\n" +
        "from sarki_iliski\n" +
        "inner join sarki on sarki.sar_id=sarki_iliski.sar_id\n" +
        "inner join album on album.alb_id=sarki_iliski.alb_id\n" +
        "inner join tur on tur.tur_id=album.tur_id\n" +
        "inner join sanatci on sanatci.san_id=sarki_iliski.san_id\n" +
        "inner join ulke on ulke.ulke_id=sanatci.ulke_id\n" +
        "inner join kullanici_sarki on sarki_iliski.id=kullanici_sarki.sar_iliski_id\n"+
        "inner join kullanici on kullanici.kul_id=kullanici_sarki.kul_id\n"+
        "where kullanici.kul_id="+frm.tId+"\n"+
        "group by sarki.sarki_adi,album.album_adi,sarki_turu,suresi,ulke.ulke_adi," +
        "sarki.dinlenme_sayisi,album.tarih,sanatci.sanatci_adi,kullanici.kul_id,kullanici_sarki.sar_iliski_id,sarki.sar_id\n" +
        "order by suresi desc;";
        veritabani.baglan();
        rs=veritabani.listele(sorgu);
        
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                satirlar[0]=rs.getString("sarki_adi");
                satirlar[1]=rs.getString("sanatci_adi");
                satirlar[2]=rs.getString("suresi");
                satirlar[3]=rs.getString("dinlenme_sayisi");
                satirlar[4]=rs.getString("sarki_turu");
                satirlar[5]=rs.getString("album_adi");
                satirlar[6]=rs.getString("ulke_adi");
                satirlar[7]=rs.getString("tarih");
                sarki_iliski_Id.add(Integer.parseInt(rs.getString("sar_iliski_id")));
                sarki_id.add(Integer.parseInt(rs.getString("sar_id")));
                model.addRow(satirlar);
            }
            jTable1.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lbl_kayitSayisi.setText("Toplam Kayıt Sayısı : "+jTable1.getRowCount());
        
    }
    

     void sqlTurListele(int tur_id){
        
        TakipEdilen frm = new TakipEdilen();
        sarki_iliski_Id.clear();
        sarki_id.clear();
        DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        String[] kolonlar= {"Şarki Adi","Sanatçı","Süresi","Dinlenme Sayisi","Şarki Türü","Albüm Adi","Ülke Adi","Tarih"};
        Object[] satirlar = new Object[8];
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(kolonlar);

        ResultSet rs;
        String sorgu= "select sarki.sarki_adi,sanatci.sanatci_adi,sarki.suresi,sarki.dinlenme_sayisi,tur.sarki_turu,"+
        "album.album_adi,ulke.ulke_adi,album.tarih,kullanici.kul_id,kullanici_sarki.sar_iliski_id,sarki.sar_id\n" +
        "from sarki_iliski\n" +
        "inner join sarki on sarki.sar_id=sarki_iliski.sar_id\n" +
        "inner join album on album.alb_id=sarki_iliski.alb_id\n" +
        "inner join tur on tur.tur_id=album.tur_id\n" +
        "inner join sanatci on sanatci.san_id=sarki_iliski.san_id\n" +
        "inner join ulke on ulke.ulke_id=sanatci.ulke_id\n" +
        "inner join kullanici_sarki on sarki_iliski.id=kullanici_sarki.sar_iliski_id\n"+
        "inner join kullanici on kullanici.kul_id=kullanici_sarki.kul_id\n"+
        "where kullanici.kul_id="+frm.tId+" and tur.tur_id="+tur_id+"\n"+
        "group by sarki.sarki_adi,album.album_adi,sarki_turu,suresi,ulke.ulke_adi," +
        "sarki.dinlenme_sayisi,album.tarih,sanatci.sanatci_adi,kullanici.kul_id,kullanici_sarki.sar_iliski_id,sarki.sar_id\n" +
        "order by suresi desc;";
        veritabani.baglan();
        rs=veritabani.listele(sorgu);
        
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                satirlar[0]=rs.getString("sarki_adi");
                satirlar[1]=rs.getString("sanatci_adi");
                satirlar[2]=rs.getString("suresi");
                satirlar[3]=rs.getString("dinlenme_sayisi");
                satirlar[4]=rs.getString("sarki_turu");
                satirlar[5]=rs.getString("album_adi");
                satirlar[6]=rs.getString("ulke_adi");
                satirlar[7]=rs.getString("tarih");
                sarki_iliski_Id.add(Integer.parseInt(rs.getString("sar_iliski_id")));
                sarki_id.add(Integer.parseInt(rs.getString("sar_id")));
                model.addRow(satirlar);
                
                
            }
            jTable1.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lbl_kayitSayisi.setText("Toplam Kayıt Sayısı : "+jTable1.getRowCount());
        
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
        jTable1 = new javax.swing.JTable();
        btn_anasayfa = new javax.swing.JButton();
        btn_ekle = new javax.swing.JButton();
        btn_dinle = new javax.swing.JButton();
        lbl_kayitSayisi = new javax.swing.JLabel();
        btn_Muzikler = new javax.swing.JButton();
        btn_pop = new javax.swing.JButton();
        btn_klasik = new javax.swing.JButton();
        btn_jazz = new javax.swing.JButton();
        btn_tumEkle = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        btn_anasayfa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_anasayfa.setText("Anasayfa");
        btn_anasayfa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anasayfaActionPerformed(evt);
            }
        });

        btn_ekle.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_ekle.setText("Muzik Listeme Ekle");
        btn_ekle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ekleActionPerformed(evt);
            }
        });

        btn_dinle.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_dinle.setText("Şimdi Dinle");
        btn_dinle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dinleActionPerformed(evt);
            }
        });

        lbl_kayitSayisi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbl_kayitSayisi.setText("Kayıt Sayısı");

        btn_Muzikler.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_Muzikler.setText("Tüm Müzikler");
        btn_Muzikler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MuziklerActionPerformed(evt);
            }
        });

        btn_pop.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_pop.setText("Pop Müzik");
        btn_pop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_popActionPerformed(evt);
            }
        });

        btn_klasik.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_klasik.setText("Klasik Müzik");
        btn_klasik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_klasikActionPerformed(evt);
            }
        });

        btn_jazz.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_jazz.setText("Jazz Müzik");
        btn_jazz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_jazzActionPerformed(evt);
            }
        });

        btn_tumEkle.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_tumEkle.setText("Tümünü Ekle");
        btn_tumEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tumEkleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_ekle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_tumEkle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_dinle, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Muzikler, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_pop, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_klasik)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_jazz)
                        .addGap(18, 18, 18)
                        .addComponent(btn_anasayfa, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_kayitSayisi)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(btn_jazz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_ekle, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_anasayfa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_pop, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_klasik, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_Muzikler, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_dinle, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tumEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(lbl_kayitSayisi)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_anasayfaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anasayfaActionPerformed
        TakipEdilen frm = new TakipEdilen();
        this.setVisible(false);
        frm.setVisible(true);
    }//GEN-LAST:event_btn_anasayfaActionPerformed

    private void btn_MuziklerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MuziklerActionPerformed
       sqlListele();
    }//GEN-LAST:event_btn_MuziklerActionPerformed

    private void btn_popActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_popActionPerformed
        sqlTurListele(1);
    }//GEN-LAST:event_btn_popActionPerformed

    private void btn_klasikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_klasikActionPerformed
        sqlTurListele(3);
    }//GEN-LAST:event_btn_klasikActionPerformed

    private void btn_jazzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jazzActionPerformed
        sqlTurListele(2);
    }//GEN-LAST:event_btn_jazzActionPerformed

    private void btn_ekleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ekleActionPerformed
        
        if(jTable1.getSelectedRowCount()==1){

            Giris frm = new Giris();
            ResultSet rs;
            String sorgu= "select * from kullanici_sarki where kul_id="+frm.ID+" order by sar_iliski_id";

            int id=0;
            rs=veritabani.listele(sorgu);

            try {
                while(rs.next()){
                    //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                    id=Integer.parseInt(rs.getString("sar_iliski_id"));
                    System.out.println(id);
                    if(id==sarki_iliski_Id.get(jTable1.getSelectedRow())){
                        id=0;
                        break;
                    }

                }

            }
            catch (SQLException ex) {
                Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(id!=0){


                sorgu = "insert into kullanici_sarki(kul_id,sar_iliski_id) values ("+frm.ID+","+sarki_iliski_Id.get(jTable1.getSelectedRow())+")";

                veritabani.kayitEkle(sorgu);
            }
            else{
                JOptionPane.showMessageDialog(this,"Şarkı Listede var");
            }
           
        }
        else{
            JOptionPane.showMessageDialog(this,"Herhangi bir şarkı seçilmedi");
        }
       
    }//GEN-LAST:event_btn_ekleActionPerformed

    private void btn_tumEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tumEkleActionPerformed
       
        System.out.println(jTable1.getRowCount());
        //Giris frm = new Giris();
        
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            
            
            Giris frm = new Giris();
            ResultSet rs;
            String sorgu= "select * from kullanici_sarki where kul_id="+frm.ID+" order by sar_iliski_id";

            int id=-1;
            rs=veritabani.listele(sorgu);

            try {
                while(rs.next()){
                    //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                    id=Integer.parseInt(rs.getString("sar_iliski_id"));
                    System.out.println(id);
                    if(id==sarki_iliski_Id.get(i)){
                        id=0;
                        break;
                    }

                }

            }
            catch (SQLException ex) {
                Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(id!=0){


                sorgu = "insert into kullanici_sarki(kul_id,sar_iliski_id) values ("+frm.ID+","+sarki_iliski_Id.get(i)+")";

                veritabani.kayitEkle(sorgu);
            }
           
        }
    }//GEN-LAST:event_btn_tumEkleActionPerformed

    private void btn_dinleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dinleActionPerformed

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if(jTable1.getSelectedRowCount()==1){
            
            int dinlenmeSayisi = Integer.parseInt(model.getValueAt(jTable1.getSelectedRow(),3).toString());
        
            dinlenmeSayisi++;

            System.out.println(jTable1.getSelectedRowCount());

            String sorgu = "update sarki set dinlenme_sayisi=("+dinlenmeSayisi+") where sar_id=("+sarki_id.get(jTable1.getSelectedRow())+")";
            veritabani.kayitGuncelle(sorgu);

            sqlListele();
            
        }else{
            
             JOptionPane.showMessageDialog(this,"Herhangi bir şarkı seçilmedi");
        }        
        
            
        
    }//GEN-LAST:event_btn_dinleActionPerformed

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
            java.util.logging.Logger.getLogger(TakipMuzikListesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TakipMuzikListesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TakipMuzikListesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TakipMuzikListesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TakipMuzikListesi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Muzikler;
    private javax.swing.JButton btn_anasayfa;
    private javax.swing.JButton btn_dinle;
    private javax.swing.JButton btn_ekle;
    private javax.swing.JButton btn_jazz;
    private javax.swing.JButton btn_klasik;
    private javax.swing.JButton btn_pop;
    private javax.swing.JButton btn_tumEkle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_kayitSayisi;
    // End of variables declaration//GEN-END:variables
}
