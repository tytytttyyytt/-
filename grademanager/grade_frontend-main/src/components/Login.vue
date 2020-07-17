<template>
  <div>
    <div class="login-box">
      <el-row :gutter="22">
        <el-col :span="22">
          <span class="title">xx大学成绩管理系统</span>
        </el-col>
      </el-row>
      <el-row :gutter="22">
        <el-col :span="22">
          <el-input id="name" v-model="name" placeholder="请输入学号/工号">
            <template slot="prepend">
              学号/工号
            </template>
          </el-input>
        </el-col>
      </el-row>
      <el-row :gutter="22">
        <el-col :span="22">
          <el-input id="password" v-model="password" type="password" placeholder="请输入密码">
            <template slot="prepend">
              密码
            </template>
          </el-input>
        </el-col>
      </el-row>
      <el-row :gutter="22">
        <el-col :span="22">
          <el-button id="login" v-on:click="check()" style="width:100%" type="primary" v-loading.fullscreen.lock="logining" element-loading-background="rgba(255, 255, 255, 0.7)">登录</el-button>
        </el-col>
      </el-row>
    </div>
    <div class="all">
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data: () => ({
    name: "",
    password: "",
    logining: false,
    radio: "1",
    dialogFormVisible: false,
    formLabelWidth: "120px"
  }),
  methods: {
    check: function(event) {
      //获取值
      var name = this.name;
      var password = this.password;
      var role = this.radio;
      if (name == "" || password == "") {
        this.$message({
          message: "学号/工号或密码为空！",
          type: "error"
        });
        return;
      }

      const that = this;
      var url = "http://localhost:8088/login/login";
      var reqData = { username: that.name, password: that.password };
      this.logining = true;
      this.Post(url, reqData, function(data) {
        if (data.status === "success") {
          that.$message({
            message: "登录成功",
            type: "success"
          });
          that.logining = false;
          if (data.data.role === "student") {
            setTimeout(function() {
              that.global.setRole("student");
              // 存储学生信息
              that.global.setRedisInformation(JSON.stringify(data.data.data));
              that.global.setPwd(that.password);
              const { name, pwd, $router } = this;
              that.$router.push({
                name: "mainPlot",
                params: {}
              });
            }, 1000);
          } else if (data.data.role === "teacher") {
            // 教师登录端
            setTimeout(function() {
              that.global.setRole("teacher");
              // 存储教师信息
              that.global.setRedisInformation(JSON.stringify(data.data.data));
              that.global.setPwd(that.password);
              const { name, pwd, $router } = this;
              that.$router.push({
                name: "otherPlot",
                params: {}
              });
            }, 1000);
          } else if (data.data.role === "admin") {
            // 教师管理员端
            setTimeout(function() {
              that.global.setRole("teacher");
              // 存储管理员信息
              that.global.setRedisInformation(JSON.stringify(data.data.data));
              that.global.setPwd(that.password);
              const { name, pwd, $router } = this;
              that.$router.push({
                name: "adminMain",
                params: {}
              });
            }, 1000);
          }
        } else {
          that.logining = false;
          that.$message({
            message: "学号/工号或密码错误或网络异常",
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
  }
};
</script>

<style>
.el-row {
  margin-bottom: 20px;
}

.login-box {
  margin-top: 10%;
  margin-left: 60%;
  padding-left: 5%;
  padding-top: 5%;
  padding-bottom: 5%;
  padding-right: 4%;
  background-color: rgba(255, 255, 255, 0.7);
  border-radius: 10px;
  position: absolute;
  z-index: 1;
}

.title {
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  font-size: 18px;
}

.all {
  position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background-image: url("~@/assets/images/main_login.jpg");
  background-size: 100% 100%;
}

.demo-ruleForm {
  margin-right: 8%;
}

/* 隐藏右侧滚动条 */
::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;
  height: 0;
}
</style>
