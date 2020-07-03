<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="addRoom.aspx.cs" Inherits="HouseRetetalSystemV2.Page.addRoom" %>

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
    <title>新建房间</title>
    <style>
        .el-row {
            font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
        }

        .el-header {
            font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
            background-color: #E6A23C;
            color: #fff;
            line-height: 60px;
        }

        .el-aside {
            color: #333;
        }

        .el-header, .el-footer {
            background-color: #E6A23C;
            color: #fff;
            text-align: center;
            line-height: 60px;
        }

        .el-footer {
            background-color: #FF9900;
            font-size: 12px;
        }

        .text {
            font-size: 14px;
            font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
        }

        .item {
            font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
            margin-bottom: 18px;
        }

        .demo-ruleForm {
            margin-right: 10%;
        }
    </style>
</head>
<body>
    <div id="app">
        <el-container style="border: 1px solid #eee">

    <el-header>
        <el-row>
            <el-col :span="16" style="text-align:left; font-size:18px;">网上租房系统（管理员）</el-col>
            <el-col :span="8" style="text-align:right;font-size:14px;">
                <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px;color:white;"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item><a href="/Login/Login.aspx" style="text-decoration:none;color: #333;">退出系统</a></el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span style="              vertical-align: middle;
      ">欢迎您，管理员</span>
      <span id="username" style="vertical-align: middle;">111</span>&nbsp;&nbsp
      <el-avatar src="/img/Manager.png" size="medium" style="vertical-align: middle;"></el-avatar>
        </el-col>

        </el-row>
    </el-header>
  <el-container>
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu unique-opened>
      <el-submenu index="1">
        <template slot="title"><i class="el-icon-user"></i>房间管理</template>
        <el-menu-item-group>
          <template slot="title">房间操作</template>
          <el-menu-item index="1-1">新建房间</el-menu-item>
          <el-menu-item index="1-2" @click="checkHouse()">房间信息查看</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-menu"></i>会员管理</template>
        <el-menu-item-group>
          <template slot="title">会员操作</template>
          <el-menu-item index="2-1" @click="addNewMem()">新增会员</el-menu-item>
          <el-menu-item index="2-2" @click="checkMem()">会员信息查看</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title"><i class="el-icon-house"></i>收入查询</template>
        <el-menu-item-group>
          <template slot="title">查看记录</template>
          <el-menu-item index="3-1"  @click="gotoReturn()">收入明细</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </el-aside>

    <el-container style="height:89vh">
<el-main>
        <el-row style="margin-bottom:1%">
        <el-breadcrumb separator-class="el-icon-arrow-right">
  <el-breadcrumb-item :to="{ path: '/' }" @click="gotoHome()">首页</el-breadcrumb-item>
  <el-breadcrumb-item>房间操作</el-breadcrumb-item>
  <el-breadcrumb-item>新建房间</el-breadcrumb-item>
            
</el-breadcrumb>
            </el-row>
        <el-row>
      <el-card class="box-card" shadow="hover" v-loading="loading"
    element-loading-text="拼命上传中…"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(255, 255, 255, 0.7)">
  <div slot="header" class="clearfix">
    <span style="vertical-align: middle;">新增房间</span>
  </div>
  <div class="text item">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="房主账号" prop="account" label-width="200px">
    <el-input v-model="ruleForm.account"></el-input>
  </el-form-item>
  <el-form-item label="密码" prop="password" label-width="200px">
    <el-input type="password" v-model="ruleForm.password"></el-input>
  </el-form-item>
  <el-form-item label="确认密码" prop="repassword" label-width="200px">
    <el-input type="password" v-model="ruleForm.repassword"></el-input>
  </el-form-item>  
  <el-form-item label="房主姓名" prop="name" label-width="200px">
    <el-input v-model="ruleForm.name"></el-input>
  </el-form-item>
  <el-form-item label="房屋所在地" prop="region" label-width="200px">
    <el-select v-model="ruleForm.region" placeholder="请选择地区">
      <el-option label="杭州上城区" value="杭州上城区"></el-option>
      <el-option label="杭州下城区" value="杭州下城区"></el-option>
      <el-option label="杭州西湖区" value="杭州西湖区"></el-option>
      <el-option label="杭州余杭区" value="杭州余杭区"></el-option>
      <el-option label="杭州千岛湖" value="杭州千岛湖"></el-option>
      <el-option label="杭州萧山区" value="杭州萧山区"></el-option>
      <el-option label="温州鹿城区" value="温州鹿城区"></el-option>
      <el-option label="温州瓯海区" value="温州瓯海区"></el-option>
      <el-option label="温州瑞安市" value="温州瑞安市"></el-option>
      <el-option label="Washiton US" value="Washiton US"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="房屋星级" prop="star" label-width="200px">
    <el-select v-model="ruleForm.star" placeholder="请选择星级">
      <el-option label="1星" value="1星"></el-option>
      <el-option label="2星" value="2星"></el-option>
      <el-option label="3星" value="3星"></el-option>
      <el-option label="4星" value="4星"></el-option>
      <el-option label="5星" value="5星"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="房屋租价（元/时）" prop="cost" label-width="200px">
    <el-input v-model="ruleForm.cost"></el-input>
  </el-form-item>
  <el-form-item label="房屋状态" prop="status" label-width="200px">
    <el-select v-model="ruleForm.status" placeholder="请选择状态">
      <el-option label="空闲" value="空闲"></el-option>
      <el-option label="已出租" value="已出租"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
</el-form>
  </div>
</el-card>
            


            </el-row>
         
    </el-main>
    <el-footer>
           2020 © Copyright By
           <a href="https://www.zhangzhengtytytttyyytt.top" style="text-decoration:none;color:#FBCF8E">John Zhang</a>
    </el-footer>
        </el-container>
  </el-container>

</el-container>

    </div>
</body>
<script type="text/javascript">
    window.onload = function () {
        var managerJson = localStorage.getItem("manager");    //获取存储的信息，也是字符串格式
        var manager = JSON.parse(managerJson);      //将JSON数据反序列化为对象
        console.log(manager);
        document.querySelector("#username").textContent = manager.account;
        new Vue({
            el: '#app',
            data() {
                var checkRName = (rule, value, callback) => {
                    if (!value) {
                        return callback(new Error('姓名不能为空'));
                    }
                    callback();
                };
                var checkAccount = (rule, value, callback) => {
                    if (!value) {
                        return callback(new Error('账号不能为空'));
                    } else {
                        var url = "/API/Room/CheckUser?id=" + value;
                        this.$http.get(url).then(function (res) {
                            var result = res.body;
                            console.log(result);
                            if (result.Status == 4) {
                                return callback(new Error(result.Message));
                            } else if (result.Status == 2) {
                                callback();
                            }
                        }, function (e) {
                            console.log(e);
                            return callback(new Error('网络异常，请稍候再试！'));
                        });
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
                    setTimeout(() => {
                        if (value === '') {
                            callback(new Error('请再次输入密码'));
                        } else if (value !== this.ruleForm.password) {
                            callback(new Error('两次输入密码不一致!'));
                        } else {
                            callback();
                        }
                    }, 1000);

                };
                return {
                    loading: false,
                    ruleForm: {
                        account: '',
                        password: '',
                        repassword: '',
                        name: '',
                        region: '',
                        star: '',
                        cost: '',
                        status: ''
                    },
                    rules: {
                        account: [
                            { required: true, trigger: 'blur', validator: checkAccount },
                        ],
                        password: [
                            { required: true, validator: validatePass, trigger: 'blur' }
                        ],
                        repassword: [
                            { required: true, validator: validatePass2, trigger: 'blur' }
                        ],
                        name: [
                            { required: true, message: '请输入姓名', trigger: 'blur', validator: checkRName },
                        ],
                        region: [
                            { required: true, message: '请选择地区', trigger: 'change' }
                        ],
                        star: [
                            { required: true, message: '请选择星级', trigger: 'change' }
                        ],
                        status: [
                            { required: true, message: '请选择状态', trigger: 'change' }
                        ],
                        cost: [
                            { required: true, message: '请输入租价', trigger: 'blur' }
                        ],
                    }
                };
            },
            methods: {
                submitForm(formName) {
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            console.log(this.ruleForm);
                            this.loading = true;
                            console.log('正在上传');
                            var url = "/API/Room/AddNewHouse?id=" + this.ruleForm.account + "&password=" + this.ruleForm.password + "&ownerName=" + this.ruleForm.name + "&address=" + this.ruleForm.region + "&level=" + this.ruleForm.star + "&price=" + this.ruleForm.cost + "&status=" + this.ruleForm.status;
                            var self = this;
                            this.$http.get(url).then(function (res) {
                                var result = res.body;
                                if (result.Status == 4) {
                                    this.$message({
                                        message: result.Message,
                                        type: 'warning'
                                    })
                                } else if (result.Status == 2) {
                                    this.$message({
                                        message: result.Message,
                                        type: 'success'
                                    })
                                }
                                this.loading = false;
                                this.resetForm('ruleForm');
                            }, function (e) {
                                console.log(e);
                                this.$message({
                                    message: '抱歉，网络故障！',
                                    type: 'error'
                                })
                                this.loading = false;
                            });
                        } else {
                            this.loading = false;
                            console.log('error submit!!');
                            return false;
                        }
                    });
                },
                resetForm(formName) {
                    this.$refs[formName].resetFields();
                },
                addNewMem() {
                    window.location.href = "/Page/addMember.aspx";
                },
                checkHouse() {
                    var url = "/API/Room/GetHouse";
                    this.$http.get(url).then(function (res) {
                        var result = res.body;
                        console.log(result);
                        var recordJson = JSON.stringify(result.Data);
                        localStorage.setItem("allRecordForMana", recordJson);  //以字符串格式存储信息
                        window.location.href = "/Page/HouseList.aspx";
                    }, function (e) {
                        console.log(e);
                        this.$message({
                            message: '抱歉，网络故障！',
                            type: 'error'
                        })
                    });
                },
                checkMem() {
                    var url = "/API/Member/GetMember";
                    this.$http.get(url).then(function (res) {
                        var result = res.body;
                        console.log(result);
                        var recordJson = JSON.stringify(result.Data);
                        localStorage.setItem("allMemForMana", recordJson);  //以字符串格式存储信息
                        window.location.href = "/Page/memberList.aspx";
                    }, function (e) {
                        console.log(e);
                        this.$message({
                            message: '抱歉，网络故障！',
                            type: 'error'
                        })
                    });
                },
                gotoReturn() {
                    window.location.href = "/Page/incomeList.aspx";
                },
            }
        })
    }

</script>

</html>
