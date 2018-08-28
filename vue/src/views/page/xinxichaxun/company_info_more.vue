<template>
<div>
    <Row>
        <Col class="box_card">
            <a href="javascript:void(0);" v-on:click="goback()"><Icon type="arrow-return-left"></Icon>返回上级</a>
            <div style="height:1px;background-color: rgba(116, 116, 116, 0.63);margin-top: 10px;"></div>
        </Col>
    </Row>

     <Row class="box_card">
        <Col span="24" class="title">{{current_data.name}}</Col>
        <Col span="24"><br></Col>

        <Col span="12">注册号：{{current_data.registerCode}}</Col>
        <Col span="12">组织机构代码：{{current_data.organizationCode}}</Col>
        <Col span="24">税务登记证号：{{current_data.taxCode}}</Col>
        <Col span="24"><br></Col>

        <Col span="12">法定代表人：{{current_data.linkMan}}</Col>
        <Col span="12">经营状态：{{current_data.status}}</Col>
        <Col span="12">成立日期：{{current_data.createTime}}</Col>
        <Col span="12">营业期限：{{current_data.timeLimit}}</Col>
        <Col span="24">审核/年检日期：{{current_data.checkTime}}</Col>
        <Col span="24"><br></Col>

        <Col span="12">注册资本：{{current_data.ziben}}</Col>
        <Col span="12">企业类型：{{current_data.type}}</Col>
        <Col span="24">所属行业：{{current_data.hangye}}</Col>
        <Col span="24"><br></Col>

        <Col span="12">行政区划：{{current_data.area}}</Col>
        <Col span="12">电话号码：{{current_data.linkPhone}}</Col>
        <Col span="24">登记机关：{{current_data.loginArea}}</Col>
        <Col span="24">所在地址：{{current_data.adress}}</Col>
        <Col span="24">经营范围：{{current_data.info}}</Col>
        <Col span="24"><br></Col>
    </Row>
    <Row>
        <Col  class="box_card">
            <div class="line"></div>
        </Col>
    </Row>
    <Row>
        <Col span="12" class="box_card">
            <div id="person_date_num" style=" height:350px"></div>
        </Col>
        <Col span="12" class="box_card">
            <div id="person_area_num" style=" height:350px"></div>
        </Col>
    </Row>
    <Row>
        <Col  class="box_card">
            <div class="line"></div>
        </Col>
    </Row>
    <Row>
        
        <Col  class="box_card">
            <h3>报警事件</h3>
            <Table stripe :columns="table_columns" :data="current_data.alarm"></Table>
        </Col>
    </Row>
    <Row>
        <Col  class="box_card">
            <div class="line"></div>
        </Col>
    </Row>
    <Row>
        <Col span="12" class="box_card">
            <div id="bike_date_num" style=" height:350px"></div>
        </Col>
        <Col span="12" class="box_card">
            <div id="bike_area_num" style=" height:350px"></div>
        </Col>
    </Row>
    <Row>
        <Col  class="box_card">
            <div class="line"></div>
        </Col>
    </Row>
    
</div>

</template>
<script>
import echarts from "echarts";

export default {
  data() {
    return {
        table_columns:[
            {
                title: '区域编号',
                key: 'id'
            },
            {
                title: '区域名称',
                key: 'name'
            },
            {
                title: '区域类型',
                key: 'type'
            },
            {
                title: '车辆使用率',
                key: 'vehicleUtilization'
            },
            {
                title: '报警原因',
                key: 'reason'
            },
            {
                title: '企业名称',
                key: 'enterprise'
            }
        ],
      company: [
        {
          id: "001",
          name: "北京摩拜科技有限公司",
          registerCode: "9111010833024530XJ",
          taxCode: "11010833024530X",
          organizationCode: "33024530-X",
          linkMan: "胡玮炜",
          status: "开业",
          createTime: "2015-01-27",
          timeLimit: "2015-01-27 至 2035-01-26",
          checkTime: "2017-06-22",
          ziben: "500万",
          type: "有限责任公司(自然人投资或控股)",
          hangye: "其他未列明专业技术服务业",
          area: "北京市海淀区",
          linkPhone: "010-5825****",
          loginArea: "海淀分局",
          adress: "北京市海淀区学院路甲5号2幢平房B南1231室",
          info:
            "技术推广、技术转让、技术咨询、技术服务、通信终端设备的技术开发；销售通信终端设备及数据产品、电子产品、日用百货；自行车租赁；工程和技术研究与试验发展；计算机系统服务；基础软件服务；应用软件服务；软件开发；软件咨询；产品设计；模型设计，经济贸易咨询；企业策划；文化咨询；会议服务；投资咨询；电脑动画设计；项目投资；投资管理；资产管理；设计、制作、代理、发布广告；机械设备租赁（不含汽车租赁）。（企业依法自主选择经营项目，开展经营活动；依法须经批准的项目，经相关部门批准后依批准的内容开展经营活动；不得从事本市产业政策禁止和限制类项目的经营活动。）",
          person_date_num: [
            {
              date: "2018-05-12",
              num: 200
            },
            {
              date: "2018-05-13",
              num: 205
            },
            {
              date: "2018-05-14",
              num: 209
            },
            {
              date: "2018-05-15",
              num: 215
            },
            {
              date: "2018-05-16",
              num: 210
            },
            {
              date: "2018-05-17",
              num: 230
            }
          ],
          person_area_num: [
            {
              area: "青羊区",
              num: 700
            },
            {
              area: "武侯区",
              num: 805
            },
            {
              area: "金牛区",
              num: 509
            },
            {
              area: "锦江区",
              num: 115
            },
            {
              area: "成华区",
              num: 410
            },
            {
              area: "双流区",
              num: 250
            },
            {
              area: "龙泉驿区",
              num: 530
            },
            {
              area: "郫都区",
              num: 330
            },
            {
              area: "温江区",
              num: 530
            }
          ],
          bike_date_num: [
            {
              date: "2018-05-12",
              num: 1501
            },
            {
              date: "2018-05-13",
              num: 2105
            },
            {
              date: "2018-05-14",
              num: 2209
            },
            {
              date: "2018-05-15",
              num: 2151
            },
            {
              date: "2018-05-16",
              num: 2210
            },
            {
              date: "2018-05-17",
              num: 2400
            }
          ],
          bike_area_num: [
            {
              area: "青羊区",
              num: 3100
            },
            {
              area: "武侯区",
              num: 8005
            },
            {
              area: "金牛区",
              num: 12509
            },
            {
              area: "锦江区",
              num: 12115
            },
            {
              area: "成华区",
              num: 4210
            },
            {
              area: "双流区",
              num: 2520
            },
            {
              area: "龙泉驿区",
              num: 5130
            },
            {
              area: "郫都区",
              num: 4330
            },
            {
              area: "温江区",
              num: 5303
            }
          ],
          alarm:[
                {id:'1000290',name:'桂溪街道001',vehicleUtilization:'32%',type:'停车区域',reason:'车辆停放数量超过限制数',enterprise:'摩拜'},
                {id:'1000290',name:'桂溪街道001',vehicleUtilization:'32%',type:'停车区域',reason:'车辆停放数量超过限制数',enterprise:'摩拜'},
                {id:'1000290',name:'桂溪街道001',vehicleUtilization:'32%',type:'停车区域',reason:'车辆停放数量超过限制数',enterprise:'摩拜'},
                {id:'1000290',name:'桂溪街道001',vehicleUtilization:'32%',type:'停车区域',reason:'车辆停放数量超过限制数',enterprise:'摩拜'}

          ]
        },
        {
          id:"002",
          name:"北京拜克洛克科技有限公司",
          registerCode:"91110108351593485G",
          taxCode:"110108351593485",
          organizationCode:"35159348-5",
          linkMan:"戴威",
          status:"开业",
          createTime:"2015-08-06",
          timeLimit:"2015-08-06 至 2065-08-05",
          checkTime:"2017-04-11",
          ziben:"38.5万(元)",
          type:"其他有限责任公司",
          hangye:"其他科技推广和应用服务业",
          area:"北京市海淀区",
          linkPhone:"010-6265****",
          loginArea:"海淀分局",
          adress:"北京市海淀区北四环西路58号11层1102室",
          info:"技术开发、技术推广、技术转让、技术咨询、技术服务；销售自行开发后的产品；计算机系统服务；基础软件服务；应用软件服务；软件开发；软件咨询；公共关系服务；企业管理咨询；企业管理。（企业依法自主选择经营项目，开展经营活动；依法须经批准的项目，经相关部门批准后依批准的内容开展经营活动；不得从事本市产业政策禁止和限制类项目的经营活动。）",        person_date_num: [
            {
              date: "2018-05-12",
              num: 300
            },
            {
              date: "2018-05-13",
              num: 405
            },
            {
              date: "2018-05-14",
              num: 309
            },
            {
              date: "2018-05-15",
              num: 515
            },
            {
              date: "2018-05-16",
              num: 410
            },
            {
              date: "2018-05-17",
              num: 430
            }
          ],
          person_area_num: [
            {
              area: "青羊区",
              num: 300
            },
            {
              area: "武侯区",
              num: 905
            },
            {
              area: "金牛区",
              num: 309
            },
            {
              area: "锦江区",
              num: 215
            },
            {
              area: "成华区",
              num: 610
            },
            {
              area: "双流区",
              num: 750
            },
            {
              area: "龙泉驿区",
              num: 830
            },
            {
              area: "郫都区",
              num: 830
            },
            {
              area: "温江区",
              num: 130
            }
          ],
          bike_date_num: [
            {
              date: "2018-05-12",
              num: 1201
            },
            {
              date: "2018-05-13",
              num: 4105
            },
            {
              date: "2018-05-14",
              num: 2209
            },
            {
              date: "2018-05-15",
              num: 1151
            },
            {
              date: "2018-05-16",
              num: 2410
            },
            {
              date: "2018-05-17",
              num: 2440
            }
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
              num: 5303
            }
          ],
          alarm:[
                {id:'1000290',name:'桂溪街道001',vehicleUtilization:'32%',type:'停车区域',reason:'车辆停放数量超过限制数',enterprise:'ofo'},
                {id:'1000290',name:'桂溪街道001',vehicleUtilization:'32%',type:'停车区域',reason:'车辆停放数量超过限制数',enterprise:'ofo'},
                {id:'1000290',name:'桂溪街道001',vehicleUtilization:'32%',type:'停车区域',reason:'车辆停放数量超过限制数',enterprise:'ofo'},
                {id:'1000290',name:'桂溪街道001',vehicleUtilization:'32%',type:'停车区域',reason:'车辆停放数量超过限制数',enterprise:'ofo'}

          ]
        }
      ],
      current_data: ""
    };
  },
  methods: {
    goback:function(){
      this.$router.go(-1);
    },
    create_person_date_num: function(data) {
      let myChart = echarts.init(document.getElementById("person_date_num"));
      let xAxis = { name: "人数", type: "category", data: [] };
      let series = {
        areaStyle: { normal: {} },
        name: "人数",
        type: "line",
        data: []
      };
      for (let i in data) {
        xAxis.data.push(data[i].date);
        series.data.push(data[i].num);
      }
      let option = {
        title: { text: "运维人员时间分布" },
        tooltip: {},
        xAxis: xAxis,
        yAxis: { name: "日期", type: "value" },
        series: [series]
      };
      myChart.setOption(option);
    },
    create_person_area_num: function(data) {
      let myChart = echarts.init(document.getElementById("person_area_num"));
      let series = {
        areaStyle: { normal: {} },
        type: "pie",
        data: []
      };
      for (let i in data) {
        let temp = { name: data[i].area, value: data[i].num };
        series.data.push(temp);
      }
      let option = {
        title: { text: "运维人员区域分布" },
        tooltip: {},
         legend: {
            type: 'scroll',
            bottom: 10
        },
        series: [series]
      };
      myChart.setOption(option);
    },
    create_bike_date_num: function(data) {
      let myChart = echarts.init(document.getElementById("bike_date_num"));
      let xAxis = { name: "投放数量", type: "category", data: [] };
      let series = {
        areaStyle: { normal: {} },
        name: "投放数量",
        type: "line",
        data: []
      };
      for (let i in data) {
        xAxis.data.push(data[i].date);
        series.data.push(data[i].num);
      }
      let option = {
        title: { text: "车辆投放时间分布" },
        tooltip: {},
        xAxis: xAxis,
        yAxis: { name: "日期", type: "value" },
        series: [series]
      };
      myChart.setOption(option);
    },
    create_bike_area_num: function(data) {
      let myChart = echarts.init(document.getElementById("bike_area_num"));
      let series = {
        areaStyle: { normal: {} },
        type: "pie",
        data: []
      };
      for (let i in data) {
        let temp = { name: data[i].area, value: data[i].num };
        series.data.push(temp);
      }
      let option = {
        title: { text: "车辆投放区域分布" },
        tooltip: {},
         legend: {
            type: 'scroll',
            bottom: 10
        },
        series: [series]
      };
      myChart.setOption(option);
    }
  },
  mounted: function() {
    for (let i in this.$data.company) {
      if (this.$data.company[i].id == this.$route.query.id) {
        this.$data.current_data = this.$data.company[i];
      }
    }
    let company = this.$data.current_data;
    this.create_person_date_num(company.person_date_num);
    this.create_person_area_num(company.person_area_num);
    this.create_bike_date_num(company.bike_date_num);
    this.create_bike_area_num(company.bike_area_num);
  }
};
</script>

<style scoped>
.card-height {
  height: 500px;
  overflow: scroll;
}
.card-height::-webkit-scrollbar {
  display: none;
}
.box_card {
  padding: 25px;
}

.line{
    height:1px;
    background-color: rgba(116, 116, 116, 0.63)
}
.title{
        color: #ef6421;
        font-size: 20px;
    }
</style>


