<template>
  <v-card class="myCard">
    <v-data-iterator :items="items" :items-per-page.sync="itemsPerPage" hide-default-footer>
      <template v-slot:header>
        <v-toolbar class="mb-2" color="indigo darken-5" dark flat>
          <v-toolbar-title>学生成绩查询</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-col class="d-flex" cols="4" sm="2">
            <el-select v-model="year" clearable placeholder="请选择学年">
              <el-option v-for="item in optionsyear" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </v-col>
          <v-col class="d-flex" cols="1" sm="1">
            <v-btn color="primary" @click="checkgrade">查询</v-btn>
          </v-col>
        </v-toolbar>
      </template>
      <template>
        <v-row>
          <v-col cols="12" sm="12" md="8" lg="6">
            <v-card>
              <v-card-title class="subheading font-weight-bold">学业情况</v-card-title>

              <v-divider></v-divider>

              <v-list dense>
                <v-list-item>
                  <v-list-item-content>总学分:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[1].tc}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>已获学分:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[1].cc}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>学分绩点</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[1].point}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>平均绩点:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[1].gpa}}</v-list-item-content>
                </v-list-item>

              </v-list>
            </v-card>
          </v-col>

          <v-col cols="12" sm="12" md="8" lg="6">
            <v-card class="myCard">
              <v-card-title class="subheading font-weight-bold">已修课程</v-card-title>

              <v-divider></v-divider>

              <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName" height="430">
                <el-table-column prop="coname" label="课程名" width="300">
                </el-table-column>
                <el-table-column prop="grade" label="成绩">
                </el-table-column>

              </el-table>
            </v-card>
          </v-col>

        </v-row>
      </template>
    </v-data-iterator>
  </v-card>
</template>

<script>
export default {
  name: "studentGradeInfo",
  data: () => ({
    itemsPerPage: 1,
    items: [
      {},
      {
        tc: "",
        cc: "",
        point: "",
        gpa: ""
      }
    ],
    tableData: [],
    optionsyear: [],
    year: "",
    userInfo: null,
    allGrade: []
  }),

  computed: {},

  methods: {
    tableRowClassName({ row, rowIndex }) {
      console.log(row);
      if (row.grade < 60) {
        return "error-row";
      }
      if (row.grade > 85) {
        return "success-row";
      }
      return "";
    },
    checkgrade() {
      console.log(this.year);
      let userTemp = JSON.parse(this.global.getRedisInformation());
      let userInfo = userTemp[0];
      this.userInfo = userInfo;
      if (this.year == null || this.year == "") {
        this.items[1].tc = this.userInfo.zz_Totalcredit15;
        this.items[1].cc = this.userInfo.zz_Completecredit15;
        this.items[1].point = this.userInfo.zz_Point15;
        this.items[1].gpa = this.userInfo.zz_GPA15;

        const that = this;
        var url = "http://localhost:8088/ReportStuView/getInfoByCondition";
        var reqData = { sno: this.sno };
        this.Get(url, reqData, function(cb) {
          if (cb.status == "success") {
            console.log(cb.data);
            that.tableData = [];
            for (let i = 0; i < cb.data.length; i++) {
              var tempGrade = {
                coname: null,
                grade: null
              };
              tempGrade.coname = cb.data[i][16];
              tempGrade.grade = cb.data[i][0];
              that.tableData.push(tempGrade);
            }
          } else {
            that.overlay = false;
            that.$message({
              message: "网络异常，请稍候再试",
              type: "error"
            });
          }
        });
      } else {
        for (let i = 0; i < this.allGrade.length; i++) {
          if (this.allGrade[i][9] == this.year) {
            this.items[1].tc = this.allGrade[i][11];
            this.items[1].cc = this.allGrade[i][12];
            this.items[1].point = this.allGrade[i][13];
            this.items[1].gpa = this.allGrade[i][14];
            break;
          }
        }

        const that = this;
        var url = "http://localhost:8088/ReportStuView/getInfoByCondition";
        var reqData = { sno: this.sno, year: this.year };
        this.Get(url, reqData, function(cb) {
          if (cb.status == "success") {
            console.log(cb.data);
            that.tableData = [];
            for (let i = 0; i < cb.data.length; i++) {
              var tempGrade = {
                coname: null,
                grade: null
              };
              tempGrade.coname = cb.data[i][16];
              tempGrade.grade = cb.data[i][0];
              that.tableData.push(tempGrade);
            }
          } else {
            that.overlay = false;
            that.$message({
              message: "网络异常，请稍候再试",
              type: "error"
            });
          }
        });
      }
    },
    Post: function(url, reqData, callback) {
      // console.log(url)
      // console.log(reqData)
      //1.拼接请求参数
      var param = "";
      for (var item in reqData) {
        param += item + "=" + reqData[item] + "&";
      }
      console.log(param);
      //2.发起请求
      var xhr = new XMLHttpRequest();
      console.log("开始请求");

      console.log("onready");
      xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
          if (xhr.status >= 200 && xhr.status < 400) {
            var response = xhr.responseText;
            console.log(response);
            if (response) {
              var responseJson = JSON.parse(response);
              callback(responseJson);
            } else {
              console.log("返回数据不存在");
              callback(false);
            }
          } else {
            console.log("请求失败");
            callback(false);
          }
        }
      };
      xhr.open("POST", url, true);

      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      // xhr.setRequestHeader("Content-Type", "application/json");
      xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
      xhr.setRequestHeader(
        "Access-Control-Allow-Methods",
        "OPTIONS, GET, POST"
      );
      xhr.setRequestHeader(
        "Access-Control-Allow-Headers",
        "x-requested-with,content-type"
      );
      xhr.send(param); //reqData为字符串形式： "key=value"
    },

    Get: function(url, reqData, callback) {
      var self = this;

      url += "?";
      for (var item in reqData) {
        url += item + "=" + reqData[item] + "&";
      }

      console.log(url);
      var xhr = new XMLHttpRequest();
      xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
          if (xhr.status >= 200 && xhr.status < 400) {
            var response = xhr.responseText;
            // console.log(response)
            if (response) {
              var responseJson = JSON.parse(response);
              callback(responseJson);
            } else {
              console.log("返回数据不存在");
              callback(false);
            }
          } else {
            console.log("请求失败");
            callback(false);
          }
        }
      };
      xhr.open("GET", url, true);
      xhr.setRequestHeader("Content-Type", "application/json");
      xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
      xhr.setRequestHeader(
        "Access-Control-Allow-Methods",
        "OPTIONS, GET, POST"
      );
      xhr.setRequestHeader(
        "Access-Control-Allow-Headers",
        "x-requested-with,content-type"
      );
      xhr.send();
    }
  },
  created() {
    this.optionsyear = [];
    this.optionsterm = [];
    var urlyear = "http://localhost:8088/termAndYear/year";
    var urlterm = "http://localhost:8088/termAndYear/term";
    var urlgrade = "http://localhost:8088/ReportView/getInfoBySno";
    let userTemp = JSON.parse(this.global.getRedisInformation());
    let userInfo = userTemp[0];
    this.sno = userInfo.zz_Sno15;
    const that = this;
    this.Get(urlyear, null, function(cb) {
      if (cb.status == "success") {
        console.log(cb.data);
        that.optionsyear = [];
        var tempyearfirst = { value: null, label: "全部" };
        that.optionsyear.push(tempyearfirst);
        for (let i = 0; i < cb.data.length; i++) {
          var tempyear = { value: null, label: null };
          tempyear.value = cb.data[i];
          tempyear.label = cb.data[i];
          that.optionsyear.push(tempyear);
        }
        that.$forceUpdate();
      } else {
        that.$message({
          message: "网络异常，请稍候再试",
          type: "error"
        });
      }
    });

    var reqData = { sno: this.sno };
    this.Get(urlgrade, reqData, function(cb) {
      if (cb.status == "success") {
        that.allGrade = cb.data;
        console.log(that.allGrade);
      } else {
        that.$message({
          message: "网络异常，请稍候再试",
          type: "error"
        });
      }
    });
  }
};
</script>

<style scoped>
.myCard {
  padding-top: 10px;
  margin-left: 20px;
  margin-right: 20px;
  margin-bottom: 20px;
  padding-right: 20px;
}
</style>
