<template>
    <div>
        <Card style="width:auto">
            <p slot="title">
                <Icon type="ios-film-outline"></Icon>
                权限
            </p>
            <a href="#" v-on:click="addPermission" slot="extra">
                <Icon type="android-add"></Icon>
                添加
            </a>
            <Row>
                <Table border ref="selection"  :columns="permission_column" :data="permission_data"></Table>
            </Row>
            <Row style="padding-top:10px;">
                <Page :total="pageCount" :current="pageCurrent" :page-size="pageSize" size="small" show-total show-elevator></Page>
            </Row>
        </Card>
    </div>
</template>
<script>
    import editPermissionCell from "./components/edit_permission_cell.vue";
    import addPermission from "./components/add_permission.vue";

    export default {
        components:{
            addPermission,
            editPermissionCell
        },
        data () {
            return {
                permission_column: [
                    {
                        type: 'selection',
                        width: 60,
                        align: 'center'
                    },
                    {
                        title: '权限名称',
                        key: 'edit_permission_name',
                        minWidth:250,
                        render:(h,params)=>{
                            let prop_value=JSON.parse(JSON.stringify(params.row));
                            return h(editPermissionCell,{
                                props: {
                                    prop_type:'permission_name',
                                    prop_value: prop_value
                                }
                            });
                        }
                    },
                    {
                        title: '权限地址',
                        key: 'edit_permission_path',
                        minWidth:250,
                        render:(h,params)=>{
                            let prop_value=JSON.parse(JSON.stringify(params.row));
                            return h(editPermissionCell,{
                                props: {
                                    prop_type:'permission_path',
                                    prop_value: prop_value
                                }
                            });
                        }
                    },
                    {
                        title: '创建时间',
                        key: 'create_time'
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
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.$data.permission_data.splice(params.index,1);
                                        }
                                    }
                                },'删除')
                            ]);
                        }
                    }
                ],
                permission_data: [
                    {
                        permission_name: 'John Brown',
                        id: 18,
                        permission_path: 'New York No. 1 Lake Park',
                        create_time: '2016-10-03'
                    },
                    {
                        permission_name: 'Jim Green',
                        id: 24,
                        permission_path: 'London No. 1 Lake Park',
                        create_time: '2016-10-01'
                    },
                    {
                        permission_name: 'Joe Black',
                        id: 30,
                        permission_path: 'Sydney No. 1 Lake Park',
                        create_time: '2016-10-02'
                    }
                ],
                pageSize:20,
                pageCount:100,
                pageCurrent:1
            }
        },
        methods: {
            addPermission:function(){
                let date = new Date();
                let str = date.getFullYear()+"-";
                str += (date.getMonth() + 1)>=10?(date.getMonth() + 1):('0'+(date.getMonth() + 1)) + "-";
                str += (date.getDate() + 1)>=10?(date.getDate() + 1):('0'+(date.getDate() + 1));
                let prop_value={
                        permission_name: '',
                        id: null,
                        permission_path: '',
                        create_time: str
                    };
                this.$Modal.confirm({
                    onOk:()=>{
                        if(prop_value.permission_name){
                            this.$data.permission_data.push(prop_value);
                        }
                    },
                    render: (h) => {
                        return h(addPermission,{
                            props: {
                                prop_value: prop_value
                            }
                        });
                    }
                });
            }
        }
    }
</script>
