var path = require('path');

module.exports = {
  entry: './web/app/index.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'public/js')
  },
  module: {
	loaders: [
		{
		  loader: "babel-loader",
		  include: [
			path.resolve(__dirname, "web/app"),
		  ],
		  // Only run `.js` and `.jsx` files through Babel
		  test: /\.jsx?$/,
		  // Options to configure babel with
		  query: {
			plugins: ['transform-runtime'],
			presets: ['es2015', 'react'],
		  }
		}
	]
  }
};
