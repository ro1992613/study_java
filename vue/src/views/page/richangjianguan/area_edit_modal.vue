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
        <Select v-model="current_data.father_area" label="所属区域" >
            <Option value="锦江区" key="锦江区">锦江区</Option>
            <Option value="成华区" key="成华区">成华区</Option>
            <Option value="武侯区" key="武侯区">武侯区</Option>
            <Option value="金牛区" key="金牛区">金牛区</Option>
            <Option value="青羊区" key="青羊区">青羊区</Option>
            <Option value="双流区" key="双流区">双流区</Option>
            <Option value="龙泉驿区" key="龙泉驿区">龙泉驿区</Option>
            <Option value="郫都区" key="郫都区">郫都区</Option>
            <Option value="温江区" key="温江区">温江区</Option>
            <Option value="新都区" key="新都区">新都区</Option>
            <Option value="青白江区" key="青白江区">青白江区</Option>
        </Select>
        <br>
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
            let data=JSON.parse(JSON.stringify(this.$props.prop_value));
            if(data.tags){
                data.tags=data.tags.split(",");
            }else{
                data.tags=[];
            }
            this.$data.current_data=data
        }
    }
</script>
<style scoped>
</style>
