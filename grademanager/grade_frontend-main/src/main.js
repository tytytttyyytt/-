import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import leftSide from './components/leftSide.vue'
import breadcrumbs from './components/breadcrumbs.vue'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import global from './plugins/global';

Vue.prototype.global = global;

Vue.config.productionTip = false
Vue.prototype.$http = axios

Vue.use(ElementUI)

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

new Vue({
  router,
  store,
  render: h => h(App),
  leftSide,
  vuetify,
  breadcrumbs,
  axios,
  data: function () {
    return {
      userInfo: null,
    }
  },
}).$mount('#app')
