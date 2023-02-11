const durak = require("../models/durak");

exports.postDurakAdd = async (req,res) => {
    try{
        const newDurak = new durak({
            durak_adi : req.body.durak_adi,
            lat:req.body.lat,
            lng:req.body.lng,
        });

        const savedDurak = newDurak.save();
        res.status(200).json(newDurak);
    }catch(err){
        res.status(500).json(err);
    }

}
exports.getDurak = (req,res) => {
    try{

        durak.find().then(duraks => {
            //console.log(duraks);
            const sonuc = {
                data:duraks
            }
            res.status(200).json(sonuc);
        })
      console.log(req.kullanici);

    }catch(err){
        console.log(err);
        res.status(500).json(err);
    }
}
