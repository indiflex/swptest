const ogs = require('open-graph-scraper'),
      HashMap = require('hashmap'),
      Crypto = require('crypto'),
      CryptoJS = require('crypto-js'),
      SHA256 = require("crypto-js/sha256");

const EKey = 'cryptoKey',
      EType = 'aes-256-ctr';

module.exports = {
  ogs(url, fn) {
    return ogs({url: url}, (err, ret) => {
      fn(err, ret);
    });
  },

  encryptSha2(data, key) {
    if (!data) return null;

    key = key || '';

    try {
      return Crypto.createHash("sha256").update(data + key).digest("hex");
    } catch (Err) {
      console.log("Error on utils.encryptSha2>>", Err);
    }
  },

  encrypt(data, key) { // 양방향 암호화
    return CryptoJS.AES.encrypt(data, key || EKey).toString();
  },

  decrypt(data, key) { // 양방향 복호화
    return CryptoJS.AES.decrypt(data, key || EKey).toString(CryptoJS.enc.Utf8);
  },

  makeHashmap() {
    return new HashMap();
  }
}