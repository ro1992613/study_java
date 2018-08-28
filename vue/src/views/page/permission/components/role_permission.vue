<template>
    <Table height="400" style="margin-top: 15px;" @on-selection-change="selection_change" :columns="permissionColumns" :data="setPermissions"></Table>
</template>
<script>

    function getPermissionID(permissions){
        let permission_id=[];
        permissions.forEach(v => {
            permission_id.push(v.permission_id);
        });
        return permission_id;
    }

    export default {
        props:{
            prop_value:''
        },
        data:()=>{
            return {
                permissionColumns: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center'
                },{
                    title: '权限',
                    key: 'permission_name'
                }],
                permission:[
                    {
                        permission_id:1,
                        permission_name:'权限1'
                    },
                    {
                        permission_id:2,
                        permission_name:'权限2'
                    },
                    {
                        permission_id:3,
                        permission_name:'权限3'
                    },
                    {
                        permission_id:4,
                        permission_name:'权限4'
                    },
                    {
                        permission_id:5,
                        permission_name:'权限5'
                    },
                    {
                        permission_id:6,
                        permission_name:'权限6'
                    }
                ]
            };
        },
        computed:{
            setPermissions:function(){
                let selected_permission=this.$props.prop_value;
                this.$emit('recieveSelectedPermissions',selected_permission);
                let permission=this.$data.permission;
                permission.map(v=>{
                    for(let i in selected_permission){
                        if(v.permission_id==selected_permission[i]){
                            v._checked=true;
                        }
                    }
                });
                return permission;
            }
        },methods:{
            selection_change:function(selection){
                this.$emit('recieveSelectedPermissions',getPermissionID(selection));
            }
        }
    }
</script>