const Sequelize = require('sequelize');

const sequelize = new Sequelize('MusArc','postgres','975034',{
    dialect:'postgres',
    host:'localhost'
});

module.exports = sequelize;