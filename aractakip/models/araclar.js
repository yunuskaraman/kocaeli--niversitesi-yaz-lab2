const Sequelize = require('sequelize');
const sequelize = require('./database');

const Araclar = sequelize.define('arac',{
    id : {
        type: Sequelize.INTEGER,
        autoIncrement:true,
        allowNull:false,
        primaryKey:true
    },
    model : {
        type: Sequelize.STRING,
        allowNull: false
    },
    yil : {
        type: Sequelize.INTEGER,
        allowNull:false,
    },
    kilometre : {
        type: Sequelize.INTEGER,
        allowNull:false,
    },
    resim : {
        type: Sequelize.STRING,
        allowNull: false
    },
    m_id : {
        type: Sequelize.INTEGER,
        allowNull:false,
    }
});


module.exports = Araclar;

