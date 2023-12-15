<template>
  <div>
    <el-container class="home-container">
      <el-header class="home-header">
        <el-row :gutter="20">
          <el-col :span="16">
            <div class="main-title">Smart Home Energy Management System</div>
          </el-col>
          <el-col :span="6" class="customer-title">
            Hello, {{customer.username}}
          </el-col>
          <el-col :span="2">
            <el-button type="danger" size="large" @click="logout">Log out</el-button>
          </el-col>
        </el-row>
      </el-header>
      <el-container>
        <el-aside class="home-aside">
          <el-menu class="home-aside-menu"
            background-color="#ffdcdc"
            text-color="#000000"
            active-text-color="#88b7ff"
                   :router="true">
            <el-menu-item index="/serviceLocation">
              <el-icon><LocationInformation /></el-icon>
              <span>Service Location</span>
            </el-menu-item>
            <el-menu-item index="/deviceEnrollment">
              <el-icon><Coordinate /></el-icon>
              <span>Device Enrollment</span>
            </el-menu-item>
            <el-menu-item index="/energyCost">
              <el-icon><Money /></el-icon>
              <span>Energy Cost</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main class="home-main">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default ({
  name: 'HomePage',
  data() {
    return {
      customer: {},
    };
  },
  created() {
    this.customer = this.$store.getters.loadCustomer;
  },
  methods: {
    logout() {
      this.$store.commit('clearCustomer');
      this.$router.push({ name: 'login' });
    }
  }
});
</script>

<style scoped>
.home-header{
  background-color: #8fbef8;
  color: #333;
  height: 80px;
  text-align: center;
  line-height: 80px;
  border-radius: 15px;
  border: 1px solid #9a9a9a;
}

.home-aside {
  background-color: #ffdede;
  width: 200px;
  text-align: center;
  line-height: 200px;
  border-radius: 15px;
}

.home-aside-menu{
  background-color: #ffdede;
  width: 200px;
  text-align: center;
  line-height: 200px;
  border-radius: 15px;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-weight: bold;
}

.home-main {
  background-color: #fffdf2;
  color: #333;
  text-align: center;
  line-height: 160px;
  border-radius: 15px;
}

.home-container{
  height: 100vh;
}

.main-title{
  color:#505458;
  margin:0px auto 40px;
  font-weight: bold;
  font-size: 25px;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
  text-align: left;
}

.customer-title{
  text-align: right;
  color: #000000;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-weight: bold;
}

</style>
