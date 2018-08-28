<template>
    <div>
        <Row class="box_card">
            <Col span="12" class="box_card">
                <div id="complaint_date_num" style=" height:350px"></div>
            </Col>
            <Col span="12" class="box_card">
                <div id="complaint_status_num" style=" height:350px"></div>
            </Col>
        </Row>
        <Row class="box_card">
            <Card>
                <p slot="title">
                    <Icon type="code-working"></Icon>
                    最新事件
                </p>
                <a href="#" slot="extra"  v-on:click="gosomewhere()">
                    <Icon type="ios-plus-empty"></Icon>
                    查看更多
                </a>
                <Table stripe :columns="latest_complaint_column" :data="latest_complaint"></Table>
            </Card>
            
        </Row>
    </div>
</template>
<script>
import echarts from "echarts";
export default {
  data() {
    return {
      complaint_date_num: [
        { date: "2018-05-23", mobai: 64, ofo: 42, etc: 82 },
        { date: "2018-05-24", mobai: 35, ofo: 62, etc: 92 },
        { date: "2018-05-25", mobai: 62, ofo: 92, etc: 72 },
        { date: "2018-05-26", mobai: 142, ofo: 172, etc: 52 },
        { date: "2018-05-27", mobai: 81, ofo: 12, etc: 32 },
        { date: "2018-05-28", mobai: 36, ofo: 42, etc: 122 },
        { date: "2018-05-29", mobai: 24, ofo: 52, etc: 132 }
      ],
      complaint_status_num: [
        { name: "摩拜", complete: 64, complaint: 42, deal: 82 },
        { name: "ofo", complete: 35, complaint: 62, deal: 92 },
        { name: "etc", complete: 142, complaint: 172, deal: 52 }
      ],
      latest_complaint: [
        {
          id: "025",
          company: "摩拜",
          area: "青羊区",
          time: "2018-05-28 16:30:05",
          status: "未处理"
        },
        {
          id: "025",
          company: "ofo",
          area: "金牛区",
          time: "2018-05-28 14:32:25",
          status: "未处理"
        },
        {
          id: "025",
          company: "ofo",
          area: "锦江区",
          time: "2018-05-28 13:31:14",
          status: "未处理"
        },
        {
          id: "025",
          company: "摩拜",
          area: "青羊区",
          time: "2018-05-28 10:05:05",
          status: "未处理"
        },
        {
          id: "025",
          company: "摩拜",
          area: "锦江区",
          time: "2018-05-28 10:0:52",
          status: "未处理"
        }
      ],
      latest_complaint_column: [
        { title: "编号", key: "id" },
        { title: "公司", key: "company" },
        { title: "区域", key: "area" },
        { title: "时间", key: "time" },
        { title: "状态", key: "status" }
      ]
    };
  },
  methods: {
    gosomewhere() {
      this.$router.push("/richangjianguan/complaint_more");
    },
    create_complaint_date_num: function(data) {
      let myChart = echarts.init(document.getElementById("complaint_date_num"));
      let xAxis = { name: "数量", type: "category", data: [] };
      let mobai_series = {
        areaStyle: { normal: {} },
        name: "摩拜",
        type: "line",
        stack: "总量",
        data: []
      };
      let ofo_series = {
        areaStyle: { normal: {} },
        name: "ofo",
        type: "line",
        stack: "总量",
        data: []
      };
      let etc_series = {
        areaStyle: { normal: {} },
        name: "其它",
        type: "line",
        stack: "总量",
        data: []
      };
      for (let i in data) {
        xAxis.data.push(data[i].date);
        ofo_series.data.push(data[i].ofo);
        mobai_series.data.push(data[i].mobai);
        etc_series.data.push(data[i].etc);
      }

      let option = {
        title: { text: "投诉事件时间分布" },
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
          data: ["摩拜", "ofo", "其它"]
        },
        xAxis: xAxis,
        yAxis: { name: "日期", type: "value" },
        series: [mobai_series, ofo_series, etc_series]
      };
      myChart.setOption(option);
    },
    create_person_area_num: function(data) {
      let myChart = echarts.init(
        document.getElementById("complaint_status_num")
      );
      let series = {
        highlightPolicy: "ancestor",
        type: "sunburst",
        data: []
      };
      for (let i in data) {
        let temp = { name: data[i].name };
        let child = [];
        child.push({ name: "完成", value: data[i].complete });
        child.push({ name: "处理中", value: data[i].deal });
        child.push({ name: "未处理", value: data[i].complaint });
        temp.children = child;
        series.data.push(temp);
      }
      let option = {
        title: { text: "投诉事件品牌分布" },
        tooltip: {},
        series: [series]
      };
      myChart.setOption(option);
    }
  },
  mounted: function() {
    this.create_complaint_date_num(this.$data.complaint_date_num);
    this.create_person_area_num(this.$data.complaint_status_num);
  }
};
</script>
<style scoped>
.box_card {
  padding: 15px;
}
.line {
  height: 1px;
  background-color: rgba(116, 116, 116, 0.63);
}
</style>
