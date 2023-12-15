<template>
  <div>
    <div class="ec-title">Energy Cost</div>

    <div>
      <Chart5 />
    </div>
    <div>
      <Chart4 />
    </div>
  </div>
</template>

<script>
import Chart4 from "@/components/Chart4.vue";
import Chart5 from "@/components/Chart5.vue";

export default {
  name: 'EnergyCostPage',
  components: {
    Chart4,
    Chart5
  },
}

</script>

<style scoped>
.ec-title {
  font-size: 20px;
  font-weight: bold;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}
</style>
