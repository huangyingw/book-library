const merge = require('webpack-merge');
const path = require('path');
const webpack = require('webpack');
const base = require('./webpack.base.js');

module.exports = merge(base, {
  mode: 'production',
  output: {
    filename: '[name].js',
    path: path.resolve(__dirname, 'dist')
  },
  plugins: [
    new webpack.DefinePlugin({
      SERVER_URL: JSON.stringify('http://production:8010')
    })
  ]
});