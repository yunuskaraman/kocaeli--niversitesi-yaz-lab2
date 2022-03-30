const Arac = require('../models/araclar');
var amqp = require('amqplib/callback_api');
const Aracgps = require('../models/aracmdb');

exports.getHarita = (req,res,next) => {

    if(!req.session.isAuthenticated){
        return res.redirect('/giris');
    }


    
   // dic = JSON.stringify(dic);
    //console.log(dic)
    Arac.findAll({where: {m_id:req.session.kullanici.id}})
                .then(araclar => {
                    //console.log(araclar[0].id);
                    Aracgps.find().where({$or: [ { arac_id: araclar[0].id } , {arac_id: araclar[1].id} ] })
                        .then(aracgp => {
                            console.log("ARACLAR GPS",aracgp);
                            aracgp = JSON.stringify(aracgp);
                            res.render('harita',{title:'harita',path:'harita',isAuthenticated: req.session.isAuthenticated,araclar:araclar,aracgps:aracgp});
                        }).catch(err => {
                            console.log(err);
                        });
                    
                })
                .catch(err => {
                    console.log(err)
                });
    
    
};


/*
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
        
        channel.consume('hello', (mesaj,next) => {
        //mesaj = JSON.parse(mesaj)

        //console.log(mesaj.content.toString());

        var data = JSON.parse(mesaj.content.toString());
        console.log(data);
        return data;
        });
    });
});
*/




/*
    const apiOptions = {
        apiKey: "AIzaSyD7oN2a3wUv6GhxxBrhfwztZZ3YJOgWHN0"
    };

    const loader = new Loader(apiOptions);

    loader.then(() => {
        console.log('Maps JS API Loaded');
      });
  */
/*
    const googleMapsAPIKey = "AIzaSyD7oN2a3wUv6GhxxBrhfwztZZ3YJOgWHN0";

    const mapOptions = {
        center: {
          lat: 41.015137,
          lng: 28.979530
        },
        zoom: 12
      }
    const apiOptions = {
        version: 'weekly',
        libraries: ['places']
    }

    const mapLoaderOptions = {
        apiKey: googleMapsAPIKey,
        divId: 'google_map',
        append: true, // Appends to divId. Set to false to init in divId.
        mapOptions: mapOptions,
        apiOptions: apiOptions
    };

    const mapLoader = new GoogleMap();
    console.log("çalıştı");
// Load the map
    mapLoader
    .initMap(mapLoaderOptions)
    .then(googleMap => {
        
        // returns instance of google.maps.Map
        console.log('google map',googleMap);
    });
*/

/*
var amqp = require('amqplib/callback_api');
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
    
    channel.consume('hello', (mesaj,next) => {
    //mesaj = JSON.parse(mesaj)

    //console.log(mesaj.content.toString());

    var data = JSON.parse(mesaj.content.toString());
    
    channel.ack(mesaj);
   })
  });
});
*/

   
    


/*
exports.postHarita = (req,res,next) => {
    if(!req.session.isAuthenticated){
        return res.redirect('/giris');
    }
  
    console.log(req.session.kullanici.id);
    res.render('harita',{title:'harita',path:'./includes/navbar',isAuthenticated: req.session.isAuthenticated,m_id:req.session.kullanici.id});
};
*/