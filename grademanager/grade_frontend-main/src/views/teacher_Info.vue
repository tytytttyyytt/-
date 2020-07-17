<template>
  <v-card class="myCard">
    <v-data-iterator :items="items" :items-per-page.sync="itemsPerPage" hide-default-footer>
      <template v-slot:header>
        <v-toolbar class="mb-2" color="deep-orange darken-2" dark flat>
          <v-toolbar-title>教师基本信息</v-toolbar-title>
        </v-toolbar>
      </template>
      <template>
        <v-row>

          <v-col cols="24" sm="12" md="8" lg="12">
            <v-card>
              <v-card-title class="subheading font-weight-bold">个人信息</v-card-title>

              <v-divider></v-divider>

              <v-list dense>
                <v-list-item>
                  <v-list-item-content>工号:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[0].tno}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>姓名:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[0].tname}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>年龄:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[0].tage}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>性别:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[0].tsex == 1 ? '男' : '女'}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>联系方式:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[0].phone}}</v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>职称:</v-list-item-content>
                  <v-list-item-content class="align-end">{{items[0].level}}</v-list-item-content>
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
  name: "teacherInfo",
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
      }
    ],
    userInfo: null
  }),

  computed: {},

  methods: {},
  created() {
    let userTemp = JSON.parse(this.global.getRedisInformation());
    this.userInfo = userTemp;
    this.items[0].tno = this.userInfo.zz_Tno15;
    this.items[0].tname = this.userInfo.zz_Name15;
    this.items[0].tage = this.userInfo.zz_Age15;
    this.items[0].tsex = this.userInfo.zz_Sex15;
    this.items[0].phone = this.userInfo.zz_Phone15;
    if (this.userInfo.zz_Level15 == 0) {
      this.items[0].level = "助教";
    } else if (this.userInfo.zz_Level15 == 1) {
      this.items[0].level = "讲师";
    } else if (this.userInfo.zz_Level15 == 2) {
      this.items[0].level = "副教授";
    } else if (this.userInfo.zz_Level15 == 3) {
      this.items[0].level = "教授";
    } else if (cb.data[i].zz_Level15 == -1) {
      this.items[0].level = "无";
    } else if (cb.data[i].zz_Level15 == -1) {
      tempGrade.level = "无";
    }
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
