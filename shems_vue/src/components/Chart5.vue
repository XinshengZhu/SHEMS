<template>
  <div>
    <div class="chart-title">Energy Cost Line Graph For a Specified Service Location in a Given Period of Time</div>
    <el-row>
      <el-select v-model="slid" placeholder="Select a ServiceLocation ID">
        <el-option
            v-for="servicelocation in servicelocations"
            :key="servicelocation.slid"
            :label="servicelocation.slid"
            :value="servicelocation.slid">
        </el-option>
      </el-select>
      <el-select v-model="scope" placeholder="Select a Scope" @change="changeScope">
        <el-option
            v-for="scope in scopes"
            :key="scope"
            :label="scope"
            :value="scope">
        </el-option>
      </el-select>
      <el-date-picker
          v-model="range"
          :type="pickerType"
          range-separator="To"
          start-placeholder="Start"
          end-placeholder="End">
      </el-date-picker>
      <el-button type="primary" :disabled="isButtonDisabled" @click = "display" > View </el-button>
    </el-row>
    <el-row>
      <div id="myChart5" class="myChart5" style="width:1600px; height:800px"></div>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Chart5Page',
  data() {
    return {
      customer: {},
      servicelocations: [],
      slid: '',
      scope: 'Daily',
      scopes: ['Daily', 'Monthly'],
      pickerType: 'daterange',
      range: [],
      viewData: {}
    };
  },
  created() {
    this.customer = this.$store.getters.loadCustomer;
    this.$http.get('servicelocation/getByCid/' + this.customer.cid).then(data => {
      this.servicelocations = data;
    });
  },
  mounted() {
    let myChart5 = this.$echarts.init(document.getElementById("myChart5"));
    myChart5.setOption({
    });
  },
  computed: {
    isButtonDisabled() {
      return !this.slid || !this.scope || this.range.length !== 2;
    }
  },
  methods: {

    changeScope() {
      if (this.scope === 'Daily') {
        this.pickerType = 'daterange';
      } else if (this.scope === 'Monthly') {
        this.pickerType = 'monthrange';
      }
    },
    formatDay(inputString) {
      const date = new Date(inputString);
      const year = date.getFullYear();
      const month = ('0' + (date.getMonth() + 1)).slice(-2);
      const day = ('0' + date.getDate()).slice(-2);
      return `${year}-${month}-${day}`;
    },
    formatMonth(inputString) {
      const date = new Date(inputString);
      const year = date.getFullYear();
      const month = ('0' + (date.getMonth() + 1)).slice(-2);
      return `${year}-${month}`;
    },
    display() {
      let params = {};
      let url = '';
      if (this.scope === 'Daily') {
        url = 'event/daily_energy_cost';
        params = {
          slid: this.slid,
          start: this.formatDay(this.range[0]),
          end: this.formatDay(this.range[1])
        };
      } else if (this.scope === 'Monthly') {
        url = 'event/monthly_energy_cost';
        params = {
          slid: this.slid,
          start: this.formatMonth(this.range[0]),
          end: this.formatMonth(this.range[1])
        };
      }
      this.$http.get(url, {params:params}).then(data => {
        this.viewData = data;
        if (this.scope === 'Daily') {
          this.showDailyGraph();
        } else if (this.scope === 'Monthly') {
          this.showMonthlyGraph();
        }
      });
    },
    showDailyGraph() {
      const rawData = this.viewData;
      const xAxisData = [];
      const start = new Date(this.range[0]);
      const end = new Date(this.range[1]);
      const oneDay = 24 * 60 * 60 * 1000;
      for (let d = start; d <= end; d = new Date(d.getTime() + oneDay)) {
        xAxisData.push(this.formatDay(d));
      }
      const yAxisData = xAxisData.map(() => 0);
      for (const [day, daily_energy_cost] of Object.entries(rawData)) {
        const index = Math.floor((new Date(day) - start) / oneDay);
        yAxisData[index] = daily_energy_cost;
      }

      let myChart5 = this.$echarts.init(document.getElementById("myChart5"));
      myChart5.setOption({
        xAxis: {
          data: xAxisData,
          name: 'Day',
          axisLabel: {
            rotate: 45
          }
        },
        yAxis: {
          name: 'Daily Energy Cost',
          type: 'value'
        },
        series: [
          {
            name: 'Daily Energy Cost',
            data: yAxisData,
            type: 'line',
            smooth: true
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
    },
    showMonthlyGraph() {
      const rawData = this.viewData;
      const xAxisData = [];
      const start = new Date(this.range[0]);
      const end = new Date(this.range[1]);
      for (let d = new Date(start); d <= end; d.setMonth(d.getMonth() + 1)) {
        xAxisData.push(this.formatMonth(d));
      }
      const yAxisData = xAxisData.map(() => 0);
      const getMonthIndex = (date) => {
        const year = date.getFullYear();
        const month = date.getMonth();
        return (year - start.getFullYear()) * 12 + (month - start.getMonth());
      };
      for (const [month, monthly_energy_cost] of Object.entries(rawData)) {
        const date = new Date(month);
        const index = getMonthIndex(date);
        yAxisData[index] = monthly_energy_cost;
      }

      let myChart5 = this.$echarts.init(document.getElementById("myChart5"));
      myChart5.setOption({
        xAxis: {
          data: xAxisData,
          name: 'Month',
          axisLabel: {
            rotate: 45
          }
        },
        yAxis: {
          name: 'Monthly Energy Cost',
          type: 'value'
        },
        series: [
          {
            name: 'Monthly Energy Cost',
            data: yAxisData,
            type: 'line',
            smooth: true
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

.myChart5 {
  margin: 0 auto;
}

.chart-title {
  font-size: 20px;
  font-weight: bold;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

</style>