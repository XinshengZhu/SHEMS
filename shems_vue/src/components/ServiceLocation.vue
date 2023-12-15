<template>
  <div>
    <div class="sl-title">Service Location</div>
    <el-table :data="pageInfo.list" height="250" border stripe :default-sort="{ prop: 'slid', order: 'ascending' }">
      <el-table-column fixed prop="slid" label="ServiceLocation ID" sortable></el-table-column>
      <el-table-column prop="cid" label="Customer ID" sortable></el-table-column>
      <el-table-column prop="acquiredDate" label="Acquired Date"></el-table-column>
      <el-table-column prop="area" label="Area (Square Foot)"></el-table-column>
      <el-table-column prop="bedroomNum" label="Bedroom Number"></el-table-column>
      <el-table-column prop="occupantNum" label="Occupant Number"></el-table-column>
      <el-table-column prop="street" label="Street"></el-table-column>
      <el-table-column prop="unit" label="Unit"></el-table-column>
      <el-table-column prop="city" label="City"></el-table-column>
      <el-table-column prop="state" label="State"></el-table-column>
      <el-table-column prop="zipcode" label="Zipcode"></el-table-column>
      <el-table-column fixed="right" label="Operation">
        <template #default="{row}">
          <el-button type="primary" size="small" plain @click="edit(row)">Edit</el-button>
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
      <el-button class="add-button" type="success" size="large" plain @click="add">Add Service Location</el-button>
    </div>

    <el-dialog v-model="enableDialog" :title="title" center width="500px">
      <el-form :model="servicelocation" :rules="rules" ref="servicelocationForm">
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="Customer ID" prop="cid">
              <el-input :readonly="true" v-model="servicelocation.cid" placeholder="Customer ID"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="Acquired Date" prop="acquiredDate">
              <el-date-picker v-model="servicelocation.acquiredDate" placeholder="Acquired Date"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="Area (Square Foot)" prop="area">
              <el-input v-model="servicelocation.area" placeholder="Area (Square Foot)"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="Bedroom Number" prop="bedroomNum">
              <el-input v-model="servicelocation.bedroomNum" placeholder="Bedroom Number"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="Occupant Number" prop="occupantNum">
              <el-input v-model="servicelocation.occupantNum" placeholder="Occupant Num"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="Street" prop="street">
              <el-input v-model="servicelocation.street" placeholder="Street"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="Unit" prop="unit">
              <el-input v-model="servicelocation.unit" placeholder="Unit"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="City" prop="city">
              <el-input v-model="servicelocation.city" placeholder="City"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="State" prop="state">
              <el-input v-model="servicelocation.state" placeholder="State"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="20">
            <el-form-item label="Zipcode" prop="zipcode">
              <el-input v-model="servicelocation.zipcode" placeholder="Zipcode"></el-input>
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
      <Chart1 />
    </div>
    <div>
      <Chart3 />
    </div>
  </div>
</template>

<script>

import Chart1 from "@/components/Chart1.vue";
import Chart3 from "@/components/Chart3.vue";

export default {
  name: 'ServiceLocationPage',
  components: {
    Chart1,
    Chart3
  },
  data() {
    return {
      customer: {},
      rules: {
        acquiredDate: [
          {required: true, message: 'Please input Acquired Date', trigger: ['blur','change']},
        ],
        area: [
          {required: true, message: 'Please input Area (Square Foot)', trigger: ['blur','change']},
          {pattern: /^(\d+|\d+\.\d+)$/, message: 'Area must be a decimal', trigger: ['blur','change']},
        ],
        bedroomNum: [
          {required: true, message: 'Please input Bedroom Number', trigger: ['blur','change']},
          {pattern: /^[0-9]*$/, message: 'Bedroom number must be an integer', trigger: ['blur','change']},
        ],
        occupantNum: [
          {required: true, message: 'Please input Occupant Number', trigger: ['blur','change']},
          {pattern: /^[0-9]*$/, message: 'Occupant number must be an integer', trigger: ['blur','change']},
        ],
        street: [
          {required: true, message: 'Please input Street', trigger: ['blur','change']},
          {min:2,max:50,message:'Street must be between 2-50 characters in length',trigger:['blur','change']}
        ],
        unit: [
          {required: true, message: 'Please input Unit', trigger: ['blur','change']},
          {min:2,max:50,message:'Unit must be between 2-50 characters in length',trigger:['blur','change']}
        ],
        city: [
          {required: true, message: 'Please input City', trigger: ['blur','change']},
          {min:2,max:50,message:'City must be between 2-50 characters in length',trigger:['blur','change']}
        ],
        state: [
          {required: true, message: 'Please input State', trigger: ['blur','change']},
          {min:2,max:50,message:'State must be between 2-50 characters in length',trigger:['blur','change']}
        ],
        zipcode: [
          {required: true, message: 'Please input Zipcode', trigger: ['blur','change']},
          {pattern: /^[0-9]{5}$/, message: 'Zipcode must be 5 digits', trigger: ['blur','change']},
        ],
      },
      pageInfo: {
        pageNum: 1,
        pageSize: 5,
      },
      servicelocation: {},
      title: '',
      enableDialog: false
    };
  },

  created(){
    this.customer = this.$store.getters.loadCustomer;
    this.servicelocation.cid = this.customer.cid
    this.showData();
  },

  methods: {
    showData() {
      let params = {
        pageNum: this.pageInfo.pageNum,
        pageSize: this.pageInfo.pageSize
      }
      this.$http.get('servicelocation/getPaged/' + this.customer.cid, {params: params}).then(data => {
        this.pageInfo = data;
      });
    },
    handleCurrentChange() {
      this.showData();
    },
    handleSizeChange() {
      this.showData();
    },
    cancel() {
      this.enableDialog = false;
      this.$refs["servicelocationForm"].resetFields();
      this.servicelocation = {
        cid: this.customer.cid,
      };
    },
    add() {
      this.title = 'Add Service Location';
      this.enableDialog = true;
      this.$refs["servicelocationForm"].resetFields();
      this.servicelocation = {
        cid: this.customer.cid,
      };
    },
    edit(row) {
      this.title = 'Edit Service Location';
      this.enableDialog = true;
      this.servicelocation = row;
    },
    del(row) {
      this.$confirm('Are you sure to delete this Service Location?', 'Warning', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        this.$http.delete('servicelocation/delete/' + row.slid).then(() => {
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
      this.$refs["servicelocationForm"].validate(valid => {
        if (valid) {
          if (this.title === 'Add Service Location') {
            this.$http.post('servicelocation/add', this.servicelocation).then(() => {
              this.$message({
                type: 'success',
                message: 'Add successfully!'
              });
              this.enableDialog = false;
              this.showData();
            });
          } else {
            this.$http.post('servicelocation/update', this.servicelocation).then(() => {
              this.$message({
                type: 'success',
                message: 'Update successfully!'
              });
              this.enableDialog = false;
              this.showData();
            });
          }
        }
      });
    }
  }

}
</script>

<style scoped>

.sl-title {
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