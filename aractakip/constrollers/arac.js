const Arac = require('../models/araclar');
const Musteri = require('../models/kullanici');
const fs = require('fs');
//magodb sifre "ed2OQwF7H8dHliNe"
//mango yunuskaraman sifre :"GwCPQjSO7E11AAe1"

exports.getAraclar =(req, res, next) =>{

    //const araclar = Arac.getAll();
    //console.log(Arac.findAll());
    Arac.findAll()
                .then(aracs=> {
                    res.render('index',{title:'Anasayfa',araclar:aracs,path:'/',isAuthenticated: req.session.isAuthenticated});
                })
                .catch((err)=>{
                    console.log(err);
                });
   
    

};

exports.getAracEkle = (req,res,next) => {
    console.log("bool değeri nedir",req.session.isAuthenticated)
    if(!req.session.isAuthenticated){
        return res.redirect('/giris');
    }
/*
   Arac.findAll({where: {m_id:req.session.kullanici.id}})
                .then(araclar=> {
                    console.log("secilmiş id",araclar);
                })
                .catch(err => {
                    console.log(err)
            });
     */
    res.render('arac-ekle',{title:'admin arac ekle',path:'admin/arac-eke',isAuthenticated: req.session.isAuthenticated});

};

exports.postAracEkle = (req,res,next) => {
    
    if(!req.session.isAuthenticated){
        return res.redirect('/giris');
    }

    const model = req.body.aracModel;
    const yil = req.body.aracYili;
    const kilometre = req.body.aracKm;
    const resim = req.body.aracResim;
    const m_id = req.body.m_id;

    Arac.create({
        model : model,
        yil : yil,
        kilometre : kilometre,
        resim: resim,
        m_id: m_id
    })
    .then(result => {
        //console.log(result);
        
    })
    .catch(err => {
        console.log(err)
    });
    res.redirect('/');


    //database kayıt
    /*const araclar = new Arac (
        req.body.aracModel,
        req.body.aracYili,
        req.body.aracKm,
        req.body.aracResim,
        //req.body.m_id
    );
    araclar.saveArac();

    
    var id ='a'+req.body.m_id;
    console.log(id,req.body.m_id);
   
    
    var arac = {
        'id': {
            "model":req.body.aracModel,
            "yil":req.body.aracYili,
            "km":req.body.aracKm},
    };
    
    console.log('id',arac[id]);
    
    fs.readFile('arac.json','utf8',function(err,data){
        console.log(err);
        data = JSON.parse(data);
        console.log('id ya bak',id);
        data[id] = arac['id'];
        data = JSON.stringify(data)
        console.log('buraya kadar çalıştı',data);
        fs.writeFile('arac.json',data,function(err){
            console.log("bir hata oluştu");
        });
    });

   */
   
    
    
};  