const express = require('express');
const router = express.Router();

const accountController = require('../constrollers/account')

router.get('/giris',accountController.getGiris);
router.post('/giris',accountController.postGiris);

router.get('/cikis',accountController.getCikis);

module.exports = router;