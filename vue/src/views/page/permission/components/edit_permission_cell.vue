<template>
    <div  v-if="!is_edit">
        <span v-if="prop_type=='permission_name'">{{prop_value.permission_name}}</span>
        <span v-if="prop_type=='permission_path'">{{prop_value.permission_path}}</span>
        <span style="float: right;">
            <Tooltip content="编辑" placement="top">
                <a style="color:black;float:right;" v-on:click="edit_value"><Icon type="edit"></Icon></a>
            </Tooltip>
        </span>
    </div>
    <div v-else>
        <span style="float: left;width:150px;"><Input size="small"  v-model="col_value"></Input></span>
        <span style="float: right;">
            <Tooltip content="确认" placement="top">
                <Button type="success" size="small" @click="confirm_value"><Icon type="checkmark"></Icon></Button>
            </Tooltip>
            <Tooltip content="取消" placement="top">
                <Button type="error" size="small" @click="cancel_value"><Icon type="close-round"></Icon></Button>
            </Tooltip>
        </span>
    </div>
    
</template>
<script>
    export default {
        props:{
            prop_value:'',
            prop_type:''
        },
        data:()=>{
            return {
                is_edit:false,
                col_value:''
            }
        },
        methods:{
            edit_value:function(){
                if(this.$props.prop_type=='permission_name'){
                    this.$data.col_value=this.$props.prop_value.permission_name;
                }
                if(this.$props.prop_type=='permission_path'){
                    this.$data.col_value=this.$props.prop_value.permission_path;
                }
                this.$data.is_edit=true;
            },
            confirm_value:function(){
                if(this.$props.prop_type=='permission_name'){
                    this.$props.prop_value.permission_name=this.$data.col_value;
                } 
                if(this.$props.prop_type=='permission_path'){
                    this.$props.prop_value.permission_path=this.$data.col_value;
                }
                this.$data.is_edit=false;
            },cancel_value:function(){
                this.$data.is_edit=false;
            }
        }
    }
</script>