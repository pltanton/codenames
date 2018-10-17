const path = require("path");
const HtmlWebpackPlugin = require('html-webpack-plugin');

const HtmlWebpackPluginConfig = new HtmlWebpackPlugin({
  template : 'index.html',
  filename : 'index.html',
  inject : 'body',
});

module.exports = {
  context: path.resolve(__dirname, 'src'),
  entry: ["./app.tsx"],
  output: {
    path: path.resolve(__dirname, "build"),
    filename: "bundle.js"
  },
  resolve: {
    extensions: [".ts", ".tsx", ".js"]
  },

  module: {
    rules: [
      {
        test: /\.tsx?$/,
        loader: "ts-loader",
        exclude: /node_modules/
      }
    ]
  },

  plugins: [
		HtmlWebpackPluginConfig,
	],
};
