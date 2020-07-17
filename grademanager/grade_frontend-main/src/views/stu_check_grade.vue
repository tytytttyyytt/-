<template>
  <v-card class="myCard">

    <template>
      <v-toolbar class="mb-2" color="indigo darken-5" dark flat>
        <v-toolbar-title>学生成绩查询</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-col class="d-flex" cols="4" sm="2">
          <el-select v-model="year" clearable placeholder="请选择学年">
            <el-option v-for="item in optionsyear" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </v-col>
        <v-col class="d-flex" cols="4" sm="2">
          <el-select v-model="term" clearable placeholder="请选择学期">
            <el-option v-for="item in optionsterm" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </v-col>
        <v-col class="d-flex" cols="1" sm="1">
          <v-btn color="primary" @click="checkgrade">查询</v-btn>
        </v-col>
      </v-toolbar>
    </template>
    <template>
      <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName" height="430">
        <el-table-column prop="sno" label="学号" width="120">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="160">
        </el-table-column>
        <el-table-column prop="cname" label="班级" width="180">
        </el-table-column>
        <el-table-column prop="cono" label="课程号" width="120">
        </el-table-column>
        <el-table-column prop="coname" label="课程名称" width="180">
        </el-table-column>
        <el-table-column prop="grade" label="成绩" width="90">
        </el-table-column>
        <el-table-column prop="credit" label="学分" width="90">
        </el-table-column>
        <el-table-column prop="period" label="学时" width="90">
        </el-table-column>
        <el-table-column prop="tname" label="教师">
        </el-table-column>
      </el-table>
    </template>
    <v-overlay :value="overlay">
      <v-progress-circular indeterminate color="primary"></v-progress-circular>
    </v-overlay>
  </v-card>
</template>

<script>
export default {
  name: "stuCheckGrade",
  data: () => ({
    optionsyear: [],
    optionsterm: [],
    year: "",
    term: "",
    tableData: [],
    userSno: null,
    sno: null,
    overlay: false
  }),
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
      var url = "http://localhost:8088/ReportStuView/getInfoByCondition";
      var reqData;
      if (
        (this.year == null || this.year == "") &&
        (this.term == null || this.term == "")
      ) {
        reqData = { sno: this.sno };
      } else if (this.term == null || this.term == "") {
        reqData = { sno: this.sno, year: this.year };
      } else if (this.year == null || this.year == "") {
        reqData = { sno: this.sno, term: this.term - 1 };
      } else {
        reqData = { sno: this.sno, year: this.year, term: this.term - 1 };
      }
      this.overlay = true;
      const that = this;
      this.Get(url, reqData, function(cb) {
        if (cb.status == "success") {
          that.overlay = false;
          console.log(cb.data);
          that.tableData = [];
          for (let i = 0; i < cb.data.length; i++) {
            var tempGrade = {
              sno: null,
              name: null,
              cname: null,
              cono: null,
              coname: null,
              grade: null,
              credit: null,
              period: null,
              tname: null
            };
            tempGrade.sno = cb.data[i][1];
            tempGrade.name = cb.data[i][2];
            tempGrade.cname =
              cb.data[i][6] + "级" + cb.data[i][9] + cb.data[i][7];
            tempGrade.cono = cb.data[i][15];
            tempGrade.coname = cb.data[i][16];
            tempGrade.grade = cb.data[i][0];
            tempGrade.credit = cb.data[i][17];
            tempGrade.period = cb.data[i][19];
            tempGrade.tname = cb.data[i][24];
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
    this.Get(urlterm, null, function(cb) {
      if (cb.status == "success") {
        console.log(cb.data);
        var temptermfirst = { value: null, label: "全部" };
        that.optionsterm = [];
        that.optionsterm.push(temptermfirst);
        for (let i = 0; i < cb.data.length; i++) {
          var tempterm = { value: null, label: null };
          tempterm.value = cb.data[i] + 1;
          tempterm.label = cb.data[i] + 1;
          that.optionsterm.push(tempterm);
        }
        that.$forceUpdate();
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

<style>
.myCard {
  padding-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
  margin-bottom: 20px;
  padding-right: 20px;
  padding-bottom: 20px;
}
.el-table .warning-row {
  background: oldlace;
}
.el-table .error-row {
  background: #f56c6c;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>
