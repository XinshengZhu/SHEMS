<template>
  <div>
    <div class="chart-title">Energy Consumption Comparison in a Given Period of Time: Service Locations vs. Similar Ones' Average</div>
    <el-row>
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
      <div id="myChart3" class="myChart3" style="width:1600px; height:800px"></div>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Chart3Page',
  data() {
    return {
      customer: {},
      servicelocations: [],
      range: [],
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
    let myChart3 = this.$echarts.init(document.getElementById("myChart3"));
    myChart3.setOption({
    });
  },
  computed: {
    isButtonDisabled() {
      return this.range.length !== 2;
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
      let url = 'event/percentage_of_similar_service_average';
      let params = {
        cid: this.customer.cid,
        start: this.formatDay(this.range[0]),
        end: this.formatDay(this.range[1])
      };
      this.$http.get(url, {params:params}).then(data => {
        this.viewData = data;
        this.showGraph();
      });
    },
    showGraph() {
      const rawData = this.viewData;
      const xAxisData = [];
      const yAxisDataTotal = [];
      const yAxisDataAverage = [];
      const yAxisDataPercentage = [];
      for (let i = 0; i < rawData.length; i++) {
        xAxisData.push(rawData[i].slid);
        yAxisDataTotal.push(rawData[i].total_energy);
        yAxisDataAverage.push(rawData[i].avg_similar_energy);
        yAxisDataPercentage.push(rawData[i].percentage_of_average);
      }
      this.servicelocations.forEach(function (item) {
        if (!xAxisData.includes(item.slid)) {
          xAxisData.push(item.slid);
          yAxisDataTotal.push(0);
          yAxisDataAverage.push(0);
          yAxisDataPercentage.push(0);
        }
      });
      let myChart3 = this.$echarts.init(document.getElementById("myChart3"));
      myChart3.setOption({
        legend: {
          data: ['Total Energy', 'Average Energy of Similar ServiceLocations', 'Percentage of Average']
        },
        xAxis: {
          data: xAxisData,
          name: 'ServiceLocation ID',
          axisLabel: {
          }
        },
        yAxis: [
            {
              name: 'Energy Consumption (kWh)',
              type: 'value'
            },
            {
              name: 'Percentage of Average Energy Consumption (%)',
              type: 'value'
            }
        ],
        series: [
          {
            name: 'Total Energy Consumption (kWh)',
            data: yAxisDataTotal,
            type: 'bar',
            label: {
              show: true,
              position: 'inside'
            },
          },
          {
            name: 'Average Energy Consumption of Similar ServiceLocations (kWh)',
            data: yAxisDataAverage,
            type: 'bar',
            label: {
              show: true,
              position: 'inside'
            },
          },
          {
            name: 'Percentage of Average Energy Consumption (%)',
            data: yAxisDataPercentage,
            type: 'bar',
            yAxisIndex: 1,
            label: {
              show: true,
              position: 'top'
            },
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

.myChart3 {
  margin: 0 auto;
}

.chart-title {
  font-size: 20px;
  font-weight: bold;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

</style>