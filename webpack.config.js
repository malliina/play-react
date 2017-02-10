var path = require('path');

module.exports = {
    entry: {
        react: './web/react/react-src.js',
        preact: './web/preact/preact-src.js'
    },
    output: {
        filename: '[name].js',
        path: path.resolve(__dirname, 'public/js')
    },
    module: {
        loaders: [
            {
                loader: "babel-loader",
                include: [
                    path.resolve(__dirname, "web/react"),
                ],
                // Only run `.js` and `.jsx` files through Babel
                test: /\.jsx?$/,
                // Options to configure babel with
                query: {
                    plugins: ['transform-runtime'],
                    presets: ['es2015', 'react'],
                }
            },
            {
                loader: "babel-loader",
                include: [
                    path.resolve(__dirname, "web/preact"),
                ],
                // Only run `.js` and `.jsx` files through Babel
                test: /\.jsx?$/,
                // Options to configure babel with
                query: {
                    plugins: [
                        ["transform-react-jsx", { "pragma":"h" }]
                    ],
                    presets: ['es2015'],
                }
            }
        ]
    }
};
