const express = require('express');
const router = express.Router();

const aracController = require('../constrollers/arac')
const haritaController = require('../constrollers/harita')

router.get('/',aracController.getAraclar);
router.get('/harita',haritaController.getHarita );
//router.post('/harita/:m_id',haritaController.postHarita );
module.exports = router