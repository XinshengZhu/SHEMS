<template>
  <div>
    <div class="chart-title">Hourly Energy Consumption and Cost for a Device of a Service Location in a Given Period of Time</div>
    <el-row class="select-row">
      <el-select v-model="slid" style="width: 30%;" placeholder="Select a ServiceLocation ID" @change="updateDeviceInfos">
        <el-option
            v-for="servicelocation in servicelocations"
            :key="servicelocation.slid"
            :label="servicelocation.slid"
            :value="servicelocation.slid">
        </el-option>
      </el-select>
      <el-select v-model="enid" style="width: 35%;" placeholder="Select a Device in the ServiceLocation">
      <el-option
          v-for="deviceInfo in deviceInfos"
          :key="deviceInfo.enid"
          :label="deviceInfo.device_info"
          :value="deviceInfo.enid">
      </el-option>
      </el-select>
      <el-date-picker
          v-model="date"
          type="date"
          style="width: 27%;"
          placeholder="Pick a day">
      </el-date-picker>
      <el-button type="primary" :disabled="isButtonDisabled" @click = "display" > View </el-button>
    </el-row>
    <el-row>
      <div id="myChart4" class="myChart4" style="width:1600px; height:800px"></div>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Chart4Page',
  data() {
    return {
      customer: {},
      servicelocations: [],
      slid: '',
      enid: '',
      deviceInfos: [],
      date: '',
      prices: [],
      viewData: {},
    };
  },
  created() {
    this.customer = this.$store.getters.loadCustomer;
    this.$http.get('servicelocation/getByCid/' + this.customer.cid).then(data => {
      this.servicelocations = data;
    });
  },
  mounted() {
    let myChart4 = this.$echarts.init(document.getElementById("myChart4"));
    myChart4.setOption({
    });
  },
  computed: {
    isButtonDisabled() {
      return !this.slid || !this.enid || !this.date;
    }
  },
  methods: {
    updateDeviceInfos() {
      this.enid = '';
      this.$http.get('device/getInfoBySlid/' + this.slid).then(data => {
        this.deviceInfos = Object.entries(data).map(([enid, device_info]) => {
          return { enid, device_info };
        });
      });
    },

    formatDay(inputString) {
      const date = new Date(inputString);
      const year = date.getFullYear();
      const month = ('0' + (date.getMonth() + 1)).slice(-2);
      const day = ('0' + date.getDate()).slice(-2);
      return `${year}-${month}-${day}`;
    },

    display() {
      let url = 'event/hourly_energy_cost';
      let params = {
        enid: this.enid,
        date: this.formatDay(this.date)
      };
      this.$http.get(url, {params:params}).then(data => {
        this.viewData = data;
        this.$http.get('price/getPrice/'+ this.slid).then(data => {
          this.prices = Object.entries(data).map(([hour, price_per_kwh]) => {
            return { hour, price_per_kwh };
          });
          this.showGraph();
        });
      });
    },

    showGraph() {
      const xAxisData = [];
      const yAxisDataPrice = [];
      const yAxisDataEnergy = [];
      const yAxisDataCost = [];
      for (let i = 0; i < this.prices.length; i++) {
        const index = xAxisData.length;
        xAxisData[index] = this.prices[i].hour;
        yAxisDataPrice[index] = this.prices[i].price_per_kwh;
        yAxisDataEnergy[index] = 0;
        yAxisDataCost[index] = 0;
        for (let j = 0; j < this.viewData.length; j++) {
          if (this.prices[i].hour === this.viewData[j].hour) {
            yAxisDataEnergy[index] = this.viewData[j].hourly_energy_consumption;
            yAxisDataCost[index] = this.viewData[j].hourly_energy_cost;
            break;
          }
        }
      }

      let peakPriceHours = [];
      let isPeak = false;
      let startPeak;
      for (let i = 0; i < yAxisDataPrice.length; i++) {
        if (yAxisDataPrice[i] >= 0.3 && !isPeak) {
          isPeak = true;
          startPeak = i;
        } else if ((yAxisDataPrice[i] < 0.3 || i === yAxisDataPrice.length - 1) && isPeak) {
          isPeak = false;
          peakPriceHours.push({ start: startPeak.toString(), end: i.toString() });
        }
      }
      let troughPriceHours = [];
      let isTrough = false;
      let startTrough;
      for (let i = 0; i < yAxisDataPrice.length; i++) {
        if (yAxisDataPrice[i] < 0.3 && !isTrough) {
          isTrough = true;
          startTrough = i;
        } else if ((yAxisDataPrice[i] >= 0.3 || i === yAxisDataPrice.length - 1) && isTrough) {
          isTrough = false;
          troughPriceHours.push({ start: startTrough.toString(), end: i.toString() });
        }
      }
      let markAreaData1 = peakPriceHours.map(period => {
        return [
          { xAxis: `${period.start}`, name: 'Peak Price Hours' },
          { xAxis: `${period.end}` }
        ];
      });
      let markAreaData2 = troughPriceHours.map(period => {
        return [
          { xAxis: `${period.start}`, name: 'Trough Price Hours' },
          { xAxis: `${period.end}` }
        ];
      });
      let myChart4 = this.$echarts.init(document.getElementById("myChart4"));
      myChart4.setOption({
        legend: {
          data: ['Hourly Energy Consumption (kWh)', 'Hourly Energy Cost']
        },
        xAxis: {
          data: xAxisData,
          name: 'Hour',
          axisLabel: {
          }
        },
        yAxis: [
          {
            type: 'value',
            name: 'Hourly Energy Consumption (kWh)',
          },
          {
            type: 'value',
            name: 'Hourly Energy Cost',
          }
        ],
        series: [
          {
            name: 'Hourly Energy Consumption (kWh)',
            type: 'line',
            data: yAxisDataEnergy,
            smooth: true,
            color: '#ffc814',
            markArea: {
              itemStyle: {
                color: 'rgba(170,255,153,0.4)'
              },
              data: markAreaData2
            }
          },
          {
            name: 'Hourly Energy Cost',
            type: 'line',
            yAxisIndex: 1,
            data: yAxisDataCost,
            smooth: true,
            color: '#6cacff',
            markArea: {
              itemStyle: {
                color: 'rgba(255, 173, 177, 0.4)'
              },
              data: markAreaData1
            },
          }
        ],

        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
      });
    }
  }
}
</script>

<style scoped>

.myChart4 {
  margin: 0 auto;
}

.chart-title {
  font-size: 20px;
  font-weight: bold;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

</style>