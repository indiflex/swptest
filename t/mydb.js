const Promise = require('bluebird');

module.exports = class {
  constructor(pool) {
    this.pool = pool;
  }

  execute(fn) {
    console.log("mydb.execute!!")
    Promise.using( this.pool.connect(), conn => {
      fn(conn);
    });
  }

  executeTx(fn) {
    console.log("mydb.executeTx!!")
    Promise.using( this.pool.connect(), conn => {
      conn.beginTransaction( txerr => {
        fn(conn);
      });
    });
  }
};