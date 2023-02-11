const bilet = require("../models/biletal");

exports.postBiletAdd = async (req,res) => {

    try{
        const newBilet = new bilet({
            durak_adi : req.body.durak_adi,
            kul_id:req.body.kul_id,
        });

        const savedBilet = newBilet.save();
        res.status(200).json(newBilet);
    }catch(err){
        res.status(500).json(err);
    }

}