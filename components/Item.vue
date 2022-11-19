<template>
  <div class="grid grid-cols-4 justify-items-center">
    <input class="w-6 text-center border-2" type="number"/>
    <div>
      <img class="" src="http://clipart-library.com/img/1565435.png"/>
    </div>

    <div>{{ item.item_ }}</div>

    <button
      class="bg-red-500 hover:bg-red-700 text-white font-bold rounded" @click="remove">
      X
    </button>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  props: {
    item: {},
  },
  methods:{
    remove(){
      this.$emit('remove-item', this.$props.item)
    }
  }
});
</script>
