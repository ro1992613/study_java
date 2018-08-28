<template  >
    <div class="bg">
        <Row style="height: 20%;">
            <div style="height:90%" class="card">
                <Row style="height:100%">
                    <Col span="6" style="height:100%">
                        <div v-on:click="showBackground()" style="height:100%;text-align: center;">
                            <span style="font-size: 25px;color:#bbb;"><Tag  color="#7bbfea"></Tag> 投放总量</span>
                            <span style="font-size: 50px;color:rgb(180, 255, 255);font-weight:bold;">
                                <count-to :end-val="19555555"></count-to>
                            </span>
                        </div>
                    </Col>
                    <Col span="6" style="height:100%">
                        <div v-on:click="showBackground()" style="height:100%;text-align: center;">
                            <span style="font-size: 25px;color:#bbb;"><Tag  color="#2A8FBD"></Tag> 骑行总量</span>
                            <span style="font-size: 50px;color:rgb(180, 255, 255);font-weight:bold;">
                                <count-to :end-val="789555555"></count-to>
                            </span>
                        </div>
                    </Col>
                    <Col span="6" style="height:100%">
                        <div v-on:click="showBackground()" style="height:100%;text-align: center;">
                            <span style="font-size: 25px;color:#bbb;"><Tag  color="#4C556E"></Tag> 维护人员</span>
                            <span style="font-size: 50px;color:rgb(180, 255, 255);font-weight:bold;">
                                <count-to :end-val="9214"></count-to>
                            </span>
                        </div>
                    </Col>
                    <Col span="6" style="height:100%">
                        <div v-on:click="showBackground()" style="height:100%;text-align: center;">
                            <span style="font-size: 25px;color:#bbb;"><Tag  color="#DDF0ED"></Tag> 单车企业</span>
                            <span style="font-size: 50px;color:rgb(200, 255, 255);font-weight:bold;">
                                <count-to :end-val="8"></count-to>
                            </span>
                        </div>
                    </Col>
                </Row>
            </div>
        </Row>
        <Row style="height: 40%;">
            <Col span="16" style="height:100%">
                <div v-on:click="showBackground()" id="bike_limit_num" style="height:90%" class="card"></div>
            </Col>
            <Col  span="8" style="height:100%">
                <div v-on:click="showBackground()" id="bike_company_num" style="height:90%" class="card"></div>
            </Col>
        </Row>
        <Row style="height: 40%;">
            <Col span="12" style="height:100%">
                <div v-on:click="showBackground()" id="area_bike" style="height:90%" class="card"></div>
            </Col>
            <Col span="12" style="height:100%">
                <div v-on:click="showBackground()" id="date_bike" style="height:90%" class="card"></div>
            </Col>
        </Row>
    </div>
</template>
<script>
import echarts from "echarts";
import CountTo from "../page/components/CountTo.vue";

const colorList = [
  "#7bbfea",
  "#2A8FBD",
  "#4C556E",
  "#DDF0ED",
  "#495A80",
  "#EEEEFF",
  "#426ab3",
  "#228fbd",
  "#9999FF",
  "#CCCCFF",
  "#06B9D1"
];

export default {
  components: {
    CountTo
  },
  data() {
    return {
      bike_date_num: [
        { date: "2018-05-23", num: 766664 },
        { date: "2018-05-24", num: 776664 },
        { date: "2018-05-25", num: 786664 },
        { date: "2018-05-26", num: 796664 },
        { date: "2018-05-27", num: 866664 },
        { date: "2018-05-28", num: 966664 },
        { date: "2018-05-29", num: 1066664 },
        { date: "2018-05-30", num: 1066664 }
      ],
      bike_area_num: [
        {
          area: "青羊区",
          num: 123100
        },
        {
          area: "武侯区",
          num: 114005
        },
        {
          area: "金牛区",
          num: 102509
        },
        {
          area: "锦江区",
          num: 101115
        },
        {
          area: "成华区",
          num: 100210
        },
        {
          area: "双流区",
          num: 83520
        },
        {
          area: "龙泉驿区",
          num: 75130
        },
        {
          area: "郫都区",
          num: 64330
        },
        {
          area: "温江区",
          num: 56303
        },
        {
          area: "青白江区",
          num: 44330
        },
        {
          area: "新都区",
          num: 32303
        }
      ],
      bike_company_num: [
        { name: "摩拜", num: 48756 },
        { name: "ofo", num: 35555 },
        { name: "青桔", num: 25360 },
        { name: "黑拜", num: 15360 },
        { name: "1 步", num: 5360 },
        { name: "哈罗", num: 4360 },
        { name: "赳赳", num: 3360 },
        { name: "智聪", num: 1360 }
      ],
      bike_limit_num: [
        { name: "摩拜", num: 418756,limit:1000000 },
        { name: "ofo", num: 315555 ,limit:1000000 },
        { name: "青桔", num: 215360 ,limit:500000 },
        { name: "黑拜", num: 115360 ,limit:400000 },
        { name: "1 步", num: 15360 ,limit:400000 },
        { name: "哈罗", num: 34360 ,limit:400000 },
        { name: "赳赳", num: 23360 ,limit:300000 },
        { name: "智聪", num: 21360 ,limit:300000 }
      ]
    };
  },
  methods: {
    showBackground:function(){
      this.$router.push("/");
    },
    create_bike_company_num: function(data) {
      let myChart = echarts.init(document.getElementById("bike_company_num"));
      let legend= {
        orient: 'horizontal',
        textStyle:{color:'#eeeeee'},
        bottom:5,
        data:[]
        };
      let series = {
        type: "pie",
        label: {
            normal: {
                formatter: '{b}：{c}  {d}%  '
            }
        },
        radius: ['35%', '55%'],
        data: []
      };
      for (let i in data) {
          legend.data.push(data[i].name);
        let temp = { name: data[i].name, value: data[i].num };
        series.data.push(temp);
      }
      let option = {
        color: colorList,
        legend:legend,
        title: { text: "车辆投放品牌占比", textStyle: { color: "#eeeeee" } },
        tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
        },
        series: [series]
      };
      myChart.setOption(option);
    },  
    create_area_bike: function(data) {
      let myChart = echarts.init(document.getElementById("area_bike"));
      let xAxis = {
        splitLine: true,
        type: "category",
        data: [],
        axisLine: { show: true, lineStyle: { color: "#eeeeee" } },
        axisTick: { show: false },
        nameTextStyle: { color: "#eeeeee", width: 500 },
        axisLabel: { color: "#eeeeee" }
      };

      let temp_xAxis = {
        show: false,
        type: "category",
        data: []
      };

      let series = {
        name: "数量",
        type: "bar",
        itemStyle: {
          normal: {
            barBorderRadius: 2,
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
            shadowBlur: 10
          }
        },
        label: {
          normal: {
            show: true,
            position: "insideBottom"
          }
        },
        barWidth: 22,
        data: []
      };

      let max = 0;
      for (let i in data) {
        xAxis.data.push(data[i].area);
        temp_xAxis.data.push(data[i].area);
        series.data.push(data[i].num);
        if (data[i].num > max) {
          max = data[i].num;
        }
      }
      let temp_series = {
        // 辅助系列
        type: "bar",
        silent: true,
        xAxisIndex: 1,
        itemStyle: {
          normal: {
            barBorderRadius: 2,
            color: "#dddddddd"
          }
        },
        barWidth: 25,
        data: []
      };

      for (let j in data) {
        temp_series.data.push(max);
      }
      let option = {
        grid: { x: 20, x2: 20, y: 35, y2: 55 },
        title: {
          text: "单车区域投放数量排名",
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
        yAxis: {
          show: true,
          nameRotate: 270,
          type: "value",
          axisLine: { lineStyle: { color: "#eeeeee" } },
          axisTick: { show: false },
          splitLine: false,
          nameTextStyle: { show: false, color: "#eeeeee" },
          axisLabel: { show: false, color: "#eeeeee", rotate: 270 }
        },
        xAxis: [xAxis, temp_xAxis],
        series: [temp_series, series]
      };
      myChart.setOption(option);
    },
    create_date_bike: function(data){
      let myChart = echarts.init(document.getElementById("date_bike"));
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
        type: "line",
        itemStyle:{},
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
        title: { text: "投放总量时间分布", textStyle: { color: "#eeeeee" } },
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
    create_bike_limit_num :function(data){
      let myChart = echarts.init(document.getElementById("bike_limit_num"));
      let xAxis = {
        name: "数量",
        type: "category",
        data: [],
        axisLine: { lineStyle: { color: "#eeeeee" } },
        axisTick: { lineStyle: { color: "#eeeeee" } },
        nameTextStyle: { color: "#eeeeee" },
        axisLabel: { color: "#eeeeee" }
      };
      let toufang_series = {
        name: "投放量",
        type: "bar",
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let limit_series = {
        name: "阈值",
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
        xAxis.data.push(data[i].name);
        toufang_series.data.push(data[i].num);
        limit_series.data.push(data[i].limit);
      }

      let option = {
        color: colorList,
        title: { text: "车辆投放状态", textStyle: { color: "#eeeeee" } },
        grid:{y2:25},
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
          data: ["投放量", "阈值"],
          left: 200,
          textStyle: { color: "#eeeeee" }
        },
        xAxis: xAxis,
        yAxis: {
          name: "数量",
          type: "value",
          axisLine: { lineStyle: { color: "#eeeeee" } },
          axisTick: { lineStyle: { color: "#eeeeee" } },
          nameTextStyle: { color: "#eeeeee" },
          axisLabel: { color: "#eeeeee" }
        },
        series: [toufang_series, limit_series]
      };
      myChart.setOption(option);
    }
  },
  mounted: function() {
    this.create_area_bike(this.$data.bike_area_num);
    this.create_bike_company_num(this.$data.bike_company_num);
    this.create_date_bike(this.$data.bike_date_num);
    this.create_bike_limit_num(this.$data.bike_limit_num);
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
  background-color: rgba(0, 0, 0, 0.5);
}

.card-title {
  border-radius: 25px;
  margin: 15px;
  padding: 15px;
  box-shadow: 0px 0px 30px rgba(200, 255, 255, 0.75) inset;
}
</style>


