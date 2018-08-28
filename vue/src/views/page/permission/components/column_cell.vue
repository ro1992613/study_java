<template>
    <div  v-if="!is_edit">
        <span>{{prop_value}}</span>
    </div>
    <div v-else>
        <span style="float: left;width:150px;"><Input size="small"  v-model="col_value"></Input></span>
    </div>
    
</template>
<script>
    export default {
        props:{
            prop_value:'',
            is_edit:false
        },
        data:()=>{
            return {
                col_value:''
            }
        },
        methods:{

        },
        watch:{
            col_value:function(){
                this.$emit('recieveData',this.$data.col_value);
            },
            is_edit:function(){
                if(this.$props.is_edit){
                    this.$data.col_value=this.$props.prop_value;
                }
            }
        }
    }
</script>