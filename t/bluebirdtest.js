const Promise = require('bluebird'),
      util = require('util');

const Pool = require('./pool');

let sql1 = "update User set lastlogin=now() where uid='user1'",
    sql2 = "update User set lastlogin=now() where uid='user2'",
    sql3 = "update User set lastlogin=now() where uidx='js'",
    sql4 = "update User set lastlogin=now() where uid='naver'";

let pool = new Pool();

Promise.using( pool.connect(), conn => {
  conn.queryAsync(sql1, (err, ret) => {
    util.log("sql1=", ret.affectedRows);
    
    conn.query(sql2, (err2, ret2) => {
      util.log("sql2=", ret2.affectedRows);
    });
  });
});


Promise.using( pool.connect(), conn => {
  Promise.all([
    conn.queryAsync(sql1),
    conn.queryAsync(sql2)
  ]).then( r => {
    util.log("End of Then!!!!!!!!!!!!!!!!!!!");
    util.log("sql1=", r[0].affectedRows);
    util.log("sql2=", r[1].affectedRows);
    pool.end();
  });
});

Promise.using( pool.connect(), conn => {
  conn.beginTransaction( txerr => {
    Promise.all([
      conn.queryAsync(sql1),
      // conn.queryAsync(sql2)
      conn.queryAsync(sql2).then( r2 => {
        console.log("22222222222222222222222222222222");
        conn.queryAsync(sql4);
        return r2;
      })
      , conn.queryAsync(sql3)
    ]).then( r => {
      util.log("End of Then!!!!!!!!!!!!!!!!!!!", r.length);
      for (let i = 0; i < r.length; i++)
        util.log(`sql${i+1}=`, r[i].affectedRows);
      conn.commit();
      pool.end();
    }).catch( e => {
      conn.rollback();
      util.log("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", e)
      pool.end();
    });
  });
});


// Promise.using( pool.connect(), conn => {
//   util.log("=================================111111111111111111")
//   conn.queryAsync(sql1).then( r => {
//     util.log("1111111QQQQQQQQQQQQQQQQQQQQQQ", r.affectedRows)
//   });

//   util.log("=================================")

//   conn.queryAsync(sql2).then( r => {
//     util.log("22222QQQQQQQQQQQQQQQQQQQQQQ", r.affectedRows)
//   }, e => {
//     util.log("Error sql2!!", e)
//   });

//   util.log("=================================3333333333333")
// }).then( t => {
//   util.log("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeennnnnnnnnnnndddddddddddd");
//   pool.end();
// });


// Promise.using( pool.connect(), conn => {
//   let tx = conn.beginTransaction();

//   Promise.try( () => {

//     util.log("=================================111111111111111111")
//     conn.queryAsync(sql1).then( r => {
//       util.log("1111111QQQQQQQQQQQQQQQQQQQQQQ", r.affectedRows)
//     }).catch(e1 => {
//       util.log("eeeeeeeeeeeeee1111111111>>")
//     });

//     util.log("=================================")

//     conn.queryAsync(sql2).then( r => {
//       util.log("22222QQQQQQQQQQQQQQQQQQQQQQ", r.affectedRows)
//     }, e => {
//       util.log("Error sql2!!", e)
//     });

//     conn.queryAsync(sql3).then( r => {
//       util.log("33333QQQQQQQQQQQQQQQQQQQQQQ", r.affectedRows)
//     }, e => {
//       util.log("Error sql3!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
//       throw e;
//     }).catch(e3 => {
//       util.log("eeeeeeeeeeeeee333333333333333333>>")
//       throw e3;
//     });

//     util.log("=================================3333333333333")

//   }).then( txr => {
//     console.log("tttttttttttttttttttttttttttttttttttttttttttttttthen!!!!!!!!!!!!!!!!!!!!!!!", txr)
//   }, (txe) => {
//     console.log("ttttttttttttttttttttttttttttttttttttttttttttttttcatch!!!!!!!!!!!!!!!!!!!!!!!", txe)
//   });

// }).then( () => {
//   pool.end();
//   util.log("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeennnnnnnnnnnndddddddddddd");
// }).catch( eee => {  // 안먹어!!
//   util.log("catttttttttttttch>>", eee)
// })