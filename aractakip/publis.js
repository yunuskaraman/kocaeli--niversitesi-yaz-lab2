var amqp = require('amqplib/callback_api');
const fs = require('fs');

var queue = 'hello';
data1 = 'Bilgiler';
amqp.connect('amqp://localhost', function(error0, connection) {
  if (error0) {
    throw error0;
  }
  connection.createChannel(function(error1, channel) {
    if (error1) {
      throw error1;
    }

  fs.readFile('arac.json','utf8',function(err,data){
    data = JSON.parse(data);
    if(!err){
      for (var i = 0; i < 2; i++) {
     
        var data1 = data[i];
        console.log(data1);
        data1 = JSON.stringify(data1);
        channel.sendToQueue(queue, Buffer.from(data1));
        //console.log(" [x] Sent %s", data1);
      }
      console.log(i);
    }else{
      console.log('error',err);
    }
    
  });
    
 
/*
setInterval(() => {
  if(i<6){
    setTimeout
  }
    fs.readFile('arac.json','utf8',function(err,data){
      data = JSON.parse(data);
      //console.log(data['1']);
      data = data[i.toString()];
      console.log( data);
      data = JSON.stringify(data);
      channel.sendToQueue(queue, Buffer.from(data));
      console.log(" [x] Sent %s", data);
      });
    i+=1;
  
},1000);
    */
  });
});

