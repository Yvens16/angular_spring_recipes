import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { AuthenticationService } from './authentication.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular_app';
  loginData = null;
  registerData = null;
  userData = null;
  adminOnlyData = null;

  constructor(private authenticationService: AuthenticationService) { }

  registerForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    email: new FormControl(''),
  });

  onRegisterSubmit() {
    console.log("Toutes les valeurs de register forms", this.registerForm.value);

    const { username, password, email } = this.registerForm.value;
    if (username?.length && password?.length && email?.length) {
      this.authenticationService.register({
        email: email,
        password: password,
        username: username
      }).subscribe((data: any) => {
        console.log('data in subscription:', data)
        this.registerData = data;
        const token = data.data.token;
      });
    }
  }


  loginForm = new FormGroup({
    password: new FormControl(''),
    email: new FormControl(''),
  });

  onLoginSubmit() {
    console.log(this.loginForm.value);

    const { password, email } = this.loginForm.value;
    if (password?.length && email?.length) {
      this.authenticationService.login(email, password).subscribe((data: any) => {
        console.log('data in subscription:', data)
        this.loginData = data;
        const token = data.data.token;
      });
    }
  }

  getUserData(){
    this.authenticationService.getUserData().subscribe((data: any) => {
      console.log('getUserData in subscription:', data)
      this.userData = data;
    });
  }

  getAdminOnlyData(){
    this.authenticationService.getAdminOnlyData().subscribe((data: any) => {
      console.log('getAdminOnlyData in subscription:', data)
      this.adminOnlyData = data;
    });
  }

  logout() {
    localStorage.clear();
  }

}
