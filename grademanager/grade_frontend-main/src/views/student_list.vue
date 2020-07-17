<template>
  <v-card class="myCard">

    <template>
      <v-toolbar class="mb-2" color="cyan darken-2" dark flat>
        <v-toolbar-title>学生信息维护</v-toolbar-title>
        <v-spacer></v-spacer>

        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn v-on="on" v-bind="attrs" icon @click="dialogadd = true">
              <v-icon>mdi-account-plus</v-icon>
            </v-btn>
          </template>
          <span>新增学生</span>
        </v-tooltip>
        <v-dialog v-model="dialogadd" persistent max-width="600px">
          <v-card>
            <v-card-title>
              <span class="headline">新增学生信息</span>
            </v-card-title>
            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="12" sm="6">
                    <v-text-field label="学号" v-model="changesnoadd"></v-text-field>
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
                    <el-select v-model="sexadd" placeholder="请选择性别">
                      <el-option v-for="item in optionsSex" :key="item.value" :label="item.label" :value="item.value">
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
                    <el-select v-model="provinceadd" placeholder="请选择省份" clearable @change="getcities" @clear="clearcities">
                      <el-option v-for="item in optionsProvinces" :key="item.value" :label="item.label" :value="item.value">
                      </el-option>
                    </el-select>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <el-select v-model="cityadd" clearable placeholder="请选择城市">
                      <el-option v-for="item in optionsCity" :key="item.value" :label="item.label" :value="item.value">
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
        <el-table-column prop="sno" label="学号" width="150">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="150">
        </el-table-column>
        <el-table-column prop="sex" label="性别" width="100">
        </el-table-column>
        <el-table-column prop="year" label="年级" width="150">
        </el-table-column>
        <el-table-column prop="profession" label="专业" width="150">
        </el-table-column>
        <el-table-column prop="cname" label="班级" width="150">
        </el-table-column>
        <el-table-column prop="location" label="生源地" width="150">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            <v-dialog v-model="dialogdelete" max-width="290">
              <v-card>
                <v-card-title class="headline">警告</v-card-title>

                <v-card-text>
                  确定删除该学生信息吗？此操作将删除该学生全部信息，且操作不可逆！
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

          <v-dialog v-model="dialog" persistent max-width="600px">
            <template v-slot:activator="{ on, attrs }">
              <v-btn color="primary" dark v-bind="attrs" v-on="on">
                Open Dialog
              </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline">学生基本信息维护</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6">
                      <v-text-field label="学号" v-model="changesno" readonly></v-text-field>
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
                      <el-select v-model="sex" placeholder="请选择性别">
                        <el-option v-for="item in optionsSex" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                      </el-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <el-select v-model="pro" clearable placeholder="请选择专业" @change="getcyear" @clear="clearcyear">
                        <el-option v-for="item in optionsprofession" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                      </el-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <el-select v-model="cyear" clearable placeholder="请选择年级" @change="getclass" @clear="clearclass">
                        <el-option v-for="item in optionscyear" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                      </el-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <el-select v-model="classname" clearable placeholder="请选择班级">
                        <el-option v-for="item in optionsclass" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                      </el-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <el-select v-model="province" placeholder="请选择省份" clearable @change="getcities" @clear="clearcities">
                        <el-option v-for="item in optionsProvinces" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                      </el-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <el-select v-model="city" clearable placeholder="请选择城市">
                        <el-option v-for="item in optionsCity" :key="item.value" :label="item.label" :value="item.value">
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
    cityadd: null
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
      this.changesno = this.tableDataOri[index].zz_Sno15;
      this.changename = this.tableDataOri[index].zz_Name15;
      this.age = this.tableDataOri[index].zz_Age15;
      this.password = "";
      this.sex = this.tableDataOri[index].zz_Sex15;
      this.pro = this.tableDataOri[index].zz_Pno15;
      this.getcyear(this.pro);
      this.cyear = this.tableDataOri[index].zz_CYear15;
      this.getclass(this.cyear);
      this.classname = this.tableDataOri[index].zz_Cno15;
      this.province = this.tableDataOri[index].zz_Prno15;
      this.getcities(this.province);
      this.city = this.tableDataOri[index].zz_Cino15;
    },
    submitupdate() {
      var url = "http://localhost:8088/student/updateStudentInfo";
      var reqData = {
        zz_Sno15: this.changesno,
        zz_Name15: this.changename,
        zz_Sex15: this.sex,
        zz_Age15: this.age,
        zz_Cno15: this.classname,
        zz_Cino15: this.city,
        zz_SPwd: this.password
      };
      const that = this;
      this.overlay = true;
      this.Post(url, reqData, function(cb) {
        if (cb.status == "success") {
          that.$message({
            message: "修改成功",
            type: "success"
          });
          that.updatestudent();
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
        if (this.changesnoadd == this.tableDataOri[i].zz_Sno15) {
          this.$message({
            message: "已存在该学生",
            type: "warning"
          });
          return;
        }
      }
      var url = "http://localhost:8088/student/addStudent";
      var reqData = {
        zz_Sno15: this.changesnoadd,
        zz_Name15: this.changenameadd,
        zz_Sex15: parseInt(this.sexadd),
        zz_Age15: this.ageadd,
        zz_Totalcredit15: 0,
        zz_Cno15: this.classnameadd,
        zz_Cino15: this.cityadd,
        zz_SPwd: this.passwordadd
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
      var url = "http://localhost:8088/student/deleteStudentBySno";
      var reqData = { sno: this.nowrow.sno };
      const that = this;
      this.Get(url, reqData, function(cb) {
        if ((cb.status = "success")) {
          that.dialogdelete = false;
          that.$message({
            message: "删除成功",
            type: "success"
          });
          that.updatestudent();
        } else {
          that.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
      });
    },
    updatestudent() {
      var url = "http://localhost:8088/StuView/getAllInfo";
      const that = this;
      this.Get(url, null, function(cb) {
        if (cb.status == "success") {
          that.overlay = false;
          console.log(cb.data);
          that.tableDataOri = cb.data;
          that.tableData = [];
          for (let i = 0; i < cb.data.length; i++) {
            var tempGrade = {
              sno: null,
              name: null,
              sex: null,
              year: null,
              profession: null,
              cname: null,
              location: null
            };
            tempGrade.sno = cb.data[i].zz_Sno15;
            tempGrade.name = cb.data[i].zz_Name15;
            tempGrade.cname = cb.data[i].zz_PName15 + cb.data[i].zz_CName15;
            tempGrade.sex = cb.data[i].zz_Sex15 == 1 ? "男" : "女";
            tempGrade.year = cb.data[i].zz_CYear15 + "级";
            tempGrade.profession = cb.data[i].zz_PName15;
            tempGrade.cname = cb.data[i].zz_PName15 + cb.data[i].zz_CName15;
            tempGrade.location =
              cb.data[i].zz_Prname15 + cb.data[i].zz_Ciname15;
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
    var url = "http://localhost:8088/StuView/getAllInfo";
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
            sno: null,
            name: null,
            sex: null,
            year: null,
            profession: null,
            cname: null,
            location: null
          };
          tempGrade.sno = cb.data[i].zz_Sno15;
          tempGrade.name = cb.data[i].zz_Name15;
          tempGrade.cname = cb.data[i].zz_PName15 + cb.data[i].zz_CName15;
          tempGrade.sex = cb.data[i].zz_Sex15 == 1 ? "男" : "女";
          tempGrade.year = cb.data[i].zz_CYear15 + "级";
          tempGrade.profession = cb.data[i].zz_PName15;
          tempGrade.cname = cb.data[i].zz_PName15 + cb.data[i].zz_CName15;
          tempGrade.location = cb.data[i].zz_Prname15 + cb.data[i].zz_Ciname15;
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
