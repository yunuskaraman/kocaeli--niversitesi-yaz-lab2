const mongoose = require("mongoose");
const db = require("./db");

const BiletSchema = new mongoose.Schema({
    durak_adi: {
        type: String,
        require: true,
        min:5,
        max:20,
        enique:true

    },
    kul_id: {
        type: String,
        require: true,  
        max:50,
        enique:true

    },
   
}, {timestamps:true});

module.exports = mongoose.model("bilet",BiletSchema);