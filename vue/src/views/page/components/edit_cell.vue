<template>
    <div v-if="is_edit">
        <span style="float: left;width:100px;align:center"><Input size="small"  v-model="input_value"></Input></span>
        <span style="float: right;">
            <Tooltip content="确认" placement="top">
                <Button type="success" size="small" @click="save"><Icon type="checkmark"></Icon></Button>
            </Tooltip>
            <Tooltip content="取消" placement="top">
                <Button type="error" size="small" @click="cancel"><Icon type="close-round"></Icon></Button>
            </Tooltip>
        </span>
    </div>
    <div v-else>
        <span>{{show_value}}</span>
        <span style="float: right;">
            <Tooltip content="编辑" placement="top">
                <a style="color:black;" v-on:click="edit" href="javascript:void(0);"><Icon type="edit"></Icon></a>
            </Tooltip>
        </span>
    </div>
    
</template>
<script>
    export default {
        props:{
            prop_value:''
        },
        data(){
            return {
                input_value:"",
                show_value:"",
                is_edit:false
            }
        },
        mounted:function(){
            this.$data.show_value=this.$props.prop_value;
        },
        methods:{
            edit:function(){
                this.$data.input_value=this.$data.show_value;
                this.$data.is_edit=true;
            },
            save:function(){
                this.$data.show_value=this.$data.input_value;
                this.$data.is_edit=false;
            },
            cancel:function(){
                this.$data.is_edit=false;
            }
        }
    }
</script>