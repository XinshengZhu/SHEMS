import {createStore} from 'vuex'

export default createStore({
    state(){
        return {
            customer: {}
        }
    },
    mutations:{
        setCustomer(state,payload){
            state.customer = payload
            sessionStorage.setItem("customer",JSON.stringify(state.customer))
        },
        clearCustomer(state){
            state.customer = null
            sessionStorage.removeItem("customer")
        }
    },
    getters:{
        loadCustomer(state){
            state.customer = JSON.parse(sessionStorage.getItem("customer"))
            return state.customer
        }
    },
    actions:{
        // setUser2(context,payload){
        //     setTimeout(()=>{
        //         context.commit("setUser",payload)
        //     },1000)
        // }
    }
})
