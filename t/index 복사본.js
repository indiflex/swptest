import util from 'util';

const express = require('express'),
      // util    = require('util'),
      HashMap = require('hashmap');


const app = express();
const testJson = require('./data/test.json');

const mydb = require('')

app.use(express.static('public'));

app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');
app.engine('html', require('ejs').renderFile);

app.get('/', (req, res) => {
  // res.send("Hello NodeJS!!");
  res.render('index', {name: '홍길동'});
});

app.get('/test/:email', (req, res) => {
  
  testJson.email = req.params.email;
  res.json(testJson);
});

app.get('/replies', (req, res) => {

});



const webserver = app.listen(7000, function(){
    console.log("Express Server's started on port 7000 by T");
});

const io = require('socket.io').listen(webserver, {
  log: false,
  origins: '*:*',
  pingInterval: 3000,
  pingTimeout: 5000
});

io.sockets.on('connection', function(socket, opt) {

  let q = socket.handshake.query;

  util.log('Client connected...', socket.id, socket.handshake.query);

  socket.emit('message', {msg: 'Welcome ' + socket.id}, socket.id, q);

  socket.on('join', function(data, fn){
    socket.join(data, function() {
      util.log("join>>", data, Object.keys(socket.rooms));

      if (fn)
        fn(data, Object.keys(socket.rooms));
    });
  }); 

  socket.on('leave', function(data, fn){
    socket.leave(data, function() {
      util.log("leave>>", data, Object.keys(socket.rooms));

      if (fn)
        fn(data, Object.keys(socket.rooms));
    });
  }); 

  socket.on('rooms', function(fn) {
    if (fn)
      fn(Object.keys(socket.rooms))
  });

  socket.on('message', function(data, fn){
    util.log("message>>", data, Object.keys(socket.rooms));
    if (fn)
      fn(data.msg);

    // socket.broadcast.to(data.room).emit('message', {msg: data.msg}); // 이 방에서 나를 제외한 사람 모두에게!!
    io.to(data.room).emit('message', {msg: data.msg}); // 이방 모두(나 포함)

    socket.broadcast.emit('message', {msg: 'BBBBBBBBBBBBBB'}) // 모든방에 모든 이에게 (나 제외!)
  });

  socket.on('disconnect', function(){
    util.log("disconnect>>", socket.rooms);
  });

  socket.on('disconnecting', (reason) => {
    let rooms = Object.keys(socket.rooms); //이 socket이 소속된 방들
    util.log("disconnecting>>", socket.rooms); // [ <socket.id>, 'room 237' ]
  });

});
