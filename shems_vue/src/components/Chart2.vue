<template>
  <div>
    <div class="chart-title">Energy Consumption Bar Graph For Devices Enrolled in a Specified Service Location in a Given Period of Time</div>
    <el-row>
      <el-select v-model="slid" placeholder="Select a ServiceLocation ID">
        <el-option
            v-for="servicelocation in servicelocations"
            :key="servicelocation.slid"
            :label="servicelocation.slid"
            :value="servicelocation.slid">
        </el-option>
      </el-select>
      <el-date-picker
          v-model="range"
          type="daterange"
          range-separator="To"
          start-placeholder="Start"
          end-placeholder="End">
      </el-date-picker>
      <el-button type="primary" :disabled="isButtonDisabled" @click = "display" > View </el-button>
    </el-row>
    <el-row>
      <div id="myChart2" class="myChart2" style="width:1600px; height:800px"></div>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Chart2Page',
  data() {
    return {
      customer: {},
      servicelocations: [],
      slid: '',
      range: [],
      viewData: {},
      deviceInfos: {}
    };
  },
  created() {
    this.customer = this.$store.getters.loadCustomer;
    this.$http.get('servicelocation/getByCid/' + this.customer.cid).then(data => {
      this.servicelocations = data;
    });
  },
  mounted() {
    let myChart2 = this.$echarts.init(document.getElementById("myChart2"));
    myChart2.setOption({
    });
  },
  computed: {
    isButtonDisabled() {
      return !this.slid || this.range.length !== 2;
    }
  },
  methods: {
    formatDay(inputString) {
      const date = new Date(inputString);
      const year = date.getFullYear();
      const month = ('0' + (date.getMonth() + 1)).slice(-2);
      const day = ('0' + date.getDate()).slice(-2);
      return `${year}-${month}-${day}`;
    },

    display() {
      let url = 'event/enrollment_energy_consumption';
      let params = {
        slid: this.slid,
        start: this.formatDay(this.range[0]),
        end: this.formatDay(this.range[1])
      };
      this.$http.get(url, {params:params}).then(data => {
        this.viewData = data;
        this.$http.get('device/getInfoBySlid/' + this.slid).then(data => {
          this.deviceInfos = data;
          this.showGraph();
        });
      });
    },
    showGraph() {
      const deviceData = this.deviceInfos;
      const rawData = this.viewData;
      const xAxisData = [];
      const yAxisData = [];
      for (const [enid, device_info] of Object.entries(deviceData)) {
        xAxisData.push(device_info);
        const temp = enid;
        const index = xAxisData.length - 1;
        yAxisData[index] = 0;
        for (const [enid, enrollment_energy_consumption] of Object.entries(rawData)) {
          if (enid === temp) {
            yAxisData[index] = enrollment_energy_consumption;
            break;
          }
        }
      }
      let myChart2 = this.$echarts.init(document.getElementById("myChart2"));
      myChart2.setOption({
        xAxis: {
          name: 'Energy Consumption (kWh)',
          type: 'value',
          axisLabel: {
            rotate: 45
          }
        },
        yAxis: {
          name: 'Device Info',
          type: 'category',
          data: xAxisData,
        },
        series: [
          {
            name: 'Energy Consumption (kWh)',
            data: yAxisData,
            type: 'bar',
            label: {
              show: true,
              position: 'inside'
            },
            itemStyle: {
              color: '#ffc814'
            }
          }
        ],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        }
      });
    }
  }
}
</script>

<style scoped>



.chart-title {
  font-size: 20px;
  font-weight: bold;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

</style>