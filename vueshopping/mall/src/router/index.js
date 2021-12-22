import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        redirect: '/main',
        children: [
            {
                path: '/main',
                component: () => import('../views/Goods'),
                redirect: '/index',
                children: [
                    {
                        path: '/index',
                        component: () => import('../views/DefaultResult')
                    },
                    {
                        path: '/search',
                        component: () => import('../views/SearchResult')
                    }
                ]
            },
            {
                path: '/goods/:id',
                component: () => import('../views/GoodsDetail')
            },
            {
                path: '/cart',
                component: () => import('../views/Cart')
            },
            {
                path: '/login',
                component: () => import('../views/Login')
            },
            {
                path: '/register',
                component: () => import('../views/Register')

            },
            {
                path: '/personal',
                component: () => import('../views/Personal'),
                redirect: '/personal/personal',
                children: [
                    {
                        path: '/personal/personal',
                        component: () => import('../components/PersonalManage'),
                    },
                    {
                        path: '/personal/order',
                        component: () => import('../components/OrderManage'),
                    },
                    {
                        path: '/personal/store',
                        component: () => import('../components/StoreManage'),
                    },
                    {
                        path: '/system/seller',
                        component: () => import('../components/SystemManage/SystemSeller'),
                    },
                    {
                        path: '/system/category',
                        component: () => import('../components/SystemManage/SystemCategory'),
                    },
                    {
                        path: '/system/recommend',
                        component: () => import('../components/SystemManage/SystemRecommend'),
                    },
                    {
                        path: '/system/carousel',
                        component: () => import('../components/SystemManage/SystemCarousel'),
                    },
                ]
            },
        ]
    },
]

const router = new VueRouter({
    // mode: 'history',
    routes
})

export default router
