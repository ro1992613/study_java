<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="角色名称" prop="roleName">
        <el-input v-model="dataForm.roleName" placeholder="角色名称"></el-input>
      </el-form-item>
      <el-form-item label="选择部门" prop="name">
        <el-popover
          ref="s_deptListPopover"
          placement="bottom-start"
          trigger="click">
          <el-tree
            :data="deptList"
            :props="deptListTreeProps"
            node-key="deptId"
            ref="deptListTree"
            @current-change="s_deptListTreeCurrentChangeHandle"
            :default-expand-all="true"
            :highlight-current="true"
            :expand-on-click-node="false">
          </el-tree>
        </el-popover>
        <el-input v-model="dataForm.deptName" v-popover:s_deptListPopover :readonly="true" placeholder="点击选择部门" class="menu-list__input"></el-input>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="dataForm.remark" placeholder="备注"></el-input>
      </el-form-item>
      <el-form-item size="mini" label="菜单权限">
        <el-tree
          :data="menuList"
          :props="menuListTreeProps"
          node-key="menuId"
          ref="menuListTree"
          :default-expand-all="false"
          show-checkbox>
        </el-tree>
      </el-form-item>
      <el-form-item size="mini" label="数据权限">
        <el-tree
          :data="deptList"
          :props="deptListTreeProps"
          node-key="deptId"
          ref="deptListTree"
          :default-expand-all="false"
          show-checkbox>
        </el-tree>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { treeDataTranslate } from '@/utils'
  export default {
    data () {
      return {
        visible: false,
        menuList: [],
        menuListTreeProps: {
          label: 'name',
          children: 'children'
        },
        deptList: [],
        deptListTreeProps: {
          label: 'name',
          children: 'children'
        },
        dataForm: {
          id: 0,
          roleName: '',
          deptName:'',
          deptId:'',
          remark: ''
        },
        dataRule: {
          roleName: [
            { required: true, message: '角色名称不能为空', trigger: 'blur' }
          ]
        },
        tempKey: -666666 // 临时key, 用于解决tree半选中状态项不能传给后台接口问题. # 待优化
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.$http({
          url: this.$http.adornUrl('/sys/menu/list'),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          this.menuList = treeDataTranslate(data, 'menuId')
        }).then(() => {
            this.$http({
              url: this.$http.adornUrl('/sys/dept/list'),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data})=>{
              this.deptList = treeDataTranslate(data, 'deptId');
            }).then(()=>{
              this.visible = true
              this.$nextTick(() => {
                this.$refs['dataForm'].resetFields()
                this.$refs.menuListTree.setCheckedKeys([])
                this.$refs.deptListTree.setCheckedKeys([])
              })
            }).then(()=>{
              if (this.dataForm.id) {
                this.$http({
                  url: this.$http.adornUrl(`/sys/role/info/${this.dataForm.id}`),
                  method: 'get',
                  params: this.$http.adornParams()
                }).then(({data}) => {
                  if (data && data.code === 0) {
                    this.dataForm.roleName = data.role.roleName
                    this.dataForm.deptName = data.role.deptName
                    this.dataForm.deptId = data.role.deptId
                    this.dataForm.remark = data.role.remark
                    var idx = data.role.menuIdList.indexOf(this.tempKey)
                    if (idx !== -1) {
                      data.role.menuIdList.splice(idx, data.role.menuIdList.length - idx)
                    }
                    var id_dept = data.role.deptIdList.indexOf(this.tempKey)
                    if (id_dept !== -1) {
                      data.role.deptIdList.splice(id_dept, data.role.deptIdList.length - id_dept)
                    }
                    this.$refs.menuListTree.setCheckedKeys(data.role.menuIdList)
                    this.$refs.deptListTree.setCheckedKeys(data.role.deptIdList)
                  }
                })
              }
          })
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/sys/role/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'roleId': this.dataForm.id || undefined,
                'roleName': this.dataForm.roleName,
                'deptId': this.dataForm.deptId,
                'deptName': this.dataForm.deptName,
                'remark': this.dataForm.remark,
                'menuIdList': [].concat(this.$refs.menuListTree.getCheckedKeys(), [this.tempKey], this.$refs.menuListTree.getHalfCheckedKeys()),
                'deptIdList':[].concat(this.$refs.deptListTree.getCheckedKeys(), [this.tempKey], this.$refs.deptListTree.getHalfCheckedKeys())
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      },
            // 菜单树选中
      s_deptListTreeCurrentChangeHandle (data, node) {
        this.dataForm.deptId = data.deptId
        this.dataForm.deptName = data.name
      },
      // 菜单树设置当前选中节点
      s_deptListTreeSetCurrentNode () {
        this.$refs.deptListTree.setCurrentKey(this.dataForm.deptId)
        this.dataForm.deptName = (this.$refs.deptListTree.getCurrentNode() || {})['name']
      },
    }
  }
</script>
