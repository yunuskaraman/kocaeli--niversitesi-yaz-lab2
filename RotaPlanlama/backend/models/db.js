const mongoose = require("mongoose");

const sqlcumlesi ='mongodb+srv://yunuskaraman:GwCPQjSO7E11AAe1@cluster0.lhtg4.mongodb.net/rotaplan?retryWrites=true&w=majority';
exports.module = mongoose.connect(sqlcumlesi,
{
    useNewUrlParser: true,
    useUnifiedTopology: true
}).then(()=>{
    console.log("mangoodb Connected")
}).catch((err) => console.log(err));
