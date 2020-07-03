<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="memberList.aspx.cs" Inherits="HouseRetetalSystemV2.Page.memberList" %>

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
    <title>房间信息查看</title>
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

        .el-table .warning-row {
            background: oldlace;
        }

        .el-table .success-row {
            background: #f0f9eb;
        }

        .demo-ruleForm {
            margin-left: 10%;
            margin-right: 15%;
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
          <el-menu-item index="1-1" @click="addNewHouse()">新建房间</el-menu-item>
          <el-menu-item index="1-2" @click="checkHouse()">房间信息查看</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-menu"></i>会员管理</template>
        <el-menu-item-group>
          <template slot="title">会员操作</template>
          <el-menu-item index="2-1" @click="addNewMem()">新增会员</el-menu-item>
          <el-menu-item index="2-2" @cilck="checkHouse()">会员信息查看</el-menu-item>
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
  <el-breadcrumb-item>房间操作</el-breadcrumb-item>
  <el-breadcrumb-item>房间信息查看</el-breadcrumb-item>
</el-breadcrumb>
            </el-row>
      <el-row>
          <el-table v-loading="loading"
    element-loading-text="拼命加载中…"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(255, 255, 255, 0.7)"
    :data="tableData"
    style="width: 100%"
    :row-class-name="tableRowClassName">
    <el-table-column
      prop="accout"
      label="账号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="password"
      label="密码"
      width="160">
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      width="240">
    </el-table-column>
    <el-table-column
      prop="phone"
      label="电话"
        width="240">
    </el-table-column>
    <el-table-column
      prop="rest"
      label="余额"
      width="180">
    </el-table-column>
    <el-table-column
      label="操作">
        <template slot-scope="scope">
        <el-button
          @click.native.prevent="open(scope.$index, tableData)"
          type="primary"
          size="small">
          充值
        </el-button>
        <el-button
          @click.native.prevent="deleteRow(scope.$index, tableData)"
          size="small">
          删除
        </el-button>
            </template>
    </el-table-column>
  </el-table>
      </el-row>
        <el-dialog title="充值界面（管理员）" :visible.sync="dialogVisible" center>
            <el-dialog
      width="30%"
      title="确认信息"
      :visible.sync="dialogInternalVisible"
      append-to-body>
                确认为账户{{ruleForm.accout}}充值{{ruleForm.rest}}元吗？
    <div slot="footer" class="dialog-footer">
    <el-button @click="dialogInternalVisible = false">取 消</el-button>
    <el-button type="primary" @click="recharge()">确 定</el-button>
  </div>
    </el-dialog>
   <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="余额" prop="rest" label-width="200px">
    <el-input-number v-model="ruleForm.rest" @change="handleChange" :min="1" :max="1000000" label="描述文字"></el-input-number>
  </el-form-item>
       </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogInternalVisible = true">充 值</el-button>
    <el-button @click="dialogVisible = false">取消</el-button>
  </div>

</el-dialog>
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
        var memJson = localStorage.getItem("allMemForMana");
        var memInfo = JSON.parse(memJson);
        console.log(memInfo);

        new Vue({
            el: '#app',
            data() {
                var temp = [];
                var tempShow = [];
                var pwd = '';
                for (let i = 0; i < memInfo.length; i++) {
                    pwd = '';
                    for (let j = 0; j < memInfo[i]["密码"].length; j++) {
                        pwd += "*";
                        if (i >= 7) {
                            break;
                        }
                    }
                    var tempObjectShow = {
                        accout: memInfo[i]["账号"],
                        password: pwd,
                        name: memInfo[i]["姓名"],
                        phone: memInfo[i]["电话"],
                        rest: memInfo[i]["余额"],
                    };
                    var tempObject = {
                        accout: memInfo[i]["账号"],
                        password: memInfo[i]["密码"],
                        name: memInfo[i]["姓名"],
                        phone: memInfo[i]["电话"],
                        rest: memInfo[i]["余额"],
                    };
                    temp.push(tempObject);
                    tempShow.push(tempObjectShow);
                }
                console.log(temp);
                return {
                    ruleForm: {
                        accout: '',
                        password: '',
                        name: '',
                        phone: '',
                        rest: 1,
                    },
                    tempCost: '',
                    formLabelWidth: '120px',
                    tableData: tempShow,
                    tableDataNotShow: temp,
                    managerInfo: manager,
                    dialogVisible: false,
                    dialogInternalVisible: false,
                    loading: false,
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
                gotoReturn() {
                    window.location.href = "/Page/incomeList.aspx";
                },
                deleteRow(index, tableData) {
                    this.$confirm('是否抹去该用户的所有信息?，此操作不可逆！', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.loading = true;
                        var Haccount = tableData[index]["accout"];
                        var url = '/API/Member/DeleteMember?id=' + Haccount;
                        console.log(url);
                        var self = this;
                        this.$http.get(url).then(function (res) {
                            var result = res.body;
                            if (result.Status == 2) {
                                tableData.splice(index, 1);
                                this.$message({
                                    type: 'success',
                                    message: result.Message
                                });
                            } else {
                                this.$message({
                                    type: 'error',
                                    message: result.Message
                                });
                            }
                            // 刷新用户数据
                            var urlRefresh = "/API/Member/GetMember";
                            this.$http.get(urlRefresh).then(function (res) {
                                var result = res.body;
                                console.log(result);
                                var houseJson = JSON.stringify(result.Data);
                                localStorage.setItem("allMemForMana", houseJson);  //以字符串格式存储信息
                                setTimeout(function () {
                                    window.location.reload();
                                    this.loading = false;
                                }, 1500)
                            }, function (e) {
                                console.log(e);
                                this.$message({
                                    message: '抱歉，网络故障！',
                                    type: 'error'
                                });
                                window.location.reload();
                                this.loading = false;
                            });
                        }, function (e) {
                            console.log(e);
                            self.$message({
                                message: '抱歉，查询功能正在维护',
                                type: 'error'
                            })
                        });
                    }).catch((e) => {
                        console.log(e);
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        });
                    });
                },
                resetForm(formName) {
                    this.$refs[formName].resetFields();
                },
                open(index, Data) {
                    console.log(this.tableDataNotShow);
                    this.dialogVisible = true;
                    this.tempCost = this.tableDataNotShow[index]["rest"];
                    this.ruleForm.accout = this.tableDataNotShow[index]["accout"];
                    this.ruleForm.password = this.tableDataNotShow[index]["password"];
                    this.ruleForm.name = this.tableDataNotShow[index]["name"];
                    this.ruleForm.phone = this.tableDataNotShow[index]["phone"];
                    this.ruleForm.rest = 1;
                },
                handleChange() {
                    
                },
                recharge() {
                    this.dialogVisible = false;
                    this.dialogInternalVisible = false;
                    this.loading = true;
                    var url = "/API/Member/Recharge?id=" + this.ruleForm.accout + "&money=" + this.ruleForm.rest + "&rest=" + this.tempCost;
                    var self = this;
                    console.log(url);
                    this.$http.get(url).then(function (res) {
                        var result = res.body;
                        console.log(result);
                        if (result.Status == 2) {
                            this.$message({
                                message: result.Message,
                                type: 'success'
                            })
                            var urlRefresh = "/API/Member/GetMember";
                            this.$http.get(urlRefresh).then(function (res) {
                                var result = res.body;
                                console.log(result);
                                var recordJson = JSON.stringify(result.Data);
                                localStorage.setItem("allMemForMana", recordJson);  //以字符串格式存储信息
                                this.dialogVisible = false;
                                setTimeout(function () {
                                    window.location.reload();
                                    this.loading = false;
                                }, 1500)
                            }, function (e) {
                                console.log(e);
                                this.$message({
                                    message: '抱歉，网络故障！',
                                    type: 'error'
                                })
                            });
                        }
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

