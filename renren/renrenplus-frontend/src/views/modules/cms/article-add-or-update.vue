<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    width="90%"
    :visible.sync="visible"
  >
    <el-tabs v-model="activeTab" @tab-click="tabClick">
      <el-tab-pane label="信息编辑" name="first">
        <el-form
          :model="dataForm"
          ref="dataForm"
          @keyup.enter.native="dataFormSubmit()"
          label-width="80px"
        >
          <el-form-item label="标题">
            <el-input v-model="dataForm.title"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="dataForm.status" placeholder="请选择">
              <el-option
                v-for="item in statusList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="栏目">
            <el-select v-model="dataForm.channelId" clearable placeholder="请选择">
              <el-option
                v-for="item in channelList"
                :key="item.id"
                :label="item.channelName"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="类型">
            <el-select v-model="dataForm.typeId" clearable placeholder="请选择">
              <el-option
                v-for="item in typeList"
                :key="item.id"
                :label="item.typeName"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="关键字">
            <el-input v-model="dataForm.keywords" placeholder="用逗号隔开"></el-input>
          </el-form-item>
          <el-form-item label="特征">
            <el-input v-model="dataForm.feature" placeholder="用逗号隔开"></el-input>
          </el-form-item>
          <el-form-item label="简介">
            <el-input type="textarea" v-model="dataForm.summary" placeholder="请输入内容"></el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="内容编辑" name="second">
        <div style="min-height: 100%;margin-bottom:10px;">
          <div id="editorElem" style="text-align:left"></div>
        </div>
      </el-tab-pane>
    </el-tabs>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import E from "wangeditor";
var editor;
export default {
  data() {
    return {
      visible: false,
      activeTab: "first",
      isEditTabInited: false,
      dataForm: {
        id: 0,
        channelId: "",
        typeId: "",
        title: "",
        summary: "",
        status: "",
        keywords: "",
        feature: "",
        content:''
      },
      typeList: [],
      channelList: [],
      statusList: [
        { label: "草稿", value: 0 },
        { label: "审核中", value: 1 },
        { label: "已发布", value: 2 }
      ]
    };
  },
  methods: {
    init(id) {
      this.dataForm.id = id || 0;
      this.visible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].resetFields();
        this.$http({
          url: this.$http.adornUrl("/cms/channel/list"),
          method: "post",
          data: this.$http.adornData()
        })
          .then(({ data }) => {
            this.channelList = data.data;
          })
          .then(() => {
            this.$http({
              url: this.$http.adornUrl("/cms/type/list"),
              method: "post",
              data: this.$http.adornData()
            })
              .then(({ data }) => {
                this.typeList = data.data;
              })
              .then(() => {
                if (this.dataForm.id) {
                  this.$http({
                    url: this.$http.adornUrl("/cms/article/find"),
                    method: "post",
                    data: this.$http.adornData({
                      id: this.dataForm.id
                    })
                  }).then(({ data }) => {
                    if (data && data.code === 0) {
                      this.dataForm.channelId = data.data.channelId==null ? '' :data.data.channelId;
                      this.dataForm.typeId = data.data.typeId==null ? '' :data.data.typeId;
                      this.dataForm.title = data.data.title==null ? '' :data.data.title;
                      this.dataForm.summary = data.data.summary==null ? '' :data.data.summary;
                      this.dataForm.status = data.data.status==null ? '' :data.data.status;
                      this.dataForm.keywords = data.data.keywords==null ? '' :data.data.keywords;
                      this.dataForm.feature = data.data.feature==null ? '' :data.data.feature;
                      this.dataForm.content = data.data.content==null ? '' :data.data.content;
                      editor.txt.html(this.dataForm.content);
                    }
                  });
                } else {
                  this.dataForm.channelId = "";
                  this.dataForm.typeId = "";
                  this.dataForm.title = "";
                  this.dataForm.summary ="";
                  this.dataForm.status ="";
                  this.dataForm.keywords = "";
                  this.dataForm.feature ="";
                  this.dataForm.content ="";
                  editor.txt.html("");
                }
              });
          });
      });
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(
              `/cms/article/${!this.dataForm.id ? "insert" : "update"}`
            ),
            method: "post",
            data: this.$http.adornData({
              id: this.dataForm.id || undefined,
              channelId: this.dataForm.channelId,
              typeId: this.dataForm.typeId,
              title: this.dataForm.title,
              summary: this.dataForm.summary,
              status: this.dataForm.status,
              keywords: this.dataForm.keywords,
              feature: this.dataForm.feature,
              content: this.dataForm.content
            })
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.visible = false;
                  this.$emit("refreshDataList");
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    },
    tabClick() {
      if (!this.isEditTabInited) {
        this.isEditTabInited = true;
        this.initWangEditor();
      }
      editor.txt.html(this.dataForm.content);
    },
    initWangEditor() {
      editor = new E("#editorElem");
      editor.customConfig.uploadImgShowBase64 = true;
      editor.customConfig.onchange = html => {
        this.dataForm.content = html;
      };
      editor.create();
    }
  }
};
</script>
