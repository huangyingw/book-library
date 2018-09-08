const webpack = require('webpack');
const path = require('path');

const environment = process.env.NODE_ENV === 'development' ? 'development' : 'production';
const SERVER_URL = {
  development: JSON.stringify('http://localhost:8010'),
  production: JSON.stringify('http://production:8010')
};

module.exports = {
  entry: './src/index.js',
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: ['babel-loader', 'eslint-loader']
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader']
      },
      {
        test: /\.(jpe?g|png|ttf|eot|svg|woff(2)?)(\?[a-z0-9=&.]+)?$/,
        use: 'base64-inline-loader?limit=1000&name=[name].[ext]'
      }
    ]
  },
  resolve: {
    extensions: ['*', '.js', '.jsx']
  },
  output: {
    path: path.join(__dirname, '/dist'),
    publicPath: '/',
    filename: 'bundle.js'
  },
  devServer: {
    contentBase: './dist',
    historyApiFallback: true
  },
  plugins: [
    new webpack.DefinePlugin({
      SERVER_URL: SERVER_URL[environment]
    })
  ]
};