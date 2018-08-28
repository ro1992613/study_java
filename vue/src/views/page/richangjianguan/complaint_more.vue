<template>
    <div>
        <Row>
            <Col class="box_card">
                <a href="javascript:void(0);" v-on:click="goback()"><Icon type="arrow-return-left"></Icon>返回上级</a>
                <div style="height:1px;background-color: rgba(116, 116, 116, 0.63);margin-top: 10px;"></div>
            </Col>
        </Row>
        <Row class="box_card">
            <Col span="4">当前投诉总数：<Tag color="green"><CountTo :endVal="1025564"/> </Tag></Col>
            <Col span="3">未处理：<Tag color="blue"><CountTo :endVal="900064"/> </Tag></Col>
            <Col span="3">处理中：<Tag color="red"><CountTo :endVal="25560"/> </Tag></Col>
            <Col span="2">完成：<Tag color="yellow"><CountTo :endVal="100000"/> </Tag></Col>
        </Row>
        <Row class="box_card">
            <Col span="3" style="padding-right:10px">
                <Input placeholder="输入编号"></Input>
            </Col>
            <Col span="3" style="padding-right:10px">
                <Select placeholder="选择公司">
                    <Option v-for="item in companyList" :value="item.value" :key="item.value">{{ item.label }}</Option>
                </Select>
            </Col>
            <Col span="3" style="padding-right:10px">
                <Select placeholder="选择处理状态">
                    <Option v-for="item in statusList" :value="item.value" :key="item.value">{{ item.label }}</Option>
                </Select>
            </Col>
            <Col span="3" style="padding-right:10px">
                <Select placeholder="选择区域">
                    <Option v-for="item in areaList" :value="item.value" :key="item.value">{{ item.label }}</Option>
                </Select>
            </Col>
            <Col span="4" style="padding-right:10px">
                <Button type="primary" icon="ios-search">查询</Button>
            </Col>
        </Row>
        <Row class="box_card">
            <Table stripe :columns="latest_complaint_column" :data="latest_complaint"></Table>
        </Row>
        <Row class="box_card">
            <Page :total="pageCount" :current="pageCurrent" :page-size="pageSize" size="small" show-total show-elevator></Page>
        </Row>
    </div>
</template>
<script>
import CountTo from "../components/CountTo.vue";
import complaint_more_modal from "./complaint_more_modal.vue";
export default {
  components: {
    CountTo,
    complaint_more_modal
  },
  data() {
    return {
      pageSize: 20,
      pageCount: 100,
      pageCurrent: 1,
      companyList: [
        {
          value: "所有",
          label: "所有"
        },
        {
          value: "ofo",
          label: "ofo"
        },
        {
          value: "摩拜",
          label: "摩拜"
        }
      ],
      statusList: [
        {
          value: "全部",
          label: "全部"
        },
        {
          value: "未处理",
          label: "未处理"
        },
        {
          value: "处理中",
          label: "处理中"
        },
        {
          value: "完成",
          label: "完成"
        }
      ],
      areaList: [
        {
          value: "所有",
          label: "所有"
        },
        {
          value: "武侯区",
          label: "武侯区"
        },
        {
          value: "成华区",
          label: "成华区"
        },
        {
          value: "锦江区",
          label: "锦江区"
        },
        {
          value: "青羊区",
          label: "青羊区"
        },
        {
          value: "金牛区",
          label: "金牛区"
        },
        {
          value: "龙泉驿区",
          label: "龙泉驿区"
        },
        {
          value: "双流区",
          label: "双流区"
        },
        {
          value: "温江区",
          label: "温江区"
        },
        {
          value: "郫都区",
          label: "郫都区"
        },
        {
          value: "青白江区",
          label: "青白江区"
        },
        {
          value: "新都区",
          label: "新都区"
        }
      ],
      latest_complaint: [
        {
          id: "025",
          company: "摩拜",
          area: "锦江区",
          time: "2018-05-28 16:30:05",
          status: "未处理",
          msg: "服务人员态度差",
          deal_time: "",
          deal_msg: "",
          complete_time: "",
          complete_msg: ""
        },
        {
          id: "025",
          company: "ofo",
          area: "青羊区",
          time: "2018-05-28 14:32:25",
          status: "未处理",
          msg: "服务质量差",
          deal_time: "",
          deal_msg: "",
          complete_time: "",
          complete_msg: ""
        },
        {
          id: "025",
          company: "ofo",
          area: "金牛区",
          time: "2018-05-28 13:31:14",
          status: "未处理",
          msg: "服务质量差",
          deal_time: "",
          deal_msg: "",
          complete_time: "",
          complete_msg: ""
        },
        {
          id: "025",
          company: "摩拜",
          area: "成华区",
          time: "2018-05-28 10:05:05",
          status: "未处理",
          msg: "服务人员态度差",
          deal_time: "",
          deal_msg: "",
          complete_time: "",
          complete_msg: ""
        },
        {
          id: "025",
          company: "ofo",
          area: "金牛区",
          time: "2018-05-27 10:05:05",
          status: "处理中",
          msg: "服务质量差",
          deal_time: "2018-05-27 12:05:05",
          deal_msg: "已经发送给服务企业",
          complete_time: "",
          complete_msg: ""
        },
        {
          id: "025",
          company: "摩拜",
          area: "锦江区",
          time: "2018-05-26 10:05:05",
          status: "处理中",
          msg: "服务人员态度差",
          deal_time: "2018-05-27 12:05:05",
          deal_msg: "已经发送给服务企业",
          complete_time: "",
          complete_msg: ""
        },
        {
          id: "025",
          company: "ofo",
          area: "锦江区",
          time: "2018-05-25 10:05:05",
          status: "未处理",
          msg: "服务质量差",
          deal_time: "",
          deal_msg: "",
          complete_time: "",
          complete_msg: ""
        },
        {
          id: "025",
          company: "摩拜",
          area: "青羊区",
          time: "2018-05-24 10:05:05",
          status: "未处理",
          msg: "服务人员态度差",
          deal_time: "",
          deal_msg: "",
          complete_time: "",
          complete_msg: ""
        },
        {
          id: "025",
          company: "ofo",
          area: "锦江区",
          time: "2018-04-28 10:05:05",
          status: "完成",
          msg: "服务质量差",
          deal_time: "2018-04-28 12:05:05",
          deal_msg: "已经发送给服务企业",
          complete_time: "2018-04-28 12:05:05",
          complete_msg: "收到投诉，限期整改"
        },
        {
          id: "025",
          company: "摩拜",
          area: "锦江区",
          time: "2018-03-28 10:0:52",
          status: "完成",
          msg: "服务人员态度差",
          deal_time: "2018-03-28 10:35:05",
          deal_msg: "已经发送给服务企业",
          complete_time: "2018-04-29 12:05:05",
          complete_msg: "收到投诉，对相关人员进行处理"
        }
      ],
      latest_complaint_column: [
        { title: "编号", key: "id" },
        { title: "公司", key: "company" },
        { title: "区域", key: "area" },
        { title: "时间", key: "time" },
        {
          title: "状态",
          align: "center",
          render: (h, params) => {
            let type = "error";
            if (params.row.status && params.row.status == "处理中") {
              type = "warning";
            }
            if (params.row.status && params.row.status == "完成") {
              type = "success";
            }
            return h(
              "Button",
              {
                props: {
                  type: type,
                  size: "small",
                  shape: "circle"
                }
              },
              params.row.status
            );
          }
        },
        {
          title: "详情",
          align: "center",
          render: (h, params) => {
            let type = "info";
            return h(
              "Button",
              {
                props: {
                  type: type
                },
                on: {
                  click: () => {
                    this.$Modal.info({
                      title: "投诉详情",
                      width: "500px",
                      okText: "关闭",
                      onOk: () => {},
                      render: h => {
                        return h(complaint_more_modal, {
                          props: {
                            prop_value: params.row
                          }
                        });
                      }
                    });
                  }
                }
              },
              "查看详情"
            );
          }
        }
      ]
    };
  },
  methods: {
    goback: function() {
      this.$router.go(-1);
    }
  },
  mounted: function() {}
};
</script>
<style scoped>
.box_card {
  padding: 15px;
}
.line {
  height: 1px;
  background-color: rgba(116, 116, 116, 0.63);
}
</style>
