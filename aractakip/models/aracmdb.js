const mongoose = require("mongoose");

const aracSchema = new mongoose.Schema({
    tarih: {
        type: Date,
        require: true,
        enique:true

    },
    lat: {
        type: String,
        require: true,  
        enique:true

    },
    lng: {
        type: String,
        require: true,
        enique:true

    },
    arac_id: {
        type: Number,
        require: true,
        enique:true

    }
}, {timestamps:true});

module.exports = mongoose.model("aracmdb",aracSchema);