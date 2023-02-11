const express = require('express');
const app = express();
const mongoose = require("mongoose");


app.use(express.json());
app.use(express.urlencoded({extended: true}));


const userRouter = require('./routes/user');
const adminRouter = require('./routes/admin');


app.use('/user',userRouter);
app.use('/admin',adminRouter);




app.listen(8000, () =>{
    console.log('Listenning port on 8000...');
});
