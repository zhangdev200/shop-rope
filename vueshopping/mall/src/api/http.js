import axios from 'axios' //引入
import {Loading} from "element-ui";
import {Message} from "element-ui";

let baseURL = 'http://localhost:8080/'

let config = {
    baseURL: baseURL,
    timeout: 10000,       //设置最大请求时间
}
const _axios = axios.create(config);

let loading;

function startLoading() {
    loading = Loading.service({
        lock: false,
        text: 'loading...',
        background: 'rgba(0, 0, 0, 0)'
    });
}

function endLoading() {
    loading.close();
}

//showFullScreenLoading() tryHideFullScreenLoading() 将同一时刻的请求合并。
//声明一个变量 needLoadingRequestCount，每次调用showFullScreenLoading方法 needLoadingRequestCount + 1。
//调用tryHideFullScreenLoading()方法，needLoadingRequestCount - 1。needLoadingRequestCount为 0 时，结束 loading。
let needLoadingRequestCount = 0

function showFullScreenLoading() {
    if (needLoadingRequestCount === 0) {
        startLoading();
    }
    needLoadingRequestCount++;
}

function tryHideFullScreenLoading() {
    if (needLoadingRequestCount <= 0) return
    needLoadingRequestCount--;
    if (needLoadingRequestCount === 0) {
        endLoading();
    }
}

// 请求拦截器
_axios.interceptors.request.use(
    config => {
        showFullScreenLoading();
        let token = localStorage.getItem('token');
        if (token) {
            config.headers.token = token;  //添加token
        }
        return config;
    },
    err => Promise.reject(err)
);

//响应拦截器
_axios.interceptors.response.use(
    res => {
        tryHideFullScreenLoading();
        return res
    },
    err => {
        if (err.toString().endsWith('exceeded'))
            Message.error('请求超时');
        else
            Message.error(err);
        tryHideFullScreenLoading();
        return Promise.reject(err);
    }
);

//封装post,get,put方法
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
    },
    put(url = '', params = {}) {
        return new Promise((resolve, reject) => {
            _axios({
                url,
                data: params,
                headers: {'Content-Type': 'application/json;charset=UTF-8'},
                method: 'PUT'
            }).then(res => {
                resolve(res.data)
                return res;
            }).catch(error => {
                reject(error);
            })
        })
    },

}

export default http
