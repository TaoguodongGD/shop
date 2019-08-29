import Vue from 'vue'
import Router from 'vue-router'
import Backstage from './views/Backstage.vue'
import orderall from './components/order/orderall.vue'
import text from './components/shop/text.vue'
import UserQuery from './components/user/UserQuery.vue'
import Selectcategory from './components/Selectcategory.vue'
import addshop from './components/shop/addshop.vue'
import UserAdd from './components/user/UserAdd.vue'
import updateshop from './components/shop/updateshop.vue'
import chart1 from './components/chart/chart1.vue'
import chart2 from './components/chart/chart2.vue'
//qiantai
import Main from './views/main.vue'
import User from './views/User.vue'
import Login from './views/Login.vue'
import adminlogin from './views/adminlogin.vue'
import Shopone from './views/Shopone.vue'
import Resinger from './views/Resinger.vue'
import shopCart from './views/shopCart.vue'
import notfond from './views/404.vue'
import UserMessage from './components/usersub/UserMessage.vue'
import UserPush2 from './components/usersub/UserPush2.vue'
import Home from './views/Home.vue'


Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/backstage',
      name: 'backstage',
      component: Backstage,
      children:[
        {
          path: '/orderall',
          name: 'orderall',
          component: orderall,
        },
        {
          path: '/text',
          name: 'text',
          component: text,
        },
        {
          path: '/userquery',
          name: 'userquery',
          component: UserQuery,
        },
        {
          path: '/useradd',
          name: 'useradd',
          component: UserAdd,
        },
        {
          path: '/selectcategory',
          name: 'selectcategory',
          component: Selectcategory,
        },
        {
          path: '/addshop',
          name: 'addshop',
          component: addshop,
        },
        {
          path: '/updateshop',
          name: 'updateshop',
          component: updateshop,
        },
        {
          path: '/chart1',
          name: 'chart1',
          component: chart1
        },
        {
          path: '/',
          name: 'chart2',
          component: chart2
        },
        {
          path: '/chart2',
          name: 'chart2',
          component: chart2
        }
      ]

    },
    //前台
    {
      path: '/main',
 name: 'main',
      component: Main,
    },
   
 {
      path: '/Resinger',
      name: 'Resinger',
      component: Resinger
    },
    
{
      path: '/addshop',
      name: 'addshop',
      component: addshop
    },
    
{
      path: '/updateshop',
      name: 'updateshop',
      component: updateshop
    },
   
 {
      path: '/Login',
      name: 'Login',
      component: Login
    },
    
{
      path: '/adminlogin',
      name: 'adminlogin',
      component: adminlogin
    },
   
 {
      path: '/Shopone',
      name: 'Shopone',
      component: Shopone
    },
   
 {
      path: '/shopCart',
      name: 'shopCart',
      component: shopCart
    },
   
 {
      path: '/',
      name: 'home',
      component: Home,
    },
    
{
      path: '/user',
      name: 'user',
      component: User,
      
children: [
        {
          path: '/usermessage',
          name: 'usermessage',
          component: UserMessage
        },  
 {
          path: '/userpush2',
          name: 'userpush2',
          component: UserPush2
        }
      ]
    },
   
 {
      path: '*',
      name: 'notfond',
      component: notfond,
    }


 

  ]
})
