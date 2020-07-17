<template>
  <v-card class="myCard">
    <v-data-iterator :items="items" :items-per-page.sync="itemsPerPage" hide-default-footer>
      <template v-slot:header>
        <v-toolbar class="mb-2" color="indigo darken-5" dark flat>
          <v-toolbar-title>学生基本信息</v-toolbar-title>
        </v-toolbar>
      </template>
      <template>
        <v-row>

          <v-col cols="12" sm="12" md="8" lg="6">
            <v-card>
              <v-card-title class="subheading font-weight-bold">个人信息</v-card-title>

              <v-divider></v-divider>

              <v-list dense>
                <v-list-item>
                  <v-list-item-content>学号:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[0].sno}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>姓名:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[0].sname}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>年龄:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[0].sage}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>性别:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[0].ssex == 1 ? '男' : '女'}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>入学年份:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[0].year}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>行政班:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[0].classInfo}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>生源地:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[0].location}}</v-list-item-content>
                </v-list-item>

              </v-list>
            </v-card>
          </v-col>

          <v-col cols="12" sm="12" md="8" lg="6">
            <v-card>
              <v-card-title class="subheading font-weight-bold">学业基本情况</v-card-title>

              <v-divider></v-divider>

              <v-list dense>
                <v-list-item>
                  <v-list-item-content>总学分:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[1].tc}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>已获学分:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[1].cc}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>学分绩点</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[1].point}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>平均绩点:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[1].gpa}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>班级排名:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[1].oc}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>专业排名:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[1].op}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>总排名:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[1].os}}</v-list-item-content>
                </v-list-item>

              </v-list>
            </v-card>
          </v-col>

        </v-row>
      </template>
    </v-data-iterator>
  </v-card>
</template>

<script>
export default {
  name: "studentInfo",
  data: () => ({
    itemsPerPage: 1,
    items: [
      {
        sno: "获取中……",
        sname: "获取中……",
        sage: "获取中……",
        ssex: "获取中……",
        year: "获取中……",
        classInfo: "获取中……",
        location: "获取中……"
      },
      {
        tc: "获取中……",
        cc: "获取中……",
        point: "获取中……",
        gpa: "获取中……",
        os: "获取中……",
        oc: "获取中……",
        op: "获取中……"
      }
    ],
    userInfo: null
  }),

  computed: {},

  methods: {},
  created() {
    let userTemp = JSON.parse(this.global.getRedisInformation());
    this.userInfo = userTemp[0];
    this.items[0].sno = this.userInfo.zz_Sno15;
    this.items[0].sname = this.userInfo.zz_Name15;
    this.items[0].sage = this.userInfo.zz_Age15;
    this.items[0].ssex = this.userInfo.zz_Sex15;
    this.items[0].year = this.userInfo.zz_CYear15 + "年";
    this.items[0].classInfo =
      this.userInfo.zz_PName15 + this.userInfo.zz_CName15;
    this.items[0].location =
      this.userInfo.zz_Prname15 + this.userInfo.zz_Ciname15;
    this.items[1].tc = this.userInfo.zz_Totalcredit15;
    this.items[1].cc = this.userInfo.zz_Completecredit15;
    this.items[1].point = this.userInfo.zz_Point15;
    this.items[1].gpa = this.userInfo.zz_GPA15;
    this.items[1].os = "第" + this.userInfo.zz_Orderofschool15 + "名";
    this.items[1].oc = "第" + this.userInfo.zz_Orderofclass15 + "名";
    this.items[1].op = "第" + this.userInfo.zz_Orderofprofession15 + "名";
  }
};
</script>

<style scoped>
.myCard {
  padding-top: 10px;
  margin-left: 20px;
  margin-right: 20px;
  margin-bottom: 20px;
  padding-right: 20px;
}
</style>
