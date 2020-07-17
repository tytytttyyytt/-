<template>
  <v-card class="myCard">

    <template>
      <v-toolbar class="mb-2" color="cyan darken-2" dark flat>
        <v-toolbar-title>课程信息维护</v-toolbar-title>
        <v-spacer></v-spacer>

        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn v-on="on" v-bind="attrs" icon @click="dialogadd = true">
              <v-icon>mdi-book-plus-multiple</v-icon>
            </v-btn>
          </template>
          <span>新增课程</span>
        </v-tooltip>
        <v-dialog v-model="dialogadd" persistent max-width="600px">
          <v-card>
            <v-card-title>
              <span class="headline">新增课程信息</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6">
                    <v-text-field label="课程名" v-model="changenameadd"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field label="学时" v-model="periodadd"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field label="学分" v-model="creditadd"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <el-select v-model="waysadd" clearable placeholder="请选择考察方式">
                      <el-option v-for="item in optionsways" :key="item.value" :label="item.label" :value="item.value">
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
        <el-table-column prop="cono" label="课程号" width="150">
        </el-table-column>
        <el-table-column prop="name" label="课程名" width="250">
        </el-table-column>
        <el-table-column prop="period" label="学时" width="150">
        </el-table-column>
        <el-table-column prop="ways" label="考察方式" width="200">
        </el-table-column>
        <el-table-column prop="credit" label="学分" width="150">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            <v-dialog v-model="dialogdelete" max-width="290">
              <v-card>
                <v-card-title class="headline">警告</v-card-title>

                <v-card-text>
                  确定删除该课程信息吗？若此课程未开课，则删除课程，否则无法删除！
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn color="red" text @click="deleteCourse">
                    是
                  </v-btn>

                  <v-btn color="green darken-1" text @click="dialogdelete = false">
                    否
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </template>

          <v-dialog v-model="dialog" persistent max-width="600px">
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="primary" dark v-bind="attrs" v-on="on">
                Open Dialog
              </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline">课程基本信息维护</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6">
                      <v-text-field label="课程号" v-model="changesno" readonly></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-text-field label="课程名" v-model="changename"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-text-field label="学时" v-model="period"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-text-field label="学分" v-model="credit"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <el-select v-model="ways" clearable placeholder="请选择考察方式">
                        <el-option v-for="item in optionsways" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                      </el-select>
                    </v-col>
                  </v-row>
                </v-container>
                <small>*确认无误后提交</small>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="submitupdate">修改</v-btn>
                <v-btn color="blue darken-1" text @click="dialog = false">取消</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
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
  name: "courseList",
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
    optionslevel: [
      { value: 0, label: "助教" },
      { value: 1, label: "讲师" },
      { value: 2, label: "副教授" },
      { value: 3, label: "教授" }
    ],
    phone: null,
    phoneadd: null,
    optionsways: [
      {
        value: 0,
        label: "考试"
      },
      {
        value: 1,
        label: "考查"
      }
    ],
    period: null,
    ways: null,
    credit: null,
    periodadd: null,
    waysadd: null,
    creditadd: null
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
    handleEdit(index, row) {
      console.log(index, this.tableDataOri[index]);
      this.dialog = true;
      this.changesno = this.tableDataOri[index].zz_Cono15;
      this.changename = this.tableDataOri[index].zz_Name15;
      this.period = this.tableDataOri[index].zz_Period15;
      this.ways = this.tableDataOri[index].zz_Way15;
      this.credit = this.tableDataOri[index].zz_Credit15;
    },
    submitupdate() {
      var url = "http://localhost:8088/courses/updateCourse";
      var reqData = {
        zz_Cono15: this.changesno,
        zz_Name15: this.changename,
        zz_Period15: this.period,
        zz_Way15: this.ways,
        zz_Credit15: this.credit
      };
      const that = this;
      console.log(reqData);
      this.overlay = true;
      this.Post(url, reqData, function(cb) {
        if (cb.status == "success") {
          that.$message({
            message: "修改成功",
            type: "success"
          });
          that.updatecourse();
          that.dialog = false;
        } else {
          that.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
      });
    },
    submitadd() {
      for (let i = 0; i < this.tableDataOri.length; i++) {
        if (this.changesnoadd == this.tableDataOri[i].zz_Tno15) {
          this.$message({
            message: "已存在该教师",
            type: "warning"
          });
          return;
        }
      }
      var url = "http://localhost:8088/courses/insertCourse";
      var reqData = {
        zz_Name15: this.changenameadd,
        zz_Period15: this.periodadd,
        zz_Way15: this.waysadd,
        zz_Credit15: this.creditadd
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
          that.updatecourse();
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
    deleteCourse() {
      console.log(this.nowindex, this.nowrow);
      var url = "http://localhost:8088/courses/deleteCourse";
      var reqData = { cono: this.nowrow.cono };
      console.log(reqData);
      const that = this;
      this.Get(url, reqData, function(cb) {
        if (cb.status == "success") {
          that.dialogdelete = false;
          that.$message({
            message: "删除课程成功",
            type: "success"
          });
          that.updatecourse();
        } else {
          that.dialogdelete = false;
          if (cb.data.errCode == 70002) {
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
    updatecourse() {
      var url = "http://localhost:8088/courses/getAllCourses";
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
              cono: null,
              name: null,
              period: null,
              ways: null,
              credit: null
            };
            tempGrade.cono = cb.data[i].zz_Cono15;
            tempGrade.name = cb.data[i].zz_Name15;
            tempGrade.period = cb.data[i].zz_Period15;
            tempGrade.ways = cb.data[i].zz_Way15 == 1 ? "考查" : "考试";
            tempGrade.credit = cb.data[i].zz_Credit15;
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
    var url = "http://localhost:8088/courses/getAllCourses";
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
            cono: null,
            name: null,
            period: null,
            ways: null,
            credit: null
          };
          tempGrade.cono = cb.data[i].zz_Cono15;
          tempGrade.name = cb.data[i].zz_Name15;
          tempGrade.period = cb.data[i].zz_Period15;
          tempGrade.ways = cb.data[i].zz_Way15 == 1 ? "考查" : "考试";
          tempGrade.credit = cb.data[i].zz_Credit15;
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
