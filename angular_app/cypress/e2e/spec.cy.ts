describe('My First Test', () => {
  it('Visits the initial project page', () => {
    cy.visit('/')
    cy.contains('Register Form')
  })

  it('Should visit the public page by clicking on the link', () => {
    cy.visit('/')
    cy.contains('Route Public').click();
    cy.contains("Cette route est accessible pour tous le monde connectée ou non")
  })

  it("Should not be able to visit the protected route", () => {
    cy.visit('/')
    cy.contains('Route Protéger').click();
    cy.contains('Cette route accessible seulement aux utilisateurs connectés').should('not.exist');
  })

  it('Should be able to visit the protected route after login manually', () => {
    // Intercepte l'appelle api 
    // Pour pouvoir attendre sa complétion plus tard
    cy.intercept('POST', '/login').as('loginCall');
    cy.visit('/');
    cy.get('#email_login').type("yvens@gmail.com")
    cy.get('#password_login').type("Belaston")
    cy.contains('Log in').click()
    cy.wait("@loginCall") // Ici on attend la complétion de l'appel api login
    cy.contains('Route Protéger').click();
    cy.url().should('contain', '/protected')
  })

  it.only('Should be able to visit the protected page after programmatically login', () => {
    cy.visit('/')
    cy.request({
      method: 'POST', 
      url: 'http://localhost:8080/login', 
      body: { 
        email: "yvens@gmail.com",
        password: "Belaston"
      },
      headers: {
        'Content-Type': 'application/json'
      }
    }).then((response: any) => {
      console.log('response:', response)
      const accessToken = response.body.data.token;
      localStorage.setItem("token", accessToken);
    }).then(() => {
      cy.contains('Route Protéger').click();
      cy.url().should('contain', '/protected')
    })
  })

  it('Should be able to visit the protected page after programmatically login with cypress Command', () => {
    cy.visit("/")
    cy.login("yvens@gmail.com", "Belaston").then(() => {
      cy.contains('Route Protéger').click();
      cy.url().should('contain', '/protected')
    });
  });
})
