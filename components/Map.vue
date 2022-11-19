<template>
  <div class="map">
    <div class="grid grid-cols-3 gap-4" id="store">
        <div class="col-span-1 bg-white border p-4"></div>
        <div class="col-span-1 bg-white border p-4"></div>
        <div class="col-span-1 bg-white border p-4"></div>
        <div class="col-span-1 bg-white border p-4"></div>
        <div class="col-span-1 bg-white border p-4"></div>
        <div class="col-span-1 bg-white border p-4"></div>
        <div class="col-span-1 bg-white border p-4"></div>
        <div class="col-span-1 bg-white border p-4"></div>
        <div class="col-span-1 bg-white border p-4"></div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "Map",
  data() {
    return {
      cart: this.cart_ as {
        itemName: string;
        itemLocale: { row: number; col: number };
      }[],
      cartQu: [] as any,
    };
  },
  props: {
    cart_: [],
  },
  methods: {
    setup() {
      // Create a 4x4 grid
      // Represent the grid as a 2-dimensional array
      var gridSize = this.cart.length;
      var grid = [];
      for (var i = 0; i < gridSize; i++) {
        grid[i] = [] as string[];
        for (var j = 0; j < gridSize; j++) {
          grid[i][j] = "Empty";
        }
        
      }
      grid[1][1] = "Obstacle";
      grid[1][2] = "Obstacle";
      grid[1][3] = "Obstacle";
      grid[2][1] = "Obstacle";
    },
    findShortestPath(startCoordinates: any, grid: any) {
      var distanceFromTop = startCoordinates[0];
      var distanceFromLeft = startCoordinates[1];

      // Each "location" will store its coordinates
      // and the shortest path required to arrive there
      var location = {
        distanceFromTop: distanceFromTop,
        distanceFromLeft: distanceFromLeft,
        path: [],
        status: "Start",
      };
      this.cartQu.push(location);
      // Loop through the grid searching for the goal
      while (this.cartQu.length > 0) {
        // Take the first location off the this.cartQu
        var currentLocation = this.cartQu.shift();

        // Explore North
        var newLocation = this.exploreInDirection(
          currentLocation,
          "North",
          grid
        );
        if (newLocation.status === "Goal") {
          return newLocation.path;
        } else if (newLocation.status === "Valid") {
          this.cartQu.push(newLocation);
        }

        // Explore East
        var newLocation = this.exploreInDirection(
          currentLocation,
          "East",
          grid
        );
        if (newLocation.status === "Goal") {
          return newLocation.path;
        } else if (newLocation.status === "Valid") {
          this.cartQu.push(newLocation);
        }

        // Explore South
        var newLocation = this.exploreInDirection(
          currentLocation,
          "South",
          grid
        );
        if (newLocation.status === "Goal") {
          return newLocation.path;
        } else if (newLocation.status === "Valid") {
          this.cartQu.push(newLocation);
        }

        // Explore West
        var newLocation = this.exploreInDirection(
          currentLocation,
          "West",
          grid
        );
        if (newLocation.status === "Goal") {
          return newLocation.path;
        } else if (newLocation.status === "Valid") {
          this.cartQu.push(newLocation);
        }
      }

      // No valid path found
      return false;
    },
    exploreInDirection(currentLocation: any, direction: any, grid: any) {
      var newPath = currentLocation.path.slice();
      newPath.push(direction);

      var dft = currentLocation.distanceFromTop;
      var dfl = currentLocation.distanceFromLeft;

      if (direction === "North") {
        dft -= 1;
      } else if (direction === "East") {
        dfl += 1;
      } else if (direction === "South") {
        dft += 1;
      } else if (direction === "West") {
        dfl -= 1;
      }

      var newLocation = {
        distanceFromTop: dft,
        distanceFromLeft: dfl,
        path: newPath,
        status: "Unknown",
      };
      newLocation.status = this.locationStatus(newLocation, grid);

      // If this new location is valid, mark it as 'Visited'
      if (newLocation.status === "Valid") {
        grid[newLocation.distanceFromTop][newLocation.distanceFromLeft] =
          "Visited";
      }

      return newLocation;
    },
    locationStatus(location: any, grid: any) {
      var gridSize = grid.length;
      var dft = location.distanceFromTop;
      var dfl = location.distanceFromLeft;

      if (
        location.distanceFromLeft < 0 ||
        location.distanceFromLeft >= gridSize ||
        location.distanceFromTop < 0 ||
        location.distanceFromTop >= gridSize
      ) {
        // location is not on the grid--return false
        return "Invalid";
      } else if (grid[dft][dfl] === "Goal") {
        return "Goal";
      } else if (grid[dft][dfl] !== "Empty") {
        // location is either an obstacle or has been visited
        return "Blocked";
      } else {
        return "Valid";
      }
    },
  },
});
</script>
