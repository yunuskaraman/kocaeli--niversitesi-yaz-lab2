const admin = require("../models/admin");
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

exports.postAdminLogin = async (req,res,next) => {

    try{
        const adminLogin = await admin.findOne({email:req.body.email});

        !admin && res.status(400).json("Mail adresi Bulunamadı");
        console.log(adminLogin);

        const validPassword = await bcrypt.compare(
            req.body.sifre,
            adminLogin.sifre
        );
        
        
        !validPassword && res.status(400).json("Şifres Yanlış");
               
        jwt.sign({adminLogin:adminLogin}, 'secretkey', {expiresIn: '30m'}, (err,jwtToken) => {
            res.status(200).json({
                adminId: adminLogin.id,
                admin:true,
                jwtToken
            });
        });

        

    }catch(err){
        
        console.log("hata var",err);
        res.status(500).json(err);
    }
    
}