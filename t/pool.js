const mysql   = require("mysql"),
      util    = require('util'),
      Promise = require("bluebird");

Promise.promisifyAll(mysql);
Promise.promisifyAll(require("mysql/lib/Connection").prototype);
Promise.promisifyAll(require("mysql/lib/Pool").prototype);

const DB_INFO = {
  host     : '115.71.233.22',
  user     : 'testuser',
  password : 'testuser!@#',
  database : 'testdb',
  multipleStatements: true,
  connectionLimit:5,
  waitForConnections:false
};

module.exports = class {
  constructor(dbinfo) {
    util.log("constructor of pool!!");

    dbinfo = dbinfo || DB_INFO;
    this.pool = mysql.createPool(dbinfo);
  }

  connect() {
    return this.pool.getConnectionAsync().disposer(function (connection) {
      console.log("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrealse pooooooooooooooool!!")
      return connection.release();
    });
  }

  end() {
    this.pool.end( function(err) {
      util.log(">>>>>>>>>>>>>>>>>>>>>>>>>>> End of Pool!!");
      if (err)
        util.log("ERR pool ending!!");
    });
  }
};