<template>
  <div class="full-register-page">
    <el-form class="register-container" :model="customer" :rules="rules" ref="registerForm">
      <h3 class="register-title">Register</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="customer.username" placeholder="Username"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="customer.password" placeholder="Password"></el-input>
      </el-form-item>
      <el-form-item prop="passwordConfirm">
        <el-input type="password" v-model="customer.passwordConfirm" placeholder="Confirm Password"></el-input>
      </el-form-item>
      <el-form-item prop="firstName">
        <el-input type="text" v-model="customer.firstName" placeholder="First Name"></el-input>
      </el-form-item>
      <el-form-item prop="lastName">
        <el-input type="text" v-model="customer.lastName" placeholder="Last Name"></el-input>
      </el-form-item>
      <el-form-item prop="billingAddress">
        <el-input type="text" v-model="customer.billingAddress" placeholder="Billing Address"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width:100%;" @click="register">Register</el-button>
      </el-form-item>
      <el-form-item >
        <el-button type="primary" style="width:100%" @click="goBack">Go Back</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name:"RegisterPage",
  data(){
    return{
      customer:{},
      rules:{
        username:[
          {required:true,message:'Please type in your username',trigger:['blur','change']},
          {min:2,max:50,message:'Username must be between 2-50 characters in length',trigger:['blur','change']},
          {validator:this.checkUsernameValidate,trigger:'blur'}
        ],
        password:[
          {required:true,message:'Please type in your password',trigger:['blur','change']},
          {min:2,max:50,message:'Password must be between 2-50 characters in length',trigger:['blur','change']}
        ],
        passwordConfirm:[
          {required:true,message:'Please confirm your password',trigger:['blur','change']},
          {min:2,max:50,message:'Password must be between 2-50 characters in length',trigger:['blur','change']},
          {validator:(rule,value,callback)=>{
            if(value !== this.customer.password){
              callback(new Error("Password does not match"));
            }else{
              callback();
            }
          },trigger:'blur'}
        ],
        firstName:[
          {required:true,message:'Please type in your first name',trigger:['blur','change']},
          {min:2,max:50,message:'First name must be between 2-50 characters in length',trigger:['blur','change']}
        ],
        lastName:[
          {required:true,message:'Please type in your last name',trigger:['blur','change']},
          {min:2,max:50,message:'Last name must be between 2-50 characters in length',trigger:['blur','change']}
        ],
        billingAddress:[
          {required:true,message:'Please type in your billing address',trigger:['blur','change']},
          {min:5,max:100,message:'Billing address must be between 5-100 characters in length',trigger:['blur','change']}
        ]
      }
    }
  },
  methods:{
    checkUsernameValidate(rule,value,callback){
      let url = `customer/checkUsername?username=${this.customer.username}`;
      this.$http.get(url).then(data=>{
        if(data){
          callback(new Error("Username already exists!"));
        }else{
          callback();
        }
      });
    },
    register(){
      this.$refs["registerForm"].validate(valid=>{
        if(valid){
          var url = "customer/register";
          this.$http.post(url,this.customer).then(data=>{
            if(data){
              this.$store.commit("setCustomer", data);
              this.$router.push({name:"home"});
            }else{
              this.$alert("Register failed");
            }
          });
        }

      });
    },
    goBack(){
      this.$router.push({name:"login"});
    }
  }
}
</script>

<style scoped>
.register-container{
  width:350px;
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

.register-title{
  text-align: center;
  color:#505458;
  margin:0px auto 40px;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.full-register-page{
  background-image: url("../assets/bg.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: top;
  height: 100vh;
}
</style>