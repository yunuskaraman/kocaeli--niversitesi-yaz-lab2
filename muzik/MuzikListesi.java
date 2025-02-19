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
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author YunusKARAMAN
 */
public class MuzikListesi extends javax.swing.JFrame {

    /**
     * Creates new form MuzikListesi
     */
    
   ArrayList<Integer> sarki_iliski_Id = new ArrayList<Integer>();
   ArrayList<Integer> sarkiId = new ArrayList<Integer>();
    DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    public MuzikListesi() {
        initComponents();
        sqlListele();
    }
    
    
    void sqlListele(){
        
        sarki_iliski_Id.clear();
        sarkiId.clear();
        
        
        String[] kolonlar= {"Şarki Adi","Sanatçı","Süresi","Dinlenme Sayisi","Şarki Türü","Albüm Adi","Ülke Adi","Tarih"};
        Object[] satirlar = new Object[9];
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(kolonlar);

        ResultSet rs;
        String sorgu= "select sarki.sarki_adi,sanatci.sanatci_adi,sarki.suresi,sarki.dinlenme_sayisi,"+
        "tur.sarki_turu,album.album_adi,ulke.ulke_adi,album.tarih,sarki_iliski.id,sarki.sar_id\n" +
        "from sarki_iliski\n" +
        "inner join sarki on sarki.sar_id=sarki_iliski.sar_id\n" +
        "inner join album on album.alb_id=sarki_iliski.alb_id\n" +
        "inner join tur on tur.tur_id=album.tur_id\n" +
        "inner join sanatci on sanatci.san_id=sarki_iliski.san_id\n" +
        "inner join ulke on ulke.ulke_id=sanatci.ulke_id\n" +
        "group by sarki.sarki_adi,album.album_adi,sarki_turu,suresi,ulke.ulke_adi,"+
        "sarki.dinlenme_sayisi,album.tarih,sanatci.sanatci_adi,sarki_iliski.id,sarki.sar_id\n" +
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
                sarkiId.add(Integer.parseInt(rs.getString("sar_id")));
                model.addRow(satirlar);
            }
            jTable1.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lbl_kayitSayisi.setText("Toplam Kayıt Sayısı : "+jTable1.getRowCount());
    }
    
    private void filter(String arama){
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        
        jTable1.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(arama));
        
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_Ekle = new javax.swing.JButton();
        btn_anasayfa = new javax.swing.JButton();
        btn_dinle = new javax.swing.JButton();
        lbl_kayitSayisi = new javax.swing.JLabel();
        txt_arama = new javax.swing.JTextField();

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
        jScrollPane2.setViewportView(jTable1);

        btn_Ekle.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_Ekle.setText("Müzik Listeme Ekle");
        btn_Ekle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EkleActionPerformed(evt);
            }
        });

        btn_anasayfa.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_anasayfa.setText("Anasayfa");
        btn_anasayfa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anasayfaActionPerformed(evt);
            }
        });

        btn_dinle.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btn_dinle.setText("Şimdi Dinle");
        btn_dinle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dinleActionPerformed(evt);
            }
        });

        lbl_kayitSayisi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbl_kayitSayisi.setText("Kayit Sayisi");

        txt_arama.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txt_arama.setForeground(new java.awt.Color(204, 204, 204));
        txt_arama.setText("Şarkı arama");
        txt_arama.setToolTipText("");
        txt_arama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_aramaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_aramaFocusLost(evt);
            }
        });
        txt_arama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_aramaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Ekle)
                                .addGap(35, 35, 35)
                                .addComponent(btn_dinle, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(txt_arama, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_kayitSayisi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_anasayfa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_anasayfa, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Ekle, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_dinle, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_arama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_kayitSayisi)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_EkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EkleActionPerformed
        
       if(jTable1.getSelectedRowCount()==1){
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
        
       
        
    }//GEN-LAST:event_btn_EkleActionPerformed

    private void btn_anasayfaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anasayfaActionPerformed
        AnaSayfa frm = new AnaSayfa();
        this.setVisible(false);
        frm.setVisible(true);
    }//GEN-LAST:event_btn_anasayfaActionPerformed

    private void btn_dinleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dinleActionPerformed
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if(jTable1.getSelectedRowCount()==1){
            
            int dinlenmeSayisi = Integer.parseInt(model.getValueAt(jTable1.getSelectedRow(),3).toString());
        
            dinlenmeSayisi++;

            System.out.println(jTable1.getSelectedRowCount());

            String sorgu = "update sarki set dinlenme_sayisi=("+dinlenmeSayisi+") where sar_id=("+sarkiId.get(jTable1.getSelectedRow())+")";
            veritabani.kayitGuncelle(sorgu);

            sqlListele();
        }else{
            
             JOptionPane.showMessageDialog(this,"Herhangi bir şarkı seçilmedi");
        }        
              
                
        
                
    }//GEN-LAST:event_btn_dinleActionPerformed

    private void txt_aramaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_aramaFocusGained
        
        if(txt_arama.getText().equals("Şarkı arama")){
            txt_arama.setText("");
        }
        
    }//GEN-LAST:event_txt_aramaFocusGained

    private void txt_aramaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_aramaKeyReleased
         String arama = txt_arama.getText().toLowerCase();
        
        System.out.println(arama);
        
        filter(arama);
    }//GEN-LAST:event_txt_aramaKeyReleased

    private void txt_aramaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_aramaFocusLost
       if(txt_arama.getText().equals("")){
            txt_arama.setText("Şarkı arama");
        }
    }//GEN-LAST:event_txt_aramaFocusLost

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
            java.util.logging.Logger.getLogger(MuzikListesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MuzikListesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MuzikListesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MuzikListesi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MuzikListesi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Ekle;
    private javax.swing.JButton btn_anasayfa;
    private javax.swing.JButton btn_dinle;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_kayitSayisi;
    private javax.swing.JTextField txt_arama;
    // End of variables declaration//GEN-END:variables
}
