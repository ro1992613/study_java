<template>
    <div>
        <Row class="box_card" style="margin-top:10px;">
            <Col span="12" style="line-height: 20px;">
                <Row>姓名：{{current_data.name}}</Row>
                <Row>性别：{{current_data.sex}}</Row>
                <Row>年龄：{{current_data.age}}</Row>
                <Row>电话：{{current_data.phone}}</Row>
                <Row>证件号：{{current_data.card_id}}</Row>
            </Col>
            <Col span="12">
             <Avatar style="margin-left:90px;width:100px;height:100px;"><Icon type="person" size="95"></Icon></Avatar>
                <!-- <img style="margin-left:90px;" width="100" height="100" src="/dist/renxiang.jpg"> -->
            </Col>
        </Row>
        <Row class="box_card" style="margin-top:20px;">
            <Col>
                <div class="line"></div>
            </Col>
        </Row>
        <Row  class="box_card" style="margin-top:20px;line-height: 30px;">
            <Col span="12">所属公司：{{current_data.company}}</Col>
            <Col span="12">处理事件：{{current_data.event_num}}</Col>
            <Col span="12">入职时间：{{current_data.time}}</Col>
            <Col span="12">在线状态：{{current_data.status}}</Col>
            <Col span="12"><Button type="info"><Icon type="location"></Icon> 位置</Button></Col>
        </Row>
        <Row class="box_card" style="margin-top:20px;">
            <Col>
                <div class="line"></div>
            </Col>
        </Row>
    </div>
</template>
<script>
    export default {
        name:"person_info_modal",
        props:{
            prop_value:''
        },
        data:()=>{
            return {
                current_data:""
            };
        },
        mounted:function(){
            this.$data.current_data=JSON.parse(JSON.stringify(this.$props.prop_value));
        }
    }
</script>
<style scoped>
.box_card{
    width: 400px;
}
.row_card{
    padding: 5px;
}
.line{
    height:1px;
    background-color: rgba(116, 116, 116, 0.63)
}
</style>
