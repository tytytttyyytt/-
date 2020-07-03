<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ManagerMain.aspx.cs" Inherits="HouseRetetalSystemV2.WebForm1" %>

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
    <title>首页（管理员）</title>
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
      <span style="vertical-align: middle;">欢迎您，管理员</span>
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
          <el-menu-item index="1-1" @click="addNewHouse()">新建房间</el-menu-item>
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
    <el-main style="padding-bottom:0">
        <el-row>
        <el-breadcrumb separator-class="el-icon-arrow-right">
  <el-breadcrumb-item :to="{ path: '/Page/ManagerMain.aspx' }">首页</el-breadcrumb-item>
</el-breadcrumb>
            </el-row>
      <el-row style="color:black; font-size:20px;text-align:center;font-weight:600">
          <br /><br />欢迎使用网上租房管理系统
      </el-row>
      <el-row style="text-align: center;">
          <br /><br />
          <img src="/img/ManagerMain.png" alt="欢迎进入网上系统" width="275"/>
      </el-row >
       <el-row style="text-align: center;position:absolute;bottom:10%">
           <br /><br />
          <img src="/img/UsermainBg.png" alt="欢迎进入网上系统" width="1200"/>
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
                addNewHouse() {
                    window.location.href = "/Page/addRoom.aspx";
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
                addNewMem() {
                    window.location.href = "/Page/addMember.aspx";
                },
                gotoReturn() {
                    window.location.href = "/Page/incomeList.aspx";
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
            }
        })
    }

</script>

</html>
