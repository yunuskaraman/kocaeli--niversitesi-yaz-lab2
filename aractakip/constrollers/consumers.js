
var amqp = require('amqplib/callback_api');
const aracmdb = require('../models/aracmdb');
const res = require('express/lib/response');    

amqp.connect('amqp://localhost', function(error0, connection) {
    if (error0) {
        console.log('error0',error0);
        throw error0;
    }
    connection.createChannel(function(error1, channel) {
        if (error1) {
            console.log('error1',error1);
        throw error1;
        }
     
        console.log("mesaj bekleniyor..");
        
        channel.consume('hello', async (mesaj) => {

        var data = JSON.parse(mesaj.content.toString());
       
        //res.render('harita',{title:'harita',path:'harita',data:data});
        

        /*
        const newArac = await new aracmdb({
            tarih : data.tarih,
            lat : data.lat,
            lng : data.lng,
            arac_id : data.arac_id
        });

        newArac.save();
        */
        channel.ack(mesaj);
        console.log(data);

        });
    });
});
