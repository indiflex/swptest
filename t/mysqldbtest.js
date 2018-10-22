const Mysqldb = require('./mysqldb');

const mdb = new Mysqldb();
const mdb2 = new Mysqldb();

mdb.select('select count(*) from User', null, (ret) => {
  console.log("retttttttt>>", ret);
});

mdb.select('select * from User where uid = ?', 'user1', (ret) => {
  console.log("retttttttt>>", ret);
});

mdb2.select('select * from User where uid = ?', ['user2'], (ret) => {
  console.log("retttttttt>>", ret);
});

// mdb.close();