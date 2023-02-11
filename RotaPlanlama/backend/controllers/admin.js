const admin = require("../models/admin");
const bcrypt = require('bcrypt');

exports.postAdminAdd = async (req,res) => {
    try{
        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(req.body.sifre, salt);

        const newAdmin = new admin({
            adi : req.body.adi,
            soyadi:req.body.soyadi,
            tel:req.body.tel,
            email : req.body.email,
            sifre : hashedPassword,
        });

        const saveAdmin = newAdmin.save();
        res.status(200).json(saveAdmin);
    }catch(err){
        res.status(500).json(err);
    }

}

exports.getAdminAdd = async (req,res) => {
   
    res.send("/");

}