import { HttpClientModule } from "@angular/common/http";
import { TestBed } from "@angular/core/testing";
import { firstValueFrom } from "rxjs/internal/firstValueFrom";
import { HeroesService } from "./heroes.service";


// Docs for intercept: https://docs.cypress.io/guides/guides/network-requests
// WHen to stub, when not to: https://docs.cypress.io/guides/guides/network-requests
describe('Heroes service', () => {
  it('Should return the new hero', () => {
    cy.intercept('POST', 'http://localhost:3000/heroes', {}).then(async () => {
      TestBed.configureTestingModule({
        providers: [HeroesService],
        imports: [HttpClientModule],
      })

      const heroesService = TestBed.inject(HeroesService);

      const res = await firstValueFrom(
        heroesService.submitData("Yvens")
      );

      expect(res).deep.eq({
        message: "Yvens",
        status: 200
      })
    })
  })

  it('Should return 400', async () => {
    cy.intercept('POST', 'http://localhost:3000/heroes', { statusCode: 400 }).then(async () => {
      TestBed.configureTestingModule({
        providers: [HeroesService],
        imports: [HttpClientModule],
      })

      const heroesService = TestBed.inject(HeroesService);
      const res = await firstValueFrom(
        heroesService.submitData("Yvens")
      );

      expect(res).deep.eq({
        status: 400,
        message: `Error happened sorry braaaaaaa:  400`
      })
    })
  })

  // Non conseillé mais possible
  // Par exemple lazy load un composant qui fait un appel api un peu long
  // it("Should return heroes", () => {
  //   cy.intercept('GET', 'http://localhost:3000/heroes', { fixture: 'heroes.json' })
  //     .then(async () => {
  //       TestBed.configureTestingModule({
  //         providers: [HeroesService],
  //         imports: [HttpClientModule],
  //       })
  
  //       const heroesService = TestBed.inject(HeroesService);
  //       const res = await firstValueFrom(
  //         heroesService.getHeroes()
  //       );
  //       expect(res).deep.eq({
  //         message: 
  //       })
  //     })
  // })
})