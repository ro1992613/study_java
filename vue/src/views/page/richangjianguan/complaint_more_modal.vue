<template>
    <div class="modal_complaint">
        <Row class="box_card" style="margin-top:10px;">
            <Col span="12" style="line-height: 20px;margin-top:5px;">编号:{{current_data.id}}</Col>
            <Col span="12" style="line-height: 20px;margin-top:5px;">公司:{{current_data.company}}</Col>
            <Col span="12" style="line-height: 20px;margin-top:5px;">区域:{{current_data.area}}</Col>
            <Col span="12" style="line-height: 20px;margin-top:5px;">状态:{{current_data.status}}</Col>
        </Row>

        <Row class="box_card" style="margin-top:20px;line-height: 30px;">
            <Steps :current="getStatus()">
                <Step title="未处理" content=""></Step>
                <Step title="处理中" content=""></Step>
                <Step title="完成" content=""></Step>
            </Steps>
        </Row>
        <Row class="box_card" style="margin-top:20px;">
            <Col>
                <div class="line"></div>
            </Col>
        </Row>

        <Row class="box_card" style="margin-top:20px;line-height: 30px;">
            <Card>
                <p slot="title">
                    投诉
                </p>
                <p>投诉时间：{{current_data.time}}</p>
                <p>事件描述：{{current_data.msg}}</p>
            </Card>
        </Row>

        <Row class="box_card" style="margin-top:20px;line-height: 30px;" v-if="current_data.status=='完成' || current_data.status=='处理中'"> 
            <Card>
                <p slot="title">
                    处理中
                </p>
                <p>处理时间：{{current_data.deal_time}}</p>
                <p>处理描述：{{current_data.deal_msg}}</p>
            </Card>
        </Row>
        <Row class="box_card" style="margin-top:20px;line-height: 30px;" v-if="current_data.status=='完成'">
            <Card>
                <p slot="title">
                    完成
                </p>
                <p>处理时间：{{current_data.complete_time}}</p>
                <p>处理描述：{{current_data.complete_msg}}</p>
            </Card>
        </Row>
    </div>
</template>
<script>
    export default {
        props:{
            prop_value:''
        },
        data:()=>{
            return {
                current_data:""
            };
        },
        methods:{
            getStatus:function(){
                let status=1;
                if(this.$data.current_data.status=='处理中'){
                    status=2;
                }
                if(this.$data.current_data.status=='完成'){
                    status=3;
                }
                return status;
            }
        },
        mounted:function(){
            this.$data.current_data=JSON.parse(JSON.stringify(this.$props.prop_value));
        }
    }
</script>
<style scoped>
.modal_complaint{
    height:500px;
    overflow: scroll;
}
.modal_complaint::-webkit-scrollbar{
  display:none;
}
.box_card{
    font-size: 13px;
    padding: 5px;
}
.row_card{
    padding: 5px;
}
.line{
    height:1px;
    background-color: rgba(116, 116, 116, 0.63)
}
</style>
