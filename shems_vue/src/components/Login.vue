<template>
  <div class="full-login-page">
    <el-form class="login-container" :model="customer" :rules="rules" ref="loginForm">
      <h3 class="login-title">Login</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="customer.username" placeholder="Username"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="customer.password" placeholder="Password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width:100%;" @click="login">Login</el-button>
      </el-form-item>
      <el-form-item >
        <el-button type="primary" style="width:100%" @click="register">Register</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name:"LoginPage",
  data(){
    return{
      customer:{},
      rules:{
        username:[
          {required:true,message:'Please type in your username',trigger:['blur','change']},
          {min:2,max:50,message:'Username must be between 2-50 characters in length',trigger:['blur','change']}
        ],
        password:[
          {required:true,message:'Please type in your password',trigger:['blur','change']},
          {min:2,max:50,message:'Password must be between 2-50 characters in length',trigger:['blur','change']}
        ]
      }
    }
  },
  methods:{
    login(){
      this.$refs["loginForm"].validate(valid=>{
        if(valid){
          let url = "customer/login";
          this.$http.get(url,{params:this.customer}).then(data=>{
            if(data){
              this.$store.commit("setCustomer", data);
              this.$router.push({name:"home"});
            }else{
              this.$alert("Incorrect user name or password");
            }
          });
        }
      });
    },
    register(){
      this.$router.push({name:"register"});
    }
  }
}
</script>

<style scoped>
.login-container{
  width: 350px;
  margin: 0 auto;
  border:1px solid #eaeaea;
  padding:35px 35px 15px 35px;
  border-radius: 15px;
  box-shadow:0 0 25px #cac6c6;
  background-color: #f5f5f5;
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.login-title{
  text-align: center;
  color:#505458;
  margin:0px auto 40px;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.full-login-page{
  background-image: url("../assets/bg.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: top;
  height: 100vh;
}
</style>
