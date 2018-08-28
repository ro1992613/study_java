<template>
    <Row>
        <Col span="24">
            <Card style="width:auto">
                <p slot="title">
                    <Icon type="ios-film-outline"></Icon>
                    用户
                </p>
                <a href="#" v-on:click="addUser" slot="extra">
                    <Icon type="android-add"></Icon>
                    添加
                </a>
                <Row>
                    <Table height="600" :columns="userColumns" :data="user"></Table>
                </Row>
                <Row style="padding-top:10px;">
                     <Page :total="pageCount" :current="pageCurrent" :page-size="pageSize" size="small" show-total show-elevator></Page>
                </Row>
            </Card>
        </Col>
    </Row>
</template>
<script>
import editUser from './components/edit_user.vue';
import userRole from './components/user_role.vue';
import columnCell from './components/column_cell.vue';

export default {
    components:{
        editUser,
        userRole,
        columnCell
    },
    data () {
        return {
            user:[{
                    name: 'John Brown',
                    age: 18,
                    state:'on',
                    address: 'New York No. 1 Lake Park',
                    date: '2016-10-03',
                    is_edit:false
                },
                {
                    name: 'Jim Green',
                    age: 24,
                    state:'off',
                    address: 'London No. 1 Lake Park',
                    date: '2016-10-01',
                    is_edit:false
                }],
            pageSize:20,
            pageCount:100,
            pageCurrent:1,
            userColumns: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center'
                },
                {
                    title: '用户名',
                    render:(h,params)=>{
                        return h(columnCell,{
                            props: {
                                prop_value:params.row.name,
                                is_edit:params.row.is_edit
                            },
                            on:{
                                recieveData:function(v){
                                    params.row.name=v;
                                }
                            }
                        });
                    }
                },
                {
                    title: '年龄',
                    render:(h,params)=>{
                        return h(columnCell,{
                            props: {
                                prop_value:params.row.age,
                                is_edit:params.row.is_edit
                            },
                            on:{
                                recieveData:function(v){
                                    params.row.age=v;
                                }
                            }
                        });
                    }
                },
                {
                    title: '地址',
                    render:(h,params)=>{
                        return h(columnCell,{
                            props: {
                                prop_value:params.row.address,
                                is_edit:params.row.is_edit
                            },
                            on:{
                                recieveData:function(v){
                                    params.row.address=v;
                                }
                            }
                        });
                    }
                },
                {
                    title: '状态',
                    align: 'center',
                    render:(h,params)=>{
                        let type='success';
                        if(params.row.state && params.row.state=='off'){
                            type='error';
                        }
                        return h('Button',{
                            props: {
                                type: type,
                                size: 'small',
                                shape:'circle'
                            },
                            on:{
                                click:()=>{
                                    if(params.row.state=='off'){
                                        params.row.state='on';
                                    }else{
                                        params.row.state='off';
                                    }
                                }
                            }
                        },params.row.state);
                    }
                },
                {
                    title: '操作',
                    key: 'action',
                    width: 250,
                    align: 'center',
                    render: (h, params) => {
                        if(!params.row.is_edit){
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            params.row.is_edit=true;
                                        }
                                    }
                                }, '编辑'),
                                h('Button', {
                                    props: {
                                        type: 'success',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            let selectedRoles=[];
                                            this.$Modal.confirm({
                                                title:'选择角色',
                                                onOk:()=>{
                                                    console.log(selectedRoles);
                                                },
                                                render: (h) => {
                                                    return h(userRole,{
                                                        props: {
                                                            prop_value: [1,2]
                                                        },
                                                        on:{
                                                            recieveSelectedRoles:(v)=>{
                                                                selectedRoles=v;
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    }
                                }, '授权'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.$data.user.splice(params.index,1);
                                        }
                                    }
                                }, '删除')
                            ]);
                        }else{
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'success',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            params.row.is_edit=false;
                                            let success=false;

                                        }
                                    }
                                }, '保存'),
                                h('Button', {
                                    props: {
                                        type: 'success',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            let selectedRoles=[];
                                            this.$Modal.confirm({
                                                title:'选择角色',
                                                onOk:()=>{
                                                    console.log(selectedRoles);
                                                },
                                                render: (h) => {
                                                    return h(userRole,{
                                                        props: {
                                                            prop_value: [1,2]
                                                        },
                                                        on:{
                                                            recieveSelectedRoles:(v)=>{
                                                                selectedRoles=v;
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    }
                                }, '授权'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.$data.user.splice(params.index,1);
                                            console.log(params.index);
                                            console.log(this.$data.user);
                                        }
                                    }
                                }, '删除')
                            ]);
                        }
                        
                    }
                }
            ]
        }
    },
    methods:{
        tableCheck(data){
            if(this.selecteduser==''){
                    this.$Message.error('请选中一个用户');
            }else{
                this.$Message.success('成功');
            }
        },
        addUser(){
            console.log('adduser');
            let user={
                name: '',
                age: 0,
                state:'on',
                address: '',
                is_edit:false,
                date: ''
            };
            this.$Modal.confirm({
                onOk:()=>{
                    if(user.name && user.age>0 && user.address){
                        this.$data.user.push(user);
                    }else{
                        this.$Message.warning('请完整输入数据！');
                    }
                },
                render: (h) => {
                    return h(editUser,{
                        props: {
                            prop_value: user
                        }
                    });
                }
            });
        }
    },
    computed: {

    }
 }
</script>
