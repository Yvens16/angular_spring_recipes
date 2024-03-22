import { HeroesListBetterComponent } from "./heroes-list-better.component";
import { MountConfig } from "cypress/angular";
import heroes from '../../../cypress/fixtures/heroes.json';

/**
 * To allow import data check tsconfig.json
 *  "resolveJsonModule": true,
    "esModuleInterop": true,
 * 
 */
describe('Heroes List dumb component', () => {
  it('Should show mount Heroes list', () => {
    cy.mount(HeroesListBetterComponent, {
      componentProperties: {
        heroes: heroes
      }
    });
    // Juste li fonctionne aussi mais c'est plus précis comme ça
    cy.get('ul > li').should('have.length', 3);
    cy.get('ul li:first').contains('Fixture Superman');
    // eq(1) correspond au second element
    cy.get('ul li').eq(1).contains('Fixture Batman');
    // eq(2) correspond au 3ème element
    cy.get('ul li').eq(2).contains('Fixture Wonder Woman');
  })
})
