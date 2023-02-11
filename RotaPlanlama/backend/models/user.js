const mongoose = require("mongoose");
const db = require("./db");
const UserSchema = new mongoose.Schema({
    adi: {
        type: String,
        require: true,
        min:5,
        max:20,
        enique:true

    },
    soyadi: {
        type: String,
        require: true,  
        max:50,
        enique:true

    },
    tel: {
        type: String,
        require: true,
        min:6,
        enique:true

    },
    email: {
        type: String,
        require: true,
        min:6,
        enique:true

    },
    sifre: {
        type: String,
        require: true,
        enique:true

    },
}, {timestamps:true});

module.exports = mongoose.model("User",UserSchema);