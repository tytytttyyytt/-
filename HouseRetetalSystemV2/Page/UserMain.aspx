<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="UserMain.aspx.cs" Inherits="HouseRetetalSystemV2.WebForm1" %>

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
    <title>首页</title>
    <style>
        .el-row {
            font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
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
            <el-col :span="8" style="text-align:right;font-size:14px;">
                <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px;color:white;"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item><a href="/Login/Login.aspx" style="text-decoration:none;color: #333;">退出系统</a></el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span style="vertical-align: middle;">欢迎您，</span>
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
          <el-menu-item index="3-1"  @click="gotoReturn()">退房</el-menu-item>
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
    <el-main style="padding-bottom:0">
        <el-row>
        <el-breadcrumb separator-class="el-icon-arrow-right">
  <el-breadcrumb-item :to="{ path: '/Page/UserMain.aspx' }">首页</el-breadcrumb-item>
</el-breadcrumb>
            </el-row>
      <el-row style="color:black; font-size:20px;text-align:center;font-weight:600">
          <br /><br />欢迎使用网上租房系统
      </el-row>
      <el-row style="text-align: center;">
          <br /><br />
          <img src="/img/Usermain.png" alt="欢迎进入网上系统" width="250"/>
      </el-row >
       <el-row style="text-align: center;position:absolute;bottom:10%">
           <br /><br />
          <img src="/img/UsermainBg.png" alt="欢迎进入网上系统" width="1200"/>
      </el-row>
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
        var username = JSON.parse(userJson);      //将JSON数据反序列化为对象
        console.log(username);
        document.querySelector("#username").textContent = username.account;
        new Vue({
            el: '#app',
            data() {
                const item = {
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                };
                return {
                    tableData: Array(20).fill(item),
                    dialogVisible: false
                }
            },
            methods: {
                gotoInfo() {
                    window.location.href = "/Page/PersonalInfo.aspx";
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
                            type: 'error'
                        })
                    });
                },
                gotoRecord() {
                    console.log('正在查询');
                    var url = "/API/Info/GetRecord?account=" + username.account;
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
                gotoReturn() {
                    console.log('正在查询');
                    var url = "/API/Info/GetRetentingRecord?account=" + username.account;
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
            }
        })
    }

</script>

</html>
