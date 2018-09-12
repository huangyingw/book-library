const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const HtmlWebpackTemplate = require('html-webpack-template');
const CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = {
  context: path.join(__dirname),
  entry: {
    bundle: path.resolve(__dirname, 'src/index.js')
  },
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
  plugins: [
    new HtmlWebpackPlugin({
      inject: false,
      title: 'Book Library',
      template: HtmlWebpackTemplate,
      appMountId: 'root',
      meta: {
        viewport: 'width=device-width, initial-scale=1, shrink-to-fit=no'
      },
      links: [
        {
          href: 'https://fonts.googleapis.com/css?family=Raleway',
          rel: 'stylesheet'
        }
      ]
    })
    // new CopyWebpackPlugin([
    //   {
    //     from: 'src/static/images',
    //     to: 'images'
    //   }
    // ])
  ]
};