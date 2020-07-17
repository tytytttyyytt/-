import Vue from 'vue'
import VueRouter from 'vue-router'
import mainPlot from '../views/main_plot.vue'
import otherPlot from '../views/other_plot.vue'
import changePassword from '../views/change_password.vue'
import studentInfo from '../views/student_Info.vue'
import stuCheckGrade from '../views/stu_check_grade.vue'
import openCourse from '../views/open_Courses.vue'
import studentGradeInfo from '../views/student_Grade_Info.vue'
import teacherInfo from '../views/teacher_Info.vue'
import stuGradeIn from '../views/student_Grade_In.vue'
import studentGradeAnalysis from '../views/stu_grade_ana.vue'
import adminMain from '../views/admin_main.vue'
import openCoursesTea from '../views/open_CoursesTea.vue'
import openCourseAdmin from '../views/open_CourseAdmin.vue'
import studentList from '../views/student_list.vue'
import teacherList from '../views/teacher_list.vue'
import courseList from '../views/course_list.vue'
import openCourseList from '../views/openCourse_list.vue'
import studentLocationAnalysis from '../views/student_location_analysis.vue'
import Login from '@/components/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    meta: {
      title: 'xx大学教务管理系统',
      keepalive: false,
      role: 'student'
    }
  },
  {
    path: '/main',
    name: 'mainPlot',
    component: mainPlot,
    meta: {
      title: 'xx大学教务管理系统',
      keepalive: true,
      role: 'student'
    }
  },
  {
    path: '/teachermain',
    name: 'otherPlot',
    component: otherPlot,
    meta: {
      title: 'xx大学教务管理系统',
      keepalive: true,
      role: 'teacher'
    }
  },
  {
    path: '/studentInfo',
    name: 'studentInfo',
    component: studentInfo,
    meta: {
      title: '学生信息',
      keepalive: true,
      role: 'student'
    }
  },
  {
    path: '/changePassword',
    name: 'changePassword',
    component: changePassword,
    meta: {
      title: '修改密码',
      keepalive: true,
      role: 'student'
    }
  },
  {
    path: '/stuCheckGrade',
    name: 'stuCheckGrade',
    component: stuCheckGrade,
    meta: {
      title: '成绩查询',
      keepalive: true,
      role: 'student'
    }
  },
  {
    path: '/openCourse',
    name: 'openCourse',
    component: openCourse,
    meta: {
      title: '教学安排查询',
      keepalive: true,
      role: 'student'
    }
  },
  {
    path: '/studentGradeInfo',
    name: 'studentGradeInfo',
    component: studentGradeInfo,
    meta: {
      title: '学生学业情况',
      keepalive: true,
      role: 'student'
    }
  },
  {
    path: '/teacherInfo',
    name: 'teacherInfo',
    component: teacherInfo,
    meta: {
      title: '学生学业情况',
      keepalive: true,
      role: 'teacher'
    }
  },
  {
    path: '/stuGradeIn',
    name: 'stuGradeIn',
    component: stuGradeIn,
    meta: {
      title: '教学安排查询',
      keepalive: true,
      role: 'teacher'
    }
  },
  {
    path: '/studentGradeAnalysis',
    name: 'studentGradeAnalysis',
    component: studentGradeAnalysis,
    meta: {
      title: '学生成绩分析',
      keepalive: true,
      role: 'teacher'
    }
  },
  {
    path: '/adminMain',
    name: 'adminMain',
    component: adminMain,
    meta: {
      title: 'xx大学教务管理系统',
      keepalive: true,
      role: 'admin'
    }
  },
  {
    path: '/openCourseAdmin',
    name: 'openCourseAdmin',
    component: openCourseAdmin,
    meta: {
      title: '教学安排查询',
      keepalive: true,
      role: 'admin'
    }
  },
  {
    path: '/openCourseTeacher',
    name: 'openCoursesTea',
    component: openCoursesTea,
    meta: {
      title: '教学安排查询',
      keepalive: true,
      role: 'teacher'
    }
  },
  {
    path: '/studentList',
    name: 'studentList',
    component: studentList,
    meta: {
      title: '学生信息维护',
      keepalive: true,
      role: 'admin'
    }
  },
  {
    path: '/teacherList',
    name: 'teacherList',
    component: teacherList,
    meta: {
      title: '教师信息维护',
      keepalive: true,
      role: 'admin'
    }
  },
  {
    path: '/courseList',
    name: 'courseList',
    component: courseList,
    meta: {
      title: '课程信息维护',
      keepalive: true,
      role: 'admin'
    }
  },
  {
    path: '/openCourseList',
    name: 'openCourseList',
    component: openCourseList,
    meta: {
      title: '开课信息维护',
      keepalive: true,
      role: 'admin'
    }
  },
  {
    path: '/studentLocationAnalysis',
    name: 'studentLocationAnalysis',
    component: studentLocationAnalysis,
    meta: {
      title: '学生生源地分析',
      keepalive: true,
      role: 'admin'
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
