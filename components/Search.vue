<template>
  <div class="relative z-20 flex justify-self-center mr-3" id="search">
    <form @submit.prevent="addingitem">
      <div class="relative">
        <div
          class="flex absolute inset-y-0 left-0 items-center pl-2 pointer-events-none"
        >
          <svg
            aria-hidden="true"
            class="w-5 h-5 text-gray-500 dark:text-gray-400"
            fill="none"
            stroke="black"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
            ></path>
          </svg>
        </div>
        <input
          type="search"
          id="default-search"
          class="block px-20 p-1 pl-10 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          placeholder="Search Keys, Stores..."
          required="false"
          v-model="search_v"
        />
        <label
          for="default-search"
          class="mb-2 z-40 text-sm font-medium text-gray-900 sr-only dark:text-gray-300"
          >Search</label
        >
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  data() {
    return {
      search_v: "",
      inStoreItems: this.storedItems as {itemName:string, itemLocale:{row:number,col:number}}[]
    };
  },
  props: {
    storedItems: [],
  },
  methods: {
    addingitem() {
      let search = this.search_v;
      let item = {
        itemName: "",
        itemLocale: {
          row: -1,
          col: -1,
        },
      };

      for(let i=0; i<this.inStoreItems.length; i++){
        let element = this.inStoreItems[i]
        if (search == element.itemName) {
          item.itemName = search;
          item.itemLocale = element.itemLocale;
          return this.itemAdded(item);
        }
      }

      console.log("item not found");
      return;
    },
    itemAdded(item: {}) {
      this.$emit("add-item", item);
    },
  },
});
</script>
