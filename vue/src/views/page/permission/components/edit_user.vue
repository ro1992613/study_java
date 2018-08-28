<template>
    <div>
        <Input v-model="prop_value.name">
            <span slot="prepend">用户</span>
        </Input>
        <br>
        <Input v-model="prop_value.age">
            <span slot="prepend">年龄</span>
        </Input>
        <br>
        <Input v-model="prop_value.address">
            <span slot="prepend">地址</span>
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