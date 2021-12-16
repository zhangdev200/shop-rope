module.exports = {
    publicPath: './',
    devServer: {
        proxy: {
            '/apis': {
                target: 'http://localhost:8080',
                changeOrigin: true,
                pathRewrite: {
                    '^/apis': ''
                }
            }
        }
    }
}