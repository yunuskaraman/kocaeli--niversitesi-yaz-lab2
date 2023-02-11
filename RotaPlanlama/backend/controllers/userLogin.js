const  router = require('express').Router();
const user = require('../models/user');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

exports.postUserLogin = async (req,res,next) => {

    try{
        const kullanici = await user.findOne({email:req.body.email});

        !kullanici && res.status(400).json("Mail adresi bulunamadı");

        const validPassword = await bcrypt.compare(
            req.body.sifre,
            kullanici.sifre
        );
        

        req.kullanici = kullanici;
       // console.log("kullanıcı",req.kullanici);
        
        !validPassword && res.status(400).json("Şifres Yanlış");

        console.log(req.kullanici);
        jwt.sign({kullanici:kullanici}, 'secretkey', {expiresIn: '30m'}, (err,jwtToken) => {
           // token = jwtToken;
            res.status(200).json({
                kullaniciId: kullanici.id,
                admin:false,
                jwtToken
            });
        });
        //console.log(token.toString());
    }catch(err){
        res.status(500).json(err);
    }
    
}