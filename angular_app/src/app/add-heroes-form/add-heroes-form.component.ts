import { Component, EventEmitter, Output } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Component({
  selector: 'app-add-heroes-form',
  templateUrl: './add-heroes-form.component.html',
  styleUrls: ['./add-heroes-form.component.css']
})
export class AddHeroesFormComponent {
  name = '';
  submitted = false;
  @Output() submitEvent: EventEmitter<{ name: string }> = new EventEmitter()

  constructor(private http: HttpClient) {}

  handleSubmit() {
    this.submitted = true;
    console.log("Form Submited")
    this.submitEvent.emit({ name: this.name })
  }

}
