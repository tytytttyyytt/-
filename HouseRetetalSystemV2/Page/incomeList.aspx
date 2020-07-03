<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="incomeList.aspx.cs" Inherits="HouseRetetalSystemV2.Page.incomeList" %>

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
    <title>收入明细</title>
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

        .demonstration {
            font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
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
        <el-row style="margin-bottom:5%">
        <el-breadcrumb separator-class="el-icon-arrow-right">
  <el-breadcrumb-item :to="{ path: '/Page/ManagerMain.aspx' }">首页</el-breadcrumb-item>
  <el-breadcrumb-item>收入查询</el-breadcrumb-item>
  <el-breadcrumb-item>收入明细</el-breadcrumb-item>
</el-breadcrumb>
            </el-row>
      <el-row style="font-size:18px;text-align:center;margin-bottom:2%">
          <el-col :span="12">
         <div class="block">
    <span class="demonstration">日期:</span>
    <el-date-picker
      v-model="value2"
      align="right"
      type="date"
      value-format="yyyy/MM/dd"
      placeholder="选择日期:"
      :picker-options="pickerOptions"
        @change="search()">
    </el-date-picker>
  </div>
      </el-col>
      <el-col :span="12">
          已完成订单总收入：{{allfee}}元
      </el-col>
      </el-row>
      <el-row>
          <el-table
              v-loading="loading"
    element-loading-text="拼命加载中…"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(255, 255, 255, 0.7)"
    :data="tableData"
    style="width: 100%"
    :row-class-name="tableRowClassName">
    <el-table-column
      prop="orderNo"
      label="订单号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="accout"
      label="房屋所有者账号"
      width="140">
    </el-table-column>    
    <el-table-column
      prop="user"
      label="租借者账号"
      width="140">
    </el-table-column>
    <el-table-column
      prop="username"
      label="租借者姓名"
      width="140">
    </el-table-column>
    <el-table-column
      prop="cost"
      label="租价（元/时）"
      width="100">
    </el-table-column>
    <el-table-column
      prop="stime"
      label="租出时间"
      width="180">
    </el-table-column>
    <el-table-column
      prop="etime"
      label="归还时间"
     width="180">
    </el-table-column>
    <el-table-column
      prop="atime"
      label="用时（小时）"
     width="100">
    </el-table-column>
    <el-table-column
      prop="fee"
      label="费用">
    </el-table-column>
    
  </el-table>
      </el-row>
       <el-row style="text-align: center;position:absolute;bottom:10%">
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
                return {
                    pickerOptions: {
                        disabledDate(time) {
                            return time.getTime() > Date.now();
                        },
                        shortcuts: [{
                            text: '今天',
                            onClick(picker) {
                                picker.$emit('pick', new Date());
                            }
                        }, {
                            text: '昨天',
                            onClick(picker) {
                                const date = new Date();
                                date.setTime(date.getTime() - 3600 * 1000 * 24);
                                picker.$emit('pick', date);
                            }
                        }, {
                            text: '一周前',
                            onClick(picker) {
                                const date = new Date();
                                date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                                picker.$emit('pick', date);
                            }
                        }]
                    },
                    value2: '',
                    tableData: [],
                    loading: false,
                    allfee: 0,

                }
            },
            methods: {
                tableRowClassName({ row, rowIndex }) {
                    if (rowIndex % 4 === 0) {
                        return 'warning-row';
                    } else if (rowIndex % 2 === 0) {
                        return 'success-row';
                    }
                    return '';
                },
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
                search() {
                    this.loading = true;
                    console.log(this.value2);
                    var url = "/API/Income/GetIncome?time=" + this.value2;
                    this.$http.get(url).then(function (res) {
                        var result = res.body;
                        console.log(result);
                        this.$message({
                            message: result.Message,
                            type: result.Status == 2 ? 'success' : 'info' 
                        });
                        this.allfee = 0;
                        this.tableData = [];
                        setTimeout(() => {
                            this.loading = false;
                        }, 1500);
                        for (let i = 0; i < result.Data.length; i++) {
                            this.allfee += parseInt(result.Data[i]["应收金额"]);
                        }
                        var temptableData = [];
                        for (let i = 0; i < result.Data.length; i++) {
                            let temp = {
                                orderNo: result.Data[i]["订单号"],
                                accout: result.Data[i]["账号"],
                                user: result.Data[i]["租借人账号"],
                                username: result.Data[i]["租借人姓名"],
                                cost: result.Data[i]["租价"],
                                stime: result.Data[i]["租出时间"],
                                etime: result.Data[i]["归还时间"],
                                atime: result.Data[i]["共借小时数"],
                                fee: result.Data[i]["应收金额"],
                            };
                            temptableData.push(temp);
                        }
                        this.tableData = temptableData;

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
