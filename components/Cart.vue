<template>
  <div
    class="static sticky top-10 left-0 items-stretch w-48 min-h-auto p-3 pr-6 mt-3 mr-5 ml-3 z-40 flex flex-col text-sm font-bold tracking-tight text-gray-500 bg-white rounded-lg shadow-md border-2"
    id="sidebar"
  >
    <div class="flex mb-3 justify-center" v-for="key_ in cart" v-bind:key="key_">
      <Item :item="key_" @remove-item="removeItem" />
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Item from "./Item.vue";

export default Vue.extend({
  components: {
    Item,
  },
  props:{
    cart: []
  },
  methods:{
    removeItem(elToRem: string){
      let ind = 0;
      for(let i = 0; i < this.$props.cart.length; i++){
        if(this.$props.cart[i] == elToRem){
          ind = i;
        }
      }

      this.$props.cart.splice(ind,1);
    
    }
  }
});
</script>


