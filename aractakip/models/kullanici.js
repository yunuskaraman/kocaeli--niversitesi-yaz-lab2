const Sequelize = require('sequelize');
const sequelize = require('./database');

const Musteri = sequelize.define('musteriler',{
    id : {
        type: Sequelize.INTEGER,
        autoIncrement:true,
        allowNull:false,
        primaryKey:true
    },
    adi: {
        type: Sequelize.STRING,
        allowNull: false
    },
    soyadi: {
        type: Sequelize.STRING,
        allowNull: false
    },
    tel: {
        type: Sequelize.STRING,
        allowNull: false
    },
    adres: {
        type: Sequelize.STRING,
        allowNull: false
    },
    giris: {
        type: Sequelize.DATE,
        defaultValue:Sequelize.NOW,
        allowNull: false
    },
    cikis: {
        type: Sequelize.DATE,
        defaultValue:Sequelize.NOW,
        allowNull: false
    },
    durum: {
        type: Sequelize.BOOLEAN,
        defaultValue:false,
        allowNull: false
    },
    kullanici_adi: {
        type: Sequelize.STRING,
        allowNull: false
    },
    sifre: {
        type: Sequelize.STRING,
        allowNull: false
    }

});

module.exports = Musteri;