import { TestBed } from '@angular/core/testing';

import { FetchService } from './fetch.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { of, throwError } from 'rxjs';

// Test d'un service qui fais un appel api



// Permet de renvoyer un observable car on utilise HttpClient de '@angular/common/http'
function returnObservable(data: any) {
  return of(data);
}

// Typer  la variable
let httpClientSpy: jasmine.SpyObj<HttpClient>;
describe('FetchService', () => {

  let service: FetchService;

  beforeEach(() => {
    // Mocker l'appel api get de HttpCLient
    httpClientSpy = jasmine.createSpyObj('HttpCLient', ['get']);

    TestBed.configureTestingModule({ providers: [FetchService, { provide: HttpClient, useValue: httpClientSpy }] });
    service = TestBed.inject(FetchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('Should return the data', (done: DoneFn) => {
    const expectedQuotes: any[] = [
      {
        "quote": "Shoplifting is a victimless crime, like punching someone in the dark.",
        "character": "Nelson Muntz",
        "image": "https://cdn.glitch.com/3c3ffadc-3406-4440-bb95-d40ec8fcde72%2FNelsonMuntz.png?1497567511185",
        "characterDirection": "Left"
      }
    ];
    // Définir la valeur de retour de l'appel api
    httpClientSpy.get.and.returnValue(returnObservable(expectedQuotes));

    // Faire l'appel api et tester la valeur de retour en cas de réussite
    service.getData().subscribe({
      next: (quotes) => {
        expect(quotes).withContext("context").toEqual(expectedQuotes)
        done();
      },
      error: done.fail,
    });
    expect(httpClientSpy.get.calls.count()).toBe(1)
  });

  it("Should return an error when the server fails", (done: DoneFn) => {
    // Faire l'appel api et tester la valeur de retour en cas d'échec
    const errorResponse = new HttpErrorResponse({
      error: "404 error",
      status: 404,
      statusText: "Not Found",
    });
    httpClientSpy.get.and.returnValue(throwError(() => errorResponse));

    service.getData().subscribe({
      next: (quotes) => done.fail("Waiting for error not the quotes data"),
      error: (error) => {
        console.log('error:', error)
        expect(error.statusText).toBe('Not Found');
        done();
      }
    });
  })
});
