import { Component } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-heroes-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './heroes-list.component.html',
  styleUrl: './heroes-list.component.css'
})
export class HeroesListComponent {

  heroes: { name: string, id: string }[] = [];
  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.getData();
  }

  getData() {
    this.http.get("http://localhost:3000/heroes")
      .subscribe({
        next: (res) => {
          this.heroes = res as { id: string, name: string }[];
        },
        error: (err) => console.log(err)
      })
  }
}
