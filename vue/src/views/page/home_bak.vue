<template>  
  <div>  
    <Row>
      <Col class="box_card" span="24">
        <Card>
          <HomeBikeDate></HomeBikeDate>
        </Card>
      </Col>
      <Col class="box_card" span="16">
        <Card>
          <HomeMap></HomeMap>
        </Card>
        
      </Col>
      <Col class="box_card"  span="8">
        <Card>    
          <p  style=" height:500px" id="bike_company"></p>
        </Card>
      </Col>
      
    </Row>
    
  </div>  
</template>  
<script>
import HomeMap from "./components/home_map.vue";
import HomeBikeDate from "./components/home_bike_date.vue";
import echarts from "echarts";
export default {
  components:{
    HomeMap,
    HomeBikeDate
  },
  data(){
    return {
      bike_company:[
        {company:"摩拜",num:"33654"},
        {company:"ofo",num:"42555"},
        {company:"其它",num:"23654"}
      ]
    }
  },
  methods:{
    create_bike_company:function(data){
      let myChart = echarts.init(document.getElementById("bike_company"));
      let series = {
        areaStyle: { normal: {} },
        type: "pie",
        data: []
      };
      for (let i in data) {
        let temp = { name: data[i].company, value: data[i].num };
        series.data.push(temp);
      }
      let option = {
        title: { text: "共享单车品牌数量分布" },
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
  mounted:function(){
    this.create_bike_company(this.$data.bike_company);
  }
}
</script>
<style scoped>
  .box_card{
    padding: 5px;;
  }
</style>

