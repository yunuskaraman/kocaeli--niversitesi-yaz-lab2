module.exports.get404 =(req,res)=>{
    if(!req.session.isAuthenticated){
        return res.redirect('/giris');
    }
    res.status(404).render('404',{title:'404',isAuthenticated: req.session.isAuthenticated})
};