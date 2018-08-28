<template>
    <div>
        <Row class="box_card">
            <Col span="3" style="padding-right:10px">
                <Input placeholder="查询条件"></Input>
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
            <Card>
                <p slot="title">
                    <Icon type="code-working"></Icon>
                    区域列表
                </p>
                <a href="javascript:void(0);" slot="extra"  v-on:click="add_area()">
                    <Icon type="ios-plus-empty"></Icon>
                    新增区域
                </a>
                <Table stripe :columns="areas_column" :data="areas"></Table>
            </Card>
            
        </Row>
        <Row class="box_card">
            <Page :total="pageCount" :current="pageCurrent" :page-size="pageSize" size="small" show-total show-elevator></Page>
        </Row>
    </div>
</template>
<script>
import edit_cell from "../components/edit_cell.vue";
import area_edit_modal from "./area_edit_modal.vue";
import area_add_modal from "./area_add_modal.vue";
export default {
  data() {
    return {
      pageSize: 20,
      pageCount: 100,
      pageCurrent: 1,
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
      areas: [
        {
          id: "1",
          name: "锦江区",
          father_area: "锦江区",
          type: "行政区域",
          important: "重要",
          tags: "禁停,禁投",
          limit: "300000",
          bike_num: "250000"
        },
        {
          id: "2",
          name: "武侯区",
          father_area: "武侯区",
          type: "行政区域",
          important: "重要",
          tags: "禁停,禁投",
          limit: "300000",
          bike_num: "250000"
        },
        {
          id: "3",
          name: "成华区",
          father_area: "成华区",
          type: "行政区域",
          important: "重要",
          tags: "禁投",
          limit: "300000",
          bike_num: "250000"
        },
        {
          id: "4",
          name: "青羊区",
          father_area: "青羊区",
          type: "行政区域",
          important: "重要",
          tags: "禁停,禁投",
          limit: "300000",
          bike_num: "250000"
        },
        {
          id: "5",
          name: "金牛区",
          father_area: "金牛区",
          type: "行政区域",
          important: "重要",
          tags: "禁停,禁投",
          limit: "300000",
          bike_num: "250000"
        },
        {
          id: "6",
          name: "双流区",
          father_area: "双流区",
          type: "行政区域",
          important: "重要",
          tags: "禁停,禁投",
          limit: "300000",
          bike_num: "250000"
        },
        {
          id: "7",
          name: "龙泉驿区",
          father_area: "龙泉驿区",
          type: "行政区域",
          important: "重要",
          tags: "禁停,禁投",
          limit: "300000",
          bike_num: "250000"
        },
        {
          id: "8",
          name: "郫都区",
          father_area: "郫都区",
          type: "行政区域",
          important: "重要",
          tags: "禁停,禁投",
          limit: "300000",
          bike_num: "250000"
        },
        {
          id: "9",
          name: "温江区",
          father_area: "温江区",
          type: "行政区域",
          important: "重要",
          tags: "禁停,禁投",
          limit: "300000",
          bike_num: "250000"
        },
        {
          id: "10",
          name: "新都区",
          father_area: "新都区",
          type: "行政区域",
          important: "重要",
          tags: "禁停,禁投",
          limit: "300000",
          bike_num: "250000"
        },
        {
          id: "11",
          name: "青白江区",
          father_area: "青白江区",
          type: "行政区域",
          important: "重要",
          tags: "禁停,禁投",
          limit: "300000",
          bike_num: "250000"
        },
        {
          id: "12",
          name: "金融城区域",
          father_area: "武侯区",
          type: "自定义区域",
          important: "普通",
          tags: "",
          limit: "300000",
          bike_num: "250000"
        }
      ],
      areas_column: [
        { title: "编号", key: "id", width: 80 },
        {
          title: "名称",
          key: "name"
          // minWidth: 200,
          // render: (h, params) => {
          //   return h(edit_cell, {
          //     props: {
          //       prop_value: params.row.name
          //     }
          //   });
          // }
        },
        { title: "所属区域", key: "father_area" },
        { title: "类型", key: "type" },
        {
          title: "重点区域",
          align: "center",
          render: (h, params) => {
            let type = "info";
            if (params.row.important && params.row.important == "重要") {
              type = "error";
            }
            return h(
              "Button",
              {
                props: {
                  type: type,
                  size: "small",
                  shape: "circle"
                },
                on: {
                  click: () => {
                    if (
                      params.row.important &&
                      params.row.important == "重要"
                    ) {
                      params.row.important = "普通";
                    } else {
                      params.row.important = "重要";
                    }
                  }
                }
              },
              params.row.important
            );
          }
        },
        {
          title: "标签",
          align: "center",
          render: (h, params) => {
            let block = [];
            if (params.row.tags) {
              let tag_array = params.row.tags.split(",");
              for (let i in tag_array) {
                if (tag_array[i]) {
                  let bt = h(
                    "Tag",
                    {
                      props: {
                        color: "blue",
                        fade: true
                      }
                    },
                    tag_array[i]
                  );
                  block.push(bt);
                }
              }
            }

            return h("div", block);
          }
        },
        {
          title: "阈值",
          key: "limit"
          // minWidth: 200,
          // render: (h, params) => {
          //   return h(edit_cell, {
          //     props: {
          //       prop_value: params.row.limit
          //     }
          //   });
          // }
        },
        { title: "当前数量", key: "bike_num" },
        {
          title: "操作",
          align: "center",
          minWidth: 300,
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    size: "small"
                  },
                  on: {
                    click: () => {
                      let current_data = "";
                      this.$Modal.confirm({
                        title: "编辑区域",
                        onOk: () => {
                          if (current_data) {
                            params.row.name = current_data.name;
                            params.row.important = current_data.important;
                            params.row.limit = current_data.limit;
                            params.row.type = current_data.type;
                            params.row.tags = current_data.tags.join();
                          }
                        },
                        render: h => {
                          return h(area_edit_modal, {
                            props: {
                              prop_value: params.row
                            },
                            on: {
                              recieveData: v => {
                                current_data = v;
                              }
                            }
                          });
                        }
                      });
                    }
                  }
                },
                "编辑"
              ),
              h(
                "Button",
                {
                  props: {
                    size: "small"
                  },
                  style: {
                    marginLeft: "5px"
                  },
                  on: {
                    click: () => {
                      if (params.row.type == "行政区域") {
                        this.$Message.warning("不能编辑行政区域");
                      } else {
                        this.$router.push('/richangjianguan/addRegion');
                      }
                    }
                  }
                },
                "区域编辑"
              ),
              h(
                "Button",
                {
                  props: {
                    size: "small",
                    type: "error"
                  },
                  style: {
                    marginLeft: "5px"
                  },
                  on: {
                    click: () => {
                      if (params.row.type == "行政区域") {
                        this.$Message.warning("不能删除行政区域");
                      } else {
                        this.$data.areas.splice(params.index, 1);
                      }
                    }
                  }
                },
                "删除"
              )
            ]);
          }
        }
      ]
    };
  },
  methods: {
    add_area: function() {
      let area = {
        id: "",
        tags: "",
        name: "",
        limit: "",
        bike_num: "",
        important: "不重要",
        type: "自定义区域"
      };
      this.$Modal.confirm({
        title: "添加区域",
        onOk: () => {
          console.log(area.tags);
          area.tags = area.tags.join();
          this.$data.areas.push(area);
        },
        render: h => {
          return h(area_add_modal, {
            props: { prop_value: area }
          });
        }
      });
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
