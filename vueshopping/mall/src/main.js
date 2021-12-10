import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/icon/iconfont.css'

Vue.use(ElementUI)
Vue.config.productionTip = false

router.afterEach(() => {
  window.scrollTo(0, 0);
});

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
