<template>
  <div class="grid" id="">
    <Search @add-item="addItem" :storedItems="storedItems"/>
    <Cart :cart="cart" />
    <Map></Map>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Cart from "~/components/Cart.vue";
import Search from "~/components/Search.vue";
import Map from "~/components/Map.vue";

export default Vue.extend({
  components: { Search, Cart, Map },
  name: "IndexPage",
  data() {
    return {
      cart: [{}],
      storedItems: [
        {
          itemName: "eggs",
          itemLocale: {
            row: 0,
            col: 0,
          },
        },
        {
          itemName: "milk",
          itemLocale: {
            row: 1,
            col: 1,
          },
        },
        {
          itemName: "bread",
          itemLocale: {
            row: 0,
            col: 1,
          },
        },
        {
          itemName: "apple",
          itemLocale: {
            row: 0,
            col: 2,
          },
        },
        {
          itemName: "orange",
          itemLocale: {
            row: 1,
            col: 0,
          },
        },
        {
          itemName: "banana",
          itemLocale: {
            row: 1,
            col: 2,
          },
        },
      ],
    };
  },
  methods: {
    addItem: function (item: {itemName:string, itemLocale:{row:number, col:number}}) {
      console.log(item)
      this.cart.push({ item_: item.itemName, locale: {row: item.itemLocale.row, col: item.itemLocale.col} });
    },
  },
});
</script>
