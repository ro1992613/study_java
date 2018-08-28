<template  >
    <div class="bg">
        <Row style="height: 10%;">
            <div class="card" style="height: 80%;text-align: center;font-size: 35px;padding-top:8px;font-weight:bold;letter-spacing:3px;">成都市共享单车监管平台</div>
        </Row>
        <Row style="height: 55%;">
            <Col span="6" style="height: 100%;">
              <Row style="height: 20%;">
                <Col span="12" style="height: 100%;">
                  <div v-on:click="showTotalData()" class="card" style="height: 93%;padding:5px;padding-left:10px;">
                      <span style="font-size: 15px;line-height:15px;">投放总量:</span>
                     <span style="text-align: center;font-size: 38px;line-height:38px;color:rgb(180, 255, 255);font-weight:bold;"> <count-to :end-val="68789"></count-to> </span>
                  </div>
                </Col>
                <Col span="12" style="height: 100%;">
                  <div v-on:click="showTotalData()" class="card" style="height: 93%;padding:5px;padding-left:10px;">
                      <span style="font-size: 15px;line-height:15px;">骑行次数:</span>
                     <span style="text-align: center;font-size: 38px;line-height:38px;color:rgb(180, 255, 255);font-weight:bold;"> <count-to :end-val="4589712"></count-to> </span>
                  </div>
                </Col>
              </Row>
              <Row style="height: 80%;margin-top:4px;">
                <div  v-on:click="showTotalData()" id="company_bike" class="card" style="height:93%;"></div>
              </Row>
            </Col>
            <Col span="10"  style="height: 95%;">
                <div v-on:click="showBackground()" class="card"  style="height: 100%;">
                  <my-map></my-map>
                </div>
            </Col>
            <Col span="8"  style="height: 100%;">
                <Row  style="height: 50%;">
                    <Col style="height: 100%;" span="12">
                      <div v-on:click="showWeihuData()" id="person_num"  style="height: 90%;" class="card"></div>
                    </Col>
                    <Col style="height: 100%;" span="12">
                      <div v-on:click="showWeihuData()" id="complaint_num" style="height: 90%;" class="card"></div>
                    </Col>
                </Row>
                <Row  style="height: 50%;">
                    <div v-on:click="showWeihuData()" id="complaint_date_num" style="height: 90%;" class="card"></div>
                </Row>
            </Col>
        </Row>
        <Row style="height: 35%;">
            <Col style="height: 95%;" span="6">
                <div v-on:click="showTotalData()" id="bike_area_num" class="card" style="height: 100%;"></div>
            </Col>
            <Col style="height: 95%;" span="10">
                <div v-on:click="showBackground()" class="card content" style="height: 100%;">
                  <Row>
                    <h2>实时消息推送</h2>
                  </Row>
                  <transition-group name="list" tag="p">
                      <Row  v-for="item in current_news" v-bind:key="item.id" class="item_row">
                        <Col class="item" span="2"><Tag  color="#2A8FBD">{{item.type}}</Tag></Col>
                        <Col class="item" span="3">{{item.area}}</Col>
                        <Col class="item" span="4">{{item.date}}</Col>
                        <Col class="item" span="15">{{item.info}}</Col>
                      </Row>
                    </transition-group>
                </div>
            </Col>
            <Col style="height: 95%;" span="8">
                <div v-on:click="showWeihuData()" id="weihu_date_num" style="height: 100%;" class="card"></div>
            </Col>
        </Row>
    </div>
</template>
<script>
import echarts from "echarts";
import myMap from "./map.vue";
import CountTo from "../page/components/CountTo.vue";

const colorList=['#7bbfea','#2A8FBD','#4C556E','#DDF0ED','#495A80','#EEEEFF','#426ab3','#228fbd','#9999FF','#CCCCFF','#06B9D1'];

export default {
  components: {
    CountTo,
    myMap
  },
  data() {
    return {
      news: [
        {
          id: "1",
          type: "报警",
          area: "青羊区",
          date: "2018-05-23",
          info:
            "区域投放车辆超过阈值！区域投放车辆超过阈值！区域投放车辆超过阈值！区域投放车辆超过阈值！区域投放车辆超过阈值！区域投放车辆超过阈值！区域投放车辆超过阈值！区域投放车辆超过阈值！"
        },
        {
          id: "2",
          type: "投诉",
          area: "青羊区",
          date: "2018-05-23",
          info: "ofo服务人员态度恶劣"
        },
        {
          id: "3",
          type: "报警",
          area: "锦江区",
          date: "2018-05-23",
          info: "摩拜维护人员低于30人"
        },
        {
          id: "4",
          type: "投诉",
          area: "金牛区",
          date: "2018-05-23",
          info: "摩拜的服务质量差"
        },
        {
          id: "5",
          type: "报警",
          area: "成华区",
          date: "2018-05-23",
          info: "区域投放车辆超过阈值！"
        },
        {
          id: "6",
          type: "警告",
          area: "龙泉驿区",
          date: "2018-05-23",
          info: "区域投放车辆接近阈值！"
        },
        {
          id: "7",
          type: "投诉",
          area: "锦江区",
          date: "2018-05-23",
          info: "一步单车的坏车太多了"
        },
        {
          id: "8",
          type: "投诉",
          area: "双流区",
          date: "2018-05-23",
          info: "机场附近的单车太多，占用人行道！"
        },
        {
          id: "9",
          type: "投诉",
          area: "武侯区",
          date: "2018-05-23",
          info: "火车南站地铁站附近堆积了大量单车"
        },
        {
          id: "0",
          type: "投诉",
          area: "青白江区",
          date: "2018-05-23",
          info: "单车占用人行道"
        },
        {
          id: "11",
          type: "投诉",
          area: "青羊区",
          date: "2018-05-23",
          info: "摩拜服务人员态度差"
        },
        {
          id: "12",
          type: "投诉",
          area: "温江区",
          date: "2018-05-23",
          info: "单车堆积"
        },
        {
          id: "13",
          type: "报警",
          area: "温江区",
          date: "2018-05-23",
          info: "区域投放车辆超过阈值！"
        },
        {
          id: "14",
          type: "报警",
          area: "双流区",
          date: "2018-05-23",
          info: "区域投放车辆超过阈值！"
        }
      ],
      current_news: [],
      company_bike: [
        { name: "摩拜", num: 48756 },
        { name: "ofo", num: 35555 },
        { name: "青桔", num: 25360 },
        { name: "黑拜", num: 15360 },
        { name: "1 步", num: 5360 },
        { name: "哈罗", num: 4360 },
        { name: "赳赳", num: 3360 },
        { name: "智聪", num: 1360 }
      ],
      complaint_date_num: [
        { date: "2018-05-23", num: 64 },
        { date: "2018-05-24", num: 35 },
        { date: "2018-05-25", num: 62 },
        { date: "2018-05-26", num: 142 },
        { date: "2018-05-27", num: 81 },
        { date: "2018-05-28", num: 36 },
        { date: "2018-05-29", num: 24 }
      ],
      weihu_date_num: [
        { date: "2018-05-23", fix: 64, set: 42, bad: 82 },
        { date: "2018-05-24", fix: 35, set: 62, bad: 92 },
        { date: "2018-05-25", fix: 62, set: 92, bad: 72 },
        { date: "2018-05-26", fix: 142, set: 172, bad: 52 },
        { date: "2018-05-27", fix: 81, set: 12, bad: 32 },
        { date: "2018-05-28", fix: 36, set: 42, bad: 122 },
        { date: "2018-05-29", fix: 24, set: 52, bad: 132 }
      ],
      bike_area_num: [
        {
          area: "青羊区",
          num: 3100
        },
        {
          area: "武侯区",
          num: 4005
        },
        {
          area: "金牛区",
          num: 12509
        },
        {
          area: "锦江区",
          num: 11115
        },
        {
          area: "成华区",
          num: 14210
        },
        {
          area: "双流区",
          num: 23520
        },
        {
          area: "龙泉驿区",
          num: 15130
        },
        {
          area: "郫都区",
          num: 14330
        },
        {
          area: "温江区",
          num: 6303
        },
        {
          area: "青白江区",
          num: 14330
        },
        {
          area: "新都区",
          num: 2303
        }
      ],
      complaint_num: [
        { name: "摩拜", num: 48756 },
        { name: "ofo", num: 35555 },
        { name: "青桔", num: 25360 },
        { name: "黑拜", num: 15360 },
        { name: "1 步", num: 5360 },
        { name: "哈罗", num: 2360 },
        { name: "赳赳", num: 4360 },
        { name: "智聪", num: 1360 }
      ],
      person_num: [
        { name: "摩拜", num: 38756 },
        { name: "ofo", num: 25555 },
        { name: "青桔", num: 15360 },
        { name: "黑拜", num: 5360 },
        { name: "1 步", num: 4360 },
        { name: "哈罗", num: 3060 },
        { name: "赳赳", num: 1360 },
        { name: "智聪", num: 2360 }
      ]
    };
  },
  methods: {
    showTotalData:function(){
      this.$router.push("/show_data_total");
    },
    showWeihuData:function(){
      this.$router.push("/show_data_weihu");
    },
    showBackground:function(){
      this.$router.push("/");
    },
    create_complaint_date_num: function(data) {
      let myChart = echarts.init(document.getElementById("complaint_date_num"));
      let xAxis = {
        name: "数量",
        type: "category",
        data: [],
        axisLine: { lineStyle: { color: "#eeeeee" } },
        axisTick: { lineStyle: { color: "#eeeeee" } },
        nameTextStyle: { color: "#eeeeee" },
        axisLabel: { color: "#eeeeee" }
      };
      let series = {
        name: "数量",
        type: "bar",
        barWidth: 30,
        itemStyle: {
          normal: {
            barBorderRadius: 3,
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: "#9bdffa"
              },
              {
                offset: 1,
                color: "#2A8FBD"
              }
            ]),
            shadowColor: "rgba(0, 0, 0, 0.3)",
            shadowBlur: 20
          }
        },
        label: {
          normal: {
            show: true,
            color:'#eeeeee',
            position: "top"
          }
        },
        data: []
      };

      for (let i in data) {
        xAxis.data.push(data[i].date);
        series.data.push(data[i].num);
      }

      let option = {
        color: colorList,
        title: { text: "投诉事件时间分布", textStyle: { color: "#eeeeee" } },
        grid: { y2: 30 },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#6a7985"
            }
          }
        },
        xAxis: xAxis,
        yAxis: {
          name: "日期",
          type: "value",
          axisLine: { lineStyle: { color: "#eeeeee" } },
          axisTick: { lineStyle: { color: "#eeeeee" } },
          nameTextStyle: { color: "#eeeeee" },
          axisLabel: { color: "#eeeeee" }
        },
        series: [series]
      };
      myChart.setOption(option);
    },
    create_weihu_date_num: function(data) {
      let myChart = echarts.init(document.getElementById("weihu_date_num"));
      let xAxis = {
        name: "数量",
        type: "category",
        data: [],
        axisLine: { lineStyle: { color: "#eeeeee" } },
        axisTick: { lineStyle: { color: "#eeeeee" } },
        nameTextStyle: { color: "#eeeeee" },
        axisLabel: { color: "#eeeeee" }
      };
      let fix_series = {
        areaStyle: { normal: {} },
        name: "维修",
        type: "bar",
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let set_series = {
        areaStyle: { normal: {} },
        name: "维修投放",
        type: "bar",
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let bad_series = {
        areaStyle: { normal: {} },
        name: "报废",
        type: "bar",
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      for (let i in data) {
        xAxis.data.push(data[i].date);
        fix_series.data.push(data[i].fix);
        set_series.data.push(data[i].set);
        bad_series.data.push(data[i].bad);
      }

      let option = {
        color: colorList,
        title: { text: "车辆维护状态", textStyle: { color: "#eeeeee" } },
        grid: { y2: 30 },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#6a7985"
            }
          }
        },
        legend: {
          data: ["维修", "维修投放", "报废"],
          left: 200,
          textStyle: { color: "#eeeeee" }
        },
        xAxis: xAxis,
        yAxis: {
          name: "日期",
          type: "value",
          axisLine: { lineStyle: { color: "#eeeeee" } },
          axisTick: { lineStyle: { color: "#eeeeee" } },
          nameTextStyle: { color: "#eeeeee" },
          axisLabel: { color: "#eeeeee" }
        },
        series: [fix_series, set_series, bad_series]
      };
      myChart.setOption(option);
    },
    create_company_bike: function(data) {
      let myChart = echarts.init(document.getElementById("company_bike"));
      let yAxis = {
        splitLine: false,
        type: "category",
        data: [],
        axisLine: { show: false },
        axisTick: { show: false },
        nameTextStyle: { color: "#eeeeee", width: 500 },
        axisLabel: { color: "#eeeeee" }
      };

      let temp_yAxis={
        show: false,
        type: "category",
        data: []
      };

      let series = {
        name: "数量",
        type: "bar",
        itemStyle: {
          normal: {
            barBorderRadius: 5,
            color: new echarts.graphic.LinearGradient(1, 1, 0, 0, [
              {
                offset: 0,
                color: "#9bdffa"
              },
              {
                offset: 1,
                color: "#2A8FBD"
              }
            ]),
            shadowColor: "rgba(0, 0, 0, 0.3)",
            shadowBlur: 10
          }
        },
        label: {
          normal: {
            show: true,
            position: "insideLeft"
          }
        },
        barWidth: 22,
        data: []
      };

      let max = 0;
      for (let j in data) {
        let i = data.length - j - 1;
        yAxis.data.push(data[i].name);
        temp_yAxis.data.push(data[i].name);
        series.data.push(data[i].num);
        if (data[i].num > max) {
          max = data[i].num;
        }
      }
      let temp_series = {
        // 辅助系列
        type: "bar",
        silent: true,
        yAxisIndex: 1,
        itemStyle: {
          normal: {
            barBorderRadius: 8,
            color: "#ddddddaa"
          }
        },
        barWidth: 25,
        data: []
      };

      for (let j in data) {
        temp_series.data.push(max);
      }
      let option = {
        grid: { x: 50, x2: 20, y: 35, y2: 5 },
        title: {
          text: "单车品牌投放数量排名",
          textStyle: { color: "#eeeeee" }
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#6a7985"
            }
          }
        },
        xAxis: {
          show: false,
          name: "品牌",
          nameRotate: 270,
          type: "value",
          axisLine: { lineStyle: { color: "#eeeeee" } },
          axisTick: { show: false },
          splitLine: false,
          nameTextStyle: { color: "#eeeeee" },
          axisLabel: { color: "#eeeeee", rotate: 270 }
        },
        yAxis: [yAxis,temp_yAxis],
        series: [temp_series,series ]
      };
      myChart.setOption(option);
    },
    create_complaint_num: function(data) {
      let myChart = echarts.init(document.getElementById("complaint_num"));
      let legend = [];
      let series = {
        type: "pie",
        radius: "40%",
        data: []
      };
      for (let i in data) {
        legend.push(data[i].name);
        let temp = { name: data[i].name, value: data[i].num };
        series.data.push(temp);
      }
      let option = {
        color: colorList,
        title: { text: "投诉品牌数量分布", textStyle: { color: "#eeeeee" } },
        tooltip: {},
        legend: {
          type: "scroll",
          bottom: 1,
          left: "center",
          textStyle: { color: "#eeeeee" },
          data: legend
        },
        series: [series]
      };
      myChart.setOption(option);
    },
    create_bike_area_num: function(data) {
      let myChart = echarts.init(document.getElementById("bike_area_num"));
      let series = {
        type: "pie",
        radius: "65%",
        data: []
      };
      for (let i in data) {
        let temp = { name: data[i].area, value: data[i].num };
        series.data.push(temp);
      }
      let option = {
        color: colorList,
        title: { text: "车辆投放区域分布", textStyle: { color: "#eeeeee" } },
        tooltip: {},
        series: [series]
      };
      myChart.setOption(option);
    },
    create_person_num: function(data) {
      let myChart = echarts.init(document.getElementById("person_num"));
      let legend = [];
      let series = {
        type: "pie",
        radius: "40%",
        data: []
      };
      for (let i in data) {
        legend.push(data[i].name);
        let temp = { name: data[i].name, value: data[i].num };
        series.data.push(temp);
      }
      let option = {
        color: colorList,
        title: { text: "车辆维护人员分布", textStyle: { color: "#eeeeee" } },
        tooltip: {},
        legend: {
          type: "scroll",
          bottom: 1,
          left: "center",
          textStyle: { color: "#eeeeee" },
          data: legend
        },
        series: [series]
      };
      myChart.setOption(option);
    },
    scrollNews: function(i) {
      if (this.current_news.length > 8) {
        this.current_news.splice(0, 1);
      }
      while (this.current_news.length <= 8) {
        this.current_news.push(this.news[i]);
        i++;
      }
      this.current_news.push(this.news[i]);
      i++;
      if (i >= this.news.length) {
        i = 0;
      }
      let self = this;
      if (self && !self._isDestroyed) {
        setTimeout(() => {
          if (self && !self._isDestroyed) {
            self.scrollNews(i);
          }
        }, 3000);
      }
    }
  },
  mounted: function() {
    this.create_company_bike(this.$data.company_bike);
    this.create_complaint_date_num(this.$data.complaint_date_num);
    this.create_complaint_num(this.$data.complaint_num);
    this.create_person_num(this.$data.person_num);
    this.create_bike_area_num(this.$data.bike_area_num);
    this.create_weihu_date_num(this.$data.weihu_date_num);
    this.scrollNews(0);
  }
};
</script>
<style scoped>
.bg {
  display: block;
  height: 100%;
  width: 100%;
  background-image: url("/dist/data_bg.jpg");
  background-size: cover;
  position: relative;
  padding: 10px;
  color: #eeeeee;
}
.card {
  margin: 15px;
  padding: 15px;
  box-shadow: 0px 0px 30px rgba(200, 255, 255, 0.75) inset;
  background: linear-gradient(to left, rgb(180, 255, 255), rgb(180, 255, 255))
      left top no-repeat,
    linear-gradient(to bottom, rgb(180, 255, 255), rgb(180, 255, 255)) left top
      no-repeat,
    linear-gradient(to left, rgb(180, 255, 255), rgb(180, 255, 255)) right top
      no-repeat,
    linear-gradient(to bottom, rgb(180, 255, 255), rgb(180, 255, 255)) right top
      no-repeat,
    linear-gradient(to left, rgb(180, 255, 255), rgb(180, 255, 255)) left bottom
      no-repeat,
    linear-gradient(to bottom, rgb(180, 255, 255), rgb(180, 255, 255)) left
      bottom no-repeat,
    linear-gradient(to left, rgb(180, 255, 255), rgb(180, 255, 255)) right
      bottom no-repeat,
    linear-gradient(to left, rgb(180, 255, 255), rgb(180, 255, 255)) right
      bottom no-repeat;
  background-size: 2px 20px, 20px 2px, 2px 20px, 20px 2px;
  background-color: rgba(0, 0, 0, 0.23);
}
.item_row {
  margin: 1px;
  background-color: rgba(0, 0, 0, 0.1);
}
.item {
  margin-top: 11px;
  font-size: 15px;
  text-align: center;
}
.list-enter-active {
  transition: all 1s;
}
.list-leave-active {
  transition: all 1s;
}
.list-enter {
  opacity: 0;
  transform: translateX(10px);
}
.list-leave-to {
  opacity: 0;
  transform: translateX(10px);
}
.content {
  overflow: hidden;
}
</style>


