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
public class MuzikListem extends javax.swing.JFrame {

    /**
     * Creates new form MuzikListem
     */
    
    ArrayList<Integer> sarki_iliski_Id = new ArrayList<Integer>();
    
    ArrayList<Integer> sarki_id = new ArrayList<Integer>();
    
    public MuzikListem() {
        initComponents();
        
        sqlListele();
       
    }
    
    void sqlListele(){
        
        Giris frm = new Giris();
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
        "album.album_adi,ulke.ulke_adi,album.tarih,kullanici.kul_id,kullanici_sarki.sar_iliski_id,kullanici_sarki.id,sarki.sar_id\n" +
        "from sarki_iliski\n" +
        "inner join sarki on sarki.sar_id=sarki_iliski.sar_id\n" +
        "inner join album on album.alb_id=sarki_iliski.alb_id\n" +
        "inner join tur on tur.tur_id=album.tur_id\n" +
        "inner join sanatci on sanatci.san_id=sarki_iliski.san_id\n" +
        "inner join ulke on ulke.ulke_id=sanatci.ulke_id\n" +
        "inner join kullanici_sarki on sarki_iliski.id=kullanici_sarki.sar_iliski_id\n"+
        "inner join kullanici on kullanici.kul_id=kullanici_sarki.kul_id\n"+
        "where kullanici.kul_id="+frm.ID+"\n"+
        "group by sarki.sarki_adi,album.album_adi,sarki_turu,suresi,ulke.ulke_adi,kullanici_sarki.id," +
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
                sarki_iliski_Id.add(Integer.parseInt(rs.getString("id")));
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
        
        Giris frm = new Giris();
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
        "album.album_adi,ulke.ulke_adi,album.tarih,kullanici.kul_id,kullanici_sarki.sar_iliski_id,sarki.sar_id,kullanici_sarki.id\n" +
        "from sarki_iliski\n" +
        "inner join sarki on sarki.sar_id=sarki_iliski.sar_id\n" +
        "inner join album on album.alb_id=sarki_iliski.alb_id\n" +
        "inner join tur on tur.tur_id=album.tur_id\n" +
        "inner join sanatci on sanatci.san_id=sarki_iliski.san_id\n" +
        "inner join ulke on ulke.ulke_id=sanatci.ulke_id\n" +
        "inner join kullanici_sarki on sarki_iliski.id=kullanici_sarki.sar_iliski_id\n"+
        "inner join kullanici on kullanici.kul_id=kullanici_sarki.kul_id\n"+
        "where kullanici.kul_id="+frm.ID+" and tur.tur_id="+tur_id+"\n"+
        "group by sarki.sarki_adi,album.album_adi,sarki_turu,suresi,ulke.ulke_adi,kullanici_sarki.id," +
        "sarki.dinlenme_sayisi,album.tarih,sanatci.sanatci_adi,kullanici.kul_id,kullanici_sarki.sar_iliski_id,sarki.sar_id\n" +
        "order by suresi desc;";
        veritabani.baglan();
        rs=veritabani.listele(sorgu);
        int i=0;
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
                sarki_iliski_Id.add(Integer.parseInt(rs.getString("id")));
                sarki_id.add(Integer.parseInt(rs.getString("sar_id")));
                model.addRow(satirlar);
                
                if(i==9){
                    break;
                }
                
            }
            jTable1.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lbl_kayitSayisi.setText("Toplam Kayıt Sayısı : "+jTable1.getRowCount());
        
    }
    
    void kulSarId(){
        
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
        btn_sil = new javax.swing.JButton();
        btn_anasayfa = new javax.swing.JButton();
        btn_klasik = new javax.swing.JButton();
        btn_pop = new javax.swing.JButton();
        btn_jazz = new javax.swing.JButton();
        btn_tumu = new javax.swing.JButton();
        lbl_kayitSayisi = new javax.swing.JLabel();
        btn_dinle = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
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

        btn_sil.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_sil.setText("Sil");
        btn_sil.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_sil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_silActionPerformed(evt);
            }
        });

        btn_anasayfa.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_anasayfa.setText("AnaSayfa");
        btn_anasayfa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anasayfaActionPerformed(evt);
            }
        });

        btn_klasik.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_klasik.setText("Klasik Şarkı");
        btn_klasik.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_klasik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_klasikActionPerformed(evt);
            }
        });

        btn_pop.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_pop.setText("Pop Şarkı");
        btn_pop.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_pop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_popActionPerformed(evt);
            }
        });

        btn_jazz.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_jazz.setText("Jazz Şarkı");
        btn_jazz.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_jazz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_jazzActionPerformed(evt);
            }
        });

        btn_tumu.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_tumu.setText("Tüm Müzikler");
        btn_tumu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tumuActionPerformed(evt);
            }
        });

        lbl_kayitSayisi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbl_kayitSayisi.setText("Kayit Sayisi");

        btn_dinle.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_dinle.setText("Şimdi Dinle");
        btn_dinle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dinleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_dinle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_tumu, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_jazz)
                        .addGap(18, 18, 18)
                        .addComponent(btn_pop)
                        .addGap(18, 18, 18)
                        .addComponent(btn_klasik)
                        .addGap(18, 18, 18)
                        .addComponent(btn_sil, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_anasayfa))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_kayitSayisi)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_jazz, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_pop, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_klasik, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_sil, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_anasayfa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_tumu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_dinle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_kayitSayisi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_silActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_silActionPerformed

        if(jTable1.getSelectedRowCount()==1){
            
            System.out.println(sarki_iliski_Id.get(jTable1.getSelectedRow()));

            String sorgu = "delete from kullanici_sarki as k where k.id="+sarki_iliski_Id.get(jTable1.getSelectedRow())+"";

            veritabani.KayitSil(sorgu);

            sqlListele();
            
        }
        else{
            
             JOptionPane.showMessageDialog(this,"Herhangi bir şarkı seçilmedi");
        }      
        
    }//GEN-LAST:event_btn_silActionPerformed

    private void btn_anasayfaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anasayfaActionPerformed
        
        AnaSayfa frm = new AnaSayfa();
        this.setVisible(false);
        frm.setVisible(true);
        
    }//GEN-LAST:event_btn_anasayfaActionPerformed

    private void btn_klasikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_klasikActionPerformed
        sqlTurListele(3);
    }//GEN-LAST:event_btn_klasikActionPerformed

    private void btn_popActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_popActionPerformed
         sqlTurListele(1);
    }//GEN-LAST:event_btn_popActionPerformed

    private void btn_jazzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jazzActionPerformed
         sqlTurListele(2);
    }//GEN-LAST:event_btn_jazzActionPerformed

    private void btn_tumuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tumuActionPerformed
       sqlListele();
    }//GEN-LAST:event_btn_tumuActionPerformed

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
        
        
        
        //System.out.println(sarki_id.get(jTable1.getSelectedRow()));
        
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
            java.util.logging.Logger.getLogger(MuzikListem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MuzikListem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MuzikListem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MuzikListem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MuzikListem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_anasayfa;
    private javax.swing.JButton btn_dinle;
    private javax.swing.JButton btn_jazz;
    private javax.swing.JButton btn_klasik;
    private javax.swing.JButton btn_pop;
    private javax.swing.JButton btn_sil;
    private javax.swing.JButton btn_tumu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_kayitSayisi;
    // End of variables declaration//GEN-END:variables
}
