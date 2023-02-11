const  router = require('express').Router();
const userLogin = require("../controllers/userLogin");
const arac = require('../controllers/arac');
const durak = require('../controllers/durak');
const bilet = require('../controllers/biletal');

router.get('/durak',durak.getDurak);
router.get('/arac',arac.getArac);
router.post('/login',userLogin.postUserLogin);
router.post('/biletal',bilet.postBiletAdd);

module.exports = router;