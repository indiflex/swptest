const mysql = require('mysql');

// let connection = null;


module.exports = class {
  constructor() {

    console.log("============ Constructor of mysqldb ===========")

    this.connection = mysql.createConnection({
      host     : '115.71.233.22',
      user     : 'testuser',
      password : 'testuser!@#',
      database : 'testdb'
    });

    this.connection.connect( (err, conn) => {
      if (err) {
        console.error("Error on connecting!!", err);
        throw err;
      }

      console.log("created connection object!")
      this.connection.beginTransaction( err2 => {
        if (err) console.log("EEEEEEEE>>", err2)
      });
    });
  }

  begin(fn) {
    this.isTx = true;
    this.connection.beginTransaction( err => {
      fn(err);
    });
  }

  select(sql, params, fn) {
    this.connection.query(sql, params, (err, ret) => {
      if (err) {
        // if (this.isTx) 
          this.connection.rollback();
        console.error("Error on select>>", err)
        throw err;
      }

      fn(ret);
    });
  }

  update(sql, params, fn) {

  }

  close() {
    this.connection.end();
  }

}