<template>
    <Row>
        <Col span="8" class="box_card" v-for="item in company">
            <Card>
                <p slot="title" class="title"  v-on:click="click_card(item)">{{item.name}}</p>
                <p>
                    <Row>
                        <Col span="24">法定代表人：{{item.linkMan}}</Col>
                        <Col span="24">成立日期：{{item.createTime}}</Col>
                        <Col span="24">企业类型：{{item.type}}</Col>
                        <Col span="24">注册资本：{{item.ziben}}</Col>
                        <Col span="24">电话号码：{{item.linkPhone}}</Col>
                        <Col span="24">所在地址：{{item.adress}}</Col>
                    </Row>
                </p>
            </Card>
        </Col>
    </Row>
</template>
<script>
export default {
    components:{

    },
    data(){
        return {
            company:[
                {
                    id:"001",
                    name:"北京摩拜科技有限公司",
                    registerCode:"9111010833024530XJ",
                    taxCode:"11010833024530X",
                    organizationCode:"33024530-X",
                    linkMan:"胡玮炜",
                    status:"开业",
                    createTime:"2015-01-27",
                    timeLimit:"2015-01-27 至 2035-01-26",
                    checkTime:"2017-06-22",
                    ziben:"500万",
                    type:"有限责任公司(自然人投资或控股)",
                    hangye:"其他未列明专业技术服务业",
                    area:"北京市海淀区",
                    linkPhone:"010-5825****",
                    loginArea:"海淀分局",
                    adress:"北京市海淀区学院路甲5号2幢平房B南1231室",
                    info:"技术推广、技术转让、技术咨询、技术服务、通信终端设备的技术开发；销售通信终端设备及数据产品、电子产品、日用百货；自行车租赁；工程和技术研究与试验发展；计算机系统服务；基础软件服务；应用软件服务；软件开发；软件咨询；产品设计；模型设计，经济贸易咨询；企业策划；文化咨询；会议服务；投资咨询；电脑动画设计；项目投资；投资管理；资产管理；设计、制作、代理、发布广告；机械设备租赁（不含汽车租赁）。（企业依法自主选择经营项目，开展经营活动；依法须经批准的项目，经相关部门批准后依批准的内容开展经营活动；不得从事本市产业政策禁止和限制类项目的经营活动。）"                   
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
                    info:"技术开发、技术推广、技术转让、技术咨询、技术服务；销售自行开发后的产品；计算机系统服务；基础软件服务；应用软件服务；软件开发；软件咨询；公共关系服务；企业管理咨询；企业管理。（企业依法自主选择经营项目，开展经营活动；依法须经批准的项目，经相关部门批准后依批准的内容开展经营活动；不得从事本市产业政策禁止和限制类项目的经营活动。）"
                }
            ]
        };
    },
    methods:{
        click_card:function(company){
            this.$router.push({path:"/xinxichaxun/company_info_more",query:{id:company.id}});
        }
    }
}
</script>
<style scoped>
    .box_card{
        padding: 7px;
    }
    .title{
        color: #ef6421;
        font-size: 20px;
    }
</style>
