var path = require('path');

module.exports = {
  entry: './app/index.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'dist')
  },
  module: {
	loaders: [
		{
		  loader: "babel-loader",
		  // Skip any files outside of your project's `src` directory
		  include: [
			path.resolve(__dirname, "app"),
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
