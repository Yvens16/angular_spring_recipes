import { HeroesListComponent } from "./heroes-list.component";
import { HttpClientModule } from "@angular/common/http";
import { MountConfig } from "cypress/angular";


describe('Heroes list that make http request', () => {
  const config: MountConfig<HeroesListComponent> = {
    imports: [HttpClientModule],
    declarations: [],
    providers: [],
  }

  it('Should mount component', () => {
    cy.intercept("http://localhost:3000/heroes", {fixture: 'heroes'}).as('getHeroes')
    cy.mount(HeroesListComponent, config);
    cy.wait(['@getHeroes'])
  })
})