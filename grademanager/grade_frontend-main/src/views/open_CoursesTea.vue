<template>
  <v-card class="myCard">

    <template>
      <v-toolbar prominent src="https://www.zhangzhengtytytttyyytt.top/database/opencource.jpg" dark>
        <v-toolbar-title>教学安排查询</v-toolbar-title>
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
        <v-col class="d-flex" cols="6" sm="2">

          <el-input placeholder="请输入课程号" v-model="inputcono" clearable>
          </el-input>
        </v-col>
        <v-col class="d-flex" cols="6" sm="2">

          <el-input placeholder="请输入课程名" v-model="inputconame" clearable>
          </el-input>
        </v-col>
        <v-col class="d-flex" cols="6" sm="2">

          <el-select v-model="pro" clearable placeholder="请选择专业" @change="getcyear" @clear="clearcyear">
            <el-option v-for="item in optionsprofession" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </v-col>
      </v-row>
      <v-row :align="alignment" :justify="justify" style="height: 75px;">
        <v-col class="d-flex" cols="6" sm="2">

          <el-select v-model="cyear" clearable placeholder="请选择年级" @change="getclass" @clear="clearclass">
            <el-option v-for="item in optionscyear" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </v-col>
        <v-col class="d-flex" cols="6" sm="2">

          <el-select v-model="classname" clearable placeholder="请选择班级">
            <el-option v-for="item in optionsclass" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </v-col>
        <v-col class="d-flex" cols="6" sm="2">

          <el-input placeholder="请输入教师名" v-model="inputtname" clearable>
          </el-input>
        </v-col>
        <v-col class="d-flex" cols="6" sm="2">

          <el-select v-model="ways" clearable placeholder="请选择考察形式">
            <el-option v-for="item in optionsways" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </v-col>
        <v-col class="d-flex" cols="1" sm="1">
          <v-btn color="primary" @click="checkOpen">查询</v-btn>
        </v-col>
      </v-row>
    </template>
    <template>
      <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName" height="380">
        <el-table-column prop="cono" label="课程号" width="120">
        </el-table-column>
        <el-table-column prop="coname" label="课程名称" width="180">
        </el-table-column>
        <el-table-column prop="credit" label="学分" width="90">
        </el-table-column>
        <el-table-column prop="period" label="课时" width="90">
        </el-table-column>
        <el-table-column prop="year" label="开课学年" width="100">
        </el-table-column>
        <el-table-column prop="term" label="开课学期" width="100">
        </el-table-column>
        <el-table-column prop="tno" label="教师编号" width="120">
        </el-table-column>
        <el-table-column prop="tname" label="教师姓名">
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
  name: "openCourseTea",
  data: () => ({
    optionsyear: [],
    optionsterm: [],
    optionsways: [
      {
        value: null,
        label: "全部"
      },
      {
        value: 0,
        label: "考试"
      },
      {
        value: 1,
        label: "考查"
      }
    ],
    optionsprofession: [],
    optionscyear: [],
    optionsclass: [],
    year: "",
    term: "",
    inputcono: "",
    inputconame: "",
    inputtname: "",
    ways: "",
    cyear: "",
    classname: "",
    pro: "",
    pno: "",
    tableData: [],
    userSno: null,
    sno: null,
    overlay: false,
    alignment: "center",
    justify: "center"
  }),
  methods: {
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 4 === 0) {
        return "warning-row";
      } else if (rowIndex % 2 === 0) {
        return "success-row";
      }
      return "";
    },
    checkOpen() {
      var url = "http://localhost:8088/OpenCoursesStuView/getInfoByCondition";
      var reqData = {};
      if (!(this.year == null || this.year == "")) {
        var reqDatatemp = { year: this.year };
        Object.assign(reqData, reqDatatemp);
      }
      if (!(this.term == null || this.term == "")) {
        var reqDatatemp = { term: this.term - 1 };
        Object.assign(reqData, reqDatatemp);
      }
      if (this.inputcono != "") {
        var reqDatatemp = { cono: this.inputcono };
        Object.assign(reqData, reqDatatemp);
      }
      if (this.inputconame != "") {
        var reqDatatemp = { coname: this.inputconame };
        Object.assign(reqData, reqDatatemp);
      }
      if (this.inputtname != "") {
        var reqDatatemp = { tname: this.inputtname };
        Object.assign(reqData, reqDatatemp);
      }
      if (!(this.ways == null || this.ways == "")) {
        var reqDatatemp = { ways: this.ways };
        Object.assign(reqData, reqDatatemp);
      }
      if (this.ways == 0) {
        var reqDatatemp = { ways: this.ways };
        Object.assign(reqData, reqDatatemp);
      }
      if (!(this.pro == "" || this.pro == null)) {
        var reqDatatemp = { pno: this.pro };
        Object.assign(reqData, reqDatatemp);
        if (!(this.cyear == "" || this.cyear == null)) {
          var reqDatatemp = { cyear: this.cyear };
          Object.assign(reqData, reqDatatemp);
          if (!(this.classname == "" || this.classname == null)) {
            var reqDatatemp = { cno: this.classname };
            Object.assign(reqData, reqDatatemp);
          }
        }
      }
      console.log(reqData);
      this.overlay = true;
      const that = this;
      console.log(reqData);
      this.Get(url, reqData, function(cb) {
        if (cb.status == "success") {
          that.overlay = false;
          console.log(cb.data);
          that.tableData = [];
          for (let i = 0; i < cb.data.length; i++) {
            var tempCourse = {
              cono: null,
              coname: null,
              credit: null,
              period: null,
              year: null,
              term: null,
              tno: null,
              tname: null
            };
            tempCourse.cono = cb.data[i][1];
            tempCourse.coname = cb.data[i][2];
            tempCourse.credit = cb.data[i][3];
            tempCourse.period = cb.data[i][4];
            tempCourse.year = cb.data[i][6];
            tempCourse.term = cb.data[i][7] + 1;
            tempCourse.tno = cb.data[i][13];
            tempCourse.tname = cb.data[i][14];
            that.tableData.push(tempCourse);
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
    getcyear(data) {
      if (data == null || data == "") {
        return;
      }
      console.log(data);
      this.optionscyear = [];
      var urlyear = "http://localhost:8088/xclass/getAllYears";
      var reqData = { pno: data };
      this.pno = data;
      const that = this;
      this.Get(urlyear, reqData, function(cb) {
        if (cb.status == "success") {
          console.log(cb.data);
          that.optionscyear = [];
          var tempyearfirst = { value: null, label: "全部" };
          that.optionscyear.push(tempyearfirst);
          for (let i = 0; i < cb.data.length; i++) {
            var tempcyear = { value: null, label: null };
            tempcyear.value = cb.data[i];
            tempcyear.label = cb.data[i];
            that.optionscyear.push(tempcyear);
          }
          that.$forceUpdate();
        } else {
          that.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
      });
    },
    getclass(data) {
      if (data == null || data == "") {
        return;
      }
      console.log(data);
      this.optionsclass = [];
      var urlyear = "http://localhost:8088/xclass/getAllClasses";
      var reqData = { pno: this.pno, year: data };
      const that = this;
      this.Get(urlyear, reqData, function(cb) {
        if (cb.status == "success") {
          console.log(cb.data);
          that.optionsclass = [];
          var tempclassfirst = { value: null, label: "全部" };
          that.optionsclass.push(tempclassfirst);
          for (let i = 0; i < cb.data.length; i++) {
            var tempclass = { value: null, label: null };
            tempclass.value = cb.data[i][0];
            tempclass.label = cb.data[i][1];
            that.optionsclass.push(tempclass);
          }
          that.$forceUpdate();
        } else {
          that.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
      });
    },
    clearcyear() {
      this.cyear = "";
      this.pno = "";
      this.optionscyear = [];
      this.classname = "";
      this.optionsclass = [];
      var tempyearfirst = { value: null, label: "全部" };
      this.optionscyear.push(tempyearfirst);
      this.optionsclass.push(tempyearfirst);
    },
    clearclass() {
      this.classname = "";
      this.optionsclass = [];
      var tempyearfirst = { value: null, label: "全部" };
      this.optionsclass.push(tempyearfirst);
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
    this.optionsprofession = [];
    var urlyear = "http://localhost:8088/termAndYear/year";
    var urlterm = "http://localhost:8088/termAndYear/term";
    var urlprofession = "http://localhost:8088/profession/getAllProfession";
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
    this.Get(urlprofession, null, function(cb) {
      if (cb.status == "success") {
        console.log(cb.data);
        var temptermfirst = { value: null, label: "全部" };
        that.optionsprofession = [];
        that.optionsprofession.push(temptermfirst);
        for (let i = 0; i < cb.data.length; i++) {
          var temppro = { value: null, label: null };
          temppro.value = cb.data[i].zz_Pno15;
          temppro.label = cb.data[i].zz_Name15;
          that.optionsprofession.push(temppro);
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
