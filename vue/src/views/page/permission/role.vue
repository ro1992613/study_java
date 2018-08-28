<template>
    <Row>
        <Col span="24">
            <Card style="width:auto">
                <p slot="title">
                    <Icon type="ios-film-outline"></Icon>
                    角色
                </p>
                <a href="#" v-on:click="addRole" slot="extra">
                    <Icon type="android-add"></Icon>
                    添加
                </a>
                <Row>
                    <Table height="600"  :columns="roleColumns" :data="role"></Table>
                </Row>
                <Row style="padding-top:10px;">
                     <Page :total="pageCount" :current="pageCurrent" :page-size="pageSize" size="small" show-total show-elevator></Page>
                </Row>
            </Card>
        </Col>
    </Row>
</template>
<script>
import editRole from './components/edit_role.vue';
import rolePermission from './components/role_permission.vue';

export default {
    components:{
        editRole,
        rolePermission
    },
    data () {
        return {
            role:[{
                    role_name: 'John Brown',
                    info: '描述',
                    state:'on',
                    create_time: '2016-10-03'
                },
                {
                    role_name: 'John Brown',
                    info: '描述',
                    state:'on',
                    create_time: '2016-10-03'
                }],
            pageSize:20,
            pageCount:100,
            pageCurrent:1,
            roleColumns: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center'
                },
                {
                    title: '角色名',
                    key: 'role_name'
                },
                {
                    title: '描述',
                    key: 'info'
                },
                {
                    title: '创建日期',
                    key: 'create_time'
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
                                        let prop_value=JSON.parse(JSON.stringify(params.row));
                                        this.$Modal.confirm({
                                            onOk:()=>{
                                                params.row.role_name=prop_value.role_name;
                                            },
                                            render: (h) => {
                                                return h(editRole,{
                                                    props: {
                                                        prop_value: prop_value
                                                    }
                                                });
                                            }
                                        });
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
                                        let selectedPermissions=[];
                                        this.$Modal.confirm({
                                            title:'选择角色',
                                            onOk:()=>{
                                                console.log(selectedPermissions);
                                            },
                                            render: (h) => {
                                                return h(rolePermission,{
                                                    props: {
                                                        prop_value: [1,2]
                                                    },
                                                    on:{
                                                        recieveSelectedPermissions:(v)=>{
                                                            selectedPermissions=v;
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
                                        this.$data.role.splice(params.index,1);
                                    }
                                }
                            }, '删除')
                        ]);
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
        addRole(){
            let date = new Date();
            let str = date.getFullYear()+"-";
            str += (date.getMonth() + 1)>=10?(date.getMonth() + 1):('0'+(date.getMonth() + 1)) + "-";
            str += (date.getDate() + 1)>=10?(date.getDate() + 1):('0'+(date.getDate() + 1));

            let role={
                role_name: '',
                info: '描述',
                state:'on',
                create_time: str
            };
            this.$Modal.confirm({
                onOk:()=>{
                    console.log(role);
                    if(role.role_name){
                        this.$data.role.push(role);
                    }
                },
                render: (h) => {
                    return h(editRole,{
                        props: {
                            prop_value: role
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
