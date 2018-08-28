<template>
    <div>
        <Input v-model="prop_value.permission_name">
            <span slot="prepend">权限名称</span>
        </Input>
        <br>
        <Input v-model="prop_value.permission_path">
            <span slot="prepend">权限地址</span>
        </Input>
    </div>
</template>
<script>
    export default {
        props:{
            prop_value:''
        }
    }
</script>