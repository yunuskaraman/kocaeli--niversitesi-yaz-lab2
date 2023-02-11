const  router = require('express').Router();
const userAdd = require("../controllers/user");
const durakAdd = require("../controllers/durak");
const aracAdd = require("../controllers/arac");
const AdminAdd = require("../controllers/admin");
const login = require("../controllers/adminLogin");


router.post('/login',login.postAdminLogin);
router.post('/admin-add',AdminAdd.postAdminAdd);
router.post('/user-add',userAdd.postUserAdd);
router.post('/durak-add',durakAdd.postDurakAdd);
router.post('/arac-add',aracAdd.postArackAdd);
router.get('/',userAdd.getUserAdd);

module.exports = router;