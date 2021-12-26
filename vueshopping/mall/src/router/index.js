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
        redirect: '/main',
        children: [
            {
                path: '/main',
                name: 'main',
                component: () => import('../views/Search'),
                redirect: '/index',
                children: [
                    {
                        path: '/index',
                        name: 'index',
                        component: () => import('../views/DefaultResult'),
                        meta: {
                            keepAlive: true,
                        }
                    },
                    {
                        path: '/search/:keyword',
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
                        path: '/system/carousel',
                        component: () => import('../components/SystemManage/SystemCarousel'),
                    },
                    {
                        path: '/system/user',
                        component: () => import('../components/SystemManage/SystemUser'),
                    }
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
