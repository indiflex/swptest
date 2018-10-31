const Mydb = require('./mydb');

const Sql = {
  updateReply: "update Reply set replytext = ? where rno = ?"
};

module.exports = function(app, pool) {
  // Survey 
  app.get('/apis/surveys', (req, res) => {
    let mydb = new Mydb(pool);
    mydb.execute(conn => {
      conn.query("select * from Survey limit 1000", (err, ret) => {
        res.json(ret);
      });
    });
  });

  app.get('/apis/surveys/:id', (req, res) => {
    let id = req.params.id;
    let mydb = new Mydb(pool);
    mydb.execute(conn => {
      conn.query("select * from Survey where id = ?", [id], (err, ret) => {
        if (err) throw err;
        res.json(ret[0]);
      });
    });
  });

  app.put('/apis/surveys/:id', (req, res) => {
    let id = req.params.id,
      title = req.body.title,
      state = req.body.state || '0';

    let mydb = new Mydb(pool);
    mydb.executeTx(conn => {
      conn.query("update Survey set title=?, state=? where id = ?", [title, state, id], (err, ret) => {
        if (err) throw err;
        res.json(ret.affectedRows);
      });
    });
  });

  app.post('/apis/surveys', (req, res) => {
    let title = req.body.title;

    let mydb = new Mydb(pool);
    mydb.executeTx(conn => {
      conn.query("insert into Survey(title, state) values(?, 0)", [title], (err, ret) => {
        if (err) throw err;
        res.json(ret.affectedRows);
      });
    });
  });



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