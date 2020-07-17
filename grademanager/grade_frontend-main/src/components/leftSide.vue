<template>
  <v-card class="mx-auto" width="300">
    <v-list v-if="$route.meta.role == 'student'">
      <v-list-item>
        <v-list-item-icon>
          <v-icon>mdi-home</v-icon>
        </v-list-item-icon>
        <v-list-item-title>教务管理系统</v-list-item-title>
      </v-list-item>

      <v-list-group sub-group no-action disabled>
        <template v-slot:activator>
          <v-list-item-content>
            <router-link tag="div" v-bind:to="main">
              <v-list-item-title>主页</v-list-item-title>
            </router-link>
          </v-list-item-content>
        </template>
      </v-list-group>

      <v-list-group sub-group no-action>
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title>学生基本信息</v-list-item-title>
          </v-list-item-content>
        </template>
        <v-list-item link>
          <router-link tag="div" v-bind:to="setting_design[0][1]">
            <v-list-item-title v-text="setting_design[0][0]"></v-list-item-title>
          </router-link>
        </v-list-item>
        <v-list-item link>
          <v-list-item-title v-text="setting_design[1][0]" @click="dialogon()"></v-list-item-title>
          <v-dialog v-model="dialog" persistent max-width="600px">
            <v-card>
              <v-overlay :value="overlay">
                <v-progress-circular indeterminate color="primary"></v-progress-circular>
              </v-overlay>
              <v-card-title>
                <span class="headline">修改密码</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <form>
                    <v-text-field type="password" v-model="oripwd" :error-messages="oripwdErrors" label="原始密码" required @input="$v.oripwd.$touch()" @blur="$v.oripwd.$touch()"></v-text-field>
                    <v-text-field type="password" v-model="newpwd" :error-messages="newpwdErrors" label="新密码" required @input="$v.newpwd.$touch()" @blur="$v.newpwd.$touch()"></v-text-field>
                    <v-text-field type="password" v-model="newpwdq" :error-messages="newpwdqErrors" label="确认新密码" required @input="$v.newpwdq.$touch()" @blur="$v.newpwdq.$touch()"></v-text-field>
                    <v-btn class="mr-4" @click="submit" color="primary" small>修改</v-btn>
                    <v-btn class="mr-4" @click="clear" color="secondary" small>清空</v-btn>
                    <v-btn @click="dialog = false" small>返回</v-btn>
                  </form>
                </v-container>
              </v-card-text>
            </v-card>
          </v-dialog>
        </v-list-item>
      </v-list-group>

      <v-list-group sub-group no-action>
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title>信息查询</v-list-item-title>
          </v-list-item-content>
        </template>
        <v-list-item v-for="(info, i) in info" :key="i" link>
          <router-link tag="div" v-bind:to="info[1]">
            <v-list-item-title v-text="info[0]"></v-list-item-title>
          </router-link>
        </v-list-item>
      </v-list-group>
    </v-list>

    <v-list v-else-if="$route.meta.role == 'teacher'">
      <v-list-item>
        <v-list-item-icon>
          <v-icon>mdi-home</v-icon>
        </v-list-item-icon>
        <v-list-item-title>教务管理系统</v-list-item-title>
      </v-list-item>

      <v-list-group sub-group no-action disabled>
        <template v-slot:activator>
          <v-list-item-content>
            <router-link tag="div" v-bind:to="mainteacher">
              <v-list-item-title>主页</v-list-item-title>
            </router-link>
          </v-list-item-content>
        </template>
      </v-list-group>

      <v-list-group sub-group no-action>
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title>教师基本信息</v-list-item-title>
          </v-list-item-content>
        </template>
        <v-list-item link>
          <router-link tag="div" v-bind:to="setting_designteacher[0][1]">
            <v-list-item-title v-text="setting_designteacher[0][0]"></v-list-item-title>
          </router-link>
        </v-list-item>
        <v-list-item link>
          <v-list-item-title v-text="setting_designteacher[1][0]" @click="dialogon()"></v-list-item-title>
          <v-dialog v-model="dialog" persistent max-width="600px">
            <v-card>
              <v-overlay :value="overlay">
                <v-progress-circular indeterminate color="primary"></v-progress-circular>
              </v-overlay>
              <v-card-title>
                <span class="headline">修改密码</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <form>
                    <v-text-field type="password" v-model="oripwd" :error-messages="oripwdErrors" label="原始密码" required @input="$v.oripwd.$touch()" @blur="$v.oripwd.$touch()"></v-text-field>
                    <v-text-field type="password" v-model="newpwd" :error-messages="newpwdErrors" label="新密码" required @input="$v.newpwd.$touch()" @blur="$v.newpwd.$touch()"></v-text-field>
                    <v-text-field type="password" v-model="newpwdq" :error-messages="newpwdqErrors" label="确认新密码" required @input="$v.newpwdq.$touch()" @blur="$v.newpwdq.$touch()"></v-text-field>
                    <v-btn class="mr-4" @click="submittea" color="primary" small>修改</v-btn>
                    <v-btn class="mr-4" @click="clear" color="secondary" small>清空</v-btn>
                    <v-btn @click="dialog = false" small>返回</v-btn>
                  </form>
                </v-container>
              </v-card-text>
            </v-card>
          </v-dialog>
        </v-list-item>
      </v-list-group>

      <v-list-group sub-group no-action>
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title>信息维护</v-list-item-title>
          </v-list-item-content>
        </template>
        <v-list-item v-for="(info, i) in infoteacher" :key="i" link>
          <router-link tag="div" v-bind:to="info[1]">
            <v-list-item-title v-text="info[0]"></v-list-item-title>
          </router-link>
        </v-list-item>
      </v-list-group>

      <v-list-group sub-group no-action>
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title>信息查询</v-list-item-title>
          </v-list-item-content>
        </template>
        <v-list-item v-for="(info, i) in infofortea" :key="i" link>
          <router-link tag="div" v-bind:to="info[1]">
            <v-list-item-title v-text="info[0]"></v-list-item-title>
          </router-link>
        </v-list-item>
      </v-list-group>

    </v-list>
    <v-list v-else>
      <v-list-item>
        <v-list-item-icon>
          <v-icon>mdi-home</v-icon>
        </v-list-item-icon>
        <v-list-item-title>教务管理系统</v-list-item-title>
      </v-list-item>

      <v-list-group sub-group no-action disabled>
        <template v-slot:activator>
          <v-list-item-content>
            <router-link tag="div" v-bind:to="mainadmin">
              <v-list-item-title>主页</v-list-item-title>
            </router-link>
          </v-list-item-content>
        </template>
      </v-list-group>

      <v-list-group sub-group no-action>
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title>管理员基本信息</v-list-item-title>
          </v-list-item-content>
        </template>
        <v-list-item link>
          <v-list-item-title v-text="setting_designadmin[0][0]" @click="dialogon()"></v-list-item-title>
          <v-dialog v-model="dialog" persistent max-width="600px">
            <v-card>
              <v-overlay :value="overlay">
                <v-progress-circular indeterminate color="primary"></v-progress-circular>
              </v-overlay>
              <v-card-title>
                <span class="headline">修改密码</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <form>
                    <v-text-field type="password" v-model="oripwd" :error-messages="oripwdErrors" label="原始密码" required @input="$v.oripwd.$touch()" @blur="$v.oripwd.$touch()"></v-text-field>
                    <v-text-field type="password" v-model="newpwd" :error-messages="newpwdErrors" label="新密码" required @input="$v.newpwd.$touch()" @blur="$v.newpwd.$touch()"></v-text-field>
                    <v-text-field type="password" v-model="newpwdq" :error-messages="newpwdqErrors" label="确认新密码" required @input="$v.newpwdq.$touch()" @blur="$v.newpwdq.$touch()"></v-text-field>
                    <v-btn class="mr-4" @click="submitadmin" color="primary" small>修改</v-btn>
                    <v-btn class="mr-4" @click="clear" color="secondary" small>清空</v-btn>
                    <v-btn @click="dialog = false" small>返回</v-btn>
                  </form>
                </v-container>
              </v-card-text>
            </v-card>
          </v-dialog>
        </v-list-item>
      </v-list-group>

      <v-list-group sub-group no-action>
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title>信息维护</v-list-item-title>
          </v-list-item-content>
        </template>
        <v-list-item v-for="(info, i) in infoadmin" :key="i" link>
          <router-link tag="div" v-bind:to="info[1]">
            <v-list-item-title v-text="info[0]"></v-list-item-title>
          </router-link>
        </v-list-item>
      </v-list-group>

      <v-list-group sub-group no-action>
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title>信息查询</v-list-item-title>
          </v-list-item-content>
        </template>
        <v-list-item v-for="(info, i) in infoforadmin" :key="i" link>
          <router-link tag="div" v-bind:to="info[1]">
            <v-list-item-title v-text="info[0]"></v-list-item-title>
          </router-link>
        </v-list-item>
      </v-list-group>

    </v-list>
  </v-card>
</template>

<script>
import { validationMixin } from "vuelidate";
import { required, sameAs } from "vuelidate/lib/validators";
export default {
  mixins: [validationMixin],

  data: () => ({
    main: "/main",
    mainteacher: "/teachermain",
    mainadmin: "/adminMain",
    setting_design: [
      ["学生基本信息查询", "/studentInfo"],
      ["密码修改", "/changePassword"]
    ],
    setting_designteacher: [
      ["教师基本信息查询", "/teacherInfo"],
      ["密码修改", "/changePassword"]
    ],
    setting_designadmin: [["密码修改", "/changePassword"]],
    info: [
      ["学生成绩查询", "/stuCheckGrade"],
      ["教学安排查询", "/openCourse"],
      ["学生学业情况", "/studentGradeInfo"]
    ],
    infoteacher: [["学生成绩录入", "/stuGradeIn"]],
    infofortea: [
      ["教学安排查询", "/openCourseTeacher"],
      ["班级成绩分析", "/studentGradeAnalysis"]
    ],
    infoadmin: [
      ["学生信息维护", "/studentList"],
      ["教师信息维护", "/teacherList"],
      ["课程信息维护", "/courseList"],
      ["开课信息维护", "/openCourseList"]
    ],
    infoforadmin: [
      ["教学安排查询", "/openCourseAdmin"],
      ["学生来源地分析", "/studentLocationAnalysis"]
    ],
    dialog: false,
    valid: true,
    userpwd: "",
    oripwd: "",
    newpwd: "",
    newpwdq: "",
    overlay: false
  }),
  validations: {
    oripwd: { required, sameAsYuan: sameAs("userpwd") },
    newpwd: { required, sameAsOri: sameAs("oripwd") },
    newpwdq: { required, sameAsNew: sameAs("newpwd") }
  },
  methods: {
    dialogon() {
      this.dialog = true;
    },
    submit() {
      this.$v.$touch();
      if (
        this.$v.oripwd.$invalid ||
        !this.$v.newpwd.$invalid ||
        this.$v.newpwdq.$invalid
      ) {
        return;
      }
      this.overlay = true;
      // 提交后台修改逻辑
      var url = "http://localhost:8088/student/updatepassword";
      let userTemp = JSON.parse(this.global.getRedisInformation());
      let userInfo = userTemp[0];
      var sno = userInfo.zz_Sno15;
      var reqData = { no: sno, password: this.newpwd };
      const that = this;
      this.Post(url, reqData, function(cb) {
        if (cb.status == "success") {
          that.$message({
            message: "修改密码成功",
            type: "success"
          });
          that.userpwd = that.global.setPwd(that.newpwd);
          that.clear();
          that.dialog = false;
        } else {
          that.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
        that.overlay = false;
      });
    },
    submittea() {
      this.$v.$touch();
      if (
        this.$v.oripwd.$invalid ||
        !this.$v.newpwd.$invalid ||
        this.$v.newpwdq.$invalid
      ) {
        return;
      }
      this.overlay = true;
      // 提交后台修改逻辑
      var url = "http://localhost:8088/teacher/updatepassword";
      let userTemp = JSON.parse(this.global.getRedisInformation());
      let userInfo = userTemp;
      var tno = userInfo.zz_Tno15;
      var reqData = { no: tno, password: this.newpwd };
      const that = this;
      this.Post(url, reqData, function(cb) {
        if (cb.status == "success") {
          that.$message({
            message: "修改密码成功",
            type: "success"
          });
          that.userpwd = that.global.setPwd(that.newpwd);
          that.clear();
          that.dialog = false;
        } else {
          that.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
        that.overlay = false;
      });
    },
    submitadmin() {
      this.$v.$touch();
      if (
        this.$v.oripwd.$invalid ||
        !this.$v.newpwd.$invalid ||
        this.$v.newpwdq.$invalid
      ) {
        return;
      }
      this.overlay = true;
      // 提交后台修改逻辑
      var url = "http://localhost:8088/admin/updatepassword";
      let userTemp = JSON.parse(this.global.getRedisInformation());
      let userInfo = userTemp;
      var ano = userInfo.zz_Ano15;
      var reqData = { no: ano, password: this.newpwd };
      const that = this;
      this.Post(url, reqData, function(cb) {
        if (cb.status == "success") {
          that.$message({
            message: "修改密码成功",
            type: "success"
          });
          that.userpwd = that.global.setPwd(that.newpwd);
          that.clear();
          that.dialog = false;
        } else {
          that.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
        that.overlay = false;
      });
    },
    clear() {
      this.$v.$reset();
      this.oripwd = "";
      this.newpwd = "";
      this.newpwdq = "";
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
    this.userpwd = this.global.getPwd();
  },
  computed: {
    oripwdErrors() {
      const errors = [];
      if (!this.$v.oripwd.$dirty) return errors;
      !this.$v.oripwd.required && errors.push("原始密码不能为空");
      !(this.$v.oripwd.$model === this.userpwd) && errors.push("原始密码错误");
      return errors;
    },
    newpwdErrors() {
      const errors = [];
      if (!this.$v.newpwd.$dirty) return errors;
      !this.$v.newpwd.required && errors.push("新密码不能为空");
      !(this.$v.newpwd.$model !== this.$v.oripwd.$model) &&
        errors.push("新密码与原始密码一致");
      return errors;
    },
    newpwdqErrors() {
      const errors = [];
      if (!this.$v.newpwdq.$dirty) return errors;
      !this.$v.newpwdq.required && errors.push("请再次输入密码");
      !(this.$v.newpwdq.$model === this.$v.newpwd.$model) &&
        errors.push("两次输入不一致");
      return errors;
    }
  },
  watch: {
    $route(to, from) {
      const path = to.path;
      let route = "";
      const pathArr = path.toString().split("/");
      for (let i = 1; i < pathArr.length; i++) {
        if (pathArr[i] === "") {
          route = "main_plot";
          continue;
        }
        route = pathArr[i];
      }
      console.log(route);
      this.activeItem = route;
    }
  }
};
</script>

<style>
a {
  text-decoration: none;
}

.router-link-active {
  text-decoration: none;
}
</style>
