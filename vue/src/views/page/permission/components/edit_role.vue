<template>
    <div>
        <Input v-model="prop_value.role_name">
            <span slot="prepend">角色</span>
        </Input>
        <br>
        <Input v-model="prop_value.info">
            <span slot="prepend">描述</span>
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