<template>
  <div>
    <div class="de-title">Device Enrollment</div>
    <el-table :data="pageInfo.list" height="250" border stripe :default-sort="{ prop: 'enid', order: 'ascending' }">
      <el-table-column fixed prop="enid" label="Enrollment ID" sortable></el-table-column>
      <el-table-column prop="slid" label="ServiceLocation ID" sortable></el-table-column>
      <el-table-column prop="did" label="Device ID" sortable></el-table-column>
      <el-table-column prop="dtid" label="Device Type ID" sortable></el-table-column>
      <el-table-column prop="type" label="Device Type"></el-table-column>
      <el-table-column prop="model" label="Device Model"></el-table-column>
      <el-table-column fixed="right" label="Operation">
        <template #default="{row}">
          <el-button type="danger" size="small" plain @click="del(row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="flex-container">
      <el-pagination
          layout="total, sizes, prev, pager, next, jumper"
          :total="pageInfo.total"
          v-model:currentPage="pageInfo.pageNum"
          v-model:page-size="pageInfo.pageSize"
          :page-sizes="[3,5,10,20]"
          background
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
      />
      <el-button class="add-button" type="success" size="large" plain @click="add">Add Device Enrollment</el-button>
    </div>

    <el-dialog v-model="enableDialog" :title="title" center width="500px">
      <el-form :model="enrollment" :rules="rules" ref="enrollmentForm">
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="ServiceLocation ID" prop="slid">
              <el-select v-model="enrollment.slid" style="width:215px">
                <el-option
                    v-for="servicelocation in servicelocations"
                    :key="servicelocation.slid"
                    :value="servicelocation.slid"
                    :label="servicelocation.slid"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="Device Type" prop="dtid">
              <el-select v-model="enrollment.dtid" @change="updateDevices" style="width:215px">
                <el-option
                    v-for="devicetype in devicetypes"
                    :key="devicetype.dtid"
                    :value="devicetype.dtid"
                    :label="devicetype.type"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="Device Model" prop="did">
              <el-select v-model="enrollment.did" style="width:215px">
                <el-option
                    v-for="device in filteredDevices"
                    :key="device.did"
                    :value="device.did"
                    :label="device.model"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <el-button type="warning" @click="cancel">Cancel</el-button>
        <el-button type="primary" @click="saveData">Save</el-button>
      </template>
    </el-dialog>

    <div>
      <Chart2 />
    </div>

  </div>
</template>

<script>

import Chart2 from "@/components/Chart2.vue";

export default {
  name: 'DeviceEnrollmentPage',
  components: {
    Chart2,
  },
  data() {
    return {
      customer: {},
      rules: {
        slid: [
          {required: true, message: 'ServiceLocation ID is required', trigger: ['blur','change']},
        ],
        dtid: [
          {required: true, message: 'Device Type is required', trigger: ['blur','change']},
        ],
        did: [
          {required: true, message: 'Device Model is required', trigger: ['blur','change']},
        ]
      },
      pageInfo: {
        pageNum: 1,
        pageSize: 5,
      },
      enrollment: {},
      title: '',
      enableDialog: false,
      servicelocations: [],
      devicetypes: [],
      devices: [],
      filteredDevices: []
    };
  },
  created() {
    this.customer = this.$store.getters.loadCustomer;
    this.$http.get('devicetype/getAll').then(data => {
      this.devicetypes = data;
      this.$http.get('device/getAll').then(data => {
        this.devices = data;
        this.$http.get('servicelocation/getByCid/' + this.customer.cid).then(data => {
          this.servicelocations = data;
          this.showData();
        });
      });
    });
  },
  methods: {
    showData() {
      let params = {
        pageNum: this.pageInfo.pageNum,
        pageSize: this.pageInfo.pageSize
      }
      this.$http.get('enrollment/getPaged/' + this.customer.cid, {params: params}).then(data => {
        data.list.forEach(item => {
          this.devices.forEach(d => {
            if (item.did === d.did) {
              item.dtid = d.dtid;
              item.model = d.model;
            }
          });
          this.devicetypes.forEach(dt => {
            if (item.dtid === dt.dtid) {
              item.type = dt.type;
            }
          });
          this.pageInfo = data;
        });

      });
    },
    handleCurrentChange() {
      this.showData();
    },
    handleSizeChange() {
      this.showData();
    },
    updateDevices() {
      this.enrollment.did = '';
      this.filteredDevices = this.devices.filter(device => device.dtid === this.enrollment.dtid);
    },
    cancel() {
      this.enableDialog = false;
      this.$refs["enrollmentForm"].resetFields();
      this.enrollment = {}
    },
    add() {
      this.enableDialog = true;
      this.title = 'Add Device Enrollment';
      this.$refs["enrollmentForm"].resetFields();
      this.enrollment = {}
    },
    del(row) {
      this.$confirm('Are you sure to delete this Device Enrollment?', 'Warning', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        this.$http.delete('enrollment/delete/' + row.enid).then(() => {
          this.$message({
            type: 'success',
            message: 'Delete successfully!'
          });
          this.showData();
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'Delete canceled!'
        });
      });
    },
    saveData() {
      this.$refs["enrollmentForm"].validate((valid) => {
        if (valid) {
          this.$http.post('enrollment/add', this.enrollment).then(() => {
            this.$message({
              type: 'success',
              message: 'Add successfully!'
            });
            this.enableDialog = false;
            this.showData();
          });
        }
      });
    }
  }
}
</script>

<style scoped>

.de-title {
  font-size: 20px;
  font-weight: bold;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.add-button {
  float: right;
}

.flex-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

</style>
