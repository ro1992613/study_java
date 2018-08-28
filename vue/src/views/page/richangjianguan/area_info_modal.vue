<template>
    <div>
        <br>
        <Input v-model="current_data.id" disabled>
            <span slot="prepend">编号</span>
        </Input>
        <br>
        <Input v-model="current_data.name">
            <span slot="prepend">名称</span>
        </Input>
        <br>
        <Select v-model="current_data.important" label="重点区域" >
            <Option value="重要" key="重要">重要</Option>
            <Option value="普通" key="普通">普通</Option>
        </Select>
        <br>
        <br>
        <Select v-model="current_data.type" label="类型">
            <Option value="行政区域" key="行政区域">行政区域</Option>
            <Option value="自定义区域" key="自定义区域">自定义区域</Option>
        </Select>
        <!-- <br>
        <Input v-model="current_data.type">
            <span slot="prepend">类型</span>
        </Input> -->
        <br>
        <br>

        <Select v-model="current_data.tags" label="标签" multiple >
            <Option value="禁停" key="禁停">禁停</Option>
            <Option value="禁投" key="禁投">禁投</Option>
        </Select>
        <br>
        <br>
        <Input v-model="current_data.limit">
            <span slot="prepend">阈值</span>
        </Input>
        <br>
        <Input v-model="current_data.bike_num" disabled>
            <span slot="prepend">当前数量</span>
        </Input>
    </div>
</template>
<script>
    export default {
        props:{
            prop_value:''
        },
        data:()=>{
            return {
                current_data:''
            };
        },
        methods:{
        },
        watch:{
            current_data:function(){
                this.$emit('recieveData',this.$data.current_data);
            }
        },
        mounted:function(){
            this.$data.current_data=JSON.parse(JSON.stringify(this.$props.prop_value));
            this.$data.current_data.tags=this.$data.current_data.tags.split(",");
        }
    }
</script>
<style scoped>
</style>
