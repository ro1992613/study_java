<template>
    <Table height="400" style="margin-top: 15px;" @on-selection-change="selection_change" :columns="roleColumns" :data="setRoles"></Table>
</template>
<script>

    function getRoleID(role){
        let role_id=[];
        role.forEach(v => {
            role_id.push(v.role_id);
        });
        return role_id;
    }

    export default {
        props:{
            prop_value:''
        },
        data:()=>{
            return {
                roleColumns: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center'
                },{
                    title: '角色',
                    key: 'role_name'
                }],
                role:[
                    {
                        role_id:1,
                        role_name:'系统管理员'
                    },
                    {
                        role_id:2,
                        role_name:'管理员1'
                    },
                    {
                        role_id:3,
                        role_name:'管理员2'
                    },
                    {
                        role_id:4,
                        role_name:'管理员3'
                    }
                ]
            };
        },
        computed:{
            setRoles:function(){
                let selected_role=this.$props.prop_value;
                this.$emit('recieveSelectedRoles',selected_role);
                let role=this.$data.role;
                role.map(v=>{
                    for(let i in selected_role){
                        if(v.role_id==selected_role[i]){
                            v._checked=true;
                        }
                    }
                });
                return role;
            }
        },methods:{
            selection_change:function(selection){
                this.$emit('recieveSelectedRoles',getRoleID(selection));
            }
        }
    }
</script>