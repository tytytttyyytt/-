<template>
  <v-card class="myCard">

    <template>
      <v-toolbar class="mb-2" color="cyan darken-2" dark flat>
        <v-toolbar-title>教师信息维护</v-toolbar-title>
        <v-spacer></v-spacer>

        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn v-on="on" v-bind="attrs" icon @click="dialogadd = true">
              <v-icon>mdi-account-plus</v-icon>
            </v-btn>
          </template>
          <span>新增教师</span>
        </v-tooltip>
        <v-dialog v-model="dialogadd" persistent max-width="600px">
          <v-card>
            <v-card-title>
              <span class="headline">新增教师信息</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6">
                    <v-text-field label="工号" v-model="changesnoadd"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field label="姓名" v-model="changenameadd"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field label="年龄" v-model="ageadd"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field type="password" label="密码" v-model="passwordadd"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-text-field label="联系方式" v-model="phoneadd"></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="6">

                  </v-col>
                  <v-col cols="12" sm="6">
                    <el-select v-model="sexadd" placeholder="请选择性别">
                      <el-option v-for="item in optionsSex" :key="item.value" :label="item.label" :value="item.value">
                      </el-option>
                    </el-select>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <el-select v-model="proadd" clearable placeholder="请选择职称">
                      <el-option v-for="item in optionslevel" :key="item.value" :label="item.label" :value="item.value">
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
        <el-table-column prop="tno" label="工号" width="150">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="150">
        </el-table-column>
        <el-table-column prop="sex" label="性别" width="100">
        </el-table-column>
        <el-table-column prop="age" label="年龄" width="150">
        </el-table-column>
        <el-table-column prop="level" label="职称" width="150">
        </el-table-column>
        <el-table-column prop="phone" label="联系方式" width="200">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">抹去信息</el-button>
            <v-dialog v-model="dialogdelete" max-width="290">
              <v-card>
                <v-card-title class="headline">警告</v-card-title>

                <v-card-text>
                  确定删除该教师信息吗？此操作不会删除教师所附带的其他记录，仅抹去教师的信息，且操作不可逆！
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn color="red" text @click="deleteTeacher">
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
                <span class="headline">教师基本信息维护</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6">
                      <v-text-field label="工号" v-model="changesno" readonly></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-text-field label="姓名" v-model="changename"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-text-field label="年龄" v-model="age"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-text-field type="password" label="密码" v-model="password"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-text-field label="联系方式" v-model="phone"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">

                    </v-col>
                    <v-col cols="12" sm="6">
                      <el-select v-model="sex" placeholder="请选择性别">
                        <el-option v-for="item in optionsSex" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                      </el-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <el-select v-model="pro" clearable placeholder="请选择职称">
                        <el-option v-for="item in optionslevel" :key="item.value" :label="item.label" :value="item.value">
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
  name: "teacherList",
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
    phoneadd: null
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
      this.changesno = this.tableDataOri[index].zz_Tno15;
      this.changename = this.tableDataOri[index].zz_Name15;
      this.age = this.tableDataOri[index].zz_Age15;
      this.password = this.tableDataOri[index].zz_TPwd;
      this.sex = this.tableDataOri[index].zz_Sex15;
      this.pro = this.tableDataOri[index].zz_Level15;
      this.phone = this.tableDataOri[index].zz_Phone15;
    },
    submitupdate() {
      var url = "http://localhost:8088/teacher/updateTeacherInfo";
      var reqData = {
        zz_Tno15: this.changesno,
        zz_Name15: this.changename,
        zz_Sex15: this.sex,
        zz_Age15: this.age,
        zz_Level15: this.pro,
        zz_Phone15: this.phone,
        zz_TPwd: this.password
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
          that.updateteacher();
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
      var url = "http://localhost:8088/teacher/addTeacher";
      var reqData = {
        zz_Tno15: this.changesnoadd,
        zz_Name15: this.changenameadd,
        zz_Sex15: this.sexadd,
        zz_Age15: this.ageadd,
        zz_Level15: this.proadd,
        zz_Phone15: this.phoneadd,
        zz_TPwd: this.passwordadd
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
          that.updateteacher();
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
    deleteTeacher() {
      console.log(this.nowindex, this.nowrow);
      var url = "http://localhost:8088/teacher/eraseTeacherInfo";
      var reqData = { tno: this.nowrow.tno };
      console.log(reqData);
      const that = this;
      this.Get(url, reqData, function(cb) {
        if ((cb.status = "success")) {
          that.dialogdelete = false;
          that.$message({
            message: "抹去信息成功",
            type: "success"
          });
          that.updateteacher();
        } else {
          that.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
      });
    },
    updateteacher() {
      var url = "http://localhost:8088/teacher/getAllTeacher";
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
              tno: null,
              name: null,
              sex: null,
              age: null,
              level: null,
              phone: null
            };
            tempGrade.tno = cb.data[i].zz_Tno15;
            tempGrade.name = cb.data[i].zz_Name15;
            tempGrade.sex = cb.data[i].zz_Sex15 == 1 ? "男" : "女";
            tempGrade.age = cb.data[i].zz_Age15 + "岁";
            if (cb.data[i].zz_Level15 == 0) {
              tempGrade.level = "助教";
            } else if (cb.data[i].zz_Level15 == 1) {
              tempGrade.level = "讲师";
            } else if (cb.data[i].zz_Level15 == 2) {
              tempGrade.level = "副教授";
            } else if (cb.data[i].zz_Level15 == 3) {
              tempGrade.level = "教授";
            } else if (cb.data[i].zz_Level15 == -1) {
              tempGrade.level = "无";
            }
            tempGrade.phone = cb.data[i].zz_Phone15;
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
    var url = "http://localhost:8088/teacher/getAllTeacher";
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
            tno: null,
            name: null,
            sex: null,
            age: null,
            level: null,
            phone: null
          };
          tempGrade.tno = cb.data[i].zz_Tno15;
          tempGrade.name = cb.data[i].zz_Name15;
          tempGrade.sex = cb.data[i].zz_Sex15 == 1 ? "男" : "女";
          tempGrade.age = cb.data[i].zz_Age15 + "岁";
          if (cb.data[i].zz_Level15 == 0) {
            tempGrade.level = "助教";
          } else if (cb.data[i].zz_Level15 == 1) {
            tempGrade.level = "讲师";
          } else if (cb.data[i].zz_Level15 == 2) {
            tempGrade.level = "副教授";
          } else if (cb.data[i].zz_Level15 == 3) {
            tempGrade.level = "教授";
          } else if (cb.data[i].zz_Level15 == -1) {
            tempGrade.level = "无";
          }
          tempGrade.phone = cb.data[i].zz_Phone15;
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
