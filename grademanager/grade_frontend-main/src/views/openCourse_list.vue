<template>
  <v-card class="myCard">

    <template>
      <v-toolbar class="mb-2" color="cyan darken-2" dark flat>
        <v-toolbar-title>开课信息维护</v-toolbar-title>
        <v-spacer></v-spacer>

        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn v-on="on" v-bind="attrs" icon @click="dialogadd = true">
              <v-icon>mdi-book-plus-multiple</v-icon>
            </v-btn>
          </template>
          <span>新增开设课程</span>
        </v-tooltip>
        <v-dialog v-model="dialogadd" persistent max-width="600px">
          <v-card>
            <v-card-title>
              <span class="headline">新增开设课程信息</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6">
                    <el-select v-model="courseadd" placeholder="请选择课程">
                      <el-option v-for="item in optioncourse" :key="item.value" :label="item.label" :value="item.value">
                      </el-option>
                    </el-select>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <el-select v-model="proadd" clearable placeholder="请选择专业" @change="getcyear" @clear="clearcyear">
                      <el-option v-for="item in optionsprofession" :key="item.value" :label="item.label" :value="item.value">
                      </el-option>
                    </el-select>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <el-select v-model="cyearadd" clearable placeholder="请选择年级" @change="getclass" @clear="clearclass">
                      <el-option v-for="item in optionscyear" :key="item.value" :label="item.label" :value="item.value">
                      </el-option>
                    </el-select>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <el-select v-model="classnameadd" clearable placeholder="请选择班级">
                      <el-option v-for="item in optionsclass" :key="item.value" :label="item.label" :value="item.value">
                      </el-option>
                    </el-select>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <el-select v-model="year" clearable placeholder="请选择学年">
                      <el-option v-for="item in optionsyear" :key="item.value" :label="item.label" :value="item.value">
                      </el-option>
                    </el-select>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <el-select v-model="term" clearable placeholder="请选择学期">
                      <el-option v-for="item in optionsterm" :key="item.value" :label="item.label" :value="item.value">
                      </el-option>
                    </el-select>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <el-select v-model="teacher" placeholder="请选择教师">
                      <el-option v-for="item in optionsTeacher" :key="item.value" :label="item.label" :value="item.value">
                      </el-option>
                    </el-select>
                  </v-col>
                </v-row>
              </v-container>
              <small>*确认无误后提交</small>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="submitadd">添加</v-btn>
              <v-btn color="blue darken-1" text @click="dialogadd = false">取消</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template>
      <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName" height="430">
        <el-table-column prop="ono" label="开课编号" width="90">
        </el-table-column>
        <el-table-column prop="cono" label="课程编号" width="90">
        </el-table-column>
        <el-table-column prop="coname" label="课程名称" width="150">
        </el-table-column>
        <el-table-column prop="credit" label="学分" width="50">
        </el-table-column>
        <el-table-column prop="period" label="学时" width="50">
        </el-table-column>
        <el-table-column prop="ways" label="考察方式" width="90">
        </el-table-column>
        <el-table-column prop="year" label="开课学年" width="90">
        </el-table-column>
        <el-table-column prop="term" label="开课学期" width="90">
        </el-table-column>
        <el-table-column prop="classInfo" label="开课班级" width="120">
        </el-table-column>
        <el-table-column prop="tno" label="教师编号" width="120">
        </el-table-column>
        <el-table-column prop="tname" label="教师姓名" width="120">
        </el-table-column>
        <el-table-column fixed="right" label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            <v-dialog v-model="dialogdelete" max-width="290">
              <v-card>
                <v-card-title class="headline">警告</v-card-title>

                <v-card-text>
                  确定删除该开课信息吗？若此课程未选课，则删除开课课程，否则无法删除！
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn color="red" text @click="deleteStudent">
                    是
                  </v-btn>

                  <v-btn color="green darken-1" text @click="dialogdelete = false">
                    否
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </template>
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
  name: "studentList",
  data: () => ({
    optionsyear: [],
    optionsterm: [],
    optionsprofession: [],
    optionscyear: [],
    optionsclass: [],
    cyear: "",
    classname: "",
    pro: "",
    pno: "",
    city: "",
    province: "",
    tableData: [],
    tableDataOri: [],
    userSno: null,
    sno: null,
    overlay: false,
    optionsProvinces: [],
    optionsCities: [],
    optionsCity: [],
    dialog: false,
    optionsSex: [{ value: 1, label: "男" }, { value: 0, label: "女" }],
    changesno: "",
    changename: "",
    sex: "",
    age: "",
    password: "",
    dialogdelete: false,
    nowrow: null,
    nowindex: null,
    dialogadd: false,
    changesnoadd: "",
    changenameadd: "",
    ageadd: "",
    passwordadd: "",
    sexadd: null,
    proadd: "",
    cyearadd: null,
    classnameadd: null,
    provinceadd: null,
    cityadd: null,
    optioncourse: null,
    courseadd: null,
    year: null,
    teacher: null,
    optionsTeacher: null,
    term: null
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
    submitadd() {
      var url = "http://localhost:8088/openCourses/addOpenCourse";
      var reqData = {
        zz_Cono15: this.courseadd,
        zz_Cno15: this.classnameadd,
        zz_Year15: this.year,
        zz_Term15: this.term,
        zz_Tno15: this.teacher
      };
      console.log(reqData);
      const that = this;
      this.overlay = true;
      this.Post(url, reqData, function(cb) {
        if (cb.status == "success") {
          that.$message({
            message: "添加成功",
            type: "success"
          });
          that.updatestudent();
          that.dialogadd = false;
        } else {
          that.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
      });
    },
    handleDelete(index, row) {
      this.dialogdelete = true;
      this.nowrow = row;
      this.nowindex = index;
    },
    deleteStudent() {
      console.log(this.nowindex, this.nowrow);
      var url = "http://localhost:8088/openCourses/deleteOpenCourse";
      var reqData = { ono: this.nowrow.ono };
      console.log(reqData);
      const that = this;
      this.Get(url, reqData, function(cb) {
        if (cb.status == "success") {
          that.dialogdelete = false;
          that.$message({
            message: "删除开课记录成功",
            type: "success"
          });
          that.updatestudent();
        } else {
          that.dialogdelete = false;
          if (cb.data.errCode == 70003) {
            that.$message({
              message: cb.data.errMsg,
              type: "warning"
            });
          } else {
            that.$message({
              message: "网络异常，请稍候再试",
              type: "error"
            });
          }
        }
      });
    },
    updatestudent() {
      var url = "http://localhost:8088/OpenCoursesStuView/getAllInfo";
      this.overlay = true;
      const that = this;
      this.Get(url, null, function(cb) {
        if (cb.status == "success") {
          that.overlay = false;
          console.log(cb.data);
          that.tableDataOri = cb.data;
          that.tableData = [];
          for (let i = 0; i < cb.data.length; i++) {
            var tempGrade = {
              ono: null,
              cono: null,
              coname: null,
              credit: null,
              period: null,
              ways: null,
              year: null,
              term: null,
              classInfo: null,
              tno: null,
              tname: null
            };
            tempGrade.ono = cb.data[i].zz_Ono15;
            tempGrade.cono = cb.data[i].zz_Cono15;
            tempGrade.coname = cb.data[i].zz_Coname15;
            tempGrade.credit = cb.data[i].zz_Credit15;
            tempGrade.period = cb.data[i].zz_Period15;
            tempGrade.ways = cb.data[i].zz_Way15 == 0 ? "考试" : "考查";
            tempGrade.year = cb.data[i].zz_Year15;
            tempGrade.term = cb.data[i].zz_Term15 + 1;
            tempGrade.classInfo = cb.data[i].zz_PName15 + cb.data[i].zz_CName15;
            tempGrade.tno = cb.data[i].zz_Tno15;
            tempGrade.tname = cb.data[i].zz_Tname15;
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
      this.getCourse();
    },
    getCourse() {
      var urlcourse = "http://localhost:8088/courses/getAllCourses";
      this.overlay = true;
      const that = this;
      this.Get(urlcourse, null, function(cb) {
        if (cb.status == "success") {
          that.overlay = false;
          console.log(cb.data);
          that.optioncourse = [];
          for (let i = 0; i < cb.data.length; i++) {
            var tempGrade = {
              value: null,
              label: null
            };
            tempGrade.value = cb.data[i].zz_Cono15;
            tempGrade.label = cb.data[i].zz_Name15;
            that.optioncourse.push(tempGrade);
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
    getcities(data) {
      this.city = "";
      console.log(data);
      console.log(this.optionsCities);
      this.optionsCity = [];
      for (let i = 0; i < this.optionsCities.length; i++) {
        if (this.optionsCities[i].prno == data) {
          var temp = {
            value: this.optionsCities[i].value,
            label: this.optionsCities[i].label
          };
          this.optionsCity.push(temp);
        }
      }
    },
    clearcities() {
      this.city = "";
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
    var url = "http://localhost:8088/OpenCoursesStuView/getAllInfo";
    this.overlay = true;
    const that = this;
    this.Get(url, null, function(cb) {
      if (cb.status == "success") {
        that.overlay = false;
        console.log(cb.data);
        that.tableDataOri = cb.data;
        that.tableData = [];
        for (let i = 0; i < cb.data.length; i++) {
          var tempGrade = {
            ono: null,
            cono: null,
            coname: null,
            credit: null,
            period: null,
            ways: null,
            year: null,
            term: null,
            classInfo: null,
            tno: null,
            tname: null
          };
          tempGrade.ono = cb.data[i].zz_Ono15;
          tempGrade.cono = cb.data[i].zz_Cono15;
          tempGrade.coname = cb.data[i].zz_Coname15;
          tempGrade.credit = cb.data[i].zz_Credit15;
          tempGrade.period = cb.data[i].zz_Period15;
          tempGrade.ways = cb.data[i].zz_Way15 == 0 ? "考试" : "考查";
          tempGrade.year = cb.data[i].zz_Year15;
          tempGrade.term = cb.data[i].zz_Term15 + 1;
          tempGrade.classInfo = cb.data[i].zz_PName15 + cb.data[i].zz_CName15;
          tempGrade.tno = cb.data[i].zz_Tno15;
          tempGrade.tname = cb.data[i].zz_Tname15;
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
    this.getCourse();
    var urlcity = "http://localhost:8088/cities/getAllCitiesInfo";
    var urlprovince = "http://localhost:8088/provinces/getAllProvinces";
    this.Get(urlprovince, null, function(cb) {
      if (cb.status == "success") {
        that.optionsProvinces = [];
        for (let i = 0; i < cb.data.length; i++) {
          var temp = {
            value: null,
            label: null,
            sum: null
          };
          temp.value = cb.data[i].zz_Prno15;
          temp.label = cb.data[i].zz_Name15;
          temp.sum = cb.data[i].zz_Summarycount15;
          that.optionsProvinces.push(temp);
        }
      } else {
        that.$message({
          message: "网络异常，请稍候再试",
          type: "error"
        });
      }
    });
    this.Get(urlcity, null, function(cb) {
      if (cb.status == "success") {
        that.optionsCities = [];
        for (let i = 0; i < cb.data.length; i++) {
          var temp = {
            value: null,
            label: null,
            prno: null,
            sum: null
          };
          temp.value = cb.data[i].zz_Cino15;
          temp.label = cb.data[i].zz_Name15;
          temp.prno = cb.data[i].zz_Prno15;
          temp.sum = cb.data[i].zz_Summarycount15;
          that.optionsCities.push(temp);
        }
      } else {
        that.$message({
          message: "网络异常，请稍候再试",
          type: "error"
        });
      }
    });

    this.optionsprofession = [];
    var urlprofession = "http://localhost:8088/profession/getAllProfession";
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
    this.optionsyear = [];
    this.optionsterm = [];
    var urlyear = "http://localhost:8088/termAndYear/year";
    var urlterm = "http://localhost:8088/termAndYear/term";
    var urltea = "http://localhost:8088/teacher/getAllTeacher";
    this.Get(urlyear, null, function(cb) {
      if (cb.status == "success") {
        console.log(cb.data);
        that.optionsyear = [];
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
        that.optionsterm = [];
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
    this.Get(urltea, null, function(cb) {
      if (cb.status == "success") {
        console.log(cb.data);
        that.optionsTeacher = [];
        for (let i = 0; i < cb.data.length; i++) {
          var temptea = { value: null, label: null };
          if (cb.data[i].zz_Name15 == "未启用") continue;
          temptea.value = cb.data[i].zz_Tno15;
          temptea.label = cb.data[i].zz_Name15;
          that.optionsTeacher.push(temptea);
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
