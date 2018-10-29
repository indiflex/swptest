const Mydb = require('./mydb');

const Sql = {
  updateReply: "update Reply set replytext = ? where rno = ?"
};

module.exports = function(app, pool) {
  app.get('/dbtest/replies/:bno', (req, res) => {
    let mydb = new Mydb(pool);
    mydb.execute( conn => {
      conn.query("select * from Reply where bno=? limit 10", [req.params.bno], (err, ret) => {
        res.json(ret);
      });
    });
  });

  app.get('/dbtest/replies/:bno/:rno', (req, res) => {
    let mydb = new Mydb(pool);
    mydb.execute( conn => {
      conn.query("select * from Reply where rno = ? limit 10", [req.params.rno], (err, ret) => {
        res.json(ret[0]);
      });
    });
  });

  app.put('/dbtest/replies/:bno/:rno', (req, res) => {
    let rno = req.params.rno,
        replytext = req.body.replytext;

    let mydb = new Mydb(pool);
    mydb.executeTx( conn => {
      conn.query(Sql.updateReply, [replytext, rno], (err, ret) => {
        if (err) throw err;
        res.json({result: ret.affectedRows});
      });
    });
  });
}