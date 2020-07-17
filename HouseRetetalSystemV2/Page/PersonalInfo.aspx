<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="PersonalInfo.aspx.cs" Inherits="HouseRetetalSystemV2.Page.PersonalInfo" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="/Scripts/vue.js"></script>
    <script src="/Scripts/vue-router.js"></script>
    <script src="/Scripts/vue-resource.js"></script>
    <script src="/Scripts/ElementUI/element-ui.js"></script>
    <script src="/Scripts/kit.js"></script>
    <script src="/Scripts/global.js"></script>
    <link href="/Content/ElementUI/element-ui.css" rel="stylesheet" />
    <title>个人信息</title>
    <style>
        .el-row {
            font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
            margin-bottom: 20px;
            &:last-child

        {
            margin-bottom: 0;
        }

        }

        .el-header {
            font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
            background-color: #409EFF;
            color: #fff;
            line-height: 60px;
        }

        .el-aside {
            color: #333;
        }

        .el-header, .el-footer {
            background-color: #409EFF;
            color: #fff;
            text-align: center;
            line-height: 60px;
        }

        .text {
            font-size: 14px;
            font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
        }

        .item {
            font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
            margin-bottom: 18px;
        }

        .clearfix:before,
        .clearfix:after {
            display: table;
            content: "";
        }

        .clearfix:after {
            clear: both
        }

        .box-card {
            width: 480px;
        }

        .demo-ruleForm {
            margin-right: 8%;
        }

        .el-footer {
            background-color: #0067D2;
            font-size: 12px;
        }
    </style>
</head>
<body>
    <div id="app">
        <el-container style="border: 1px solid #eee">

    <el-header>
        <el-row>
            <el-col :span="16" style="text-align:left; font-size:18px;">网上租房系统</el-col>
            <el-col :span="8" style="text-align: right; font-size: 14px; ">
                <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px;color:white;"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item><a href="/Login/Login.aspx" style="text-decoration:none;color: #333;">退出系统</a></el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span style=" vertical-align: middle; ">欢迎您，</span>
      <span id="username" style="vertical-align: middle;">111</span>&nbsp;&nbsp
      <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" size="medium" style="vertical-align: middle;"></el-avatar>
        </el-col>

        </el-row>
    </el-header>
  <el-container>
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu unique-opened>
      <el-submenu index="1">
        <template slot="title"><i class="el-icon-user"></i>查看个人信息</template>
        <el-menu-item-group>
          <template slot="title">个人信息</template>
          <el-menu-item index="1-1" @click="gotoInfo()">查询与编辑个人信息</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="充值">
          <el-menu-item index="1-2" @click="dialogVisible = true">充值界面</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-menu"></i>租房</template>
        <el-menu-item-group>
          <template slot="title">可租房间</template>
          <el-menu-item index="2-1" @click="getHouse()">查看以及选择房间</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title"><i class="el-icon-house"></i>记录查询</template>
        <el-menu-item-group>
          <template slot="title">退房相关</template>
          <el-menu-item index="3-1" @click="gotoReturn()">退房</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="查询租房记录">
          <el-menu-item index="3-2" @click="gotoRecord()">查询</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </el-aside>
  <el-dialog
  title="支付界面"
  :visible.sync="dialogVisible"
  width="30%"
  :before-close="handleClose" style="text-align:center;">
   <img src="/img/pay.jpg" width="300"/>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
</el-dialog>

    <el-container style="height:89vh">
    <el-main>
        <el-row>
        <el-breadcrumb separator-class="el-icon-arrow-right">
  <el-breadcrumb-item :to="{ path: '/Page/UserMain.aspx' }" @click="gotoHome()">首页</el-breadcrumb-item>
  <el-breadcrumb-item>查看个人信息</el-breadcrumb-item>
  <el-breadcrumb-item>查询与编辑个人信息</el-breadcrumb-item>
            
</el-breadcrumb>
            </el-row>
        <el-row style="position: absolute;left: 55%;top: 50%;transform: translate(-50%,-50%)">
      <el-card class="box-card" shadow="hover">
  <div slot="header" class="clearfix">
    <span style="vertical-align: middle;">{{userInfo.account}}的个人信息</span>
    <el-button style="float: right;"size="mini" type="primary"  @click="dialogFormVisible = true">编辑</el-button>
     
  </div>
  <div class="text item">
      <pre>
    真实姓名：{{userInfo.name}}<br />
    用户名  ：{{userInfo.account}}<br />
    联系方式：{{userInfo.phone}}<br />
    余额    ：{{userInfo.cost}}
          </pre>
  </div>
</el-card>
            


            </el-row>
         <el-dialog title="修改个人信息" :visible.sync="dialogFormVisible">
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="真实姓名" prop="name">
    <el-input type="text" v-model="ruleForm.name" autocomplete="off"></el-input>
  </el-form-item>
  <el-form-item label="用户名" prop="username">
    <el-input type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
  </el-form-item>
      <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                </el-form-item>
  <el-form-item label="联系方式" prop="phone">
    <el-input v-model.number="ruleForm.phone"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
</el-form>
</el-dialog>
    </el-main>
    <el-footer>
           2020 © Copyright By
           <a href="https://www.zhangzhengtytytttyyytt.top" style="text-decoration:none;color:#409EFF">John Zhang</a>
    </el-footer>
        </el-container>
  </el-container>

</el-container>

    </div>
</body>
<script type="text/javascript">
    window.onload = function () {
        var userJson = localStorage.getItem("username");    //获取存储的信息，也是字符串格式
        var user = JSON.parse(userJson);      //将JSON数据反序列化为对象
        //console.log(user);
        document.querySelector("#username").textContent = user.account;
        new Vue({
            el: '#app',
            data() {
                var checkName = (rule, value, callback) => {
                    if (!value) {
                        return callback(new Error('姓名不能为空'));
                    }
                    callback();
                };
                var validateUsername = (rule, value, callback) => {
                    if (value === '') {
                        callback(new Error('用户名不能为空'));
                    } else {
                        callback();
                    }
                };
                var validatePhone = (rule, value, callback) => {
                    var regex = /^1[3456789]\d{9}$/;
                    if (!regex.test(value)) {
                        callback(new Error('联系方式输入错误'));
                    }
                    else if (value == '') {
                        callback(new Error('联系方式不能为空'));
                    }
                    else {
                        callback();
                    }
                };
                var validatePass = (rule, value, callback) => {
                    if (value === '') {
                        callback(new Error('请输入密码'));
                    } else {
                        if (this.ruleForm.checkPass !== '') {
                            this.$refs.ruleForm.validateField('checkPass');
                        }
                        callback();
                    }
                };
                var validatePass2 = (rule, value, callback) => {
                    if (value === '') {
                        callback(new Error('请再次输入密码'));
                    } else if (value !== this.ruleForm.pass) {
                        callback(new Error('两次输入密码不一致!'));
                    } else {
                        callback();
                    }
                };
                return {
                    ruleForm: {
                        name: '',
                        username: '',
                        pass: '',
                        checkPass: '',
                        phone: ''
                    },
                    rules: {
                        name: [
                            { required: true, validator: checkName, trigger: 'blur' }
                        ],
                        username: [
                            { required: true, validator: validateUsername, trigger: 'blur' }
                        ],
                        pass: [
                            { required: true, validator: validatePass, trigger: 'blur' }
                        ],
                        checkPass: [
                            { required: true, validator: validatePass2, trigger: 'blur' }
                        ],
                        phone: [
                            { required: true, validator: validatePhone, trigger: 'blur' }
                        ]
                    },
                    userInfo: user,
                    dialogVisible: false,
                    dialogFormVisible: false,
                    formLabelWidth: '120px'
                }
            },
            methods: {
                submitForm(formName) {
                    var self = this;
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            this.$message('正在修改');
                            self.edit();
                        } else {
                            console.log('error submit!!');
                            return false;
                        }
                    });
                },
                resetForm(formName) {
                    this.$refs[formName].resetFields();
                },
                gotoInfo() {
                    console.log("就是本界面！");
                    console.log(this.userInfo);
                },
                getHouse() {
                    console.log('正在查询');
                    var url = "/API/Info/GetHouse";
                    var self = this;
                    this.$http.get(url).then(function (res) {
                        var result = res.body;
                        console.log(result);
                        var houseJson = JSON.stringify(result.Data);
                        localStorage.setItem("houseForUser", houseJson);  //以字符串格式存储信息
                        window.location.href = "/Page/getHouseInfo.aspx";
                    }, function (e) {
                        console.log(e);
                        this.$message({
                            message: '抱歉，网络故障！',
                            type: 'warning'
                        })
                    });
                },
                gotoRecord() {
                    console.log('正在查询');
                    var url = "/API/Info/GetRecord?account=" + user.account;
                    var self = this;
                    this.$http.get(url).then(function (res) {
                        var result = res.body;
                        console.log(result);
                        var recordJson = JSON.stringify(result.Data);
                        localStorage.setItem("recordForUser", recordJson);  //以字符串格式存储信息
                        window.location.href = "/Page/RetentalRecord.aspx";
                    }, function (e) {
                        console.log(e);
                        this.$message({
                            message: '抱歉，网络故障！',
                            type: 'error'
                        })
                    });
                },
                gotoHome() {
                    window.location.href = "/Page/UserMain.aspx";
                },
                gotoReturn() {
                    console.log('正在查询');
                    var url = "/API/Info/GetRetentingRecord?account=" + user.account;
                    var self = this;
                    this.$http.get(url).then(function (res) {
                        var result = res.body;
                        console.log(result);
                        var returnJson = JSON.stringify(result.Data);
                        localStorage.setItem("returnForUser", returnJson);  //以字符串格式存储信息
                        window.location.href = "/Page/returnHouse.aspx";
                    }, function (e) {
                        console.log(e);
                        this.$message({
                            message: '抱歉，网络故障！',
                            type: 'error'
                        })
                    });
                },
                edit() {
                    console.log('正在修改');
                    var url = '/API/Info/UpdateInfo?account=' + this.ruleForm.username + "&password=" + this.ruleForm.pass + "&username=" + this.ruleForm.name + "&userphone=" + this.ruleForm.phone + "&oldaccount=" + user.account;
                    var self = this;
                    this.$http.get(url).then(function (res) {
                        var result = res.body;
                        this.$message({
                            message: result.Message,
                            type: result.Status == 1 ? 'success' : 'error',
                        })
                        // 刷新个人信息数据
                        var urlPerson = '/API/Info/GetInfo?account=' + self.ruleForm.username;
                        var that = self
                        this.$http.get(urlPerson).then(function (res) {
                            var result = res.body;
                            console.log(result.Status == 1);
                            if (result.Status == 1) {
                                var username = { name: result.Data.Name, account: result.Data.UserAccount, phone: result.Data.Phone, cost: result.Data.Cost };
                                var userJson = JSON.stringify(username);      //将对象"序列化"为JSON数据(字符串格式)
                                localStorage.setItem("username", userJson);  //以字符串格式存储信息
                                document.querySelector("#username").textContent = result.Data.UserAccount;
                                that.userInfo.name = result.Data.Name
                                that.userInfo.account = result.Data.UserAccount
                                that.userInfo.phone = result.Data.Phone
                                that.userInfo.cost = result.Data.Cost
                            }
                        }, function (e) {
                            console.log(e);
                            this.$message({
                                message: '网络异常！',
                                type: 'error'
                            })
                        });
                        self.dialogFormVisible = false;
                    }, function (e) {
                        console.log(e);
                        this.$message({
                            message: '抱歉，注册功能正在维护',
                            type: 'warning'
                        })
                        this.logining = false;
                    });
                }
            }
        })
    }

</script>

</html>
