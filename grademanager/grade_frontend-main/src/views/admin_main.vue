<template>
  <v-card class="myCard">
    <v-container fluid>
      <v-row dense>

        <v-col :cols="cards[0].flex">
          <v-card>
            <v-carousel height="200px" cycle>
              <v-carousel-item v-for="(item,i) in items" :key="i" :src="item.src" reverse-transition="fade-transition" transition="fade-transition"></v-carousel-item>
            </v-carousel>

            <v-card-actions>
              <v-spacer>欢迎您，{{username}}</v-spacer>

              <v-btn icon>
                <v-icon>mdi-arrow-right-bold</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>

        <v-col :cols="cards[1].flex">
          <v-card class="mx-auto" color="#26c6da" dark max-width="800">
            <v-card-title>
              <v-icon large left>
                mdi-account
              </v-icon>
              <span class="title font-weight-light">个人信息</span>
            </v-card-title>

            <v-card-text class="headline font-weight-bold">
              {{classInfo}}
            </v-card-text>

            <v-card-actions>
              <v-list-item class="grow">
                <v-list-item-avatar color="grey darken-3">
                  <v-img v-if="sex == 1" class="elevation-6" src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"></v-img>
                  <v-img v-else class="elevation-6" src="https://avataaars.io/"></v-img>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title>{{username}}</v-list-item-title>
                </v-list-item-content>

                <v-row align="center" justify="end">
                  <v-icon class="mr-1">mdi-domain</v-icon>
                </v-row>
              </v-list-item>
            </v-card-actions>
          </v-card>
        </v-col>

        <v-col :cols="cards[2].flex">
          <v-card>
            <v-img :src="cards[2].src" class="white--text align-end" gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)" height="200px">
              <v-card-title v-text="cards[0].title"></v-card-title>
            </v-img>

            <v-card-actions>
              <v-spacer></v-spacer>

              <router-link tag="div" v-bind:to="publicInfo">
                <v-btn icon>
                  <v-icon>mdi-arrow-right-bold</v-icon>
                </v-btn>
              </router-link>
            </v-card-actions>
          </v-card>
        </v-col>

      </v-row>
    </v-container>
  </v-card>
</template>

<script>
export default {
  name: "adminMain",
  data: () => ({
    cards: [
      {
        title: "教学公共查询",
        src: "https://cdn.vuetifyjs.com/images/cards/house.jpg",
        flex: 12
      },
      {
        title: "Favorite road trips",
        src: "https://cdn.vuetifyjs.com/images/cards/road.jpg",
        flex: 6
      },
      {
        title: "Best airlines",
        src: "https://cdn.vuetifyjs.com/images/cards/plane.jpg",
        flex: 6
      }
    ],
    items: [
      {
        src: "https://www.zhangzhengtytytttyyytt.top/database/main_1.jpeg"
      },
      {
        src: "https://www.zhangzhengtytytttyyytt.top/database/main_2.JPEG"
      },
      {
        src: "https://www.zhangzhengtytytttyyytt.top/database/main_3.jpg"
      }
    ],
    username: "welcome",
    userInfo: null,
    sex: 1,
    classInfo: "默认",
    studentInfo: "/studentInfo",
    publicInfo: "/openCourseAdmin"
  }),
  methods: {},

  created: function() {
    let userTemp = JSON.parse(this.global.getRedisInformation());
    this.userInfo = userTemp;
    this.username = "管理员" + this.userInfo.zz_Ano15;
    this.classInfo = "管理员编号：" + this.userInfo.zz_Ano15;
  }
};
</script>

<style>
.myCard {
  padding-left: 15px;
  margin-left: 20px;
  margin-right: 20px;
  margin-bottom: 20px;
  padding-right: 20px;
}
</style>
