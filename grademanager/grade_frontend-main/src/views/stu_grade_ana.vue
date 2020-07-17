<template>
  <v-card class="myCard">

    <template>
      <v-toolbar prominent src="https://www.zhangzhengtytytttyyytt.top/database/analysis.jpg" dark>
        <v-toolbar-title>学生成绩分析</v-toolbar-title>
      </v-toolbar>
    </template>
    <template>
      <v-row :align="alignment" :justify="justify" style="height: 75px;">
        <v-col class="d-flex" cols="6" sm="2">

          <el-select v-model="year" clearable placeholder="请选择学年">
            <el-option v-for="item in optionsyear" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </v-col>
        <v-col class="d-flex" cols="6" sm="2">

          <el-select v-model="term" clearable placeholder="请选择学期">
            <el-option v-for="item in optionsterm" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </v-col>
        <v-col class="d-flex" cols="1" sm="1">
          <v-btn color="primary" @click="checkList">查询</v-btn>
        </v-col>
      </v-row>
    </template>
    <template>
      <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName" height="380">
        <el-table-column prop="ono" label="开课编号" width="120">
        </el-table-column>
        <el-table-column prop="cono" label="课程编号" width="120">
        </el-table-column>
        <el-table-column prop="coname" label="课程名" width="150">
        </el-table-column>
        <el-table-column prop="credit" label="学分" width="100">
        </el-table-column>
        <el-table-column prop="period" label="学时" width="100">
        </el-table-column>
        <el-table-column prop="way" label="考察方式" width="120">
        </el-table-column>
        <el-table-column prop="year" label="开课学年" width="120">
        </el-table-column>
        <el-table-column prop="term" label="开课学期" width="90">
        </el-table-column>
        <el-table-column prop="classInfo" label="开课班级" width="150">
        </el-table-column>
        <el-table-column prop="grade" label="平均分">
        </el-table-column>
      </el-table>
    </template>
    <v-overlay :value="overlay">
      <v-progress-circular indeterminate color="primary"></v-progress-circular>
    </v-overlay>
  </v-card>
</template>

<script>
import XLSX from "xlsx";
export default {
  name: "stuGradeIn",
  data: () => ({
    optionsyear: [],
    optionsterm: [],
    year: "",
    term: "",
    course: "",
    ono: "",
    pno: "",
    tableData: [],
    userSno: null,
    sno: null,
    overlay: false,
    alignment: "center",
    justify: "center",
    gradeIn: [],
    dialog: false,
    record: 0
  }),
  methods: {
    sleep(n) {
      var start = new Date().getTime();
      //  console.log('休眠前：' + start);
      while (true) {
        if (new Date().getTime() - start > n) {
          break;
        }
      }
      // console.log('休眠后：' + new Date().getTime());
    },
    handleEdit(index, row) {
      console.log(index, row);
      var url = "http://localhost:8088/report/updateOrInsertGrade";
      const that = this;
      if (row.grade == null || row.grade == "") {
        that.$message({
          message: "成绩不能为空",
          type: "warning"
        });
        return;
      }
      var reqData = {
        ono: row.ono,
        sno: row.stuno,
        grade: parseInt(row.grade)
      };
      this.Get(url, reqData, function(cb) {
        if (cb.status == "success") {
          if (cb.data == 1) {
            that.$message({
              message: "插入成绩成功",
              type: "success"
            });
          } else if (cb.data == 2) {
            that.$message({
              message: "修改成绩成功",
              type: "success"
            });
          } else if (cb.data == -1) {
            that.$message({
              message: "课程不存在",
              type: "warning"
            });
          } else if (cb.data == -2) {
            that.$message({
              message: "成绩不能为空",
              type: "warning"
            });
          }
        } else {
          that.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
      });
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 4 === 0) {
        return "warning-row";
      } else if (rowIndex % 2 === 0) {
        return "success-row";
      }
      return "";
    },
    checkList() {
      var url = "http://localhost:8088/CourseSummaryView/getInfo";
      var reqData = { tno: this.sno };
      if (!(this.year == null || this.year == "")) {
        var tempData = { year: this.year };
        Object.assign(reqData, tempData);
      }
      if (!(this.term == null || this.term == "")) {
        var tempData = { term: this.term - 1 };
        Object.assign(reqData, tempData);
      }
      this.overlay = true;
      const that = this;
      console.log(reqData);
      this.tableData = [];
      this.Get(url, reqData, function(cb) {
        if (cb.status == "success") {
          that.overlay = false;
          for (let i = 0; i < cb.data.length; i++) {
            var tempGrade = {
              ono: null,
              cono: null,
              coname: null,
              credit: null,
              period: null,
              way: null,
              year: null,
              term: null,
              classInfo: null,
              grade: null
            };
            tempGrade.ono = cb.data[i].zz_Ono15;
            tempGrade.cono = cb.data[i].zz_Cono15;
            tempGrade.coname = cb.data[i].zz_Coname15;
            tempGrade.credit = cb.data[i].zz_Credit15;
            tempGrade.period = cb.data[i].zz_Period15;
            tempGrade.way = cb.data[i].zz_Way15;
            tempGrade.year = cb.data[i].zz_Year15;
            tempGrade.term = cb.data[i].zz_Term15;
            tempGrade.classInfo =
              cb.data[i].zz_Cyear15 +
              "级" +
              cb.data[i].zz_Pname15 +
              cb.data[i].zz_Cname15;
            tempGrade.grade = cb.data[i].zz_Avggrade15.toFixed(5);
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
    let userInfo = userTemp;
    this.sno = userInfo.zz_Tno15;
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
