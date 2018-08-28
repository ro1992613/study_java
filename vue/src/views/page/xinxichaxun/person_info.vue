<template>
    <div>
        <Row class="box_card">
            <Col span="4">维护人员总数：<Tag color="green"><CountTo :endVal="1025564"/> </Tag></Col>
            <Col span="4">处理事件总数：<Tag color="blue"><CountTo :endVal="900064"/> </Tag></Col>
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
                <Select placeholder="选择人员状态">
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
                <Table stripe :columns="person_colunm" :data="person_data"></Table>
            </Col>
        </Row>
        <Row class="box_card">
            <Page :total="pageCount" :current="pageCurrent" :page-size="pageSize" size="small" show-total show-elevator></Page>
        </Row>
    </div>
</template>
<script>
import CountTo from '../components/CountTo.vue';
import personModal from './person_info_modal.vue';

export default {
    components: {
        CountTo,
        personModal
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
                    value: '在线',
                    label: '在线'
                },
                {
                    value: '离线',
                    label: '离线'
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
            person_colunm:[
                {
                    title: '人员编号',
                    key: 'id'
                },
                {
                    title: '所属公司',
                    key: 'company'
                },
                {
                    title: '负责区域',
                    key: 'area'
                },
                {
                    title: '姓名',
                    key: 'name'
                },
                {
                    title: '在线状态',
                    align: 'center',
                    render:(h,params)=>{
                        let type='info';
                        if(params.row.status && params.row.status=='离线'){
                            type='warning';
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
                    title: '详情',
                    align: 'center',
                    render:(h,params)=>{
                        let type='info';
                        return h('Button',{
                            props: {
                                type: type
                            },
                            on: {
                                click:()=>{
                                    this.$Modal.info({
                                        title:"人员信息",
                                        width:"438px",
                                        okText:"关闭",
                                        onOk:()=>{
                                        },
                                        render: (h) => {
                                            return h(personModal,{
                                                props: {
                                                    prop_value: params.row
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        },"查看详情");
                    }
                }
            ],
            person_data:[
                {id:"0001",company:"摩拜",area:"锦江区",name:"王大牛",phone:"13566666666",status:"离线",sex:"男",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510111121111111111"},
                {id:"0002",company:"ofo",area:"成华区",name:"王大力",phone:"13566666666",status:"在线",sex:"男",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510111113111111111"},
                {id:"0003",company:"摩拜",area:"金牛区",name:"李大牛",phone:"13566666666",status:"在线",sex:"女",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510111141111111111"},
                {id:"0004",company:"ofo",area:"武侯区",name:"舜大牛",phone:"13566666666",status:"离线",sex:"男",age:"36",time:"2017-05-31",event_num:"3654",card_id:"51011116111111111"},
                {id:"0005",company:"摩拜",area:"青羊区",name:"和大牛",phone:"13566666666",status:"在线",sex:"女",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510111711111111111"},
                {id:"0006",company:"摩拜",area:"武侯区",name:"王牛",phone:"13566666666",status:"在线",sex:"男",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510111118111111111"},
                {id:"0007",company:"ofo",area:"成华区",name:"单牛",phone:"13566666666",status:"离线",sex:"女",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510111116611111111"},
                {id:"0008",company:"ofo",area:"武侯区",name:"王五",phone:"13566666666",status:"在线",sex:"女",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510111117811111111"},
                {id:"0009",company:"ofo",area:"青羊区",name:"大牛",phone:"13566666666",status:"在线",sex:"男",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510111118511111111"},
                {id:"0010",company:"摩拜",area:"成华区",name:"大王牛",phone:"13566666666",status:"离线",sex:"女",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510114511111111111"},
                {id:"0011",company:"摩拜",area:"武侯区",name:"和大牛",phone:"13566666666",status:"在线",sex:"男",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510111451111111111"},
                {id:"0012",company:"ofo",area:"金牛区",name:"孙大牛",phone:"13566666666",status:"在线",sex:"男",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510111135111111111"},
                {id:"0013",company:"ofo",area:"青羊区",name:"白牛",phone:"13566666666",status:"离线",sex:"男",age:"36",time:"2017-05-31",event_num:"3654",card_id:"5101113111111111"},
                {id:"0014",company:"ofo",area:"武侯区",name:"月牛",phone:"13566666666",status:"在线",sex:"男",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510113411111111111"},
                {id:"0015",company:"摩拜",area:"青羊区",name:"钱大牛",phone:"13566666666",status:"离线",sex:"男",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510121111111111111"},
                {id:"0016",company:"ofo",area:"武侯区",name:"王二",phone:"13566666666",status:"在线",sex:"女",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510111131111111111"},
                {id:"0017",company:"摩拜",area:"青羊区",name:"王十三",phone:"13566666666",status:"离线",sex:"男",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510171111111111111"},
                {id:"0018",company:"ofo",area:"武侯区",name:"王妞",phone:"13566666666",status:"离线",sex:"男",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510111181111111111"},
                {id:"0019",company:"ofo",area:"成华区",name:"针牛",phone:"13566666666",status:"在线",sex:"男",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510111191111111111"},
                {id:"0020",company:"摩拜",area:"锦江区",name:"李淑华",phone:"13566666666",status:"在线",sex:"女",age:"36",time:"2017-05-31",event_num:"3654",card_id:"510141111111111111"}
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
