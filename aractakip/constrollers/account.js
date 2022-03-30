const req = require("express/lib/request");
const res = require("express/lib/response");
const Musteri = require("../models/kullanici");

exports.getGiris = (req,res,next) => {

   var errorMessage = req.session.errorMessage;
   delete req.session.errorMessage;
    //console.log( Musteri.findAll())
    //console.log(req.session.isAuthenticated);
    res.render('giris',{title:'Giris Sayfası',path:'/giris',isAuthenticated:req.session.isAuthenticated,errorMessage:errorMessage})
}

exports.postGiris = (req,res,next) => {

    const kullanici_adi = req.body.kullanici_adi;
    const sifre = req.body.sifre;
    var i=0;
    Musteri.findAll()
                    .then(kullanicilar =>{
                        
                        while(kullanicilar.length>i){
                            console.log(kullanicilar[i]);
                            if((kullanicilar[i].kullanici_adi==kullanici_adi) && (kullanicilar[i].sifre == sifre)){
                                req.session.kullanici = kullanicilar[i];
                                //console.log("session kullanıcı",req.session.sid);
                                req.session.isAuthenticated = true;
                                req.session.gTarih = new Date();
                                console.log("Tarih : ",req.session.gTarih);
                                return req.session.save(function (err){
                                    console.log(err);
                                    res.redirect('/')
                                });
                                
                            }
                           i+=1;
                        }
                        req.session.errorMessage = "Kullanıcı adı ve ya şifre hatalı";
                        req.session.save(function (err){
                            console.log(err);
                            res.redirect('/giris');
                            console.log("Kullanıcı sayısı",kullanicilar.length);
                        })
                        
                        
                        
                        
                    });

    /*if((kullanici_adi == 'yunus') && (sifre == '123')){
        req.session.isAuthenticated = true;
        res.redirect('/');
    }
    else{
        res.redirect('/giris');
    }
   */
}

exports.getCikis = (req,res,next) => {
    var cTarih = new Date();
    Musteri.update({giris:req.session.gTarih, cikis:cTarih},{where:{id:req.session.kullanici.id}});
    req.session.destroy(err =>{
        console.log("cıkıs,err :",err);
        res.redirect('/giris');
    });
}

exports.postCikis = (req,res,next) => {
    res.redirect('/giris');
}