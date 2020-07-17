<template>

  <v-app id="inspire">
    <div v-if="$route.meta.keepalive">
      <!-- 左侧边栏 -->
      <v-navigation-drawer v-model="drawer" app>
        <keep-alive>
          <v-list dense>
            <left-side></left-side>
          </v-list>
        </keep-alive>
      </v-navigation-drawer>

      <!-- 顶边栏 -->
      <v-app-bar v-if="$route.meta.role == 'student'" app color=#2979FF dark>
        <keep-alive>
          <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
        </keep-alive>
        <v-toolbar-title>xx大学教务管理系统</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn icon>
          <v-icon>mdi-magnify</v-icon>
        </v-btn>

        <v-menu left bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon v-bind="attrs" v-on="on">
              <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
          </template>

          <v-list>
            <v-list-item @click="() => {}">
              <v-list-item-title @click.stop="logout()">登出</v-list-item-title>
            </v-list-item>
            <v-dialog v-model="dialog" max-width="290">
              <v-card>
                <v-card-title class="headline">要离开教务系统吗?</v-card-title>

                <v-card-text>
                  系统可能不会保存您所做的更改。
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn color="green darken-1" text @click="dialog = false">
                    取消
                  </v-btn>

                  <v-btn color="green darken-1" text @click="logoutYes()">
                    确定
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-list>
        </v-menu>
      </v-app-bar>

      <v-app-bar v-else-if="$route.meta.role == 'teacher'" app color=#FFA000 dark>
        <keep-alive>
          <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
        </keep-alive>
        <v-toolbar-title>xx大学教务管理系统</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn icon>
          <v-icon>mdi-magnify</v-icon>
        </v-btn>

        <v-menu left bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon v-bind="attrs" v-on="on">
              <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
          </template>

          <v-list>
            <v-list-item @click="() => {}">
              <v-list-item-title @click.stop="logout()">登出</v-list-item-title>
            </v-list-item>
            <v-dialog v-model="dialog" max-width="290">
              <v-card>
                <v-card-title class="headline">要离开教务系统吗?</v-card-title>

                <v-card-text>
                  系统可能不会保存您所做的更改。
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn color="green darken-1" text @click="dialog = false">
                    取消
                  </v-btn>

                  <v-btn color="green darken-1" text @click="logoutYes()">
                    确定
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-list>
        </v-menu>
      </v-app-bar>

      <v-app-bar v-else app color=#4DB6AC dark>
        <keep-alive>
          <v-app-bar-nav-icon @click.stop="drawer = !drawer" />
        </keep-alive>
        <v-toolbar-title>xx大学教务管理系统</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn icon>
          <v-icon>mdi-magnify</v-icon>
        </v-btn>

        <v-menu left bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn icon v-bind="attrs" v-on="on">
              <v-icon>mdi-dots-vertical</v-icon>
            </v-btn>
          </template>

          <v-list>
            <v-list-item @click="() => {}">
              <v-list-item-title @click.stop="logout()">登出</v-list-item-title>
            </v-list-item>
            <v-dialog v-model="dialog" max-width="290">
              <v-card>
                <v-card-title class="headline">要离开教务系统吗?</v-card-title>

                <v-card-text>
                  系统可能不会保存您所做的更改。
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>

                  <v-btn color="green darken-1" text @click="dialog = false">
                    取消
                  </v-btn>

                  <v-btn color="green darken-1" text @click="logoutYes()">
                    确定
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-list>
        </v-menu>
      </v-app-bar>

      <!--  中间content  -->
      <v-content>
        <breadcrumbs></breadcrumbs>
        <keep-alive>
          <router-view v-if="isRouterAlive">
            <component :is="activeItem"></component>
          </router-view>
        </keep-alive>
      </v-content>

      <!--  底边栏  -->
      <v-footer v-if="$route.meta.role == 'student'" color=#0D47A1 app>
        <span class="white--text">&copy; 2020 xx大学 ©1953-2020</span>
      </v-footer>
      <v-footer v-else-if="$route.meta.role == 'teacher'" color=#FF6F00 app>
        <span class="white--text">&copy; 2020 xx大学 ©1953-2020</span>
      </v-footer>
      <v-footer v-else color=#00796B app>
        <span class="white--text">&copy; 2020 xx大学 ©1953-2020</span>
      </v-footer>
    </div>

    <router-view v-if="!$route.meta.keepalive"></router-view>
  </v-app>
</template>

<script>
import LeftSide from "./components/leftSide";
import Breadcrumbs from "./components/breadcrumbs";
export default {
  components: {
    Breadcrumbs,
    LeftSide,
    mainPlot: () => import("./views/main_plot.vue"),
    otherPlot: () => import("./views/other_plot.vue"),
    changePassword: () => import("./views/change_password.vue"),
    stuentInfo: () => import("./views/student_Info.vue"),
    stuCheckGrade: () => import("./views/stu_check_grade.vue"),
    openCourse: () => import("./views/open_Courses.vue"),
    studentGradeInfo: () => import("./views/student_Grade_Info.vue"),
    teacherInfo: () => import("./views/teacher_Info.vue"),
    stuGradeIn: () => import("./views/student_Grade_In.vue"),
    studentGradeAnalysis: () => import("./views/stu_grade_ana.vue"),
    adminMain: () => import("./views/admin_main.vue"),
    openCourseAdmin: () => import("./views/open_CourseAdmin.vue"),
    openCourseTea: () => import("./views/open_CoursesTea.vue"),
    studentList: () => import("./views/student_list.vue"),
    teacherList: () => import("./views/teacher_list.vue"),
    courseList: () => import("./views/course_list.vue"),
    openCourseList: () => import("./views/openCourse_list.vue"),
    studentLocationAnalysis: () =>
      import("./views/student_location_analysis.vue")
  },
  props: {
    source: String
  },
  provide() {
    return {
      reload: this.reload
    };
  },
  data: () => ({
    drawer: null,
    activeItem: "main_plot",
    dialog: false,
    isRouterAlive: true
  }),
  methods: {
    reload() {
      this.isRouterAlive = false;
      this.$nextTick(function() {
        this.isRouterAlive = true;
      });
    },
    logout() {
      this.dialog = true;
    },
    logoutYes() {
      this.global.setRedisInformation(null);
      this.global.setPwd(null);
      this.global.setRole(null);
      this.$router.push({
        name: "Login",
        params: {}
      });
    }
  },
  watch: {
    $route(to, from) {
      const path = to.path;
      let route = "";
      const pathArr = path.toString().split("/");
      for (let i = 1; i < pathArr.length; i++) {
        if (pathArr[i] === "") {
          route = "main_plot";
          continue;
        }
        route = pathArr[i];
      }
      console.log(route);
      this.activeItem = route;
    }
  }
};
</script>
