<template>
  <v-card class="myCard">
    <v-data-iterator :items="items" :items-per-page.sync="itemsPerPage" hide-default-footer>
      <template v-slot:header>
        <v-toolbar class="mb-2" color="cyan darken-2" dark flat>
          <v-toolbar-title>学生生源地分析</v-toolbar-title>
        </v-toolbar>
      </template>
      <template>
        <v-row>

          <v-col cols="12" sm="12" md="6" lg="6">
            <v-card>
              <v-card-title class="subheading font-weight-bold">省份分析
                <v-spacer></v-spacer>
                <v-btn @click="refresh" color="#42A5F5">刷新</v-btn>
              </v-card-title>

              <v-divider></v-divider>
              <v-row :align="alignment" :justify="justify" style="padding-top:5%;padding-bottom:5%">
                <div id="myChart" :style="{width: '550px', height: '250px'}"></div>
                <div style="position: absolute;left: 44%;top: 50%;">
                  <p style="font-size: 26px;margin-left: 5px;">{{chooseEquipment.data}}</p>
                  <p style="font-size: 12px;margin-left: 18px;margin-top: 3px">{{chooseEquipment.value}}</p>
                </div>
              </v-row>

            </v-card>
          </v-col>
          <v-col cols="12" sm="12" md="6" lg="6">
            <v-card>
              <v-card-title class="subheading font-weight-bold">城市分析
                <v-spacer></v-spacer>
                <el-select v-model="province" clearable placeholder="请选择省份" @change="changePro">
                  <el-option v-for="item in optionspro" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </v-card-title>

              <v-divider></v-divider>
              <v-row :align="alignment" :justify="justify" style="padding-top:5%;padding-bottom:5%">
                <div id="myChart2" :style="{width: '550px', height: '250px'}"></div>
                <div style="position: absolute;left: 44%;top: 50%;">
                  <p style="font-size: 26px;margin-left: 5px;">{{chooseEquipment2.data}}</p>
                  <p style="font-size: 12px;margin-left: 18px;margin-top: 3px">{{chooseEquipment2.value}}</p>
                </div>
              </v-row>
            </v-card>
          </v-col>

        </v-row>
      </template>
    </v-data-iterator>
  </v-card>
</template>

<script>
import echarts from "echarts";
export default {
  inject: ["reload"],
  name: "studentLocationAnalysis",
  data() {
    return {
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
      userInfo: null,
      optionspro: [],
      province: null,
      alignment: "center",
      justify: "center",
      chooseEquipment: { data: null, value: null },
      chooseEquipment2: { data: null, value: null },
      optionsCities: [],
      optionsProvinces: [],
      prochart: null,
      ctiychart: null,
      option: null,
      option2: null,
      ldata: null,
      ldata2: null,
      select: null,
      select2: null,
      style: { width: "100px", height: "100px" }
    };
  },
  computed: {},
  mounted() {
    this.initChart();
  },
  methods: {
    changePro(data) {
      if (data == null || data == "") {
        return;
      }
      const obj = this;
      var urlcity = "http://localhost:8088/cities/getCitiesByPrno";
      var reqData = { prno: data };
      this.Get(urlcity, reqData, function(cb) {
        if (cb.status == "success") {
          obj.optionsCities = [];
          obj.ldata2 = [];
          obj.select2 = {};
          for (let i = 0; i < cb.data.length; i++) {
            var temp = {
              value: null,
              name: null
            };
            temp.value = cb.data[i].zz_Summarycount15;
            temp.name = cb.data[i].zz_Name15;
            obj.select2[temp.name] = true;
            if (temp.value == 0) obj.select2[temp.name] = false;
            obj.optionsCities.push(temp);
            obj.ldata2.push(cb.data[i].zz_Name15);
          }
          console.log("城市", obj.select2);
        } else {
          obj.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
      });
    },
    initChart() {
      this.ctiychart = echarts.init(document.getElementById("myChart2"));
      let obj = this;
      var urlprovince = "http://localhost:8088/provinces/getAllProvinces";
      this.Get(urlprovince, null, function(cb) {
        let that = obj;
        if (cb.status == "success") {
          obj.optionsProvinces = [];
          obj.ldata = [];
          obj.select = {};
          for (let i = 0; i < cb.data.length; i++) {
            var temp = {
              value: null,
              name: null
            };
            var tempPro = {
              value: null,
              label: null
            };
            temp.value = cb.data[i].zz_Summarycount15;
            temp.name = cb.data[i].zz_Name15;
            obj.select[temp.name] = true;
            if (temp.value == 0) obj.select[temp.name] = false;
            tempPro.value = cb.data[i].zz_Prno15;
            tempPro.label = cb.data[i].zz_Name15;
            obj.optionspro.push(tempPro);
            obj.optionsProvinces.push(temp);
            obj.ldata.push(cb.data[i].zz_Name15);
          }
          console.log("省", obj.select);
          obj.option = {
            legend: {
              orient: "vertical",
              x: "left",
              data: obj.ldata,
              selected: obj.select
            },
            series: [
              {
                type: "pie",
                radius: ["50%", "70%"],
                avoidLabelOverlap: false,
                hoverAnimation: true,
                itemStyle: {
                  normal: {
                    borderColor: "#FFFFFF",
                    borderWidth: 1
                  }
                },
                labelLine: {
                  normal: {
                    lineStyle: {
                      color: "#727272" // 改变标示线的颜色
                    }
                  },
                  emphasis: {
                    lineStyle: {
                      color: "#727272" // 改变标示线的颜色
                    }
                  }
                },
                label: {
                  normal: {
                    show: true,
                    position: "right",
                    formatter: "{b} : {c} 人",
                    textStyle: {
                      color: "#727272"
                    }
                  },
                  emphasis: {
                    show: true
                  }
                },
                data: that.optionsProvinces
              }
            ],
            color: [
              "#2864fa",
              "#50b6ff",
              "#E3F2FD",
              "#BBDEFB",
              "#64B5F6",
              "#90CAF9",
              "#42A5F5",
              "#82B1FF",
              "#448AFF",
              "#2979FF",
              "#2962FF",
              "#0277BD",
              "#01579B"
            ]
          };
          obj.prochart = echarts.init(document.getElementById("myChart"));
          obj.prochart.setOption(obj.option);

          obj.prochart.on("mouseover", function(params) {
            /*添加鼠标事件*/
            that.chooseEquipment.value = params.name;
            if (params.percent === 0) that.chooseEquipment.data = "0.00" + "%";
            else {
              let data = parseFloat(params.percent);
              that.chooseEquipment.data = data.toFixed(1) + "%";
            }
          });
        } else {
          obj.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
      });
    },
    refresh() {
      console.log("刷新");
      const obj = this;
      var urlprovince = "http://localhost:8088/provinces/getAllProvinces";
      this.Get(urlprovince, null, function(cb) {
        let that = obj;
        if (cb.status == "success") {
          obj.optionsProvinces = [];
          obj.ldata = [];
          obj.select = {};
          for (let i = 0; i < cb.data.length; i++) {
            var temp = {
              value: null,
              name: null
            };
            temp.value = cb.data[i].zz_Summarycount15;
            temp.name = cb.data[i].zz_Name15;
            obj.select[temp.name] = true;
            if (temp.value == 0) obj.select[temp.name] = false;
            obj.optionsProvinces.push(temp);
            obj.ldata.push(cb.data[i].zz_Name15);
          }
          console.log("省", obj.select);
        } else {
          obj.$message({
            message: "网络异常，请稍候再试",
            type: "error"
          });
        }
      });
    },
    Post: function(url, reqData, callback) {
      // console.log(url)
      // console.log(reqData)
      //1.拼接请求参数
      var param = "";
      for (var item in reqData) {
        param += item + "=" + reqData[item] + "&";
      }
      console.log(param);
      //2.发起请求
      var xhr = new XMLHttpRequest();
      console.log("开始请求");

      console.log("onready");
      xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
          if (xhr.status >= 200 && xhr.status < 400) {
            var response = xhr.responseText;
            console.log(response);
            if (response) {
              var responseJson = JSON.parse(response);
              callback(responseJson);
            } else {
              console.log("返回数据不存在");
              callback(false);
            }
          } else {
            console.log("请求失败");
            callback(false);
          }
        }
      };
      xhr.open("POST", url, true);

      xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
      // xhr.setRequestHeader("Content-Type", "application/json");
      xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
      xhr.setRequestHeader(
        "Access-Control-Allow-Methods",
        "OPTIONS, GET, POST"
      );
      xhr.setRequestHeader(
        "Access-Control-Allow-Headers",
        "x-requested-with,content-type"
      );
      xhr.send(param); //reqData为字符串形式： "key=value"
    },

    Get: function(url, reqData, callback) {
      var self = this;

      url += "?";
      for (var item in reqData) {
        url += item + "=" + reqData[item] + "&";
      }

      console.log(url);
      var xhr = new XMLHttpRequest();
      xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
          if (xhr.status >= 200 && xhr.status < 400) {
            var response = xhr.responseText;
            // console.log(response)
            if (response) {
              var responseJson = JSON.parse(response);
              callback(responseJson);
            } else {
              console.log("返回数据不存在");
              callback(false);
            }
          } else {
            console.log("请求失败");
            callback(false);
          }
        }
      };
      xhr.open("GET", url, true);
      xhr.setRequestHeader("Content-Type", "application/json");
      xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
      xhr.setRequestHeader(
        "Access-Control-Allow-Methods",
        "OPTIONS, GET, POST"
      );
      xhr.setRequestHeader(
        "Access-Control-Allow-Headers",
        "x-requested-with,content-type"
      );
      xhr.send();
    }
  },
  created() {},
  watch: {
    optionsProvinces: {
      handler(newVal, oldVal) {
        console.log("tag", newVal);
        console.log("oldVal: ", oldVal);
        this.option = {
          legend: {
            orient: "vertical",
            x: "left",
            data: this.ldata,
            selected: this.select
          },
          series: [
            {
              type: "pie",
              radius: ["50%", "70%"],
              avoidLabelOverlap: false,
              hoverAnimation: true,
              itemStyle: {
                normal: {
                  borderColor: "#FFFFFF",
                  borderWidth: 1
                }
              },
              labelLine: {
                normal: {
                  lineStyle: {
                    color: "#727272" // 改变标示线的颜色
                  }
                },
                emphasis: {
                  lineStyle: {
                    color: "#727272" // 改变标示线的颜色
                  }
                }
              },
              label: {
                normal: {
                  show: true,
                  position: "right",
                  formatter: "{b} : {c} 人",
                  textStyle: {
                    color: "#727272"
                  }
                },
                emphasis: {
                  show: true
                }
              },
              data: this.optionsProvinces
            }
          ],
          color: [
            "#2864fa",
            "#50b6ff",
            "#E3F2FD",
            "#BBDEFB",
            "#64B5F6",
            "#90CAF9",
            "#42A5F5",
            "#82B1FF",
            "#448AFF",
            "#2979FF",
            "#2962FF",
            "#0277BD",
            "#01579B"
          ]
        };
        this.prochart.setOption(this.option);
      },
      deep: true
    },
    optionsCities: {
      handler(newVal, oldVal) {
        const that = this;
        console.log("tag", newVal);
        console.log("oldVal: ", oldVal);
        this.option2 = {
          legend: {
            type: "scroll",
            orient: "vertical",
            x: "left",
            data: this.ldata2,
            selected: this.select2
          },
          series: [
            {
              type: "pie",
              radius: ["50%", "70%"],
              avoidLabelOverlap: false,
              hoverAnimation: true,
              itemStyle: {
                normal: {
                  borderColor: "#FFFFFF",
                  borderWidth: 1
                }
              },
              labelLine: {
                normal: {
                  lineStyle: {
                    color: "#727272" // 改变标示线的颜色
                  }
                },
                emphasis: {
                  lineStyle: {
                    color: "#727272" // 改变标示线的颜色
                  }
                }
              },
              label: {
                normal: {
                  show: true,
                  position: "right",
                  formatter: "{b} : {c} 人",
                  textStyle: {
                    color: "#727272"
                  }
                },
                emphasis: {
                  show: true
                }
              },
              data: this.optionsCities
            }
          ],
          color: [
            "#2864fa",
            "#50b6ff",
            "#E3F2FD",
            "#BBDEFB",
            "#64B5F6",
            "#90CAF9",
            "#42A5F5",
            "#82B1FF",
            "#448AFF",
            "#2979FF",
            "#2962FF",
            "#0277BD",
            "#01579B"
          ]
        };
        this.ctiychart.setOption(this.option2);
        this.ctiychart.on("mouseover", function(params) {
          /*添加鼠标事件*/
          that.chooseEquipment2.value = params.name;
          if (params.percent === 0) that.chooseEquipment2.data = "0.00" + "%";
          else {
            let data = parseFloat(params.percent);
            that.chooseEquipment2.data = data.toFixed(1) + "%";
          }
        });
      },
      deep: true
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
