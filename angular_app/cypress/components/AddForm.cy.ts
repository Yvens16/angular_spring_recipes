import { AddHeroesFormComponent } from '@app/add-heroes-form/add-heroes-form.component';
import { FormsModule } from "@angular/forms"
import { HttpClientModule } from '@angular/common/http';
import { MountConfig } from "cypress/angular"



// expect, it/test, beforeEach, beforeAlll



describe('Making a real post request', () => {

  beforeEach(() => {
    const config: MountConfig<AddHeroesFormComponent> = {
      imports: [FormsModule, HttpClientModule],
      declarations: [],
      providers: [],
    }
    cy.mount(AddHeroesFormComponent, config).then(response => {
      cy.spy(response.component.submitEvent, 'emit').as('onSubmitSpy')
    })
    cy.get('#name').as('nameInput'); // Alias
  });

  it('Should make a post request on submit btn clicked', () => {
    // see: https://on.cypress.io/mounting-angular
    const username = "Green lantern";
    cy.get('@nameInput').should('have.attr', 'type', "text")
    cy.get('@nameInput').type(username)
    cy.get('button[type=submit]').click();
    cy.get('@onSubmitSpy').should('have.been.calledWith', {
      name: username
    })
  })
  // it('Should make a post request on enter key pressed', () => {
  //   // 
  //   // see: https://on.cypress.io/mounting-angular
  //   const username = "Red lantern";
  //   cy.get('@nameInput').should('have.attr', 'type', "text")
  //   cy.get('@nameInput').type(username).type('{enter}')
  //   cy.get('@onSubmitSpy').should('have.been.calledWith', {
  //     name: username
  //   })
  // })
})