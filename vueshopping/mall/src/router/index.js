import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        children: [
            {
                path: '/goods',
                component: () => import('../views/Goods')
            },
            {
                path: '/goodsDetail',
                component: () => import('../views/GoodsDetail')
            },
            {
                path: '/cart',
                component: () => import('../views/Cart')
            },
            {
                path: 'login',
                component: () => import('../views/Login')
            },
            {
                path: 'register',
                component: () => import('../views/Register')

            },
            {
                path: 'personal',
                component: () => import('../views/Personal'),
                children: [
                    {
                        path: '/personalManage',
                        component: () => import('../components/PersonalManage')
                    },
                    {
                        path: '/orderManage',
                        component: () => import('../components/OrderManage')
                    },
                    {
                        path: '/storeManage',
                        component: () => import('../components/StoreManage')
                    },
                    {
                        path: '/sellerManage',
                        component: () => import('../components/SellerManage')
                    },
                ]
            },
        ]

    },
]

const router = new VueRouter({
    routes
})

export default router
