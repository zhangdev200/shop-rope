const Version = new Date().getTime().toString().match(/.*(.{8})/)[1] // 截取时间戳后八位
module.exports = {
    publicPath: '/',
    devServer: {
        proxy: {
            '/apis': {
                target: 'http://localhost:8080',
                changeOrigin: true,
                pathRewrite: {
                    '^/apis': ''
                }
            }
        },
    },
    configureWebpack:{  //生成不同文件名，避免缓存
        output: {
            filename: `js/[name].${Version}.js`,
            chunkFilename: `js/[name].${Version}.js`
        }
    }
}