import { createApp } from 'vue'
import App from './App.vue'

import axios from './request/index'
import router from './router/index'
import store from './store/index'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import * as echarts from 'echarts'

const app = createApp(App);

app.config.globalProperties.$http = axios

app.use(router)
router.beforeEach((to,from,next)=>{
    document.title = to.meta.title;
    if (!(document.title === 'Login' || document.title === 'Register') && !sessionStorage.getItem('customer')) {
        next("/")
    }
    next()
})
app.use(store)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(ElementPlus)

app.config.globalProperties.$echarts = echarts;

app.mount('#app')
