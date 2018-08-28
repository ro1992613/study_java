<template>
  <div>
      <Tag v-for="tag in prop_value.opened_page" type="dot" closable color="blue">{{tag.title}}</Tag>
  </div>
</template>
<script>
export default {
    data:function(){
        return {

        };
    },
    methods:{
        getRouterInfo:function(){
            console.log(this.$router.currentRoute);
        }
    },
    props:{
        prop_value:{}
    }
}
</script>
