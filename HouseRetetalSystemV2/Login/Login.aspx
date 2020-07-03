<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="HouseRetetalSystemV2.Login.Login" %>

<!DOCTYPE html>
 
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="/Scripts/vue.js"></script>
    <script src="/Scripts/vue-router.js"></script>
    <script src="/Scripts/vue-resource.js"></script>
    <script src="/Scripts/ElementUI/element-ui.js"></script>
    <link href="/Content/ElementUI/element-ui.css" rel="stylesheet" />
    <title>租房—登录</title>
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
            margin-right: 10%;
            background-color: rgba(255, 255, 255,0.7);
            border-radius: 10px;
        }

        .title {
            font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
            font-size: 18px;
        }

        .all {
            position: fixed;
            top: 0;
            left: 0;
            height: 100%;
            width: 100%;
            background-image: url(/img/balcony.jpg);
            background-size: 100% 100%;
            z-index: -1;
        }

        .demo-ruleForm {
            margin-right: 8%;
        }
    </style>
</head>
<body>

    <div class="login-box" id="app">
        <el-row :gutter="16">
            <el-col :span="18">
                <span class="title">网上租房系统</span>
            </el-col>
        </el-row>
        <el-row :gutter="16">
            <el-col :span="18">
                <el-input id="name" v-model="name" placeholder="请输入帐号">
                    <template slot="prepend">
                        帐号
                    </template>
                </el-input>
            </el-col>
        </el-row>
        <el-row :gutter="16">
            <el-col :span="18">
                <el-input id="password" v-model="password" type="password" placeholder="请输入密码">
                    <template slot="prepend">
                        密码
                    </template>
                </el-input>
            </el-col>
        </el-row>
        <el-row :gutter="16">
            <el-col :span="18" :offset="3">
                <el-radio v-model="radio" label="1">用户</el-radio>
                <el-radio v-model="radio" label="2">管理员</el-radio>
            </el-col>
        </el-row>
        <el-row :gutter="16">
            <el-col :span="12">
                <el-button id="login" v-on:click="check" style="width:100%" type="primary" v-loading.fullscreen.lock="logining" element-loading-background="rgba(255, 255, 255, 0.7)">登录</el-button>
            </el-col>
            <el-col :span="6">
                <el-button id="register" @click="dialogFormVisible = true" style="width:100%">注册</el-button>
            </el-col>
        </el-row>
        <el-dialog title="注册" :visible.sync="dialogFormVisible">
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model.number="ruleForm.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="pass">
                    <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名" prop="Rname">
                    <el-input v-model.number="ruleForm.Rname"></el-input>
                </el-form-item>
                <el-form-item label="联系电话" prop="phone">
                    <el-input v-model.number="ruleForm.phone"></el-input>
                </el-form-item>
                <el-form-item prop="go">
                    <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
     <div class="all"></div>

</body>
 
<script type="text/javascript">
    window.onload = function () {
        new Vue({
            el: '#app',
            data() {
                var tempStatus = 0;
                var checkPhone = (rule, value, callback) => {
                    if (!value) {
                        tempStatus--;
                        if (tempStatus < 0) tempStatus = 0;
                        return callback(new Error('电话不能为空'));
                    }
                    setTimeout(() => {
                        if (!Number.isInteger(value)) {
                            tempStatus--;
                            if (tempStatus < 0) tempStatus = 0;
                            callback(new Error('请输入数字值'));
                        } else {
                            var regex = /^1[3456789]\d{9}$/;
                            if (!regex.test(value)) {
                                callback(new Error('联系方式输入错误'));
                            }
                            else {
                                callback();
                            }
                        }
                    }, 1000);
                };
                var checkRName = (rule, value, callback) => {
                    if (!value) {
                        tempStatus--;
                        if (tempStatus < 0) tempStatus = 0;
                        return callback(new Error('姓名不能为空'));
                    }
                    tempStatus++;
                    callback();
                };
                var checkUsername = (rule, value, callback) => {
                    if (!value) {
                        tempStatus--;
                        if (tempStatus < 0) tempStatus = 0;
                        return callback(new Error('用户名不能为空'));
                    } else {
                        var url = "/API/Member/CheckUser?id=" + value;
                        this.$http.get(url).then(function (res) {
                            var result = res.body;
                            console.log(result);
                            if (result.Status == 4) {
                                callback(new Error(result.Message));
                            } else if (result.Status == 2) {
                                callback();
                            }
                        }, function (e) {
                            console.log(e);
                            return callback(new Error('网络异常，请稍候再试！'));
                        });
                    }
                    tempStatus++;
                };
                var validatePass = (rule, value, callback) => {
                    if (value === '') {
                        tempStatus--;
                        if (tempStatus < 0) tempStatus = 0;
                        callback(new Error('请输入密码'));
                    } else {
                        if (this.ruleForm.checkPass !== '') {
                            tempStatus--;
                            if (tempStatus < 0) tempStatus = 0;
                            this.$refs.ruleForm.validateField('checkPass');
                        }
                        tempStatus++;
                        callback();
                    }
                };
                var validatePass2 = (rule, value, callback) => {
                    if (value === '') {
                        callback(new Error('请再次输入密码'));
                    } else if (value !== this.ruleForm.pass) {
                        callback(new Error('两次输入密码不一致!'));
                    } else {
                        tempStatus++;
                        callback();
                    }
                };
                return {
                    ruleForm: {
                        Rname: '',
                        pass: '',
                        checkPass: '',
                        phone: '',
                        username: '',
                    },
                    rules: {
                        Rname: [
                            { required: true, validator: checkRName, trigger: 'blur' }
                        ],
                        pass: [
                            { required: true, validator: validatePass, trigger: 'blur' }
                        ],
                        checkPass: [
                            { required: true, validator: validatePass2, trigger: 'blur' }
                        ],
                        phone: [
                            { required: true, validator: checkPhone, trigger: 'blur' }
                        ],
                        username: [
                            { required: true, validator: checkUsername, trigger: 'blur' }
                        ]
                    },
                    name: '',
                    password: '',
                    logining: false,
                    radio: '1',
                    dialogFormVisible: false,
                    formLabelWidth: '120px',
                    btnStatus: tempStatus,
                };
            },
            methods: {
                check: function (event) {
                    //获取值
                    var name = this.name;
                    var password = this.password;
                    var role = this.radio;
                    if (name == '' || password == '') {
                        this.$message({
                            message: '账号或密码为空！',
                            type: 'error'
                        })
                        return;
                    }
                    var turnUrlUser = '', turnUrlMana = '';
                    // if (turnUrl == undefined || turnUrl == null || turnUrl.length <= 0) {
                    turnUrlUser = "/Page/UserMain.aspx";
                    turnUrlMana = "/Page/ManagerMain.aspx";
                    // }
                    var url = '/API/Login/Login?account=' + name + "&password=" + password + "&role=" + role;
                    this.logining = true;
                    this.$http.get(url).then(function (res) {
                        var result = res.body;
                        this.$message({
                            message: result.Message,
                            type: result.Status == 1 || result.Status == 2 ? 'success' : 'error'
                        })
                        this.logining = false;
                        if (result.Status == 1) {
                            setTimeout(function () {
                                var username = { name: result.Data.UserName, account: result.Data.UserAccount, phone: result.Data.Phone, cost: result.Data.Cost };
                                var userJson = JSON.stringify(username);      //将对象"序列化"为JSON数据(字符串格式)
                                localStorage.setItem("username", userJson);  //以字符串格式存储信息
                                window.location.href = turnUrlUser;
                            }, 1000);
                        } else if (result.Status == 2) {
                            setTimeout(function () {
                                var manager = { name: result.Data.Name, account: result.Data.UserAccount, phone: result.Data.Phone };
                                var managerJson = JSON.stringify(manager);      //将对象"序列化"为JSON数据(字符串格式)
                                localStorage.setItem("manager", managerJson);  //以字符串格式存储信息
                                window.location.href = turnUrlMana;
                            }, 1000);
                        }
                    }, function (e) {
                        console.log(e);
                        this.$message({
                            message: '登录异常',
                            type: 'error'
                        })
                        this.logining = false;
                    });
                },
                submitForm(formName) {
                    var self = this;
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            this.$message('正在注册');
                            self.register();
                        } else {
                            console.log('error submit!!');
                            return false;
                        }
                    });
                },
                resetForm(formName) {
                    this.$refs[formName].resetFields();
                },
                register() {
                    console.log('正在注册');
                    var url = '/API/Register/Register?account=' + this.ruleForm.username + "&password=" + this.ruleForm.pass + "&name=" + this.ruleForm.Rname + "&phone=" + this.ruleForm.phone;
                    var self = this;
                    this.$http.get(url).then(function (res) {
                        var result = res.body;
                        this.$message({
                            message: result.Message,
                            type: result.Status == 1 ? 'success' : 'error'
                        })
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