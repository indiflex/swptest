const nodeExternals = require('webpack-node-externals');

module.exports = {
	entry: './index.js',
	output: { filename: './ouput.js'},
  target: 'node',
  externals: [nodeExternals()]

}
