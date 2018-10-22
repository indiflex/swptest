const util = require('util');

const mysql = require('mysql');
const Mysqldb = require('./mysqldb');
const Pool = require('./pool');

let sql1 = "update User set lastlogin=now() where uid='user1'",
    sql2 = "update User set lastlogin=now() where uid='user2'",
    sql3 = "update User set lastlogin=now() where uidx='js'",
    sql4 = "update User set lastlogin=now() where uid='naver'";

let pool = new Pool();

// pure();
// mdb();



function pure() {
  const connection = mysql.createConnection({
    host     : '115.71.233.22',
    user     : 'testuser',
    password : 'testuser!@#',
    database : 'testdb'
  });
  console.log("11111111111111111")
   
  connection.connect( (err, conn) => {
    console.log("222222222222222222222: connected", conn)
  });

  connection.beginTransaction(err2 => {
    console.log("TTTTTTTTTXXXXXXXXXX", err2)
    connection.query("update User set lastlogin=now() where uid=?", ['user1'], (err, ret) => {
      console.log("user1>>", err, ret)
      if (err)
        connection.rollback();
      else
        connection.commit();

      connection.end();
    });
  });
   
  console.log("3333333333333333")
  connection.query('SELECT * from User where uid = ?', ['user1'], function (error, results, fields) {
    console.log("44444444444444444444: selected")
    if (error) throw error;
    console.log('The First user is: ', results[0]);
  });
   
  console.log("55555555555555555555")
  // connection.end( (err) => {
  //   console.log("66666666666666666")
  // });
}

function mdb() {
  const mysqldb = new Mysqldb();

  // mysqldb.begin( err => {
    mysqldb.select("update User set lastlogin=now() where uid=?", ['user1'], (ret) => {
      console.log("user1>>", ret)
    });  

    mysqldb.select("delete from User where uidx=?", ['xxx'], ret => {
      console.log("DDDD>>", ret)
    });
  // });

  mysqldb.select("select * from User where uid=?", ['user1'], (ret) => {
    console.log("TTTTTTTTTTTTTTTTTT", ret)
  });

  mysqldb.close();
}
