/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muzikdosyam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YunusKARAMAN
 */
public class SarkiEkle extends javax.swing.JFrame {

    /**
     * Creates new form SarkiEkle
     */
    
    
    ArrayList<Integer> list_sanId = new ArrayList<Integer>();
    ArrayList<Integer> sarki_iliski_id = new ArrayList<Integer>();
    ArrayList<Integer> ulke_id = new ArrayList<Integer>();
    ArrayList<Integer> list_tur_id = new ArrayList<Integer>();
    ArrayList<Integer> album_id = new ArrayList<Integer>();
    ArrayList<Integer> sarki_id = new ArrayList<Integer>();
    ArrayList<Integer> sanatci_id = new ArrayList<Integer>();
    
    
    
    DefaultTableModel model = new javax.swing.table.DefaultTableModel();
    
    
    
    
    public SarkiEkle() {
        initComponents();
        
        buttonGroup1.add(radio_sanGir);
        buttonGroup1.add(radio_sanSec);
        sqlListele();
        comboUlkeAdi();
        comboSanatciAdi();
        comboTurId();
        
      
            
    }
    
    public void sqlListele(){
        
        sarki_iliski_id.clear();
        album_id.clear();
        sarki_id.clear();
        
        String[] kolonlar= {"Şarki Adi","Sanatçı","Süresi","Dinlenme Sayisi","Şarki Türü","Albüm Adi","Ülke Adi","Tarih"};
        Object[] satirlar = new Object[8];
        model.setColumnCount(0);
        model.setRowCount(0);
        model.setColumnIdentifiers(kolonlar);

        ResultSet rs;
        String sorgu= "select sarki.sarki_adi,sanatci.sanatci_adi,sarki.suresi,sarki.sar_id,album.alb_id,"+
        "sarki.dinlenme_sayisi,tur.sarki_turu,album.album_adi,ulke.ulke_adi,album.tarih,sanatci.san_id,sarki_iliski.id\n" +
        "from sarki_iliski\n" +
        "inner join sarki on sarki.sar_id=sarki_iliski.sar_id\n" +
        "inner join album on album.alb_id=sarki_iliski.alb_id\n" +
        "inner join tur on tur.tur_id=album.tur_id\n" +
        "inner join sanatci on sanatci.san_id=sarki_iliski.san_id\n" +
        "inner join ulke on ulke.ulke_id=sanatci.ulke_id\n" +
        "where sarki_iliski.durum=true\n"+
        "group by sarki.sarki_adi,album.album_adi,sarki_turu,suresi,sarki_iliski.id,"+
        "ulke.ulke_adi,sarki.dinlenme_sayisi,album.tarih,sanatci.sanatci_adi,sanatci.san_id,sarki.sar_id,album.alb_id\n" +
        "order by sarki_iliski.id;";
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
                sarki_iliski_id.add(Integer.parseInt(rs.getString("id")));
                sarki_id.add(Integer.parseInt(rs.getString("sar_id")));
                album_id.add(Integer.parseInt(rs.getString("alb_id")));
                sanatci_id.add(Integer.parseInt(rs.getString("san_id")));
                System.out.println(Integer.parseInt(rs.getString("id")));
                model.addRow(satirlar);
            }
            jTable1.setModel(model);
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         lbl_kayitSayisi.setText("Toplam Kayıt Sayısı : "+jTable1.getRowCount());
    }

    
    void comboUlkeAdi(){
        
        ResultSet rs;
        String sorgu= "select * from ulke";
        veritabani.baglan();
        rs=veritabani.listele(sorgu);
        
        try {
            while(rs.next()){
                combo_ulke.addItem(rs.getString("ulke_adi"));
                ulke_id.add(Integer.parseInt(rs.getString("ulke_id")));
                //System.out.print(rs.getString("ulke_adi"));
                //System.out.println(Integer.parseInt(rs.getString("ulke_id")));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void comboSanatciAdi(){
        
        ResultSet rs;
        String sorgu= "select * from sanatci";
        veritabani.baglan();
        rs=veritabani.listele(sorgu);
        
        try {
            while(rs.next()){
                combo_sanatciAdi.addItem(rs.getString("sanatci_adi"));
                list_sanId.add(Integer.parseInt(rs.getString("san_id")));
                //System.out.print(Integer.parseInt(rs.getString("san_id"))+" ");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void comboTurId(){
        list_tur_id.clear();
        String sorgu="select * from tur";
        
        ResultSet rs=veritabani.listele(sorgu);
        
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                
                combo_tur.addItem(rs.getString("sarki_turu"));
                list_tur_id.add(Integer.parseInt(rs.getString("tur_id")));
                System.out.print(rs.getString("sarki_turu"));
                System.out.println(Integer.parseInt(rs.getString("tur_id")));
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnSil = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        text_sarkiAdi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        text_sarkiSuresi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        text_albumAdi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        text_sanatciAdi = new javax.swing.JTextField();
        combo_tur = new javax.swing.JComboBox<>();
        combo_ulke = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnEkle = new javax.swing.JButton();
        btnGuncelle = new javax.swing.JButton();
        combo_sanatciAdi = new javax.swing.JComboBox<>();
        radio_sanGir = new javax.swing.JRadioButton();
        radio_sanSec = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        lbl_kayitSayisi = new javax.swing.JLabel();
        btn_cikis = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnSil.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnSil.setText("Şarkı Sil");
        btnSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSilActionPerformed(evt);
            }
        });

        text_sarkiAdi.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Şarki Süresi");

        text_sarkiSuresi.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Album Adı");

        text_albumAdi.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Sanatçı Adı");

        text_sanatciAdi.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        text_sanatciAdi.setEnabled(false);

        combo_ulke.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        combo_ulke.setEnabled(false);
        combo_ulke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_ulkeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Ülke Seçiniz");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Şarki Adı");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Şarkı Türü Seçiniz");

        btnEkle.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnEkle.setText("Şarkı Ekle");
        btnEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEkleActionPerformed(evt);
            }
        });

        btnGuncelle.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnGuncelle.setText("Şarkı Güncelle");
        btnGuncelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuncelleActionPerformed(evt);
            }
        });

        combo_sanatciAdi.setEnabled(false);

        radio_sanGir.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        radio_sanGir.setText("Sanatçı İsmi Gir");
        radio_sanGir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_sanGirActionPerformed(evt);
            }
        });

        radio_sanSec.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        radio_sanSec.setText("Sanatçı İsmi Seç");
        radio_sanSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_sanSecActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Sanatçı Seçiniz");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(text_sanatciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(combo_ulke, 0, 100, Short.MAX_VALUE)
                                                        .addComponent(combo_sanatciAdi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addComponent(text_albumAdi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(text_sarkiAdi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(text_sarkiSuresi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(43, 43, 43)
                                                .addComponent(combo_tur, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(radio_sanGir)
                        .addGap(36, 36, 36)
                        .addComponent(radio_sanSec)
                        .addGap(0, 8, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_sarkiAdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(text_sarkiSuresi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(text_albumAdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radio_sanGir)
                    .addComponent(radio_sanSec))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(text_sanatciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_sanatciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_ulke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo_tur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEkle, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuncelle, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );

        lbl_kayitSayisi.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lbl_kayitSayisi.setText("Kayıt Sayisi");
        lbl_kayitSayisi.setToolTipText("");

        btn_cikis.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btn_cikis.setText("Çıkış");
        btn_cikis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cikisActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton1.setText("Veri tabanı Sistemi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_kayitSayisi)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cikis)
                                .addGap(46, 46, 46)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cikis)
                    .addComponent(jButton1))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSil, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_kayitSayisi)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuncelleActionPerformed
    
        if(jTable1.getSelectedRowCount()==1){

             veritabani.baglan();



           String sarkiUpdate = "update sarki set sarki_adi='"+text_sarkiAdi.getText()+"', suresi="+text_sarkiSuresi.getText()+" where sar_id="+sarki_id.get(jTable1.getSelectedRow())+"";
           veritabani.kayitGuncelle(sarkiUpdate);


           System.out.println(text_sarkiAdi.getText());
           System.out.println(sarki_id.get(jTable1.getSelectedRow()));

           String albumUpdate = "update album set album_adi='"+text_albumAdi.getText()+"', tur_id="+list_tur_id.get(combo_tur.getSelectedIndex())+" where alb_id="+album_id.get(jTable1.getSelectedRow())+"";
           veritabani.kayitGuncelle(albumUpdate);

           if(radio_sanGir.isSelected()==true){
               combo_ulke.setEnabled(true);
               String sanatciUpdate="update sanatci set sanatci_adi='"+text_sanatciAdi.getText()+"', ulke_id="+ulke_id.get(combo_ulke.getSelectedIndex())+" where san_id="+sanatci_id.get(jTable1.getSelectedRow())+"";
               veritabani.kayitGuncelle(sanatciUpdate);
           }
           if(radio_sanSec.isSelected()==true){
               String sarki_iliskiU="update sarki_iliski set san_id="+list_sanId.get(combo_sanatciAdi.getSelectedIndex())+" where id="+sarki_iliski_id.get(jTable1.getSelectedRow())+"";
               veritabani.kayitGuncelle(sarki_iliskiU);
           }
           sqlListele();

        }
        else{
            JOptionPane.showMessageDialog(this,"Herhangi bir kayıt seçilmedi");
        }
   
    
    /*System.out.println(ulke_id.get(combo_ulke.getSelectedIndex()));
     System.out.println(sarki_id.get(jTable1.getSelectedRow()));
     System.out.println(album_id.get(jTable1.getSelectedRow()));
     System.out.println(sanatci_id.get(jTable1.getSelectedRow()));
     */
    
    }//GEN-LAST:event_btnGuncelleActionPerformed

    private void btnEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEkleActionPerformed
        
        String sarkiAdi,albumAdi,sanatciAdi = null,ulke,tur;
        double sarkiSuresi=0;
        Object[] ulke_tur = new Object[2];
        String sorgu;
        ResultSet rs;
        SimpleDateFormat sekil = new SimpleDateFormat("yyyy/MM/dd");
        Date dateTarih = new Date();
        
        int sar_id,alb_id,san_id;
        
        String tarih=sekil.format(dateTarih);     
        sarkiAdi=text_sarkiAdi.getText();
        albumAdi=text_albumAdi.getText();
        ulke=combo_ulke.getSelectedItem().toString().toLowerCase();
        tur=combo_tur.getSelectedItem().toString().toLowerCase();
        sarkiSuresi=Double.parseDouble(text_sarkiSuresi.getText());
        
        
         veritabani.baglan();
        
        if(radio_sanGir.isSelected()==true){
            
            sanatciAdi=text_sanatciAdi.getText();
         
            String sorguSanatci="insert into sanatci(sanatci_adi,ulke_id) values ('"+sanatciAdi+"',"+ulke_id.get(combo_ulke.getSelectedIndex())+")";
            veritabani.kayitEkle(sorguSanatci);
            
        }
        
        String sorguSarki="insert into sarki (sarki_adi,suresi,dinlenme_sayisi) values ('"+sarkiAdi+"',"+sarkiSuresi+","+0+")";
       
        veritabani.kayitEkle(sorguSarki);
        
        
        String sorguAlbum="insert into album(album_adi,tur_id,tarih) values ('"+albumAdi+"',"+ list_tur_id.get(combo_tur.getSelectedIndex())+",'"+tarih+"')";
        
        veritabani.kayitEkle(sorguAlbum);
        
        // sarki iliski tablosuna eklema

        
        sorgu="select * from sarki";
        
        rs=veritabani.listele(sorgu);
        
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                ulke_tur[0]=rs.getString("sar_id");
                ulke_tur[1]=rs.getString("sarki_adi");
                if(ulke_tur[1].equals(sarkiAdi)) break;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sar_id=Integer.parseInt( ulke_tur[0].toString());
        
         sorgu="select * from album";
        
        rs=veritabani.listele(sorgu);
        
        try {
            while(rs.next()){
                //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                ulke_tur[0]=rs.getString("alb_id");
                ulke_tur[1]=rs.getString("album_adi");
                if(ulke_tur[1].equals(albumAdi)) break;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        alb_id=Integer.parseInt( ulke_tur[0].toString());
        
        if(radio_sanGir.isSelected()==true){
            
            
            sorgu="select * from sanatci";
        
            rs=veritabani.listele(sorgu);

            try {
                while(rs.next()){
                    //System.out.println(rs.getString("kul_id")+rs.getString("kullanici_adi")+rs.getString("sifre")+"\n"+rs.getString("premium"));   
                    ulke_tur[0]=rs.getString("san_id");
                    ulke_tur[1]=rs.getString("sanatci_adi");
                    if(ulke_tur[1].equals(sanatciAdi)) break;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Giris.class.getName()).log(Level.SEVERE, null, ex);
            }

            san_id=Integer.parseInt( ulke_tur[0].toString());
            
        }else{
            san_id=list_sanId.get(combo_sanatciAdi.getSelectedIndex());
        }
         
        
        System.out.println(san_id+" "+sar_id+" "+alb_id);
        
        String sorguSarIliski = "insert into sarki_iliski (sar_id,alb_id,san_id,durum) values ("+sar_id+","+alb_id+","+san_id+","+true+")";
        
        veritabani.kayitEkle(sorguSarIliski);
        
        sqlListele();
        
        text_albumAdi.setText(" ");
        text_sarkiAdi.setText(" ");
        text_sarkiSuresi.setText(" ");
        
        
    }//GEN-LAST:event_btnEkleActionPerformed

    private void combo_ulkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_ulkeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_ulkeActionPerformed

    private void radio_sanGirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_sanGirActionPerformed
        
        if(radio_sanGir.isSelected()==true){
            text_sanatciAdi.setEnabled(true);
            combo_sanatciAdi.setEnabled(false);
            combo_ulke.setEnabled(true);
        }
        
        
    }//GEN-LAST:event_radio_sanGirActionPerformed

    private void radio_sanSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_sanSecActionPerformed
        if(radio_sanSec.isSelected()==true){
            text_sanatciAdi.setEnabled(false);
            combo_sanatciAdi.setEnabled(true);
            combo_ulke.setEnabled(false);
        }
    }//GEN-LAST:event_radio_sanSecActionPerformed

    private void btn_cikisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cikisActionPerformed
        Giris frm= new Giris();
        this.setVisible(false);
        frm.setVisible(true);
    }//GEN-LAST:event_btn_cikisActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        VeritabanıSistemi frm = new VeritabanıSistemi();
        frm.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSilActionPerformed
        
        if(jTable1.getSelectedRowCount()==1){
            
            String sorgu = "update sarki_iliski set durum=false where id="+sarki_iliski_id.get(jTable1.getSelectedRow())+"";
            veritabani.KayitSil(sorgu);
            sqlListele();
            
        }
        else{
            
            JOptionPane.showMessageDialog(this,"Herhangi bir kayıt seçilmedi");
            
        }        
    }//GEN-LAST:event_btnSilActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        text_sarkiAdi.setText(model.getValueAt(jTable1.getSelectedRow(), 0).toString());
        text_sarkiSuresi.setText(model.getValueAt(jTable1.getSelectedRow(), 2).toString());
        text_sanatciAdi.setText(model.getValueAt(jTable1.getSelectedRow(), 1).toString());
        //combo_sanatciAdi.get(model.getValueAt(jTable1.getSelectedRow(), 1).toString());
        text_albumAdi.setText(model.getValueAt(jTable1.getSelectedRow(), 5).toString());
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(SarkiEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SarkiEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SarkiEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SarkiEkle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SarkiEkle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEkle;
    private javax.swing.JButton btnGuncelle;
    private javax.swing.JButton btnSil;
    private javax.swing.JButton btn_cikis;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> combo_sanatciAdi;
    private javax.swing.JComboBox<String> combo_tur;
    private javax.swing.JComboBox<String> combo_ulke;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_kayitSayisi;
    private javax.swing.JRadioButton radio_sanGir;
    private javax.swing.JRadioButton radio_sanSec;
    private javax.swing.JTextField text_albumAdi;
    private javax.swing.JTextField text_sanatciAdi;
    private javax.swing.JTextField text_sarkiAdi;
    private javax.swing.JTextField text_sarkiSuresi;
    // End of variables declaration//GEN-END:variables
}
