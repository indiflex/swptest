const fs = require('fs');
const utils = require('./utils');

let str = 'corba';
let enc = utils.encryptSha2(str);
let enc2 = utils.encryptSha2(str, 'key');
console.log(enc)
console.log(enc2)

console.log("str>>", str)
enc = utils.encrypt(str);
console.log("encrypt>>", enc)
console.log("decrypt>>", utils.decrypt(enc))

return;
const hm = utils.makeHashmap();
hm.set('id', 123);
hm.set('name', '홍길동');
console.log(hm, hm.get('name'))

utils.ogs('https://youtube.com', (err, ret) => {
  console.log("ogs>>", err, ret)  
});


fs.readFile('test.json', 'utf-8', (err, data) => {
	if (err) return console.error(err);

	console.log("data>>", data);
});

console.log("------------------------");

let data2 = fs.readFileSync('test.json', 'utf-8');
console.log("data2>>", data2);

console.log("===================================");
