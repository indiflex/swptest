console.log("This is index.js")

const express = require('express');
const app = express();

const testJson = require('./data/test.json');

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



const server = app.listen(7000, function(){
    console.log("Express Server's started on port 7000");
});
