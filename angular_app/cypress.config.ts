import { defineConfig } from "cypress";
// import { resolve } from "cypress/types/bluebird";

export default defineConfig({
  component: {
    devServer: {
      framework: "angular",
      bundler: "webpack",
      webpackConfig:(config: any) => ({
        ...config,
        resolve: {
          alias: {
            "@app": "src/app"
          }
        }
      })
    },
    specPattern: "**/*.cy.ts",
  },

  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },
});
