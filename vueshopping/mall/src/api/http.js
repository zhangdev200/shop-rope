import axios from 'axios' //引入

let baseURL = ''

let config = {
    baseURL: baseURL,
    timeout: 10000,       //设置最大请求时间
}
const _axios = axios.create(config);

// 请求拦截器
_axios.interceptors.request.use(
    config => {
        let token = localStorage.getItem('token');
        if (token) {
            config.headers.token = token;  //添加token
        }
        return config;
    },
    err => Promise.reject(err)
);

//响应拦截器
// _axios.interceptors.response.use(
//     res => {
//         // if (res.data.code === 401 ) {
//         //     Message.error('网络请求失败')
//         // }
//         // if (res.data.code === 400 ) {
//         //     Message.error('网络请求失败')
//         // }
//         // if (res.data.code === 404 ) {
//         //     Message.error('网络请求失败')
//         // }
//         return res;
//     },
//     err => {
//         if (err) {
//             Message.error('网络请求失败！')
//         }
//         return Promise.reject(err);
//     }
// );

//封装post,get方法
const http = {
    get(url = '', params = {}) {
        return new Promise((resolve, reject) => {
            _axios({
                url,
                params,
                headers: {'Content-Type': 'application/json;charset=UTF-8'},
                method: 'GET'
            }).then(res => {
                resolve(res.data);
                return res;
            }).catch(error => {
                reject(error);
            })
        })
    },
    post(url = '', params = {}) {
        return new Promise((resolve, reject) => {
            _axios({
                url,
                data: params,
                headers: {'Content-Type': 'application/json;charset=UTF-8'},
                method: 'POST'
            }).then(res => {
                resolve(res.data)
                return res;
            }).catch(error => {
                reject(error);
            })
        })
    }
}

export default http
