# Examples

<https://example.cypress.io/>

## For Angular Project

This [The npm package](https://www.npmjs.com/package/@cypress/schematic) install and configure cypress automatically for angular projects
First:`ng add @cypress/schematic`
Then : `npm run cypress:open

## To mock or not to mock

My understanding here is that if you want to test only your front-end application (what is not E2E testing in my opinion) you can use unit tests instead. If you still want to test the user interface from the browser, then you can mock the APIs' responses, but still not being E2E testing. In case you want to perform an end-to-end testing, then you shouldn't mock any database or API call. The exception here is a third-party API that is not under your control. In that specific case you can mock it to have less external dependency in your tests, but if that third party changes and you are not aware of it, you wont't notice if it's mocked. Said that, if you mock third-party APIs be sure you have a fluent communication with the API provider to get alerts on changes before your app fails.

## Examples of Test

<https://github.com/cypress-io/cypress-realworld-app>