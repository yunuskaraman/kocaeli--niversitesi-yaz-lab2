const express = require('express');
const router = express.Router();
const aracController = require('../constrollers/arac')


router.get('/arac-ekle',aracController.getAracEkle);

router.post('/arac-ekle',aracController.postAracEkle);


module.exports = router;