import {AddHeroesFormComponent} from '@app/add-heroes-form/add-heroes-form.component';


describe('StepperComponent', () => {
  it('mounts', () => {
    // see: https://on.cypress.io/mounting-angular
    cy.mount(AddHeroesFormComponent)
  })
})