<template>
  <v-card class="myCard">

    <template>
      <v-toolbar prominent src="https://www.zhangzhengtytytttyyytt.top/database/gradeIn.jpg" dark>
        <v-toolbar-title>学生成绩维护</v-toolbar-title>
      </v-toolbar>
    </template>
    <template>
      <v-row :align="alignment" :justify="justify" style="height: 75px;">
        <v-col class="d-flex" cols="6" sm="2">

          <el-select v-model="year" clearable placeholder="请选择学年" @clear="clearcourse" @change="getcoursebyyear">
            <el-option v-for="item in optionsyear" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </v-col>
        <v-col class="d-flex" cols="6" sm="2">

          <el-select v-model="term" clearable placeholder="请选择学期" @clear="clearcourse" @change="getcoursebyterm">
            <el-option v-for="item in optionsterm" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </v-col>
        <v-col class="d-flex" cols="6" sm="2">

          <el-select v-model="course" clearable placeholder="请选择教授课程">
            <el-option v-for="item in optionscourse" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </v-col>

        <v-col class="d-flex" cols="1" sm="1">
          <v-btn color="primary" @click="checkList">查询</v-btn>
        </v-col>
        <v-col class="d-flex" cols="1" sm="1">
          <el-upload class="upload-demo" action="*" ref="upload" accept=".xls,.xlsx" :on-change="upload" :show-file-list="false" :auto-upload="false">
            <v-btn color="primary">批量导入</v-btn>
          </el-upload>
          <v-dialog v-model="dialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">确认成绩</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <el-table :data="gradeIn" style="width: 100%" :row-class-name="tableRowClassName" height="380">
                    <el-table-column prop="sno" label="学号" width="150">
                    </el-table-column>
                    <el-table-column prop="ono" label="开课编号" width="150">
                    </el-table-column>
                    <el-table-column label="成绩">
                      <template slot-scope="scope">
                        <el-input style="width:60px" v-model="scope.row.grade" placeholder="请输入成绩"></el-input>
                      </template>
                    </el-table-column>
                  </el-table>
                </v-container>
                <small>*确认无误后上传</small>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="uploadGrade">插入/修改</v-btn>
                <v-btn color="blue darken-1" text @click="dialog = false">返回</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-col>
      </v-row>
    </template>
    <template>
      <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName" height="380">
        <el-table-column prop="stuno" label="学号" width="200">
        </el-table-column>
        <el-table-column prop="sname" label="姓名" width="300">
        </el-table-column>
        <el-table-column prop="ono" label="开课编号" width="100">
        </el-table-column>
        <el-table-column prop="cname" label="课程" width="250">
        </el-table-column>
        <el-table-column label="成绩" width="150">
          <template slot-scope="scope">
            <el-input style="width:60px" v-model="scope.row.grade" placeholder="请输入成绩"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="handleEdit(scope.$index, scope.row)">录入/修改</el-button>
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
import XLSX from "xlsx";
export default {
  name: "stuGradeIn",
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

    optionscourse: [],
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
    upload(file, fileList) {
      console.log("file", file);
      console.log("fileList", fileList);
      let files = { 0: file.raw };
      this.readExcel1(files);
    },
    readExcel1(files) {
      //表格导入
      var that = this;
      console.log(files);
      if (files.length <= 0) {
        //如果没有文件名
        return false;
      } else if (!/\.(xls|xlsx)$/.test(files[0].name.toLowerCase())) {
        this.$Message.error("上传格式不正确，请上传xls或者xlsx格式");
        return false;
      }

      const fileReader = new FileReader();
      fileReader.onload = ev => {
        try {
          const data = ev.target.result;
          const workbook = XLSX.read(data, {
            type: "binary"
          });
          const wsname = workbook.SheetNames[0]; //取第一张表
          const ws = XLSX.utils.sheet_to_json(workbook.Sheets[wsname]); //生成json表格内容
          console.log(ws);
          that.gradeIn = ws;
          // that.peopleArr = [];//清空接收数据
          // if(that.peopleArr.length == 1 && that.peopleArr[0].roleName == "" && that.peopleArr[0].enLine == ""){
          //     that.peopleArr = [];
          // }
          //重写数据
          try {
          } catch (err) {
            console.log(err);
          }

          this.$refs.upload.value = "";
        } catch (e) {
          console.log(e);
          return false;
        }
      };
      fileReader.readAsBinaryString(files[0]);

      this.showList();
    },
    showList() {
      this.dialog = true;
    },
    uploadGrade() {
      this.record = 0;
      for (let i = 0; i < this.gradeIn.length; i++) {
        var url = "http://localhost:8088/report/updateOrInsertGrade";
        const that = this;
        if (this.gradeIn[i].grade == null || this.gradeIn[i].grade == "") {
          that.$message({
            message: "成绩不能为空",
            type: "warning"
          });
          return;
        }
        var reqData = {
          ono: this.gradeIn[i].ono,
          sno: this.gradeIn[i].sno,
          grade: parseInt(this.gradeIn[i].grade)
        };
        this.Get(url, reqData, function(cb) {
          if (cb.status == "success") {
            that.record = that.record + 1;
            if (that.record == that.gradeIn.length) {
              console.log("succ", that.record);
              that.showResult(that.record);
            } else {
              console.log(that.record);
            }
          } else {
            that.showResult(that.record);
            return;
          }
        });
        this.sleep(1000);
      }
    },
    showResult(data) {
      if (this.record == this.gradeIn.length) {
        this.$message({
          message: "插入或修改成功，共" + this.record + "条记录",
          type: "success"
        });
      } else if (this.record == -1) {
        this.$message({
          message: "网络异常,插入失败",
          type: "error"
        });
      } else {
        this.$message({
          message: "插入或修改失败，已处理" + this.record + "条记录",
          type: "warning"
        });
      }
    },
    sleep(n) {
      var start = new Date().getTime();
      //  console.log('休眠前：' + start);
      while (true) {
        this.overlay = true;
        if (new Date().getTime() - start > n) {
          break;
        }
      }
      this.overlay = false;
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
      if (this.course == null || this.course == "") {
        return;
      }
      var url = "http://localhost:8088/OpenCoursesStuView/getInfoByCono";
      var reqData = { cono: this.course };
      console.log(reqData);
      this.overlay = true;
      const that = this;
      console.log(reqData);
      this.Get(url, reqData, function(cb) {
        if (cb.status == "success") {
          that.getclassList(cb.data[0]);
        } else {
          that.overlay = false;
          that.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
      });
    },
    getclassList(data) {
      var url = "http://localhost:8088/student/getAllByCnoAndCono";
      var reqData1 = { cno: data, cono: this.course };
      const that = this;
      this.Get(url, reqData1, function(cb) {
        that.overlay = false;
        console.log(cb.data);
        that.tableData = [];
        for (let i = 0; i < cb.data.ori.length; i++) {
          var tempCourse = {
            stuno: null,
            sname: null,
            ono: null,
            cname: null,
            grade: null
          };
          tempCourse.stuno = cb.data.ori[i].zz_Sno15;
          tempCourse.sname = cb.data.ori[i].zz_Name15;
          var coursename = {
            cname: that.optionscourse[data].label,
            ono: that.optionscourse[data].ono
          };
          Object.assign(tempCourse, coursename);
          for (let j = 0; j < cb.data.grade.length; j++) {
            if (cb.data.grade[j][1] == tempCourse.stuno) {
              var stugrade = { grade: cb.data.grade[j][0] };
              Object.assign(tempCourse, stugrade);
              break;
            }
          }
          that.tableData.push(tempCourse);
        }
      });
    },
    getcoursebyyear(data) {
      console.log(data);
      this.optionscourse = [];
      var urlyear =
        "http://localhost:8088/OpenCoursesStuView/getInfoByYearOrTerm";
      var reqData = { tno: this.sno, year: data };
      const that = this;
      this.Get(urlyear, reqData, function(cb) {
        if (cb.status == "success") {
          console.log(cb.data);
          that.optionscourse = [];
          var tempcoursefirst = { value: null, label: "全部", ono: null };
          that.optionscourse.push(tempcoursefirst);
          for (let i = 0; i < cb.data.length; i++) {
            var tempcourse = { value: null, label: null, ono: null };
            tempcourse.value = cb.data[i].zz_Cono15;
            tempcourse.label = cb.data[i].zz_Coname15;
            tempcourse.ono = cb.data[i].zz_Ono15;
            that.optionscourse.push(tempcourse);
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
    getcoursebyterm(data) {
      console.log(data);
      this.optionscourse = [];
      var urlyear =
        "http://localhost:8088/OpenCoursesStuView/getInfoByYearOrTerm";
      if (data == null || data == "") {
        var reqData = { tno: this.sno, year: this.year };
      } else if (this.year == null || this.year == "") {
        var reqData = { tno: this.sno, term: data - 1 };
      } else {
        var reqData = { tno: this.sno, year: this.year, term: data - 1 };
      }
      const that = this;
      this.Get(urlyear, reqData, function(cb) {
        if (cb.status == "success") {
          console.log(cb.data);
          that.optionscourse = [];
          var tempcoursefirst = { value: null, label: "全部", ono: null };
          that.optionscourse.push(tempcoursefirst);
          for (let i = 0; i < cb.data.length; i++) {
            var tempcourse = { value: null, label: null, ono: null };
            tempcourse.value = cb.data[i].zz_Cono15;
            tempcourse.label = cb.data[i].zz_Coname15;
            tempcourse.ono = cb.data[i].zz_Ono15;
            that.optionscourse.push(tempcourse);
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

    clearcourse() {
      this.course = "";
      this.optionscourse = [];
      var tempyearfirst = { value: null, label: "全部" };
      this.optionscourse.push(tempyearfirst);
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
