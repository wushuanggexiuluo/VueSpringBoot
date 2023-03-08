import Vue from 'vue'
import App from './App.vue'
import VueRouter from "vue-router";
import MyRouter from "@/routers";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VWave from 'v-wave'
import { DropdownMenu, DropdownItem } from 'vant';
import 'vant/lib/index.css'
import { ActionSheet } from 'vant';
import { Form } from 'vant';
import { Field } from 'vant';
import { Button } from 'vant';
import { Search } from 'vant';
import { Tag } from 'vant';


Vue.use(Tag);
Vue.use(Search);
Vue.use(Button);
Vue.use(Form);
Vue.use(Field)
Vue.use(ActionSheet);
Vue.use(DropdownMenu);
Vue.use(DropdownItem);
Vue.use(VWave)
Vue.use(ElementUI)
Vue.config.productionTip = false
Vue.use(VueRouter)
import axios from 'axios'
import ShowAdminMessage from "@/mixs/ShowAdminMessage";
import Operate from "@/mixs/Operate";
//全局混入
Vue.mixin(ShowAdminMessage)
Vue.mixin(Operate)
//引入完成之后需要挂载到vue上
// 全局设置 axios 发送请求带上cookie
axios.defaults.withCredentials = true
Vue.prototype.$http = axios
import Vuex from 'vuex'
import AxiosHandle from "@/handle/AxiosHandle";
Vue.use(Vuex)

//定义仓库
const myStore=new Vuex.Store({
  state:{
    user:"张大妈"
  },
})

AxiosHandle()
new Vue({
  Vuex,
  render: h => h(App),
  router:MyRouter,
}).$mount('#app')
