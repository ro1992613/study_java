<template>
    <div>
        <Row class="box_card">
            <Col span="3">投放总数：<Tag color="green"><CountTo :endVal="1025564"/> </Tag></Col>
            <Col span="2">正常：<Tag color="blue"><CountTo :endVal="900064"/> </Tag></Col>
            <Col span="2">报废：<Tag color="red"><CountTo :endVal="25560"/> </Tag></Col>
            <Col span="2">维修：<Tag color="yellow"><CountTo :endVal="100000"/> </Tag></Col>
        </Row>
        <Row class="box_card">
            <Col span="3" style="padding-right:10px">
                <Input placeholder="输入编号"></Input>
            </Col>
            <Col span="3" style="padding-right:10px">
                <Select placeholder="选择公司">
                    <Option v-for="item in companyList" :value="item.value" :key="item.value">{{ item.label }}</Option>
                </Select>
            </Col>
            <Col span="3" style="padding-right:10px">
                <Select placeholder="选择单车状态">
                    <Option v-for="item in statusList" :value="item.value" :key="item.value">{{ item.label }}</Option>
                </Select>
            </Col>
            <Col span="3" style="padding-right:10px">
                <Select placeholder="选择区域">
                    <Option v-for="item in areaList" :value="item.value" :key="item.value">{{ item.label }}</Option>
                </Select>
            </Col>
            <Col span="4" style="padding-right:10px">
                <Button type="primary" icon="ios-search">查询</Button>
            </Col>
        </Row>
        <Row class="box_card">
            <Col span="24">
                <Table stripe :columns="bike_colunm" :data="bike_data"></Table>
            </Col>
        </Row>
        <Row class="box_card">
            <Page :total="pageCount" :current="pageCurrent" :page-size="pageSize" size="small" show-total show-elevator></Page>
        </Row>
    </div>
</template>
<script>
import CountTo from '../components/CountTo.vue';

export default {
    components: {
        CountTo
    },
    data(){
        return {
            pageSize:20,
            pageCount:100,
            pageCurrent:1,
            companyList:[
                {
                    value: '所有',
                    label: '所有'
                },
                {
                    value: 'ofo',
                    label: 'ofo'
                },
                {
                    value: '摩拜',
                    label: '摩拜'
                }
            ],
            statusList:[
                {
                    value: '全部',
                    label: '全部'
                },
                {
                    value: '正常',
                    label: '正常'
                },
                {
                    value: '维修',
                    label: '维修'
                },
                {
                    value: '报废',
                    label: '报废'
                }
            ],
            areaList:[
                {
                    value: '所有',
                    label: '所有'
                },
                {
                    value: '武侯区',
                    label: '武侯区'
                },
                {
                    value: '成华区',
                    label: '成华区'
                },
                {
                    value: '锦江区',
                    label: '锦江区'
                },
                {
                    value: '青羊区',
                    label: '青羊区'
                },
                {
                    value: '金牛区',
                    label: '金牛区'
                },
                {
                    value: '龙泉驿区',
                    label: '龙泉驿区'
                },
                {
                    value: '双流区',
                    label: '双流区'
                },
                {
                    value: '温江区',
                    label: '温江区'
                },
                {
                    value: '郫都区',
                    label: '郫都区'
                },
                {
                    value: '青白江区',
                    label: '青白江区'
                },
                {
                    value: '新都区',
                    label: '新都区'
                }
            ],
            bike_colunm:[
                {
                    title: '车辆编号',
                    key: 'id'
                },
                {
                    title: '公司',
                    key: 'company'
                },
                {
                    title: '区域',
                    key: 'area'
                },
                {
                    title: '投放时间',
                    key: 'start_time'
                },
                {
                    title: '维修时间',
                    key: 'check_time'
                },
                {
                    title: '报废时间',
                    key: 'end_time'
                },
                {
                    title: '车辆状态',
                    align: 'center',
                    render:(h,params)=>{
                        let type='info';
                        if(params.row.status && params.row.status=='维修'){
                            type='warning';
                        }
                        if(params.row.status && params.row.status=='报废'){
                            type='error';
                        }
                        return h('Button',{
                            props: {
                                type: type,
                                size: 'small',
                                shape:'circle'
                            }
                        },params.row.status);
                    }
                },
                {
                    title: '里程',
                    key: 'mileage'
                }
            ],
            bike_data:[
                {id:"0001",company:"摩拜",area:"锦江区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"维修",mileage:"280"},
                {id:"0002",company:"ofo",area:"金牛区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"正常",mileage:"280"},
                {id:"0003",company:"ofo",area:"成华区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"2018-06-23",status:"报废",mileage:"280"},
                {id:"0004",company:"摩拜",area:"青羊区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"正常",mileage:"280"},
                {id:"0005",company:"摩拜",area:"锦江区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"2018-06-23",status:"报废",mileage:"280"},
                {id:"0006",company:"ofo",area:"锦江区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"正常",mileage:"280"},
                {id:"0007",company:"摩拜",area:"锦江区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"正常",mileage:"280"},
                {id:"0008",company:"ofo",area:"成华区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"2018-06-23",status:"报废",mileage:"280"},
                {id:"0009",company:"摩拜",area:"成华区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"2018-06-23",status:"报废",mileage:"280"},
                {id:"0010",company:"ofo",area:"金牛区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"维修",mileage:"280"},
                {id:"0011",company:"摩拜",area:"青羊区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"正常",mileage:"280"},
                {id:"0012",company:"ofo",area:"金牛区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"维修",mileage:"280"},
                {id:"0013",company:"ofo",area:"成华区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"维修",mileage:"280"},
                {id:"0014",company:"摩拜",area:"金牛区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"2018-06-23",status:"报废",mileage:"280"},
                {id:"0015",company:"ofo",area:"锦江区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"正常",mileage:"280"},
                {id:"0016",company:"ofo",area:"锦江区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"2018-06-23",status:"报废",mileage:"280"},
                {id:"0017",company:"摩拜",area:"青羊区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"正常",mileage:"280"},
                {id:"0018",company:"摩拜",area:"锦江区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"维修",mileage:"280"},
                {id:"0019",company:"ofo",area:"锦江区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"正常",mileage:"280"},
                {id:"0020",company:"摩拜",area:"金牛区",start_time:"2018-05-23",check_time:"2018-06-23",end_time:"",status:"维修",mileage:"280"},
            ]
        }
    }
}
</script>
<style scoped>
    .box_card{
        padding: 15px;
    }
    .line{
        height:1px;
        background-color: rgba(116, 116, 116, 0.63)
    }
</style>
