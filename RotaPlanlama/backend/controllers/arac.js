const arac = require("../models/arac");

exports.postArackAdd = async (req,res) => {
    try{
        const newArac = new arac({
            plaka : req.body.plaka,
        });

        const savedArac = newArac.save();
        res.status(200).json(newArac);

    }catch(err){
        res.status(500).json(err);
    }

}

exports.getArac = (req,res) => {
    try{

        arac.find().then(aracs => {
            console.log(aracs);
            res.status(200).json(aracs);
        })
      

    }catch(err){
        console.log(err);
        res.status(500).json(err);
    }
}
