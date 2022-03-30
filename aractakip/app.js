const express = require('express');
const req = require('express/lib/request');
const res = require('express/lib/response');
const app = express();
const mongoose = require("mongoose");
const bodyParser = require('body-parser')
const path = require('path');
const cookieParser = require('cookie-parser');
const session = require('express-session');
const consumer = require("./constrollers/consumers");



//const sequelize = require('./models/database');
//var MongoDBStore = require('connect-mongodb-session')(session);
const sqlcumlesi ='mongodb+srv://yunuskaraman:GwCPQjSO7E11AAe1@cluster0.lhtg4.mongodb.net/aracTakip?retryWrites=true&w=majority';
/*
var store = new MongoDBStore({
    uri: sqlcumlesi,
    collection:'mySession'
  });
*/

var Sequelize = require("sequelize");
var SequelizeStore = require("connect-session-sequelize")(session.Store);

var sequelize = new Sequelize("MusArc", "postgres", "975034", {
    dialect: "postgres",
    storage: "session.postgres",
  });

app.set("view engine","pug");
app.set("views", "./View");

const adminRoutes = require('./routes/admin');

const userRoutes = require('./routes/user');

const accountRoutes = require('./routes/account');

const errorConstroller = require('./constrollers/error');
const Musteri = require('./models/kullanici');

var myStore = new SequelizeStore({
    db:sequelize

});

app.use(bodyParser.urlencoded({extended: false}))

app.use(express.static(path.join(__dirname,"/css")))
app.use(express.static(path.join(__dirname,"/View")))
app.use(cookieParser());
app.use(session({
    secret:'keyboard cat',
    resave:false,
    saveUninitialized:false,
    cookie:{
        maxAge:3600000
    },
    store:myStore
    
}));


mongoose.connect(sqlcumlesi,
    {
        useNewUrlParser: true,
        useUnifiedTopology: true
    }).then(()=>{
        console.log("mangoodb Connected")
    }).catch((err) => console.log(err));
    
/*
app.use((req,res,next) => {
    if(!req.session.kullanici){
        return next();
    }
    console.log(req.session.kullanici.id)
    /*
    Musteri.findById(req.session.kullanici.id)
            .then(kullanici =>{
                req.kullanici=kullanici;
                next();
            });
            
})

*/
app.use('/admin',adminRoutes);
app.use(userRoutes);
app.use(accountRoutes);


consumer;

app.use(errorConstroller.get404);

/*
try {
    sequelize.authenticate();
    console.log('Connection has been established successfully.');
  } catch (error) {
    console.error('Unable to connect to the database:', error);
}
*/
/*
sequelize.sync()
        .then(result => {
            console.log(result);
        })
        .catch( err => {
            console.log(err);
        }
    );
*/
app.listen(3000, () =>{
    console.log('Listenning port on 3000...');
});


