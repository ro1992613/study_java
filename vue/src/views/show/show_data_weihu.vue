<template  >
    <div class="bg">
        <Row style="height: 20%;">
            <div style="height:90%" class="card">
                <Row style="height:100%">
                    <Col span="6" style="height:100%">
                        <div v-on:click="showBackground()" style="height:100%;text-align: center;">
                            <span style="font-size: 25px;color:#bbb;"><Tag  color="#7bbfea"></Tag> 维修总量</span>
                            <span style="font-size: 50px;color:rgb(180, 255, 255);font-weight:bold;">
                                <count-to :end-val="95555"></count-to>
                            </span>
                        </div>
                    </Col>
                    <Col span="6" style="height:100%">
                        <div v-on:click="showBackground()" style="height:100%;text-align: center;">
                            <span style="font-size: 25px;color:#bbb;"><Tag  color="#2A8FBD"></Tag> 报废总量</span>
                            <span style="font-size: 50px;color:rgb(180, 255, 255);font-weight:bold;">
                                <count-to :end-val="75125"></count-to>
                            </span>
                        </div>
                    </Col>
                    <Col span="6" style="height:100%">
                        <div v-on:click="showBackground()" style="height:100%;text-align: center;">
                            <span style="font-size: 25px;color:#bbb;"><Tag  color="#4C556E"></Tag> 调度架次</span>
                            <span style="font-size: 50px;color:rgb(180, 255, 255);font-weight:bold;">
                                <count-to :end-val="239214"></count-to>
                            </span>
                        </div>
                    </Col>
                    <Col span="6" style="height:100%">
                        <div v-on:click="showBackground()" style="height:100%;text-align: center;">
                            <span style="font-size: 25px;color:#bbb;"><Tag  color="#DDF0ED"></Tag> 投诉总数</span>
                            <span style="font-size: 50px;color:rgb(200, 255, 255);font-weight:bold;">
                                <count-to :end-val="87457"></count-to>
                            </span>
                        </div>
                    </Col>
                </Row>
            </div>
        </Row>
        <Row style="height: 40%;">
            <Col span="24" style="height:100%">
                <div v-on:click="showBackground()" id="complaint_company_date_num" style="height:90%" class="card"></div>
            </Col>
        </Row>
        <Row style="height: 40%;">
            <Col span="12" style="height:100%">
                <div v-on:click="showBackground()" id="weixiu_company_date_num" style="height:90%" class="card"></div>
            </Col>
            <Col span="12" style="height:100%">
                <div v-on:click="showBackground()" id="diaodu_company_date_num" style="height:90%" class="card"></div>
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
      complaint_company_date_num: [
        {
          date: "2018-05-23",
          mobai: 604,
          ofo: 634,
          qingju: 124,
          heibai: 165,
          yibu: 54,
          hello: 218,
          jiujiu: 14,
          zhicong: 114
        },
        {
          date: "2018-05-24",
          mobai: 654,
          ofo: 544,
          qingju: 144,
          heibai: 215,
          yibu: 154,
          hello: 108,
          jiujiu: 114,
          zhicong: 94
        },
        {
          date: "2018-05-25",
          mobai: 504,
          ofo: 344,
          qingju: 524,
          heibai: 435,
          yibu: 134,
          hello: 198,
          jiujiu: 44,
          zhicong: 74
        },
        {
          date: "2018-05-26",
          mobai: 304,
          ofo: 234,
          qingju: 564,
          heibai: 155,
          yibu: 154,
          hello: 158,
          jiujiu: 74,
          zhicong: 24
        },
        {
          date: "2018-05-27",
          mobai: 664,
          ofo: 584,
          qingju: 214,
          heibai: 155,
          yibu: 114,
          hello: 128,
          jiujiu: 94,
          zhicong: 74
        },
        {
          date: "2018-05-28",
          mobai: 104,
          ofo: 564,
          qingju: 224,
          heibai: 215,
          yibu: 164,
          hello: 168,
          jiujiu: 14,
          zhicong: 34
        },
        {
          date: "2018-05-29",
          mobai: 404,
          ofo: 544,
          qingju: 144,
          heibai: 125,
          yibu: 174,
          hello: 118,
          jiujiu: 214,
          zhicong: 34
        },
        {
          date: "2018-05-30",
          mobai: 304,
          ofo: 364,
          qingju: 114,
          heibai: 105,
          yibu: 144,
          hello: 18,
          jiujiu: 94,
          zhicong: 134
        }
      ],
      weixiu_company_date_num: [
        {
          date: "2018-05-23",
          mobai: 604,
          ofo: 634,
          qingju: 124,
          heibai: 165,
          yibu: 54,
          hello: 218,
          jiujiu: 14,
          zhicong: 114
        },
        {
          date: "2018-05-24",
          mobai: 654,
          ofo: 544,
          qingju: 144,
          heibai: 215,
          yibu: 154,
          hello: 108,
          jiujiu: 114,
          zhicong: 94
        },
        {
          date: "2018-05-25",
          mobai: 504,
          ofo: 344,
          qingju: 524,
          heibai: 435,
          yibu: 134,
          hello: 198,
          jiujiu: 44,
          zhicong: 74
        },
        {
          date: "2018-05-26",
          mobai: 304,
          ofo: 234,
          qingju: 564,
          heibai: 155,
          yibu: 154,
          hello: 158,
          jiujiu: 74,
          zhicong: 24
        },
        {
          date: "2018-05-27",
          mobai: 664,
          ofo: 584,
          qingju: 214,
          heibai: 155,
          yibu: 114,
          hello: 128,
          jiujiu: 94,
          zhicong: 74
        },
        {
          date: "2018-05-28",
          mobai: 104,
          ofo: 564,
          qingju: 224,
          heibai: 215,
          yibu: 164,
          hello: 168,
          jiujiu: 14,
          zhicong: 34
        },
        {
          date: "2018-05-29",
          mobai: 404,
          ofo: 544,
          qingju: 144,
          heibai: 125,
          yibu: 174,
          hello: 118,
          jiujiu: 214,
          zhicong: 34
        },
        {
          date: "2018-05-30",
          mobai: 304,
          ofo: 364,
          qingju: 114,
          heibai: 105,
          yibu: 144,
          hello: 18,
          jiujiu: 94,
          zhicong: 134
        }
      ],
      diaodu_company_date_num: [
        {
          date: "2018-05-23",
          mobai: 604,
          ofo: 634,
          qingju: 124,
          heibai: 165,
          yibu: 54,
          hello: 218,
          jiujiu: 14,
          zhicong: 114
        },
        {
          date: "2018-05-24",
          mobai: 654,
          ofo: 544,
          qingju: 144,
          heibai: 215,
          yibu: 154,
          hello: 108,
          jiujiu: 114,
          zhicong: 94
        },
        {
          date: "2018-05-25",
          mobai: 504,
          ofo: 344,
          qingju: 524,
          heibai: 435,
          yibu: 134,
          hello: 198,
          jiujiu: 44,
          zhicong: 74
        },
        {
          date: "2018-05-26",
          mobai: 304,
          ofo: 234,
          qingju: 564,
          heibai: 155,
          yibu: 154,
          hello: 158,
          jiujiu: 74,
          zhicong: 24
        },
        {
          date: "2018-05-27",
          mobai: 664,
          ofo: 584,
          qingju: 214,
          heibai: 155,
          yibu: 114,
          hello: 128,
          jiujiu: 94,
          zhicong: 74
        },
        {
          date: "2018-05-28",
          mobai: 104,
          ofo: 564,
          qingju: 224,
          heibai: 215,
          yibu: 164,
          hello: 168,
          jiujiu: 14,
          zhicong: 34
        },
        {
          date: "2018-05-29",
          mobai: 404,
          ofo: 544,
          qingju: 144,
          heibai: 125,
          yibu: 174,
          hello: 118,
          jiujiu: 214,
          zhicong: 34
        },
        {
          date: "2018-05-30",
          mobai: 304,
          ofo: 364,
          qingju: 114,
          heibai: 105,
          yibu: 144,
          hello: 18,
          jiujiu: 94,
          zhicong: 134
        }
      ]
    };
  },
  methods: {
    showBackground:function(){
      this.$router.push("/");
    },
    create_complaint_company_date_num: function(data) {
      let myChart = echarts.init(
        document.getElementById("complaint_company_date_num")
      );
      let xAxis = {
        name: "数量",
        type: "category",
        data: [],
        axisLine: { lineStyle: { color: "#eeeeee" } },
        axisTick: { lineStyle: { color: "#eeeeee" } },
        nameTextStyle: { color: "#eeeeee" },
        axisLabel: { color: "#eeeeee" }
      };
      let mobai_series = {
        name: "摩拜",
        type: "bar",
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let ofo_series = {
        name: "ofo",
        type: "bar",
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let qingju_series = {
        name: "青桔",
        type: "bar",
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let heibai_series = {
        name: "黑拜",
        type: "bar",
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let yibu_series = {
        name: "1步",
        type: "bar",
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let hello_series = {
        name: "哈罗",
        type: "bar",
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let jiujiu_series = {
        name: "赳赳",
        type: "bar",
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let zhicong_series = {
        name: "智聪",
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
        mobai_series.data.push(data[i].mobai);
        ofo_series.data.push(data[i].ofo);
        qingju_series.data.push(data[i].qingju);
        heibai_series.data.push(data[i].heibai);
        yibu_series.data.push(data[i].yibu);
        hello_series.data.push(data[i].hello);
        jiujiu_series.data.push(data[i].jiujiu);
        zhicong_series.data.push(data[i].zhicong);
      }

      let option = {
        color: colorList,
        title: { text: "品牌投诉时间分布", textStyle: { color: "#eeeeee" } },
        grid: { x: 40, x2: 40, y2: 25 },
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
          data: ["摩拜", "ofo", "青桔", "黑拜", "1步", "哈罗", "赳赳", "智聪"],
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
        series: [
          mobai_series,
          ofo_series,
          qingju_series,
          heibai_series,
          yibu_series,
          hello_series,
          jiujiu_series,
          zhicong_series
        ]
      };
      myChart.setOption(option);
    },
    create_weixiu_company_date_num: function(data) {
      let myChart = echarts.init(
        document.getElementById("weixiu_company_date_num")
      );
      let xAxis = {
        name: "数量",
        type: "category",
        data: [],
        axisLine: { lineStyle: { color: "#eeeeee" } },
        axisTick: { lineStyle: { color: "#eeeeee" } },
        nameTextStyle: { color: "#eeeeee" },
        axisLabel: { color: "#eeeeee" }
      };
      let mobai_series = {
        name: "摩拜",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let ofo_series = {
        name: "ofo",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let qingju_series = {
        name: "青桔",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let heibai_series = {
        name: "黑拜",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let yibu_series = {
        name: "1步",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let hello_series = {
        name: "哈罗",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let jiujiu_series = {
        name: "赳赳",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let zhicong_series = {
        name: "智聪",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
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
        mobai_series.data.push(data[i].mobai);
        ofo_series.data.push(data[i].ofo);
        qingju_series.data.push(data[i].qingju);
        heibai_series.data.push(data[i].heibai);
        yibu_series.data.push(data[i].yibu);
        hello_series.data.push(data[i].hello);
        jiujiu_series.data.push(data[i].jiujiu);
        zhicong_series.data.push(data[i].zhicong);
      }

      let option = {
        color: colorList,
        title: { text: "品牌维修时间分布", textStyle: { color: "#eeeeee" } },
        grid: { x: 40, x2: 40, y2: 25 },
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
          data: ["摩拜", "ofo", "青桔", "黑拜", "1步", "哈罗", "赳赳", "智聪"],
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
        series: [
          mobai_series,
          ofo_series,
          qingju_series,
          heibai_series,
          yibu_series,
          hello_series,
          jiujiu_series,
          zhicong_series
        ]
      };
      myChart.setOption(option);
    },
    create_diaodu_company_date_num: function(data) {
      let myChart = echarts.init(
        document.getElementById("diaodu_company_date_num")
      );
      let xAxis = {
        name: "数量",
        type: "category",
        data: [],
        axisLine: { lineStyle: { color: "#eeeeee" } },
        axisTick: { lineStyle: { color: "#eeeeee" } },
        nameTextStyle: { color: "#eeeeee" },
        axisLabel: { color: "#eeeeee" }
      };
      let mobai_series = {
        name: "摩拜",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let ofo_series = {
        name: "ofo",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let qingju_series = {
        name: "青桔",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let heibai_series = {
        name: "黑拜",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let yibu_series = {
        name: "1步",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let hello_series = {
        name: "哈罗",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let jiujiu_series = {
        name: "赳赳",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
        label: {
          normal: {
            show: true,
            position: "top"
          }
        },
        data: []
      };
      let zhicong_series = {
        name: "智聪",
        type: "line",
        stack: "总量",
        areaStyle: { normal: {} },
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
        mobai_series.data.push(data[i].mobai);
        ofo_series.data.push(data[i].ofo);
        qingju_series.data.push(data[i].qingju);
        heibai_series.data.push(data[i].heibai);
        yibu_series.data.push(data[i].yibu);
        hello_series.data.push(data[i].hello);
        jiujiu_series.data.push(data[i].jiujiu);
        zhicong_series.data.push(data[i].zhicong);
      }

      let option = {
        color: colorList,
        title: { text: "品牌调度时间分布", textStyle: { color: "#eeeeee" } },
        grid: { x: 40, x2: 40, y2: 25 },
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
          data: ["摩拜", "ofo", "青桔", "黑拜", "1步", "哈罗", "赳赳", "智聪"],
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
        series: [
          mobai_series,
          ofo_series,
          qingju_series,
          heibai_series,
          yibu_series,
          hello_series,
          jiujiu_series,
          zhicong_series
        ]
      };
      myChart.setOption(option);
    }
  },
  mounted: function() {
    this.create_complaint_company_date_num(this.$data.complaint_company_date_num);
    this.create_weixiu_company_date_num(this.$data.weixiu_company_date_num);
    this.create_diaodu_company_date_num(this.$data.diaodu_company_date_num);
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


