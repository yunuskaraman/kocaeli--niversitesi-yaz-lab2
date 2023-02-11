const mongoose = require("mongoose");
const db = require("./db");
const AracSchema = new mongoose.Schema({
    plaka: {
        type: String,
        require: true,
        min:5,
        max:20,
        enique:true

    },
   
}, {timestamps:true});

module.exports = mongoose.model("arac",AracSchema);