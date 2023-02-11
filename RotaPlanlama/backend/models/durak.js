const mongoose = require("mongoose");
const db = require("./db");

const DurakSchema = new mongoose.Schema({
    durak_adi: {
        type: String,
        require: true,
        min:5,
        max:20,
        enique:true

    },
    lat: {
        type: String,
        require: true,  
        max:50,
        enique:true

    },
    lng: {
        type: String,
        require: true,
        min:6,
        enique:true
    },
    
   
}, {timestamps:true});

module.exports = mongoose.model("durak",DurakSchema);